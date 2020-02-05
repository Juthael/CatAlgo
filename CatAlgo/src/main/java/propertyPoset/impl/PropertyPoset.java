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
	private Set<IPropertyPoset> subContexts;
	private boolean subContextsExtracted = false;
	private boolean posetReduced = false;
	
	/**
	 * As specified by {@link IPropertyPoset}, this constructor ensures that all sub-contexts have been 
	 * extracted and that there exists no more sub-context root in the original poset. It also guarantees 
	 * that the poset is displayed in a reduced form, i.e. rid of its 'non-informative' elements.
	 * 
	 * @param setOfPropNames names of a set of properties
	 * @param relation partial order relation on the set of properties
	 * @throws PropertyPosetException 
	 */
	public PropertyPoset(Set<String> setOfPropNames, IRelation relation) throws PropertyPosetException {
		set = new PropertySet(setOfPropNames, true);
		this.relation = relation;
		try {
			//extractSubContexts();
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset(2args) : sub-context extraction failed. " 
					+ System.lineSeparator() + e.getMessage());
		}
		try {
			//reducePoset();
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset(2args) : poset reduction failed. " 
					+ System.lineSeparator() + e.getMessage());
		}
	}
	
	/**
	 * As specified by {@link IPropertyPoset}, this constructor ensures that all sub-contexts have been 
	 * extracted and that there exists no more sub-context root in the original poset. It also guarantees 
	 * that the poset is displayed in a reduced form, i.e. rid of its 'non-informative' elements.
	 * 
	 * 
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
		try {
			//extractSubContexts();
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset() : sub-context extraction failed. " 
					+ System.lineSeparator() + e.getMessage());
		}
		try {
			//reducePoset();
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset() : poset reduction failed. " 
					+ System.lineSeparator() + e.getMessage());
		}	
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
		if (!subContextsExtracted || !posetReduced) {
			throw new PropertyPosetException("PropertyPoset.getSubContexts() : sub-context extaction and/or "
					+ "poset reduction has not been completed");	
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
				if (!property.isADimension(relation) && !property.isADimensionRoot(relation) && !property.isADimensionAtom(relation))
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
						+ "' is removable, then it cannot have " + Integer.toString(predecessors.size()) + " predecessors.");
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
		posetReduced = true;
	}	
	
	@Override
	public void extractSubContexts() throws PropertyPosetException {
		Set<IProperty> subContextRoots;
		try {
			subContextRoots = getSubContextRoots();
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
					currentSubContextPoset = new PropertyPoset(currentRootConsequents, relation);
					subContexts.add(currentSubContextPoset);
					subCtxtRoot.setAsNotRemovable();
					relation.setPropAsALeaf(subCtxtRoot.getPropertyName());
				}
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
						+ "failed to build subContext." + System.lineSeparator() + e.getMessage());
			}
			try {
				updatePoset();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
						+ "failed to update poset." + System.lineSeparator() + e.getMessage());
			}
			subContextsExtracted = true;
		}
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
	 * A 'sub-context root' is a minimal element in the set of dimension roots minus the poset 
	 * root. 
	 * @return the (possibly empty) set of sub-context roots. 
	 * @throws PropertyPosetException
	 */
	private Set<IProperty> getSubContextRoots() throws PropertyPosetException{
		Set<IProperty> subContextRoots = new HashSet<IProperty>();
		Set<IProperty> dimensionRootsMinusPosetRoot = new HashSet<IProperty>();
		for (IProperty property : set.getSetOfProperties()) {
			if (property.isADimensionRoot(relation) && property.getRank(relation) != 0) {
				dimensionRootsMinusPosetRoot.add(property);
			}
		}
		if (!dimensionRootsMinusPosetRoot.isEmpty()) {
			int minimalRootRank = 1;
			boolean minimalRootsFound = false;
			while (!minimalRootsFound && minimalRootRank < relation.getMaximalRank()) {
				for (IProperty dimensionRoot : dimensionRootsMinusPosetRoot) {
					if (dimensionRoot.getRank(relation) == minimalRootRank) {
						if (!minimalRootsFound)
							minimalRootsFound = true;
						subContextRoots.add(dimensionRoot);
					}
				}
				if (!minimalRootsFound)
					minimalRootRank++;
			}
			if (!minimalRootsFound)
				throw new PropertyPosetException("IPropertyPoset.getSubContextRoots() : cannot "
						+ "find a minimal sub-context root. ");
		}
		return dimensionRootsMinusPosetRoot;
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
	 * After sub-contexts have been extracted, the sub-context roots have been turned into leaves, so they have 
	 * no more consequents apart from themselves. Thus, the poset isn't connected anymore.
	 * It is back to connectivity with this update, in which all properties that are no longer a consequent of 
	 * the (ex-) poset root are removed.  
	 * This method has no effect if no sub-context has been extracted.
	 * @throws PropertyPosetException
	 */
	private void updatePoset() throws PropertyPosetException {
			Set<String> posetRootConsequents = relation.getConsequents(relation.getPosetRoot());
			Set<String> propToRemove = set.getSetOfPropertyNames();
			propToRemove.removeAll(posetRootConsequents);
			for (String prop : propToRemove) {
				removeProperty(prop);
			}
	}

}
