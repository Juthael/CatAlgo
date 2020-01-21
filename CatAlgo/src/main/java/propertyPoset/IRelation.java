package propertyPoset;

import java.util.Set;

import utils.IImplication;

/**
 * A IRelation is a binary relation on a set of properties. It is endowed with some additional 
 * functionalities, especially methods that can return some special elements of the 
 * set (such as 'dimensions', 'local roots', 'local atoms'). 
 * 
 * @author Gael Tregouet
 *
 */
public interface IRelation {

	/**
	 * After an implication (or more) has been added, most methods throw exceptions if 
	 * 'updateSpecialElementSets()' hasn't been called afterwards. 
	 * @param implication is a pair of properties (A,B) ; A being the 'antecedent' and B the 'consequent'. 
	 * A binary relation is defined by its set of implications.  
	 */
	void addImplication(IImplication implication);
	
	/**
	 * 
	 * @param propName the name of a property.
	 * @return the names of all the greater properties. 
	 */
	Set<String> getGreaterProperties(String propName);
	
	/**
	 * 
	 * @param propName the name of a property.
	 * @return the names of all the lesser properties. 
	 */
	Set<String> getLesserProperties(String propName);
	
	/**
	 * 
	 * @param propName the name of a property.
	 * @return the names of all the properties that immediately succeed the one whose name has been given in parameter. 
	 */
	Set<String> getSuccProperties(String propName);
	
	/**
	 * 
	 * @param propName the name of a property.
	 * @return the names of all the properties that immediately precede the one whose name has been given in parameter. 
	 */
	Set<String> getPrecProperties(String propName);
	
	/**
	 * The rank of a property is the maximal length of a spanning chain bounded by the root of the (lower semi-lattice) 
	 * poset and this property. 
	 * @param propName the name of a property
	 * @return the rank of the property whose name has been given in parameter.
	 */
	int getRank(String propName);
	
	/**
	 * 
	 * @param subSet a set of properties.
	 * @return a relation that contains any implication of the current relation, provided this implication's 
	 * antecedent and consequent are both included in the subset given in parameter. 
	 */
	IRelation getSubrelation(IPropertySet subSet);
	
	/**
	 * A 'dimension' is a sup-reducible element of the property poset. 
	 * @param propName
	 * @return true if the property whose name was given in parameter is a dimension, false otherwise. 
	 */
	boolean checkIfDimension(String propName);
	
	/**
	 * A 'local root' is the infimum of the immediate predecessors of (at least) one dimension.
	 * @param propName
	 * @return true if the property whose name was given in parameter is a local root, false otherwise. 
	 */
	boolean checkIfLocalRoot(String propName);
	
	/**
	 * A 'local atom' is a successor of a local root.
	 * @param propName
	 * @return true if the property whose name was given in parameter is a local atom, false otherwise. 
	 */
	boolean checkIfLocalAtom(String propName);	
	
	/**
	 * To avoid errors, the property whose name is given in parameter must return 'true' if its method 
	 * checkIfDimension() is called. 
	 * @param dimension
	 * @return the local root of a the dimension whose name has been given in parameter.
	 */
	String getLocalRoot(String dimension);
	
	/**
	 * 
	 * @return the name of the poset 'root', or unique minimal element. Since it is generated from a syntax
	 * tree, the poset has to be a lower semi-lattice.
	 */
	String getPosetRoot();
	
	/**
	 * 
	 * @return the names of the poset 'leaves', or maximal elements. Since the poset in not necessarily an 
	 * upper semi-lattice, there can be many leaves. 
	 */
	Set<String> getPosetleaves();
	
	/**
	 * After an implication (or more) has been removed, most methods throw exceptions if
	 * 'updateSpecialElementSets()' hasn't been called afterwards. <br> 
	 * The only reason why a property should be removed is because it has only one predecessor, and this 
	 * predecessor is not the root of the poset. It is then identified as a 'superfluous' property.   
	 * @see IProperty
	 * @see IPropertyPoset
	 * @param propertyName the name of the property to be removed. 
	 * @return true if the property has actually been removed. 
	 */
	boolean removeProperty(String propertyName);
	
	/**
	 * Makes sure that all data are up to date by recalculating them. Should be called after one (or more) 
	 * implication has been added or removed.
	 */
	void updateSpecialElementSets();
	
}
