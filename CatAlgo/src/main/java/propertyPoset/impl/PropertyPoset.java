package propertyPoset.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import fca.core.context.binary.BinaryContext;
import grammarModel.structure.ISyntaxGrove;
import propertyPoset.IProperty;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;

/**
 * A PropertyPoset is an implementation of a partially ordered set whose elements are properties. <br>
 * 
 * As such, it is composed of a {@link IPropertySet} object (unordered set of properties), and of a 
 * {@link IRelation} object (partial order relation on the set of properties). <br>
 * 
 * It is also endowed with a (possibly empty) set of IPropertyPoset objects called 'sub-contexts'. 
 * A 'sub-context' is a IPropertyPoset object built with the set of properties implied by a 'sub-context root'. <br> 
 * A 'sub-context root' is a minimal element in the set of all 'dimension roots' minus the poset root. <br> 
 * A 'dimension root' is the infimum of the set of properties that precede a dimension. <br>
 * A 'dimension' is a sup-reducible element of the poset. <br> 
 * 
 * @author Gael Tregouet
 *
 */
public class PropertyPoset implements IPropertyPoset {

	private IPropertySet set;
	private IRelation relation;
	private Set<IPropertyPoset> subContexts = new HashSet<IPropertyPoset>();
	private boolean subContextsExtracted = false;
	private boolean posetReduced = false;
	
	/**
	 * @param setOfPropNames names of a set of properties
	 * @param relation partial order relation on the set of properties
	 * @throws PropertyPosetException 
	 */
	public PropertyPoset(Set<String> setOfPropNames, IRelation relation) throws PropertyPosetException {
		set = new PropertySet(setOfPropNames, true);
		this.relation = relation;
		set.getProperty(relation.getPosetRoot()).setAsNotRemovable();
	}
	
	/**
	 * @param maxChains spanning chains of the poset to be built ; in principle, provided by a 
	 * {@link ISyntaxGrove} object. 
	 * @throws PropertyPosetException
	 */
	public PropertyPoset(IPosetMaxChains maxChains) throws PropertyPosetException {
		set = new PropertySet(maxChains.getProperties(), true);
		relation = new Relation(set);
		try {
			for (IImplication implication : maxChains.getImplications())
				relation.addImplication(implication);
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset() : error while adding an implication");
		}
		set.getProperty(relation.getPosetRoot()).setAsNotRemovable();
	}

	@Override
	public IPropertySet getProperties() {
		return set;
	}

	@Override
	public IRelation getRelation() {
		return relation;
	}

