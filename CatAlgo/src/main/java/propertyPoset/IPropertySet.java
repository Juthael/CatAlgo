package propertyPoset;

import java.util.Set;

import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IPropertySet is a (unordered) set of properties endowed with some basic functionalities. 
 * @author Gael Tregouet
 *
 */
public interface IPropertySet {
	
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
	
	/**
	 * This method is called in order to remove from the set a 'non-informative' property, and nonetheless 
	 * keeping track of it. <br> 
	 * A property is informative only if it is a dimension, a dimension value, an atom of the (lower semi-lattice) 
	 * poset or the poset root. Otherwise it can be removed. <br>
	 * @param propertyName name of the superfluous property.
	 * @param antecedent name of the antecedent.
	 * @return the property whose name has been given in the parameter 'propertyName' (has it been actually 
	 * removed or not)
	 * @throws PropertyPosetException 
	 * @see IProperty
	 */
	IProperty removeProperty(String propertyName, String antecedent) throws PropertyPosetException;
}
