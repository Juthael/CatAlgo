package propertyPoset.impl;

import java.util.HashSet;
import java.util.Set;

import propertyPoset.IProperty;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;

public class Property implements IProperty {

	private String name;
	private Set<IProperty> encapsulatedProp = new HashSet<IProperty>();
	
	public Property(String name) {
		this.name = name;
	}

	@Override
	public void addEncapsulatedProp(IProperty prop) {
		encapsulatedProp.add(prop);
	}

	@Override
	public String getPropertyName() {
		return name;
	}

	@Override
	public Set<String> getGreaterProperties(IRelation rel) throws PropertyPosetException {
		Set<String> greaterProp;
		try {
			greaterProp = rel.getGreaterProperties(name);
		}
		catch (Exception e){
			throw new PropertyPosetException("Property.getGreaterProperties() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return greaterProp;
	}

	@Override
	public Set<String> getLesserProperties(IRelation rel) throws PropertyPosetException {
		Set<String> lesserProp;
		try {
			lesserProp = rel.getLesserProperties(name);
		}
		catch (Exception e){
			throw new PropertyPosetException("Property.getLesserProperties() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return lesserProp;
	}

	@Override
	public Set<String> getSuccProperties(IRelation rel) throws PropertyPosetException {
		Set<String> succProp;
		try {
			succProp = rel.getSuccProperties(name);
		}
		catch (Exception e){
			throw new PropertyPosetException("Property.getSuccProperties() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return succProp;
	}

	@Override
	public Set<String> getPrecProperties(IRelation rel) throws PropertyPosetException {
		Set<String> precProp;
		try {
			precProp = rel.getPrecProperties(name);
		}
		catch (Exception e){
			throw new PropertyPosetException("Property.getPrecProperties() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return precProp;
	}

	@Override
	public Set<IProperty> getEncapsulatedProperties() {
		return encapsulatedProp;
	}

	@Override
	public int getRank(IRelation rel) throws PropertyPosetException {
		int rank;
		try {
			rank = rel.getRank(name);
		}
		catch (Exception e) {
			throw new PropertyPosetException("Property.getRank() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return rank;
	}

	@Override
	public boolean isADimension(IRelation rel) throws PropertyPosetException {
		boolean dimension;
		try {
			dimension = rel.checkIfDimension(name);
		}
		catch (Exception e) {
			throw new PropertyPosetException("Property.isADimension() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return dimension;
	}

	@Override
	public boolean isALocalRoot(IRelation rel) throws PropertyPosetException {
		boolean localRoot;
		try {
			localRoot = rel.checkIfLocalRoot(name);
		}
		catch (Exception e) {
			throw new PropertyPosetException("Property.isALocalRoot() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return localRoot;
	}

	@Override
	public boolean isALocalAtom(IRelation rel) throws PropertyPosetException {
		boolean localAtom;
		try {
			localAtom = rel.checkIfLocalAtom(name);
		}
		catch (Exception e) {
			throw new PropertyPosetException("Property.isALocalAtom() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return localAtom;
	}

}
