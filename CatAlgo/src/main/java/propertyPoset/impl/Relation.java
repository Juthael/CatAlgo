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
import propertyPoset.utils.IDimensionAnalysis;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.impl.DimensionAnalysis;

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
	public void modifyRelation(String targetProperty, Set<String> newPredecessors) throws PropertyPosetException {
		updateRelationData();
		if (relation.containsKey(targetProperty)) {
			Set<String> currentPredecessors = getPredecessors(targetProperty);
			if (!currentPredecessors.containsAll(newPredecessors)) {
				throw new PropertyPosetException("Relation.modifyRelation() : the set of new predecessors " 
						+ System.lineSeparator() + newPredecessors.toString() + System.lineSeparator() +
						"of the target property '" + targetProperty + "' must be a subset of its current set of "
								+ "predecessors : " + System.lineSeparator() + currentPredecessors.toString());
			}
			int maxRank = getMaximalRank();
			for (int i=maxRank-1 ; i>=0 ; i--) {
				for (String property : relation.keySet()) {
					if (propertyToRank.get(property) == i
							&& !property.equals(targetProperty)
							&& relation.get(property).contains(targetProperty)) {
						Set<String> propCsqtsClone = new HashSet<String>(relation.get(property));
						propCsqtsClone.retainAll(newPredecessors);
						if (propCsqtsClone.isEmpty()) {
							relation.get(property).clear();
							relation.get(property).add(property);
							for (String succ : getSuccessors(property)) {
								if (!succ.equals(targetProperty))
									relation.get(property).addAll(relation.get(succ));
							}
						}
					}
				}
			}
			rankMappingIsUpToDate = false;
			successorRelationIsUpToDate = false;
			allDataIsUpToDate = false;
		}
		else throw new PropertyPosetException("Relation.modifyRelation() : target property '" + targetProperty 
				+ "' is unknown");
	}
	
	@Override
	public String addNewProperty(String newProperty, Set<String> predecessors, Set<String> consequents) throws PropertyPosetException {
		updateRelationData();
		if (relation.containsKey(newProperty)) {
			throw new PropertyPosetException("Relation.addNewProperty() : the relation already contains the  "
					+ "property '" + newProperty + "'.");
		}
		if (!relation.keySet().containsAll(predecessors)) {
			throw new PropertyPosetException("Relation.addNewProperty() : at least one property in the following set "
					+ System.lineSeparator() + "of predecessors is unknown : " 
					+ System.lineSeparator() + predecessors.toString());
		}
		Set<String> newPropGreaterProps = new HashSet<String>(consequents);
		newPropGreaterProps.remove(newProperty);
		if (!relation.keySet().containsAll(newPropGreaterProps)) {
			throw new PropertyPosetException("Relation.addNewProperty() : at least one property in the following set "
					+ System.lineSeparator() + "of consequents is unknown : " 
					+ System.lineSeparator() + newPropGreaterProps.toString());
		}
		relation.put(newProperty, consequents);
		for (String property : relation.keySet()) {
			Set<String> propConsequents = new HashSet<String>(getConsequents(property));
			propConsequents.retainAll(predecessors);
			if (!propConsequents.isEmpty())
				relation.get(property).addAll(consequents);
		}
		rankMappingIsUpToDate = false;
		successorRelationIsUpToDate = false;
		allDataIsUpToDate = false;
		return newProperty;
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
			else if (properties.size() == 1) {
				infimum = properties.iterator().next();
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
	public Set<IDimensionAnalysis> getDimensionAnalyzes() throws PropertyPosetException{
		updateRelationData();
		Set<IDimensionAnalysis> dimensionAnalyzes = new HashSet<IDimensionAnalysis>();
		for (String dimension : dimensions) {
			DimensionAnalysis dimAnalysis = new DimensionAnalysis(dimension);
			Set<String> precssrsForThisDimInstance = new HashSet<String>();
			Set<String> remainingPrecssrs;
			try {
				remainingPrecssrs = new HashSet<String>(getPredecessors(dimension));
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.getDimensionAnalyzes() : cannot get predecessors for "
						+ "dimension '" + dimension + "'." + System.lineSeparator() + e.getMessage());
			}
			String dimensionInstanceIdx = "";
			do {
				Set<Set<String>> valuesForThisInstance = new HashSet<Set<String>>();
				precssrsForThisDimInstance.clear();
				precssrsForThisDimInstance.addAll(remainingPrecssrs);
				remainingPrecssrs.clear();
				List<Set<String>> values;
				try {
					values = calculateValues(precssrsForThisDimInstance);
				}
				catch (Exception e) {
					throw new PropertyPosetException("Relation.getDimensionAnalyses() : cannot calculate values of "
							+ "dimension '"	+ dimension + "'." + System.lineSeparator() + e.getMessage());
				}
				int valuesIdx1 = 0;
				int valuesIdx2;
				while (valuesIdx1 < values.size()) {
					valuesForThisInstance.add(values.get(valuesIdx1));
					Set<String> refSetValues = values.get(valuesIdx1);
					valuesIdx2 = valuesIdx1 + 1;
					boolean valuesHaveBeenRecalculated = false;
					while (valuesIdx2 < values.size() && !valuesHaveBeenRecalculated) {
						Set<String> refSet = new HashSet<String>(refSetValues);
						Set<String> comparedSet = new HashSet<String>(values.get(valuesIdx2));
						if (!refSet.equals(comparedSet)) {
							refSet.retainAll(comparedSet);
							if (!refSet.isEmpty()) {
								remainingPrecssrs.addAll(refSet);
								precssrsForThisDimInstance.removeAll(remainingPrecssrs);
								try {
									values = calculateValues(precssrsForThisDimInstance);
								}
								catch (Exception e) {
									throw new PropertyPosetException("Relation.getDimensionAnalyses() : cannot calculate "
											+ "values of dimension '"	+ dimension + "'." + System.lineSeparator() 
											+ e.getMessage());
								}
								valuesHaveBeenRecalculated = true;
								valuesForThisInstance.clear();
								valuesIdx1 = -1;
							}
						}
						valuesIdx2++;
					}
					valuesIdx1++;
				}
				dimAnalysis.addNewDimensionInstance(dimension.concat(dimensionInstanceIdx));
				dimAnalysis.addNewPredecessorsToSpecifiedDimensionInstance(
						dimension.concat(dimensionInstanceIdx), precssrsForThisDimInstance);
				dimensionInstanceIdx = dimensionInstanceIdx.concat("*");
			} while (!remainingPrecssrs.isEmpty());
			dimensionAnalyzes.add(dimAnalysis);
		}
		return dimensionAnalyzes;
	}
	
	@Override
	public boolean removeProperty(IProperty property) throws PropertyPosetException {
		boolean propertyRemoved = false;
		String propertyName = property.getPropertyName();
		if (!relation.containsKey(propertyName))
			throw new PropertyPosetException("Relation.removeProperty() : the property " 
					+ propertyName + " is unknown");
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
			try {
				setSuccessorRelationMapAndRanks();
				setDimensions();
				allDataIsUpToDate = true;	
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.updateRelationData() : an error has occured." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
	}
	
	private List<Set<String>> calculateValues(Set<String> properties) throws PropertyPosetException{
		Set<Set<String>> setsOfValues = new HashSet<Set<String>>();
		Set<String> contextAtoms = getSuccessors(getPosetRoot());
		for (String atom : contextAtoms) {
			Set<String> setOfValues;
			try {
				setOfValues = intersection(atom, properties);
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.calculateValues : cannot calculate value for atom '"
						+ atom + "'." + System.lineSeparator() + e.getMessage());
			}
			if (!setOfValues.isEmpty()) {
				setsOfValues.add(setOfValues);
			}
		}
		return new ArrayList<Set<String>>(setsOfValues);
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
	 * Calculates the intersection of an upper set whose minimum has been given in parameter with a subset given in 
	 * parameter.
	 * @param upperSetMinimum minimum of the upper set.
	 * @param subset any set of properties
	 * @return the intersection set
	 * @throws PropertyPosetException
	 */
	private Set<String> intersection(String upperSetMinimum, Set<String> subset) throws PropertyPosetException{
		Set<String> intersection = new HashSet<String>(getConsequents(upperSetMinimum));
		intersection.retainAll(new HashSet<String>(subset));
		return intersection;
	}
	
	/**
	 * Populates the 'dimensions' field with all sup-reducible elements. 
	 * @throws PropertyPosetException
	 */
	private void setDimensions() throws PropertyPosetException {
		dimensions.clear();
		for (String property : relation.keySet()) {
			try {
				if (getPredecessors(property).size() > 1)
					dimensions.add(property);
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.setDimensions() : error while proceeding on property '" 
						+ property + "'" + System.lineSeparator() + e.getMessage());
			}
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
