package propertyPoset.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import propertyPoset.IProperty;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IImplication;

/**
 * A Relation is an implementation of a binary relation on a set of properties. It is endowed with some additional 
 * functionalities, especially methods that can return some special elements of the set (such as 'dimensions', 
 * 'local roots', 'local atoms'). 
 * 
 * @author Gael Tregouet
 *
 */
public class Relation implements IRelation {
	
	private Map<String, Set<String>> relation = new HashMap<String, Set<String>>();
	private String root = "";
	private Set<String> dimensions = new HashSet<String>();
	private Set<String> localRoots = new HashSet<String>();
	private Set<String> localAtoms = new HashSet<String>();
	private Map<String, String> dimensionToRoot = new HashMap<String, String>();
	private Map<String, Integer> propertyToRank = new HashMap<String, Integer>();
	private boolean allDataIsUpToDate = false;
	private boolean rankMappingIsUpToDate = false;

	/**
	 * With this constructor, the relation on the set of properties must be set subsequently using 
	 * the method addImplication();
	 * @param set a set or properties
	 */
	public Relation(IPropertySet set) {
		for (IProperty prop : set.getSetOfProperties()) {
			Set<String> propConsequents = new HashSet<String>();
			relation.put(prop.getPropertyName(), propConsequents);
		}
	}
	
	/**
	 * Returns the relation given in first parameter, restricted to the subset given in second parameter.
	 * @param rel a relation on a set that includes the 'subset' in parameter
	 * @param subset a set of properties
	 * @throws PropertyPosetException
	 */
	public Relation(IRelation rel, IPropertySet subset) throws PropertyPosetException {
		new Relation(rel, subset.getSetOfPropertyNames());
	}
	
	/**
	 * Returns the relation given in first parameter, restricted to the subset given in second parameter.
	 * @param rel a relation on a set that includes the 'subset' in parameter
	 * @param subsetNames names of elements of a subset of properties
	 * @throws PropertyPosetException
	 */
	public Relation(IRelation rel, Set<String> subsetNames) throws PropertyPosetException {
		for (String propName : subsetNames) {
			Set<String> propConsequents;
			try {
				propConsequents = new HashSet<String>(rel.getConsequents(propName));
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation constructor : an error has occured.");
			}
			propConsequents.retainAll(subsetNames);
			relation.put(propName, propConsequents);
		}
		updateRelationData();
	}	

	@Override
	public void addImplication(IImplication implication) throws PropertyPosetException {
		String antecedent = implication.getAntecedent();
		String consequent = implication.getConsequent();
		if (relation.containsKey(antecedent) && relation.containsKey(consequent)) {
			relation.get(antecedent).add(consequent);
			if (allDataIsUpToDate) {
				allDataIsUpToDate = false;
				rankMappingIsUpToDate = false;
			}
		}
		else throw new PropertyPosetException("Relation.addImplication() : either " 
				+ antecedent + " or " + consequent + " (or both) are unknown."); 
	}
	
	@Override
	public void addImplicationEnsureTransitivity(IImplication implication) throws PropertyPosetException {
		addImplication(implication);
		for (Set<String> setOfConsequents : relation.values()) {
			if (setOfConsequents.contains(implication.getAntecedent()))
				setOfConsequents.add(implication.getConsequent());
		}
	}	

