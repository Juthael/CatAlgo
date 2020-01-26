package propertyPoset;

import java.util.Set;

import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IPropertySet is a (unordered) set of properties ({@link IProperty}) endowed with some basic functionalities. 
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
	 * This method is called in order to remove from the set a 'superfluous' property, while keeping track 
	 * of it nonetheless. <br> 
	 * A property is superfluous if it is not an atom of the (lower semi-lattice) property poset, and 
	 * if it is implied, according the poset relation, by a single predecessor, called its 'antecedent'.
	 * After its removal, the superfluous property is stored as an 'encapsulated property' in the dedicated 
	 * field of its antecedent.  
	 * @param propertyName name of the superfluous property.
	 * @param antecedent name of the antecedent.
	 * @return true if the property has been correctly removed. 
	 * @throws PropertyPosetException 
	 * @see IProperty
	 */
	boolean removeProperty(String propertyName, String antecedent) throws PropertyPosetException;
	
	/**
	 * This method is used to remove from the set the property given in parameter, without 
	 * keeping any track of it. It is intended to be called by a IOriginalPropertyPoset object, 
	 * only during the 'sub-context extraction' procedure. 
	 * @param propertyName
	 * @return true if the property has been correctly removed.
	 * @throws PropertyPosetException
	 */
	boolean removeProperty(String propertyName) throws PropertyPosetException;
}
