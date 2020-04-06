package propertyPoset.utils.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IDimensionAnalysis;

public class DimensionAnalysis implements IDimensionAnalysis {

	private String dimName;
	private List<String> dimensionInstances = new ArrayList<String>();
	private Map<String, Set<String>> dimInstancesToPredecessors = new HashMap<String, Set<String>>();
	
	public DimensionAnalysis(String dimName) {
		this.dimName = dimName;
	}
	
	@Override
	public String getDimensionName() {
		return dimName;
	}	

	@Override
	public boolean checkIfPosetModificationRequired() {
		return (dimensionInstances.size() > 1);
	}

	@Override
	public List<String> getAllInstancesOfThisDimension() {
		return dimensionInstances;
	}

	@Override
	public Set<String> getNewInstancesOfThisDimension() {
		Set<String> newInstances = new HashSet<String>();
		if (dimensionInstances.size() > 1) {
			newInstances.addAll(dimensionInstances.subList(1, dimensionInstances.size()));
		}
		return newInstances;
	}

	@Override
	public Set<String> getValuesForThisDimensionInstance(String dimName) {
		return dimInstancesToPredecessors.get(dimName);
	}

	@Override
	public boolean addNewDimensionInstance(String dimName) {
		boolean dimensionAdded = false;
		if (!dimensionInstances.contains(dimName)) {
			dimensionInstances.add(dimName);
			dimInstancesToPredecessors.put(dimName, new HashSet<String>());
			dimensionAdded = true;
		}
		return dimensionAdded;
	}

	@Override
	public boolean addNewPredecessorToSpecifiedDimensionInstance(String dimName, String predecessor) 
			throws PropertyPosetException {
		boolean newValueAdded = false;
		if (dimInstancesToPredecessors.containsKey(dimName)) {
			newValueAdded = dimInstancesToPredecessors.get(dimName).add(predecessor);
			
		}
		else throw new PropertyPosetException("DimensionAnalysis.addNewValueToSpecifiedDimensionInstance() : "
				+ "dimension '" + dimName + "' is unknown." );
		return newValueAdded;
	}

	@Override
	public boolean addNewPredecessorsToSpecifiedDimensionInstance(String dimName, Set<String> predecessor) 
			throws PropertyPosetException {
		boolean newValuesAdded = false;
		if (dimInstancesToPredecessors.containsKey(dimName)) {
			Set<String> knownValues = new HashSet<String>(dimInstancesToPredecessors.get(dimName));
			Set<String> newValues = new HashSet<String>(predecessor);
			knownValues.retainAll(newValues);
			if (knownValues.isEmpty()) {
				newValuesAdded = dimInstancesToPredecessors.get(dimName).addAll(predecessor);
			}
			else throw new PropertyPosetException("DimensionAnalysis.ValueToSpecifiedDimensionInstance() : "
					+ "some of the values '" + predecessor.toString() + "' are already known." );
		}
		else throw new PropertyPosetException("DimensionAnalysis.ValueToSpecifiedDimensionInstance() : "
				+ "dimension '" + dimName + "' is unknown." );
		return newValuesAdded;
	}

}