	@Override
	public Set<String> getConsequents(String propName) throws PropertyPosetException {
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.getConsequents() : property '" + propName 
					+ "' is unknown.");
		else return relation.get(propName);
	}
	
	@Override
	public Set<String> getAntecedents(String propName) throws PropertyPosetException {
		setRanks();
		Set<String> antecedents = new HashSet<String>();
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.getAntecedents() : property " + propName 
					+ "is unknown.");
		else {
			for (String comparedProp : relation.keySet()) {
				int propNameRank = getRank(propName);
				if (getRank(comparedProp) <= propNameRank && relation.get(comparedProp).contains(propName))
					antecedents.add(comparedProp);
			}
		}
		return antecedents;
	}
	
	@Override
	public Set<String> getGreaterProperties(String propName) throws PropertyPosetException {
		Set<String> greaterProps = new HashSet<String>();
		try {
			greaterProps.addAll(getConsequents(propName));
			if (!greaterProps.remove(propName))
				throw new PropertyPosetException("reflexivity violation for the property '" + propName + "'.");
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getGreaterProperties() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return greaterProps;
	}

	@Override
	public Set<String> getLesserProperties(String propName) throws PropertyPosetException {
		Set<String> lesserProp = new HashSet<String>();
		try {
			lesserProp.addAll(getAntecedents(propName));
			if (!lesserProp.remove(propName))
				throw new PropertyPosetException("reflexivity violation for the property " + propName + ".");
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getLesserProperties() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return lesserProp;
	}

	@Override
	public Set<String> getSuccessors(String propName) throws PropertyPosetException {
		setRanks();
		List<String> succProp = new ArrayList<String>();
		if (!relation.keySet().contains(propName))
			throw new PropertyPosetException("Relation.getSuccProperties : property " + propName 
					+ " is unknown." );
		else {
			Set<String> greaterProps = getGreaterProperties(propName);
			int propRank = getRank(propName);
			for (int i = propRank + 1 ; i <= getMaxRank() ; i++) {
				Set<String> greaterPropsRankI = getPropertiesAtRank(i);
				greaterPropsRankI.retainAll(greaterProps);
				for (String prop : greaterPropsRankI) {
					boolean propIsASuccessor = true;
					int succIndex = 0;
					while (propIsASuccessor && succIndex < succProp.size()) {
						if (succProp.get(succIndex).contains(prop))
							propIsASuccessor = false;
						else succIndex++;
					}
					if (propIsASuccessor)
						succProp.add(prop);	
				}
			}
		}
		return new HashSet<String>(succProp);
	}

	@Override
	public Set<String> getPredecessors(String propName) throws PropertyPosetException {
		setRanks();
		List<String> precProp = new ArrayList<String>();
		if (!relation.keySet().contains(propName))
			throw new PropertyPosetException("Relation.getSuccProperties : property " + propName 
					+ " is unknown." );
		else {
			Set<String> lesserProps = getLesserProperties(propName);
			int propRank = getRank(propName);
			for (int i = propRank - 1 ; i >= 0 ; i--) {
				Set<String> lesserPropsAtRankI = getPropertiesAtRank(i);
				lesserPropsAtRankI.retainAll(lesserProps);
				for (String prop : lesserPropsAtRankI) {
					boolean propIsAPredecessor = true;
					int precIndex = 0;
					while (propIsAPredecessor && precIndex < precProp.size()) {
						if (prop.contains(precProp.get(precIndex)))
							propIsAPredecessor = false;
						else precIndex++;
					}
					if (propIsAPredecessor)
						precProp.add(prop);
				}
			}
		}
		return new HashSet<String>(precProp);
	}

	@Override
	public int getRank(String propName) throws PropertyPosetException {
		setRanks();
		if (!propertyToRank.containsKey(propName)) {
			throw new PropertyPosetException("Relation.getRank() : the argument " + propName + " is unknown.");
		}
		else return propertyToRank.get(propName);
	}

	@Override
	public boolean checkIfDimension(String propName) throws PropertyPosetException {
		checkIfDataIsUpToDate();
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.checkIfDimension() : property '" + propName 
					+ "' is unknown.");
		else return (dimensions.contains(propName));
	}

	@Override
	public boolean checkIfLocalRoot(String propName) throws PropertyPosetException {
		checkIfDataIsUpToDate();
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.checkIfLocalRoot() : property '" + propName 
					+ "' is unknown.");
		else return (localRoots.contains(propName));
	}

	@Override
	public boolean checkIfLocalAtom(String propName) throws PropertyPosetException {
		checkIfDataIsUpToDate();
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.checkIfLocalRoot() : property '" + propName 
					+ "' is unknown.");
		else return (localAtoms.contains(propName));
	}

	@Override
	public String getLocalRoot(String dimension) throws PropertyPosetException {
		checkIfDataIsUpToDate();
		if (!dimensionToRoot.containsKey(dimension)) {
			throw new PropertyPosetException("Relation.getLocalRoot() : the argument " + dimension 
					+ " is not a dimension."); 
		}
		else return dimensionToRoot.get(dimension);
	}

	@Override
	public String getPosetRoot() throws PropertyPosetException {
		setRanks();
		if (root.isEmpty())
			throw new PropertyPosetException("Relation.getPosetRoot() : the root can't be found or is empty.");
		else return root;
	}

	@Override
	public Set<String> getPosetleaves() throws PropertyPosetException {
		Set<String> posetLeaves = new HashSet<String>();
		for (String property : relation.keySet()) {
			if (relation.get(property).size() == 1)
				posetLeaves.add(property);
		}
		return posetLeaves;
	}

	@Override
	public boolean removeProperty(String propertyName) throws PropertyPosetException {
		if (!relation.containsKey(propertyName))
			throw new PropertyPosetException("Relation.removeProperty() : the property " 
					+ propertyName + " is unknown");
		else {
			relation.remove(propertyName);
			for (Set<String> consequents : relation.values())
				consequents.remove(propertyName);
			allDataIsUpToDate = false;
			rankMappingIsUpToDate = false;
		}
		return true;
	}

	@Override
	public void updateRelationData() throws PropertyPosetException {
		if (!allDataIsUpToDate) {
			setRanks();
			setDimensions();
			setLocalRootsAndAtoms();
			allDataIsUpToDate = true;	
		}
	}
	
	/**
	 * Associates any property to a rank value, which is the length of the longest path from the root to the 
	 * property in the poset diagram.
	 * @throws PropertyPosetException
	 */
	private void setRanks() throws PropertyPosetException {
		if (!rankMappingIsUpToDate) {
			setPosetRoot();
			initializeRankMap();
			setSuccRankRecursively(root);
			rankMappingIsUpToDate = true;
		}
	}
	
	/**
	 * Ensure that every successor to the property given in parameter has a rank value greater 
	 * than this property's value. If a successor rank value has been updated, then the method 
	 * calls itself with the successor in parameter.
	 * @param propName the name of the property whose successor's ranks are checked 
	 * @throws PropertyPosetException
	 */
	private void setSuccRankRecursively(String propName) throws PropertyPosetException {
		if (!propertyToRank.containsKey(propName))
			throw new PropertyPosetException("Relation.setSuccRank() : the rank of " + propName 
					+ " is unknown.");
		else {
			int propRank = propertyToRank.get(propName);
			Set<String> successors;
			try {
				successors = findSuccessorsWithoutUsingRank(propName);
			} catch (PropertyPosetException e) {
				throw new PropertyPosetException("Relation.setSuccRank() : successors of property " + propName 
						+ " can't be retreived." + System.lineSeparator() + e.getMessage());
			}
			for (String successor : successors) {
				if (propertyToRank.get(successor) < propRank + 1) {
					propertyToRank.put(successor, propRank + 1);
					setSuccRankRecursively(successor);
				}
			}
		}
	}
	
	/**
	 * Associates every property with a rank value = 0.
	 */
	private void initializeRankMap() {
		for (String prop : relation.keySet())
			propertyToRank.put(prop, 0);			
	}
	
	/**
	 * This method can be called even when 'rankMappingIsUpToDate == false' and the rank map can't be used. 
	 * @param propName the name of the property whose successors are requested
	 * @return a set of successors' names
	 * @throws PropertyPosetException
	 */
	private Set<String> findSuccessorsWithoutUsingRank(String propName) throws PropertyPosetException {
		List<String> successors = new ArrayList<String>();
		Set<String> greaterProps;
		try {
			greaterProps = getGreaterProperties(propName);
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.findSuccessorsWithoutUsingRank() : an error has occured. "
					+ System.lineSeparator() + e.getMessage());
		}
		for (String greaterProp : greaterProps) {
			boolean greaterPropIsASuccessor = true;
			int succIndex = 0;
			while (greaterPropIsASuccessor == true && succIndex < successors.size()) {
				if (successors.get(succIndex).contains(greaterProp))
					greaterPropIsASuccessor = false;
				else succIndex++;
			}
			if (greaterPropIsASuccessor) {
				Set<String> falseSuccessors = new HashSet<String>();
				for (String successor : successors) {
					if (greaterProp.contains(successor))
						falseSuccessors.add(successor);
				}
				successors.removeAll(falseSuccessors);
				successors.add(greaterProp);
			}
		}
		return new HashSet<String>(successors);
	}
	
	/**
	 * A property is a 'dimension' if it has more than one predecessor. 
	 * @throws PropertyPosetException
	 */
	private void setDimensions() throws PropertyPosetException {
		for (String propName : relation.keySet()) {
			boolean dimension;
			try {
				dimension = (getPredecessors(propName).size() > 1);
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.setDimensions() : an error has occured." 
						+ System.lineSeparator() + e.getMessage());
			}
			if (dimension)
				dimensions.add(propName);
		}
	}
	
	/**
	 * The root of a dimension is the infimum of its predecessors, i.e. the greatest property that 
	 * implies them all. The atoms of a root are its successors. 
	 * @throws PropertyPosetException
	 */
	private void setLocalRootsAndAtoms() throws PropertyPosetException {
		if (!rankMappingIsUpToDate)
			setRanks();
		for (String dimension : dimensions) {
			String localRoot = "";
			Set<String> dimensionPredecessors;
			try {
				dimensionPredecessors = getPredecessors(dimension);
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.setLocalRoots() : an error has occured." 
						+ System.lineSeparator() + e.getMessage()); 
			}
			boolean localRootFound = false;
			int targetRank = getRank(dimension) - 2;
			while (!localRootFound && targetRank >= 0) {
				List<String> potentialRoots = getPropAtThisRank(targetRank);
				int potentialRootIndex = 0;
				while (localRootFound == false && potentialRootIndex < potentialRoots.size()) {
					String potentialRoot = potentialRoots.get(potentialRootIndex);
					if (relation.get(potentialRoot).containsAll(dimensionPredecessors)){
						localRootFound = true;
						localRoot = potentialRoot;
						localRoots.add(localRoot);
						dimensionToRoot.put(dimension, localRoot);
						localAtoms.addAll(getSuccessors(localRoot));
					}
					else potentialRootIndex++;
				}
				targetRank--;
			}
			if (!localRootFound) {
				throw new PropertyPosetException("Relation.setLocalRootsAndAtoms() : no root found for "
						+ "dimension '" + dimension + "'.");
			}
		}
	}
	
	/**
	 * @param rank the target rank
	 * @return a set of property names
	 * @throws PropertyPosetException
	 */
	private List<String> getPropAtThisRank(int rank) throws PropertyPosetException{
		if (!rankMappingIsUpToDate)
			setRanks();
		List<String> properties = new ArrayList<String>();
		try {
			for (String prop : relation.keySet()) {
				if (getRank(prop) == rank)
					properties.add(prop);
			}
		} 
		catch (PropertyPosetException e) {
			throw new PropertyPosetException("Relation.getPropAtThisRank() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return properties;
	}
	
	/**
	 * @return the maximal length of a spanning path in the poset
	 * @throws PropertyPosetException 
	 */
	private int getMaxRank() throws PropertyPosetException {
		if (!rankMappingIsUpToDate)
			setRanks();
		int maxRank = 0;
		for (Integer rank : propertyToRank.values()) {
			if (rank > maxRank)
				maxRank = rank;
		}
		return maxRank;
	}
	
	/**
	 * The root is the only property that implies all the properties in the poset (including itself).
	 * @throws PropertyPosetException
	 */
	private void setPosetRoot() throws PropertyPosetException {
		if (root.isEmpty()) {
			Set<String> properties = relation.keySet();
			List<String> propertyList = new ArrayList<String>(properties);
			int propIndex = 0;
			while (root.isEmpty() && propIndex < propertyList.size()) {
				if (relation.get(propertyList.get(propIndex)).containsAll(properties))
					root = propertyList.get(propIndex);
				else propIndex++;
			}
			if (root.isEmpty()) {
				throw new PropertyPosetException("Relation.setPosetRoot : no root has been found.");
			}
		}
	}
	
	private void checkIfDataIsUpToDate() throws PropertyPosetException {
		if (!allDataIsUpToDate) {
			updateRelationData();
		}
	}
	
	private Set<String> getPropertiesAtRank(int rank) throws PropertyPosetException {
		if (!rankMappingIsUpToDate)
			setRanks();
		Set<String> propsAtGivenRank = new HashSet<String>();
		for (String prop : propertyToRank.keySet()) {
			if (propertyToRank.get(prop) == rank)
				propsAtGivenRank.add(prop);
		}
		return propsAtGivenRank;
	}

}
