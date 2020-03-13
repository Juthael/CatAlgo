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
	 * 
	 * Non-informative properties are sup-irreducible leaves of the poset (i.e. leaves with only one predecessor, 
	 * since the poset is a lower semi-lattice). Those properties usually remain unnoticed or indistinguishable from 
	 * their antecedent for human subjects, since they do not allow any additional distinction amongst the elements 
	 * of a context. (In a collection of cars, for the 'redness' of a particular car to be salient, it takes the 
	 * 'un-redness' (e.g., blueness) of other elements). <br>
	 * 
	 * @param propertyName name of the non-informative property.
	 * @param antecedent name of the antecedent.
	 * @return the property whose name has been given in the parameter 'propertyName'
	 * @throws PropertyPosetException 
	 * @see IProperty
	 */
	IProperty removeProperty(String propertyName, String antecedent) throws PropertyPosetException;
}
