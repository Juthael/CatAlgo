package propertyPoset.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import propertyPoset.IProperty;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;

/**
 * A PropertyPoset is an implementation of a partially ordered set whose elements are properties. <br>
 * 
 * As such, it is composed of a {@link IPropertySet} object (unordered set of properties), and of a 
 * {@link IRelation} object (partial order relation on the set of properties). 
 * @author Gael Tregouet
 *
 */
public class PropertySet implements IPropertySet {

	private Set<IProperty> setOfIProperties;
	
	/**
	 * 
	 * @param setOfProperties a set of properties
	 */
	public PropertySet(Set<IProperty> setOfProperties) {
		this.setOfIProperties = setOfProperties;
	}
	
	/**
	 * 
	 * @param setOfPropNames a set of property names
	 * @param thisIsASetOfString boolean only used to have a distinct signature for this constructor
	 */
	public PropertySet(Set<String> setOfPropNames, boolean thisIsASetOfStrings) {
		setOfIProperties = new HashSet<IProperty>();
		for (String name : setOfPropNames)
			setOfIProperties.add(new Property(name));
	}	

	@Override
	public Set<IProperty> getSetOfProperties() {
		return setOfIProperties;
	}
	
	@Override
	public Set<String> getSetOfPropertyNames(){
		Set<String> setNames = new HashSet<String>();
		for (IProperty prop : setOfIProperties)
			setNames.add(prop.getPropertyName());
		return setNames;
	}

	@Override
	public IProperty getProperty(String propertyName) throws PropertyPosetException {
		IProperty requested = null;
		Iterator<IProperty> propIterator = setOfIProperties.iterator();
		try {
			if (propIterator.hasNext()) {
				requested = propIterator.next();
				while (!requested.getPropertyName().equals(propertyName) && propIterator.hasNext()) {
					requested = propIterator.next();
				}
				if (!requested.getPropertyName().equals(propertyName))
					throw new PropertyPosetException("The requested property hasn't been found.");
			}
			else throw new PropertyPosetException("setOfIProperties is empty.");
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertySet.getProperty() : an error has occured." 
					+ System.lineSeparator() + e.getMessage());
		}
		return requested;
	}

	@Override
	public Set<IProperty> getSubsetOfProperties(Set<String> propertyNames) throws PropertyPosetException {
		Set<IProperty> subset = new HashSet<IProperty>();
		try {
			for (String name : propertyNames) {
				subset.add(getProperty(name));
			}	
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertySet.getSetOfProperties() : an error has occured. "
					+ System.lineSeparator() + e.getMessage());
		}
		return subset;
	}

	@Override
	public boolean removeProperty(String propertyName, String antecedent) throws PropertyPosetException {
		IProperty removedProperty;
		IProperty antecedentProperty;
		boolean removed = false;
		try {
			removedProperty = getProperty(propertyName);
			removed = setOfIProperties.remove(getProperty(propertyName));
			if (!removed)
				throw new PropertyPosetException("the property " + propertyName + " hasn't been removed.");
			else {
				try {
					antecedentProperty = getProperty(antecedent);
				}
				catch (Exception e) {
					throw new PropertyPosetException("the antecedent property " + antecedent + " cannot be retreived." );
				}
				antecedentProperty.addEncapsulatedProp(removedProperty);
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertySet.removeProperty : an error has occured. " 
					+ System.lineSeparator() + e.getMessage());
		}
		return removed;
	}

}
