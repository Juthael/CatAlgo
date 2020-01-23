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
import utils.IImplication;

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
	private String root;
	private Set<String> dimensions = new HashSet<String>();
	private Set<String> localRoots = new HashSet<String>();
	private Map<String, String> dimensionToRoot = new HashMap<String, String>();
	private Map<String, Integer> propertyToRank = new HashMap<String, Integer>();
	private boolean specialElementSetsAreUpToDate = false;

	public Relation(IPropertySet set) {
		for (IProperty prop : set.getSetOfProperties()) {
			Set<String> propConsequents = new HashSet<String>();
			relation.put(prop.getPropertyName(), propConsequents);
		}
	}
	
	public Relation(IRelation rel, IPropertySet subset) throws PropertyPosetException {
		Set<String> subSetNames = subset.getSetOfPropertyNames();
		for (IProperty prop : subset.getSetOfProperties()) {
			Set<String> propConsequents;
			try {
				propConsequents = rel.getConsequents(prop.getPropertyName());
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation constructor : an error has occured.");
			}
			propConsequents.retainAll(subSetNames);
			relation.put(prop.getPropertyName(), propConsequents);
		}
		updateSpecialElementSets();
	}

	@Override
	public void addImplication(IImplication implication) throws PropertyPosetException {
		String antecedent = implication.getAntecedent();
		String consequent = implication.getConsequent();
		if (relation.containsKey(antecedent) && relation.containsKey(consequent)) {
			relation.get(antecedent).add(consequent);
			if (specialElementSetsAreUpToDate)
				specialElementSetsAreUpToDate = false;
		}
		else throw new PropertyPosetException("Relation.addImplication() : either " 
				+ antecedent + " or " + consequent + " (or both) cannot be retrieved in the "
						+ "relation map."); 
	}
	
	@Override
	public void addImplicationAndGuaranteeTransitivity(IImplication implication) throws PropertyPosetException {
		addImplication(implication);
		for (Set<String> setOfConsequents : relation.values()) {
			if (setOfConsequents.contains(implication.getAntecedent()))
				setOfConsequents.add(implication.getConsequent());
		}
	}	

	@Override
	public Set<String> getConsequents(String propName) throws PropertyPosetException{
		Set<String> consequents;
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.getConsequents() : property " + propName 
					+ "cannot be retreived in the relation map");
		else consequents = relation.get(propName);	
		return consequents; 
	}
	
	@Override
	public Set<String> getAntecedents(String propName) throws PropertyPosetException{
		Set<String> antecedents = new HashSet<String>();
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.getAntecedents() : property " + propName 
					+ "cannot be retreived in the relation map");
		else {
			for (String potentialAntcdt : relation.keySet()) {
				int propNameRank = getRank(propName);
				if (getRank(potentialAntcdt) <= propNameRank && relation.get(potentialAntcdt).contains(propName))
					antecedents.add(potentialAntcdt);
			}
		}
		return antecedents;
	}
	
	@Override
	public Set<String> getGreaterProperties(String propName) throws PropertyPosetException {
		Set<String> greaterProp;
		try {
			greaterProp = getConsequents(propName);
			if (!greaterProp.remove(propName))
				throw new PropertyPosetException("no reflexivity for the property " + propName + ".");
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getGreaterProperties() : an error has occured.");
		}
		return greaterProp;
	}

	@Override
	public Set<String> getLesserProperties(String propName) throws PropertyPosetException {
		Set<String> lesserProp;
		try {
			lesserProp = getAntecedents(propName);
			if (!lesserProp.remove(propName))
				throw new PropertyPosetException("no reflexivity for the property " + propName + ".");
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getLesserProperties() : an error has occured.");
		}
		return lesserProp;
	}

	@Override
	public Set<String> getSuccProperties(String propName) throws PropertyPosetException {
		Set<String> succProp = new HashSet<String>();
		int propRank;
		try {
			propRank = getRank(propName);
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getSuccProperties : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		List<String> nextRankProps = new ArrayList<String>();
		for (String prop : propertyToRank.keySet()) {
			if (getRank(prop) == propRank + 1)
				nextRankProps.add(prop);
		}
		for (String nextRankProp : nextRankProps) {
			if (relation.get(propName).contains(nextRankProp))
				succProp.add(nextRankProp);
		}
		return succProp;
	}

	@Override
	public Set<String> getPrecProperties(String propName) throws PropertyPosetException {
		Set<String> precProp = new HashSet<String>();
		int propRank;
		try {
			propRank = getRank(propName);
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getPrecProperties : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		List<String> prevRankProps = new ArrayList<String>();
		for (String prop : propertyToRank.keySet()) {
			if (getRank(prop) == propRank - 1)
				prevRankProps.add(prop);
		}
		for (String prevRankProp : prevRankProps) {
			if (relation.get(prevRankProp).contains(propName))
				precProp.add(prevRankProp);
		}
		return precProp;		
	}

	@Override
	public int getRank(String propName) throws PropertyPosetException {
		if (!propertyToRank.containsKey(propName)) {
			throw new PropertyPosetException("Relation.getRank() : the argument " + propName + " is unknown.");
		}
		else return propertyToRank.get(propName);
	}

	@Override
	public boolean checkIfDimension(String propName) throws PropertyPosetException {
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.checkIfDimension() : property '" + propName 
					+ "' is unknown.");
		else return (dimensions.contains(propName));
	}

	@Override
	public boolean checkIfLocalRoot(String propName) throws PropertyPosetException {
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.checkIfLocalRoot() : property '" + propName 
					+ "' is unknown.");
		else return (localRoots.contains(propName));
	}

	@Override
	public boolean checkIfLocalAtom(String propName) throws PropertyPosetException {
		boolean localAtom = false;
		if (!relation.containsKey(propName))
			throw new PropertyPosetException("Relation.checkIfLocalAtom() : property '" + propName 
					+ "' is unknown.");
		else{
			List<String> listOfRoots = new ArrayList<String>(localRoots);
			int rootIndex = 0;
			while (localAtom == false && rootIndex < listOfRoots.size()) {
				if (getSuccProperties(listOfRoots.get(rootIndex)).contains(propName))
					localAtom = true;
				else rootIndex++;
			}
		}
		return localAtom;
	}

	@Override
	public String getLocalRoot(String dimension) throws PropertyPosetException {
		if (!dimensionToRoot.containsKey(dimension)) {
			throw new PropertyPosetException("Relation.getLocalRoot() : the argument " + dimension 
					+ " is not a dimension."); 
		}
		else return dimensionToRoot.get(dimension);
	}

	@Override
	public String getPosetRoot() throws PropertyPosetException {
		String root = "";
		List<String> properties = new ArrayList<String>(propertyToRank.keySet());
		int propIndex = 0;
		boolean rootFound = false;
		while (!rootFound && propIndex < properties.size()) {
			String currentProp = properties.get(propIndex);
			if (propertyToRank.get(currentProp) == 0) {
				root = currentProp;
				rootFound = true;
			}
			else propIndex++;
		}
		if (rootFound == false || root.isEmpty())
			throw new PropertyPosetException("Relation.getPosetRoot() : the root can't be found or is empty.");
		else return root;
	}

	@Override
	public Set<String> getPosetleaves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeProperty(String propertyName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateSpecialElementSets() {
		// TODO Auto-generated method stub

	}
	
	private void setDimensions() throws PropertyPosetException {
		for (String propName : relation.keySet()) {
			boolean dimension;
			try {
				dimension = (getPrecProperties(propName).size() > 1);
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.setDimensions() : an error has occured." 
						+ System.lineSeparator() + e.getMessage());
			}
			if (dimension)
				dimensions.add(propName);
		}
	}
	
	private void setLocalRoots() throws PropertyPosetException {
		for (String dimension : dimensions) {
			String root = "";
			Set<String> dimensionPredecessors;
			try {
				dimensionPredecessors = getPrecProperties(dimension);
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation.setLocalRoots() : an error has occured." 
						+ System.lineSeparator() + e.getMessage()); 
			}
			boolean localRootFound = false;
			int targetRank = getRank(dimension) - 2;
			while (localRootFound == false && targetRank >= 0) {
				List<String> potentialRoots = getPropAtThisRank(targetRank);
				int potentialRootIndex = 0;
				while (localRootFound == false && potentialRootIndex < potentialRoots.size()) {
					String potentialRoot = potentialRoots.get(potentialRootIndex);
					if (relation.get(potentialRoot).containsAll(dimensionPredecessors)){
						localRootFound = true;
						root = potentialRoot;
						localRoots.add(root);
						dimensionToRoot.put(dimension, root);
					}
					else potentialRootIndex++;
				}
				targetRank--;
			}
		}
	}
	
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

}
