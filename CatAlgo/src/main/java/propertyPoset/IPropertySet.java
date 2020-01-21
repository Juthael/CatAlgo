package propertyPoset;

import java.util.Set;

/**
 * A IPropertySet is a (unordered) set of properties ({@link IProperty}) endowed with some basic functionalities. 
 * @author Gael Tregouet
 *
 */
public interface IPropertySet {
	
	/**
	 * 
	 * @return the set of properties. 
	 */
	Set<IProperty> getSetOfProperties();
	
	/**
	 * 
	 * @param propertyName the name of the requested property.
	 * @return the requested property.
	 */
	IProperty getProperty(String propertyName);
	
	/**
	 * 
	 * @param propertyName the name of the requested properties.
	 * @return the requested properties.
	 */
	Set<IProperty> getSubsetOfProperties(Set<String> propertyNames);
	
	/**
	 * This method is called in order to remove from the set a 'superfluous' property. 
	 * A property is superfluous if it is not an atom of the (lower semi-lattice) property poset, and 
	 * if it is implied, according the poset relation, by a single predecessor, called its 'antecedent'.
	 * After its removal, the superfluous property is stored as an 'encapsulated property' in the dedicated 
	 * field of its antecedent.  
	 * @param propertyName name of the superfluous property.
	 * @param antecedent name of the antecedent.
	 * @return true if the property has been correctly removed. 
	 * @see IProperty
	 */
	boolean removeProperty(String propertyName, String antecedent);

}