	@Override
	public BinaryContext getBinaryContext() throws PropertyPosetException {
		BinaryContext context;
		if (!subContextsExtracted) {
			try {
				extractSubContexts();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.getBinaryContext() : error during sub-contexts extraction."
						+ System.lineSeparator() + "Cannot proceed if sub-contexts haven't been extracted." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		if (!posetReduced) {
			try {
				reducePoset();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.getBinaryContext() : error during poset reduction."
						+ System.lineSeparator() + "Cannot proceed if poset hasn't been reduced." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		String posetName = relation.getPosetRoot();
		Vector<String> properties = new Vector<String>(set.getSetOfPropertyNames());
		Vector<Vector<String>> values = new Vector<Vector<String>>();
		for (String antecedent : properties) {
			Vector<String> valuesForThisAntcdt = new Vector<String>();
			for (String potentialConsequent : properties) {
				if (relation.getConsequents(antecedent).contains(potentialConsequent))
					valuesForThisAntcdt.add(BinaryContext.TRUE);
				else valuesForThisAntcdt.add(BinaryContext.FALSE);
			}
			values.add(valuesForThisAntcdt);
		}
		context = new BinaryContext(posetName, properties, properties, values);
		return context;
	}
	
	@Override
	public Set<IPropertyPoset> getSubContexts() throws PropertyPosetException {
		if (!subContextsExtracted) {
			throw new PropertyPosetException("PropertyPoset.getSubContexts() : sub-context extaction "
					+ "has not been completed");	
		}	
		return subContexts;
	}	
	
	@Override
	public void reducePoset() throws PropertyPosetException {
		try {
			if (!subContextsExtracted)
				extractSubContexts();
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset.reducePoset() : error during sub-contexts extraction." 
					+ System.lineSeparator() + "Cannot proceed if sub-contexts haven't been extracted." 
					+ System.lineSeparator() + e.getMessage());
		}
		Set<String> propsToRemove = new HashSet<String>();
		List<String> listOfPropsToRemove;
		for (IProperty property : set.getSetOfProperties()) {
			try {
				if (!property.isADimension(relation) && !property.isADimensionRoot(relation) 
						&& !property.getPropertyName().equals(relation.getPosetRoot()) 
						&& !property.isADimensionAtom(relation))
					propsToRemove.add(property.getPropertyName());
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.reducePoset() : property " + property.getPropertyName() 
					+ "cannot be tested." + System.lineSeparator() + e.getMessage());
			}
		}
		try {
			listOfPropsToRemove = orderPropsByDecreasingRank(propsToRemove);
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset.reducePoset() : properties to be removed "
					+ "cannot be ordered." + System.lineSeparator() + e.getMessage());
		}
		for (String property : listOfPropsToRemove) {
			List<String> predecessors = new ArrayList<String>(relation.getPredecessors(property));
			String predecessor;
			if (predecessors.size() != 1) {
				throw new PropertyPosetException("PropertyPoset.reducePoset() error : if property '" + property
						+ "' is removable, then it cannot have " + Integer.toString(predecessors.size()) 
						+ " predecessors.");
			}
			else predecessor = predecessors.get(0);
			try {
				relation.removeProperty(set.removeProperty(property, predecessor));
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.reducePoset() : removal of property '" + property 
						+ "' failed.");
			}
		}
		relation.updateRelationData();
		try {
			for (IPropertyPoset subContext : subContexts) {
				subContext.reducePoset();
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset.reducePoset() : failed to recursively call this method "
					+ "on sub-context components of '" + relation.getPosetRoot() + "' property poset"
					+ System.lineSeparator() + e.getMessage());
		}
		posetReduced = true;
	}	
	
