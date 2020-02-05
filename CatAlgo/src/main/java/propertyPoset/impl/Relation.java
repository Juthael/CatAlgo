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
	private String posetRoot = "";
	private Set<String> dimensions = new HashSet<String>();
	private Set<String> dimensionRoots = new HashSet<String>();
	private Set<String> dimensionAtoms = new HashSet<String>();
	private Map<String, String> dimensionToRoot = new HashMap<String, String>();
	private Map<String, Integer> propertyToRank = new HashMap<String, Integer>();
	private boolean allDataIsUpToDate = false;
	private boolean rankMappingIsUpToDate = false;

	/**
	 * With this constructor, the relation on the set of properties must be set subsequently using 
	 * the method addImplication();
	 * @param set a set or properties
	 * @throws PropertyPosetException 
	 */
	public Relation(IPropertySet set) throws PropertyPosetException {
		if (!set.getSetOfProperties().isEmpty()) {
			for (IProperty prop : set.getSetOfProperties()) {
				Set<String> propConsequents = new HashSet<String>();
				relation.put(prop.getPropertyName(), propConsequents);
			}
		}
		else throw new PropertyPosetException("Relation() : param 'IPropertyPoset set' returns an empty set.");
	}
	
	/**
	 * Restricts the relation given in first parameter to the subset given in second parameter.
	 * @param rel a relation on a set that includes the 'subset' in parameter
	 * @param subsetNames names of elements of a subset of properties
	 * @throws PropertyPosetException
	 */
	public Relation(IRelation rel, Set<String> subsetNames) throws PropertyPosetException {
		if (!subsetNames.isEmpty()) {
			for (String propName : subsetNames) {
				Set<String> propConsequents;
				try {
					propConsequents = new HashSet<String>(rel.getConsequents(propName));
				}
				catch (Exception e) {
					throw new PropertyPosetException("Relation() : an error has occured.");
				}
				propConsequents.retainAll(subsetNames);
				relation.put(propName, propConsequents);
			}
			try {
				updateRelationData();
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation() : failed to update relation data." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		else throw new PropertyPosetException("Relation() : param 'Set<String> subsetNames' is empty");
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
	public void setPropAsALeaf(String subContextRoot) {
		if (relation.get(subContextRoot).size() > 1) {
			Set<String> consequents = new HashSet<String>();
			consequents.add(subContextRoot);
			relation.put(subContextRoot, consequents);	
		}
	}	

	@Override
	public Set<String> getConsequents(String propName) throws PropertyPosetException {
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.getConsequents() : property '" + propName 
					+ "' is unknown.");
		else return new HashSet<String>(relation.get(propName));
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
			throw new PropertyPosetException("Relation.getSuccessors : property " + propName 
					+ " is unknown." );
		else {
			try {
				Set<String> greaterProps = getGreaterProperties(propName);
				int propRank = getRank(propName);
				for (int i = propRank + 1 ; i <= getMaxRank() ; i++) {
					Set<String> greaterPropsRankI = getPropertiesAtRank(i);
					greaterPropsRankI.retainAll(greaterProps);
					for (String prop : greaterPropsRankI) {
						boolean propIsASuccessor = true;
						int succIndex = 0;
						while (propIsASuccessor && succIndex < succProp.size()) {
							if (relation.get(succProp.get(succIndex)).contains(prop))
								propIsASuccessor = false;
							else succIndex++;
						}
						if (propIsASuccessor)
							succProp.add(prop);	
					}
				}
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.getSuccessors() : an error has occured." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		return new HashSet<String>(succProp);
	}

	@Override
	public Set<String> getPredecessors(String propName) throws PropertyPosetException {
		setRanks();
		List<String> precProp = new ArrayList<String>();
		if (!relation.keySet().contains(propName))
			throw new PropertyPosetException("Relation.getPredecessors : property " + propName 
					+ " is unknown." );
		else {
			try {
				Set<String> lesserProps = getLesserProperties(propName);
				int propRank = getRank(propName);
				for (int i = propRank - 1 ; i >= 0 ; i--) {
					Set<String> lesserPropsAtRankI = getPropertiesAtRank(i);
					lesserPropsAtRankI.retainAll(lesserProps);
					for (String prop : lesserPropsAtRankI) {
						boolean propIsAPredecessor = true;
						int precIndex = 0;
						while (propIsAPredecessor && precIndex < precProp.size()) {
							if ((relation.get(prop)).contains(precProp.get(precIndex)))
								propIsAPredecessor = false;
							else precIndex++;
						}
						if (propIsAPredecessor)
							precProp.add(prop);
					}
				}
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.getPredecessors() : an error has occured." 
						+ System.lineSeparator() + e.getMessage());
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
		updateRelationData();
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.checkIfDimension() : property '" + propName 
					+ "' is unknown.");
		else return (dimensions.contains(propName));
	}

	@Override
	public boolean checkIfDimensionRoot(String propName) throws PropertyPosetException {
		updateRelationData();
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.checkIfLocalRoot() : property '" + propName 
					+ "' is unknown.");
		else return (dimensionRoots.contains(propName));
	}

	@Override
	public boolean checkIfDimensionAtom(String propName) throws PropertyPosetException {
		updateRelationData();
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.checkIfLocalRoot() : property '" + propName 
					+ "' is unknown.");
		else return (dimensionAtoms.contains(propName));
	}
	
	@Override
	public String getPosetRoot() throws PropertyPosetException {
		setRanks();
		if (posetRoot.isEmpty())
			throw new PropertyPosetException("Relation.getPosetRoot() : the posetRoot can't be found or is empty.");
		else return posetRoot;
	}	

	@Override
	public String getDimensionRoot(String dimension) throws PropertyPosetException {
		updateRelationData();
		if (!dimensionToRoot.containsKey(dimension)) {
			throw new PropertyPosetException("Relation.getLocalRoot() : the argument " + dimension 
					+ " is not a dimension."); 
		}
		else return dimensionToRoot.get(dimension);
	}
	
	public Set<String> getSubContextRoots() throws PropertyPosetException{
		updateRelationData();
		Set<String> subContextRoots = new HashSet<String>();
		boolean subCtxtRootsFound = false;
		int testedRank = 1;
		int maxRank = getMaximalRank();
		while(subCtxtRootsFound == false && testedRank < maxRank) {
			for (String localRoot : dimensionRoots) {
				if (getRank(localRoot) == testedRank) {
					subContextRoots.add(localRoot);
					if (!subCtxtRootsFound)
						subCtxtRootsFound = true;
				}
			}
			testedRank++;
		}
		return subContextRoots;
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
	public int getMaximalRank() throws PropertyPosetException {
		if (!rankMappingIsUpToDate)
			setRanks();
		int maximalRank = 0;
		for (Integer rank : propertyToRank.values()) {
			if (rank > maximalRank)
				maximalRank = rank;
		}
		return maximalRank;
	}	

	@Override
	public boolean removeProperty(IProperty property) throws PropertyPosetException {
		boolean propertyRemoved = false;
		String propertyName = property.getPropertyName();
		if (!relation.containsKey(propertyName))
			throw new PropertyPosetException("Relation.removeProperty() : the property " 
					+ propertyName + " is unknown");
		else if (property.isRemovable()){
			relation.remove(propertyName);
			for (Set<String> consequents : relation.values())
				consequents.remove(propertyName);
			propertyRemoved = true;
			allDataIsUpToDate = false;
			rankMappingIsUpToDate = false;
		}
		return propertyRemoved;
	}

	@Override
	public void updateRelationData() throws PropertyPosetException {
		if (!allDataIsUpToDate) {
			try {
				setRanks();
				setDimensions();
				setDimensionRootsAndAtoms();
				allDataIsUpToDate = true;	
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.updateData() : an error has occured." 
						+ System.lineSeparator() + e.getMessage());
			}

		}
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
				if ((relation.get(successors.get(succIndex))).contains(greaterProp))
					greaterPropIsASuccessor = false;
				else succIndex++;
			}
			if (greaterPropIsASuccessor) {
				Set<String> falseSuccessors = new HashSet<String>();
				for (String successor : successors) {
					if ((relation.get(greaterProp)).contains(successor))
						falseSuccessors.add(successor);
				}
				successors.removeAll(falseSuccessors);
				successors.add(greaterProp);
			}
		}
		return new HashSet<String>(successors);
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
	 * @param rank the target rank
	 * @return a set of property names
	 * @throws PropertyPosetException
	 */
	private List<String> getPropAtThisRank(int rank) throws PropertyPosetException{
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
	 * Associates every property with a rank value = 0.
	 */
	private void initializeRankMap() {
		for (String prop : relation.keySet())
			propertyToRank.put(prop, 0);			
	}
	
	/**
	 * The root of a dimension is the infimum of its predecessors, i.e. the greatest property that 
	 * implies them all. The atoms of a dimension are the successors of its root. 
	 * @throws PropertyPosetException
	 */
	private void setDimensionRootsAndAtoms() throws PropertyPosetException {
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
			int testedRank = getRank(dimension) - 2;
			while (!localRootFound && testedRank >= 0) {
				List<String> potentialRoots = getPropAtThisRank(testedRank);
				int potentialRootIndex = 0;
				while (localRootFound == false && potentialRootIndex < potentialRoots.size()) {
					String potentialRoot = potentialRoots.get(potentialRootIndex);
					if (relation.get(potentialRoot).containsAll(dimensionPredecessors)){
						localRootFound = true;
						localRoot = potentialRoot;
						dimensionRoots.add(localRoot);
						dimensionToRoot.put(dimension, localRoot);
						dimensionAtoms.addAll(getSuccessors(localRoot));
					}
					else potentialRootIndex++;
				}
				testedRank--;
			}
			if (!localRootFound) {
				throw new PropertyPosetException("Relation.setLocalRootsAndAtoms() : no posetRoot found for "
						+ "dimension '" + dimension + "'.");
			}
		}
	}	
	
	/**
	 * A property is a 'dimension' if it has more than one predecessor (i.e., is sup-reducible). 
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
	 * The posetRoot is the only property that implies all the properties in the poset (including itself).
	 * @throws PropertyPosetException
	 */
	private void setPosetRoot() throws PropertyPosetException {
		if (posetRoot.isEmpty()) {
			Set<String> properties = relation.keySet();
			List<String> propertyList = new ArrayList<String>(properties);
			int propIndex = 0;
			while (posetRoot.isEmpty() && propIndex < propertyList.size()) {
				if (relation.get(propertyList.get(propIndex)).containsAll(properties))
					posetRoot = propertyList.get(propIndex);
				else propIndex++;
			}
			if (posetRoot.isEmpty()) {
				throw new PropertyPosetException("Relation.setPosetRoot : no posetRoot has been found.");
			}
		}
	}
	
	/**
	 * 
	 * @param rank for a property, the length of the longest chain bounded by the poset
	 * root and this property  
	 * @return the names of every property whose rank has the value given in parameter
	 * @throws PropertyPosetException
	 */
	private Set<String> getPropertiesAtRank(int rank) throws PropertyPosetException {
		setRanks();
		Set<String> propsAtGivenRank = new HashSet<String>();
		for (String prop : propertyToRank.keySet()) {
			if (propertyToRank.get(prop) == rank)
				propsAtGivenRank.add(prop);
		}
		return propsAtGivenRank;
	}		
	
	/**
	 * Associates any property to a rank value, which is the length of the longest path from the posetRoot to 
	 * the property in the poset diagram.
	 * @throws PropertyPosetException
	 */
	private void setRanks() throws PropertyPosetException {
		if (!rankMappingIsUpToDate) {
			try {
				setPosetRoot();
				initializeRankMap();
				setSuccRankRecursively(posetRoot);
				rankMappingIsUpToDate = true;
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.setRanks() : an error has occured." 
						+ System.lineSeparator() + e.getMessage());
			}
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
			throw new PropertyPosetException("Relation.setSuccRankRecursively() : the rank of " + propName 
					+ " is unknown.");
		else {
			int propRank = propertyToRank.get(propName);
			Set<String> successors;
			try {
				successors = findSuccessorsWithoutUsingRank(propName);
			} catch (PropertyPosetException e) {
				throw new PropertyPosetException("Relation.setSuccRankRecursively() : successors of property " 
						+ propName + " can't be retreived." + System.lineSeparator() + e.getMessage());
			}
			for (String successor : successors) {
				if (propertyToRank.get(successor) < propRank + 1) {
					propertyToRank.put(successor, propRank + 1);
					setSuccRankRecursively(successor);
				}
			}
		}
	}	

}
