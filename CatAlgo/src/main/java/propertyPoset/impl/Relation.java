package propertyPoset.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import propertyPoset.IProperty;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IImplication;

/**
 * A Relation is an implementation - endowed with some useful functionalities - of a binary relation on a set of 
 * properties.
 * 
 * @author Gael Tregouet
 *
 */
public class Relation implements IRelation {
	
	private Map<String, Set<String>> relation = new HashMap<String, Set<String>>();
	private Map<String, Set<String>> successorRelation = new HashMap<String, Set<String>>();
	private String posetRoot = "";
	private Set<String> dimensions = new HashSet<String>();
	private Set<String> allInformativeProperties = new HashSet<String>();
	private Map<String, String> dimensionToRoot = new HashMap<String, String>();
	private Map<String, Integer> propertyToRank = new HashMap<String, Integer>();
	private boolean allDataIsUpToDate = false;
	private boolean rankMappingIsUpToDate = false;
	private boolean successorRelationIsUpToDate = false;

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

	@Override
	public void addImplication(IImplication implication) throws PropertyPosetException {
		String antecedent = implication.getAntecedent();
		String consequent = implication.getConsequent();
		if (relation.containsKey(antecedent) && relation.containsKey(consequent)) {
			relation.get(antecedent).add(consequent);
			if (allDataIsUpToDate) {
				allDataIsUpToDate = false;
				rankMappingIsUpToDate = false;
				successorRelationIsUpToDate = false;
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
	public void setPropAsALeaf(String subContextRoot) throws PropertyPosetException {
		int subContextRootRank = getRank(subContextRoot);
		Set<String> subContextRootAntcdts = getAntecedents(subContextRoot);
		for (int i = (subContextRootRank - 1) ; i >= 0 ; i--) {
			Set<String> antcdtsAtThisRank = new HashSet<String>();
			for (String ancdt : subContextRootAntcdts) {
				if (getRank(ancdt) == i)
					antcdtsAtThisRank.add(ancdt);
			}
			for (String antcdtAtThisRank : antcdtsAtThisRank) {
				Set<String> antcdtConsequents = new HashSet<String>();
				antcdtConsequents.add(antcdtAtThisRank);
				for (String antcdtSucc : getSuccessors(antcdtAtThisRank)){
					if (!antcdtSucc.equals(subContextRoot)) {
						antcdtConsequents.addAll(getConsequents(antcdtSucc));
					}
					else antcdtConsequents.add(subContextRoot);
				}
				relation.put(antcdtAtThisRank, antcdtConsequents);
			}
		}
		Set<String> newLeafConsequents = new HashSet<String>();
		newLeafConsequents.add(subContextRoot);
		relation.put(subContextRoot, newLeafConsequents);
		allDataIsUpToDate = false;
		rankMappingIsUpToDate = false;
		successorRelationIsUpToDate = false;
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
		setSuccessorRelationMap();
		Set<String> successorNames;
		try {
			successorNames = successorRelation.get(propName);
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getSuccessors() : an error has occured while attempting "
					+ "to retreive successors of property '" + propName + "'. " + System.lineSeparator() 
					+ e.getMessage());
		}
		return successorNames;
	}

	@Override
	public Set<String> getPredecessors(String propName) throws PropertyPosetException {
		setSuccessorRelationMap();
		Set<String> predecessorNames = new HashSet<String>();
		try {
			for (String prop : successorRelation.keySet()) {
				if (successorRelation.get(prop).contains(propName))
					predecessorNames.add(prop);
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getPredecessors() : an error has occured while attempting "
					+ "to retreive predecessors of property '" + propName + "'. " + System.lineSeparator() 
					+ e.getMessage());
		}
		return predecessorNames;
	}
	
	@Override
	public String getInfimum(Set<String> properties) throws PropertyPosetException {
		String infimum = "";
		try {
			if (properties.isEmpty()) {
				throw new PropertyPosetException("Relation.getInfimum() : the set of properties shouldn't be empty.");
			}
			else {
				Iterator<String> propIterator = properties.iterator(); 
				int setMinRank = getRank(propIterator.next());
				while (propIterator.hasNext()) {
					String nextProp = propIterator.next();
					if (getRank(nextProp) < setMinRank)
						setMinRank = getRank(nextProp);
				}
				int testedRank = setMinRank;
				while (infimum.isEmpty() && testedRank >=0) {
					List<String> propAtTestedRank = getPropAtThisRank(testedRank);
					for (String prop : propAtTestedRank) {
						if (infimum.isEmpty() && getConsequents(prop).containsAll(properties))
							infimum = prop;
					}
					testedRank--;
				}
				if (infimum.isEmpty()) {
					StringBuilder sB = new StringBuilder();
					for (String prop : properties) {
						sB.append(prop + System.lineSeparator());
					}
					throw new PropertyPosetException("An infimum should have been found for properties "
							+ sB.toString());
				}
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getInfimum() : an error has occured." + System.lineSeparator());
		}
		return infimum;
	}

	@Override
	public int getRank(String propName) throws PropertyPosetException {
		setSuccessorRelationMapAndRanks();
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
	public String getPosetRoot() throws PropertyPosetException {
		setPosetRoot();
		if (posetRoot.isEmpty())
			throw new PropertyPosetException("Relation.getPosetRoot() : the posetRoot can't be found or "
					+ "is empty.");
		else return posetRoot;
	}	
	
	@Override
	public boolean checkIfInformativeProperty(String propName) throws PropertyPosetException {
		updateRelationData();
		return allInformativeProperties.contains(propName);
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
		setSuccessorRelationMapAndRanks();
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
		else if (checkIfInformativeProperty(propertyName) == true){
			throw new PropertyPosetException("Relation.removeProperty() : the property " 
					+ propertyName + " is informative and therefore should not be removed.");
		}
		else {
			relation.remove(propertyName);
			for (Set<String> consequents : relation.values())
				consequents.remove(propertyName);
			propertyRemoved = true;
			allDataIsUpToDate = false;
			rankMappingIsUpToDate = false;
			successorRelationIsUpToDate = false;
		}
		return propertyRemoved;
	}

	@Override
	public void updateRelationData() throws PropertyPosetException {
		if (!allDataIsUpToDate) {
			if (!dimensions.isEmpty())
				dimensions = new HashSet<String>();
			if (!allInformativeProperties.isEmpty())
				allInformativeProperties = new HashSet<String>();
			if (!dimensionToRoot.isEmpty())
				dimensionToRoot = new HashMap<String, String>();
			try {
				setSuccessorRelationMapAndRanks();
				setInformativeProperties();
				allDataIsUpToDate = true;	
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.updateRelationData() : an error has occured." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
	}
	
	/**
	 * A property d is a 'dimension' if : 
	 * 1/ it has more than one predecessor (i.e., is sup-reducible).
	 * 2/ if 'P' is the set of predecessors, 'r' its infimum ; for any property 'q' less than 'd' and 
	 * greater than 'r', there is no property 'p' that verifies (('p' < 'q') && ('p' not comparable 
	 * to 'r')). 
	 * 
	 * @param potentialDimension sup-reducible property and potential dimension
	 * @param potentialRoot infimum of the potential dimension predecessors
	 * @return 'true' if 'potentialDimension' is really a dimension, 'false' otherwise
	 * @throws PropertyPosetException
	 */
	private boolean checkIfThisIsATrueDimension(String potentialDimension, String potentialRoot) 
			throws PropertyPosetException {
		boolean thisIsATrueDimension = true;
		setPosetRoot();
		try {
			if (!potentialRoot.equals(posetRoot)) {
				Set<String> greaterThanRLessThanD = new HashSet<String>(getGreaterProperties(potentialRoot));
				greaterThanRLessThanD.retainAll(getLesserProperties(potentialDimension));
				Set<String> relatedToR = new HashSet<String>(getConsequents(potentialRoot));
				relatedToR.addAll(getAntecedents(potentialRoot));
				Iterator<String> gRlDIter = greaterThanRLessThanD.iterator();
				while (gRlDIter.hasNext() && thisIsATrueDimension == true) {
					String currentProp = gRlDIter.next();
					Set<String> currentPropAntecedents = getAntecedents(currentProp);
					if (!relatedToR.containsAll(currentPropAntecedents)) {
						thisIsATrueDimension = false;
					}
				}
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.checkIfThisIsATrueDimension() : an error has occured "
					+ "while processing on property " + potentialDimension + "." + System.lineSeparator() 
					+ e.getMessage());
		}
		return thisIsATrueDimension;
	}	

	/**
	 * This method can be called even when 'allDataIsUpToDate == false' and 'rankMappingIsUpToDate == false'
	 * (so the rank map can't be used). 
	 * @param propName the name of the property whose successors are requested
	 * @return the set of successors' names
	 * @throws PropertyPosetException
	 */
	private Set<String> findSuccessorsWithoutUsingSuccessorRelationOrRank(String propName) throws PropertyPosetException {
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
	 * @param rank the target rank
	 * @return a set of property names
	 * @throws PropertyPosetException
	 */
	private List<String> getPropAtThisRank(int rank) throws PropertyPosetException{
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
	 * Informative properties are the poset root, dimensions, dimension roots, and dimension values. 
	 * 
	 * A property d is a 'dimension' if : 
	 * 1/ it has more than one predecessor (i.e., is sup-reducible).
	 * 2/ if 'P' is the set of predecessors, 'r' its infimum ; for any property 'q' less than 'd' and 
	 * greater than 'r', there is no property 'p' that verifies (('p' < 'q') && ('p' not comparable 
	 * to 'r')). 
	 * 
	 * Let 'A' be the set of properties succeeding 'r' and less than 'd' ; then a property 'v' 
	 * is a value of 'd' iff there exists a subset 'X' of 'A' such that 'v' is the supremum of 'A'.  
	 * So 'v' is either an element of A, or the supremum of two or more elements of A ; in this last 
	 * case 'v' is a dimension itself, so it will be found as such and does not need to be explicitly 
	 * looked for as a value.  
	 * @throws PropertyPosetException
	 */
	private void setInformativeProperties() throws PropertyPosetException {
		try {
			for (String potentialDimension : relation.keySet()) {
				boolean isSupReducible = (getPredecessors(potentialDimension).size() > 1);
				if (isSupReducible){
					Set<String> predecessors = getPredecessors(potentialDimension);
					String potentialRoot = getInfimum(predecessors);
					boolean thisIsATrueDimension = checkIfThisIsATrueDimension(potentialDimension, potentialRoot);
					if (thisIsATrueDimension) {
						Set<String> dimensionAtoms = new HashSet<String>(getSuccessors(potentialRoot));
						dimensionAtoms.retainAll(getAntecedents(potentialDimension));
						if (dimensionAtoms.size() < 2)
							throw new PropertyPosetException("The dimension root " + potentialRoot + " of the dimension "
									+ potentialDimension + " having " + dimensionAtoms.size() + " atoms is "
											+ "inconsistent.");
						else {
							dimensions.add(potentialDimension);
							dimensionToRoot.put(potentialDimension, potentialRoot);
							allInformativeProperties.add(potentialDimension);
							allInformativeProperties.add(potentialRoot);
							allInformativeProperties.addAll(dimensionAtoms);
						}
					}
				}
				else if (potentialDimension.equals(posetRoot)) {
					allInformativeProperties.add(potentialDimension);
				}
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.setDimensionsAndRootsAndValues : an error has occured. "
					+ System.lineSeparator() + e.getMessage());
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
	 * Populates the 'successorRelation' map and allows the boolean variable 'successorRelationIsUpToDate' 
	 * to have 'true' as a value.
	 * @throws PropertyPosetException
	 */
	private void setSuccessorRelationMap() throws PropertyPosetException {
		if (!successorRelationIsUpToDate) {
			successorRelation = new HashMap<String, Set<String>>();
			try {
				setPosetRoot();
				setSuccRelationRecursively(posetRoot);
				if (successorRelation.size() != relation.size()) {
					throw new PropertyPosetException("Relation and "
							+ "successorRelation maps should be the same size.");
				}
				else successorRelationIsUpToDate = true;
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.setSuccessorRelationMap() : "
						+ "cannot set successor relation." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
	}
	
	/**
	 * Associates any property to a rank value, which is the length of the longest path from the posetRoot to 
	 * the property in the poset diagram.
	 * @throws PropertyPosetException
	 */
	private void setSuccessorRelationMapAndRanks() throws PropertyPosetException {
		if (!rankMappingIsUpToDate) {
			try {
				for (String prop : relation.keySet())
					propertyToRank.put(prop, 0);
				setSuccessorRelationMap();
				try {
					setPosetRoot();
					setSuccRankRecursively(posetRoot);
				}
				catch (Exception e) {
					throw new PropertyPosetException("Cannot set ranks." + System.lineSeparator() 
							+ e.getMessage());
				}
				rankMappingIsUpToDate = true;
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.AndSuccessorRelationMap() : an error has occured." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
	}
	
	/**
	 * Ensures that every successor to the property given in parameter has a rank value greater 
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
				successors = getSuccessors(propName);
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
	
	/**
	 * Maps the property whose name is given in parameter with its successors, then calls 
	 * itself on each of these successors. 
	 * @param propName the name of the property whose successors are requested. 
	 * @throws PropertyPosetException
	 */
	private void setSuccRelationRecursively(String propName) throws PropertyPosetException {
		if (!successorRelation.containsKey(propName)) {
			Set<String> successors;
			try {
				successors = findSuccessorsWithoutUsingSuccessorRelationOrRank(propName);
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.setSuccRelationRecursively() : an error occured "
						+ "while retrieving the successors of the property '" + propName + "'." 
						+ System.lineSeparator() + e.getMessage());
			}
			successorRelation.put(propName, successors);
			try {
				for (String successor : successors) {
					setSuccRelationRecursively(successor);
				}
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.setSuccRelationRecursively() : an error occured "
						+ "during the recursive call of this method on the successors of '" + propName + "'." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		
		
	}

}
