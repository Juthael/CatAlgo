package propertyPoset;

import java.util.Set;

import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.impl.PropertyPoset;

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
	 * This method is called in order to remove from the set a 'non-informative' property, while keeping 
	 * track of it nonetheless. <br> 
	 * A property is non-informative if it is not an atom of the (lower semi-lattice) property poset, and 
	 * if it is implied by a single predecessor, called its 'antecedent'.
	 * After its removal, the non-informative property is stored as an 'encapsulated property' in the dedicated 
	 * field of its antecedent.  
	 * A target {@link IProperty} instance can be protected from removal, which prevents this method to 
	 * operate any modification on the set and relation. This is not considered as an error, and no exception 
	 * is thrown.  
	 * @param propertyName name of the superfluous property.
	 * @param antecedent name of the antecedent.
	 * @return the property whose name has been given in the parameter 'propertyName' (has it been actually 
	 * removed or not)
	 * @throws PropertyPosetException 
	 * @see IProperty
	 */
	IProperty removeProperty(String propertyName, String antecedent) throws PropertyPosetException;
	
	/**
	 * This method is used to remove from the set the property given in parameter, without 
	 * keeping any track of it. It is intended to be called by a {@link PropertyPoset} object, 
	 * only during the 'sub-context extraction' procedure. 
	 * A target {@link IProperty} instance can be protected from removal, which prevents this method to 
	 * operate any modification on the set and relation. This is not considered as an error, and no 
	 * exception is thrown.  
	 * @param propertyName
	 * @return the property whose name has been given in parameter (has it been actually removed or not)
	 * @throws PropertyPosetException
	 */
	IProperty removeProperty(String propertyName) throws PropertyPosetException;
}
