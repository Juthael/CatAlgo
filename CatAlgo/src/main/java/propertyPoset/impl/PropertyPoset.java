package propertyPoset.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import fca.core.context.binary.BinaryContext;
import propertyPoset.IProperty;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;

/**
 * A IPropertyPoset is an implementation of a partially ordered set whose elements are properties. <br>
 * 
 * As such, it is composed of a {@link IPropertySet} object (unordered set of properties), and of a 
 * {@link IRelation} object (partial order relation on the set of properties). 
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
	 * 
	 * @param setOfPropNames names of a set of properties
	 * @param relation partial order relation on the set of properties
	 */
	public PropertyPoset(Set<String> setOfPropNames, IRelation relation) {
		set = new PropertySet(setOfPropNames, true);
		this.relation = relation;
	}
	
	/**
	 * Protected constructor, only to be called by OriginalPropertyPoset
	 * @param maxChains spanning chains of the poset to be built
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
		String posetName = relation.getPosetRoot();
		Vector<String> properties = new Vector<String>(set.getSetOfPropertyNames());
		Vector<Vector<String>> values = new Vector<Vector<String>>();
		for (String antecedent : properties) {
			Vector<String> valuesForThisAnt = new Vector<String>();
			for (String potentialConsequent : properties) {
				if (relation.getConsequents(antecedent).contains(potentialConsequent))
					valuesForThisAnt.add(BinaryContext.TRUE);
				else valuesForThisAnt.add(BinaryContext.FALSE);
			}
			values.add(valuesForThisAnt);
		}
		context = new BinaryContext(posetName, properties, properties, values);
		return context;
	}
	
	@Override
	public Set<IPropertyPoset> getSubContexts() throws PropertyPosetException {
		if (!subContextsExtracted) {
			try {
				extractSubContexts();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.getSubContexts() : context extraction failed. " 
						+ System.lineSeparator() + e.getMessage());
			}	
		}
		if (!posetReduced) {
			try {
				reducePoset();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.getSubContexts() : poset reduction failed. " 
						+ System.lineSeparator() + e.getMessage());
			}	
		}	
		return subContexts;
	}	
	
	@Override
	public void extractSubContexts() throws PropertyPosetException {
		if (!subContextsExtracted) {
			boolean manyRoots;
			try {
				manyRoots = checkIfManyRoots();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.extractSubContexts() : cannot check if many roots." 
						+ e.getMessage());
			}
			if (manyRoots) {
				Set<IProperty> subContextRoots;
				try {
					subContextRoots = getSubContextRoots();
				}
				catch (Exception e) {
					throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
							+ "cannot retreive sub-context roots." + System.lineSeparator() + e.getMessage());
				}		
				try {
					for (IProperty subCtxtRoot : subContextRoots) {
						subCtxtRoot.setAsNotRemovable();
						IPropertyPoset currentSubContextPoset;
						Set<String> currentRootConsequents = subCtxtRoot.getConsequents(relation);
						currentSubContextPoset = new PropertyPoset(currentRootConsequents, relation);
						subContexts.add(currentSubContextPoset);
					}
				}
				catch (Exception e) {
					throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
							+ "failed to build subContext." + System.lineSeparator() + e.getMessage());
				}
				Map<String, Boolean> propToExtractable;	
				try {
					propToExtractable = setPropToExtractable();
				}
				catch (Exception e) {
					throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
							+ "cannot set propToExtractables map." + System.lineSeparator() + e.getMessage());
				}				
				try {
					for (String propName : propToExtractable.keySet()) {
						if (propToExtractable.get(propName) == true)
							removeProperty(propName);
					}
				}
				catch (Exception e) {
					throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
							+ "failed to update Poset." + System.lineSeparator() + e.getMessage());
				}
				try {
					for (IPropertyPoset subCtxt : subContexts) {
						subCtxt.extractSubContexts();
					}
				}
				catch (Exception e) {
					throw new PropertyPosetException("PropertyPoset.extractSubContexts() : "
							+ "failed to recursively call the method on sub-context components." 
							+ System.lineSeparator() + e.getMessage());
				}
			}
			subContextsExtracted = true;
		}
	}


	@Override
	public boolean reducePoset() throws PropertyPosetException {
		Set<String> propsToRemove = new HashSet<String>();
		List<String> listOfPropsToRemove;
		for (IProperty property : set.getSetOfProperties()) {
			try {
				if (!property.isADimension(relation) && !property.isALocalRoot(relation) && !property.isALocalAtom(relation))
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
				relation.removeProperty(property);
				set.removeProperty(property, predecessor);
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.reducePoset() : removal of property '" + property 
						+ "' failed.");
			}
		}
		relation.updateRelationData();
		return true;
	}
	
	@Override
	public boolean subContextsHaveBeenExtracted() {
		return subContextsExtracted;
	}
	
	@Override
	public boolean hasBeenreduced() {
		return posetReduced;
	}	
	
	/**
	 * @param props set of properties to be ordered
	 * @return list of properties ordered by decreasing rank
	 * @throws PropertyPosetException 
	 */
	private List<String> orderPropsByDecreasingRank(Set<String> props) throws PropertyPosetException{
		Set<String> unorderedProps = new HashSet<String>(props);
		List<String> orderedProps = new ArrayList<String>();
		int maxRank = getMaxRank(props);
		for (int i=maxRank ; i >= 0 ; i--) {
			if (!unorderedProps.isEmpty()) {
				Set<String> foundProps = new HashSet<String>();
				for (String prop : unorderedProps) {
					if (relation.getRank(prop) == i) {
						foundProps.add(prop);
					}
				}
				orderedProps.addAll(foundProps);
				unorderedProps.removeAll(foundProps);	
			}
		}
		return orderedProps;
	}
	
	/**
	 * @return the maximal length of a spanning path from the poset root to one of the properties given in parameter. 
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
	
	private boolean checkIfManyRoots() throws PropertyPosetException{
		Set<IProperty> localRoots = new HashSet<IProperty>();
		try {
			Iterator<IProperty> propIterator = set.getSetOfProperties().iterator();
			while (localRoots.size() < 2 && propIterator.hasNext()) {
				IProperty currentProp = propIterator.next();
				if (currentProp.isALocalRoot(relation))
					localRoots.add(currentProp);
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("Error during root number checking." 
					+ System.lineSeparator() + e.getMessage());
		}
		return (localRoots.size() > 1);
	}
	
	private Set<IProperty> getSubContextRoots() throws PropertyPosetException{
		Set<IProperty> subContextRoots = new HashSet<IProperty>();
		Set<IProperty> allLocalRoots = new HashSet<IProperty>();
		for (IProperty property : set.getSetOfProperties()) {
			if (property.isALocalRoot(relation) && property.getRank(relation) != 0) {
				allLocalRoots.add(property);
			}
		}
		if (!allLocalRoots.isEmpty()) {
			int minimalRootRank = 1;
			boolean minimalRootsFound = false;
			while (!minimalRootsFound && minimalRootRank < relation.getMaximalRank()) {
				for (IProperty localRoot : allLocalRoots) {
					if (localRoot.getRank(relation) == minimalRootRank) {
						if (!minimalRootsFound)
							minimalRootsFound = true;
						subContextRoots.add(localRoot);
					}
				}
				if (!minimalRootsFound)
					minimalRootRank++;
			}
			if (!minimalRootsFound)
				throw new PropertyPosetException("IPropertyPoset.getSubContextRoots() : cannot "
						+ "find a minimal sub-context root. ");
		}
		return allLocalRoots;
	}
	
	private Map<String, Boolean> setPropToExtractable() throws PropertyPosetException {
		Map<String, Boolean> propToExtractable = new HashMap<String, Boolean>();
		try {
			for (IProperty prop : set.getSetOfProperties()) {
				List<String> propMaximalRoots = new ArrayList<String>(prop.getMaximalRoots(relation));
				if (propMaximalRoots.size() == 1 && propMaximalRoots.get(0).equals(relation.getPosetRoot()))
					propToExtractable.put(prop.getPropertyName(), new Boolean(false));
				else propToExtractable.put(prop.getPropertyName(), new Boolean(true));
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset.setPropToExtractable() : failed to check property "
					+ "extractability." + System.lineSeparator() + e.getMessage());
		}
		return propToExtractable;
	}
	
	private boolean removeProperty(String propName) throws PropertyPosetException {
		boolean protectedFromRemoval;
		try {
			protectedFromRemoval = set.removeProperty(propName);
		} catch (PropertyPosetException e) {
			throw new PropertyPosetException("PropertyPoset.removeProperty() : failed to remove the property "
					+ "'" + propName + "' from the set." + System.lineSeparator() + e.getMessage());
		}
		if (!protectedFromRemoval) {
			try {
				relation.removeProperty(propName);
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.removeProperty() : failed to remove the property "
						+ "'" + propName + "' from the relation." + System.lineSeparator() + e.getMessage());
			}
		}
		return protectedFromRemoval;
	}

}
