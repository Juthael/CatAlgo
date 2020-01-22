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
		Set<String> propConsequents;
		for (IProperty prop : subset.getSetOfProperties()) {
			try {
				propConsequents = rel.getConsequents(prop.getPropertyName());
			}
			catch (Exception e) {
				throw new PropertyPosetException("Relation constructor : an error has occured.");
			}
			relation.put(prop.getPropertyName(), propConsequents);
		}
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
			for (String antecedent : relation.keySet()) {
				if (relation.get(antecedent).contains(propName))
					antecedents.add(antecedent);
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
		List<String> greaterProp;
		try {
			greaterProp = new ArrayList<String>(getGreaterProperties(propName)) ;
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getSuccProperties() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		for (int i=0 ; i < greaterProp.size() ; i++) {
			String checkedProp = greaterProp.get(i);
			boolean iPropIsMinUpperBound = true;
			int j = 0;
			while (iPropIsMinUpperBound && j < greaterProp.size()) {
				String comparedProp = greaterProp.get(j);
				if (j != i && relation.get(comparedProp).containsAll(relation.get(checkedProp)))
					iPropIsMinUpperBound = false;
				j++;
			}
			if (iPropIsMinUpperBound)
				succProp.add(checkedProp);
		}
		return succProp;
	}

	@Override
	public Set<String> getPrecProperties(String propName) throws PropertyPosetException {
		Set<String> precProp = new HashSet<String>();
		List<String> lesserProp;
		try {
			lesserProp = new ArrayList<String>(getLesserProperties(propName)) ;
		}
		catch (Exception e) {
			throw new PropertyPosetException("Relation.getSuccProperties() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		for (int i=0 ; i < lesserProp.size() ; i++) {
			String checkedProp = lesserProp.get(i);
			boolean iPropIsMaxLowerBound = true;
			int j=0;
			while (iPropIsMaxLowerBound = true && j < lesserProp.size()) {
				String comparedProp = lesserProp.get(j);
				if (j != i && relation.get(checkedProp).containsAll(relation.get(comparedProp)))
					iPropIsMaxLowerBound = false;
				j++;
			}
			if (iPropIsMaxLowerBound)
				precProp.add(checkedProp);
		}
		return precProp;
	}

	@Override
	public int getRank(String propName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IRelation getSubrelation(IPropertySet subSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkIfDimension(String propName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfLocalRoot(String propName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfLocalAtom(String propName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getLocalRoot(String dimension) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPosetRoot() {
		// TODO Auto-generated method stub
		return null;
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

}
