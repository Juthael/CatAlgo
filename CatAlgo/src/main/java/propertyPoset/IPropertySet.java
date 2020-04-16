package propertyPoset;

import java.util.Set;

import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.impl.PropertyPoset;

/**
 * A IPropertySet is a (unordered) set of properties endowed with some basic functionalities. 
 * @author Gael Tregouet
 *
 */
public interface IPropertySet {
	
	/**
	 * The secure way to add a property in the property set is to add the return value of the addNewProperty() method 
	 * of {@link Relation}. This ensures that the order relation on the {@link PropertyPoset} remains consistent. 
	 * @param name the name of the new property
	 * @throws PropertyPosetException 
	 */
	void addNewProperty(String name) throws PropertyPosetException;
	
	/**
	 * 
	 * @return the set of properties
	 */
	Set<IProperty> getSetOfProperties();
	
	/**
	 * 
	 * @return the names of the properties in the set
	 */
	Set<String> getSetOfPropertyNames();
	
	/**
	 * 
	 * @param propertyName the name of the requested property.
	 * @return the requested property.
	 * @throws PropertyPosetException 
	 */
	IProperty getProperty(String propertyName) throws PropertyPosetException;
	
	/**
	 * 
	 * @param propertyName the name of the requested properties.
	 * @return the requested properties.
	 * @throws PropertyPosetException 
	 */
	Set<IProperty> getSubsetOfProperties(Set<String> propertyNames) throws PropertyPosetException;
}
