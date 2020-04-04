package propertyPoset.impl;

import java.util.Set;

import propertyPoset.IProperty;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IProperty has a name, can be part of a set on which an order relation can be defined. <br>
 * 
 * @author Gael Tregouet
 *
 */
public class Property implements IProperty {

	private String name;
	
	/**
	 * 
	 * @param name the name of the property.
	 */
	public Property(String name) {
		this.name = name;
	}

	@Override
	public String getPropertyName() {
		return name;
	}
	
	@Override
	public Set<String> getAntecedents(IRelation rel) throws PropertyPosetException {
		Set<String> antecedents;
		try {
			antecedents = rel.getAntecedents(name);
		}
		catch (Exception e){
			throw new PropertyPosetException("Property.getAntecedents() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return antecedents;
	}
	
	@Override
	public Set<String> getConsequents(IRelation rel) throws PropertyPosetException {
		Set<String> consequents;
		try {
			consequents = rel.getConsequents(name);
		}
		catch (Exception e){
			throw new PropertyPosetException("Property.getConsequents() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return consequents;
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
			succProp = rel.getSuccessors(name);
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
			precProp = rel.getPredecessors(name);
		}
		catch (Exception e){
			throw new PropertyPosetException("Property.getPrecProperties() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return precProp;
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

}