	@Override
	public void extractSubContexts() throws PropertyPosetException {
		Set<IProperty> subContextRoots;
		do {
			try {
				Set<String> subContextRootNames = relation.getSubContextRoots();
				subContextRoots = set.getSubsetOfProperties(subContextRootNames);
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
						+ "cannot retreive sub-context roots." + System.lineSeparator() + e.getMessage());
			}
			if (!subContextRoots.isEmpty()) {	
				try {
					for (IProperty subCtxtRoot : subContextRoots) {
						IPropertyPoset currentSubContextPoset;
						Set<String> currentRootConsequents = subCtxtRoot.getConsequents(relation);
						IRelation restrictedRelation = new Relation(relation, currentRootConsequents);
						currentSubContextPoset = new PropertyPoset(currentRootConsequents, restrictedRelation);
						subContexts.add(currentSubContextPoset);
						for (String subCtxtRootAntecedent : relation.getAntecedents(subCtxtRoot.getPropertyName()))
							set.getProperty(subCtxtRootAntecedent).setAsNotRemovable();
					}
				}
				catch (Exception e) {
					throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
							+ "failed to build subContext." + System.lineSeparator() + e.getMessage());
				}
				try {
					updatePosetAfterExtraction();
				}
				catch (Exception e) {
					throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
							+ "failed to update poset." + System.lineSeparator() + e.getMessage());
				}
				try {
					for (IPropertyPoset subContext : subContexts) {
						subContext.extractSubContexts();
					}
				}
				catch (Exception e) {
					throw new PropertyPosetException("PropertyPoset.extractSubContexts() : recursive "
							+ "call on poset sub-contexts failed.");
				}
			}
			subContextsExtracted = true;
		}
		while(!subContextRoots.isEmpty());
	}	
	
	/**
	 * @return the maximal length of a chain bounded by the poset root and one of the properties 
	 * given in parameter. 
	 * @throws PropertyPosetException 
	 */
	private int getMaxRank(Set<String> propNames) throws PropertyPosetException {
		int maxRank = 0;
		for (String prop : propNames) {
			int thisPropRank = relation.getRank(prop); 
			if (thisPropRank > maxRank)
				maxRank = thisPropRank;
		}
		return maxRank;
	}
	
	/**
	 * @param props set of properties to be ordered
	 * @return list of properties ordered by decreasing rank
	 * @throws PropertyPosetException 
	 */
	private List<String> orderPropsByDecreasingRank(Set<String> props) throws PropertyPosetException{
		List<String> orderedProps = new ArrayList<String>();
		int maxRank = getMaxRank(props);
		for (int i=maxRank ; i >= 0 ; i--) {
			if (!props.isEmpty()) {
				Set<String> propsAtRankI = new HashSet<String>();
				for (String prop : props) {
					if (relation.getRank(prop) == i) {
						propsAtRankI.add(prop);
					}
				}
				orderedProps.addAll(propsAtRankI);
				props.removeAll(propsAtRankI);	
			}
		}
		return orderedProps;
	}	
	
	/**
	 * If the property whose name has been given in parameter is not protected from removal, then it is 
	 * removed altogether from the set and from the relation.
	 * Otherwise, removal does not occur and the method returns 'false'. 
	 * @param propName the property to remove.
	 * @return 'true' if the property has been removed, 'false' otherwise
	 * @throws PropertyPosetException
	 */
	private boolean removeProperty(String propName) throws PropertyPosetException {
		boolean propertyRemoved;
		try {
			propertyRemoved = relation.removeProperty(set.removeProperty(propName));			
		} catch (PropertyPosetException e) {
			throw new PropertyPosetException("PropertyPoset.removeProperty() : failed to remove the property "
					+ "'" + propName + "'." + System.lineSeparator() + e.getMessage());
		}
		return propertyRemoved;
	}
	
	
	/**
	 * After sub-contexts have been extracted, any property greater than the sub-context root is 
	 * removed, except if there exists a chain bounded by the poset root and the property that does 
	 * not contain the sub-context root. 
	 * Also, every sub-context root must be turned into a leaf in the original poset, i.e. a property 
	 * with no consequent apart from itself. 
	 * 
	 * This method has no effect if no sub-context root has been found.
	 * @throws PropertyPosetException
	 */
	private void updatePosetAfterExtraction() throws PropertyPosetException {
		Set<String> propToRemove = new HashSet<String>();
		Set<String> subContextRootNames = new HashSet<String>();
		Set<String> dimensionsBoundToPosetRoot = new HashSet<String>();
		for (IPropertyPoset subContext : subContexts) {
			subContextRootNames.add(subContext.getRelation().getPosetRoot());
		}
		for (String subContextRootName : subContextRootNames) {
			propToRemove.addAll(relation.getGreaterProperties(subContextRootName));
		}
		for (String prop : propToRemove) {
			if (relation.checkIfDimension(prop)) {
				if (relation.getDimensionRoot(prop).equals(relation.getPosetRoot()))
					dimensionsBoundToPosetRoot.add(prop);
			}
		}
		for (String dimension : dimensionsBoundToPosetRoot) {
			propToRemove.removeAll(relation.getConsequents(dimension));
		}
		for (String prop : propToRemove)
			removeProperty(prop);
		for (String subCtxtRoot : subContextRootNames) {
			try {
				relation.setPropAsALeaf(subCtxtRoot);
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.updatePosetAfterExtraction() : error while "
						+ "attempting to turn '" + subCtxtRoot + " 'into a leaf." + System.lineSeparator() 
						+ e.getMessage());
			}
		}
		relation.updateRelationData();
	}

}
