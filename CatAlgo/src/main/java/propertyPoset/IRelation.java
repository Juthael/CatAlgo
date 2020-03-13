package propertyPoset;

import java.util.HashMap;
import java.util.Set;

import grammarModel.structure.ISyntaxGrove;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;

/**
 * A IRelation is a binary relation on a set of properties, endowed with some additional 
 * features to facilitate navigation within the set.  
 * 
 * @author Gael Tregouet
 *
 */
public interface IRelation {

	/**
	 * Modifies the 'relation' map to take into account the new implication. 
	 * 
	 * This method does NOT guarantees that the transitivity condition for a relation to be a partial order is 
	 * fulfilled : if the implication states that a property P implies a property Q, then it is not ensured 
	 * that every property implying P is also implying Q. This verification must therefore be taken care of by 
	 * the calling object.  <br> 
	 * @param implication is a pair of properties (A,B) ; A being the 'antecedent' and B the 'consequent'.   
	 * @throws PropertyPosetException 
	 */
	void addImplication(IImplication implication) throws PropertyPosetException;
	
	/**
	 * Modifies the 'relation' map to take into account the new implication while ensuring transitivity. 
	 * 
	 * This method guarantees that the transitivity condition for a relation to be a partial order is 
	 * fulfilled : if the implication states that a property P implies a property Q, then it is ensured 
	 * that every property implying P is also implying Q.
	 * @param implication is a pair of properties (A,B) ; A being the 'antecedent' and B the 'consequent'. 
	 * @throws PropertyPosetException
	 */
	public void addImplicationEnsureTransitivity(IImplication implication) throws PropertyPosetException;	
	
	/**
	 * Turns the property whose name has been given in parameter into a leaf, i.e. a property with no 
	 * consequent apart from itself. Supports the required modifications in order to maintain consistency 
	 * within the poset.    
	 * @param subContextRoot the name of a property
	 * @throws PropertyPosetException 
	 */
	public void setPropAsALeaf(String subContextRoot) throws PropertyPosetException;
	
	/**
	 * The set of consequents (or implied properties) of a property P is the set of properties equal to 
	 * or greater than P.  
	 * @param propName the name of a property
	 * @return the properties implied by the one whose name has been given in parameter
	 * @throws PropertyPosetException 
	 */
	Set<String> getConsequents(String propName) throws PropertyPosetException;
	
	/**
	 * The set of antecedents (or implying properties) of a property P is the set of properties equal to 
	 * or lesser than P.  
	 * @param propName the name of a property
	 * @return the properties implying the one whose name has been given in parameter
	 * @throws PropertyPosetException 
	 */
	Set<String> getAntecedents(String propName) throws PropertyPosetException;
	
	/**
	 * 
	 * @param propName the name of a property.
	 * @return the names of all the greater properties. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getGreaterProperties(String propName) throws PropertyPosetException;
	
	/**
	 * 
	 * @param propName the name of a property.
	 * @return the names of all the lesser properties. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getLesserProperties(String propName) throws PropertyPosetException;
	
	/**
	 * 
	 * @param propName the name of a property.
	 * @return the names of all the properties that immediately succeed the one whose name has been given 
	 * in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getSuccessors(String propName) throws PropertyPosetException;
	
	/**
	 * 
	 * @param propName the name of a property.
	 * @return the names of all the properties that immediately precede the one whose name has been given 
	 * in parameter
	 * @throws PropertyPosetException 
	 */
	Set<String> getPredecessors(String propName) throws PropertyPosetException;
	
	/**
	 * Since the poset of properties is a lower semi-lattice, any subset of properties has an infimum. 
	 * @param setOfProperties a set of property names 
	 * @return the (name of the) infimum of the set of properties given in parameter
	 * @throws PropertyPosetException
	 */
	String getInfimum(Set<String> properties) throws PropertyPosetException;
	
	/**
	 * The rank of a property is the maximal length of a spanning chain bounded by the root of the (lower 
	 * semi-lattice) poset and this property. 
	 * @param propName the name of a property
	 * @return the rank of the property whose name has been given in parameter.
	 * @throws PropertyPosetException 
	 */
	int getRank(String propName) throws PropertyPosetException;
	
	/**
	 * A dimension is a property that some elements of a context have in common, and which is also 
	 * used to express their differences, since there are many ways to have this property. 
	 * 
	 * A property d is a 'dimension' if : <br> 
	 * 1/ it has more than one predecessor (i.e., is sup-reducible). <br>
	 * 2/ if 'P' is the set of predecessors, 'r' its infimum ; for any property 'v' less than 'd' and 
	 * greater than 'r', there is no property 'p' that verifies (('p' < 'v') && ('p' not comparable 
	 * to 'r')). <br>
	 * @param propName the name of a property
	 * @return true if the property whose name was given in parameter is a dimension, false otherwise. 
	 * @throws PropertyPosetException 
	 */
	boolean checkIfDimension(String propName) throws PropertyPosetException;
	
	/**
	 * Informative properties are dimensions, dimension roots, and dimension values. <br>
	 * 
	 * A property d is a 'dimension' if :  <br>
	 * 1/ it has more than one predecessor (i.e., is sup-reducible).  <br>
	 * 2/ if 'P' is the set of predecessors, 'r' its infimum ; for any property 'q' less than 'd' and 
	 * greater than 'r', there is no property 'p' that verifies (('p' < 'q') && ('p' not comparable 
	 * to 'r')).  <br>
	 * 
	 * If 'd' is a dimension, then 'r' is its root. Let 'A' be the set of properties succeeding 'r' 
	 * and less than 'd' ; then a property 'v' is a value of 'd' iff there exists a subset 'X' of 'A' 
	 * such that 'v' is the supremum of 'A'. <br>
	 * @throws PropertyPosetException
	 */
	boolean checkIfInformativeProperty(String propName) throws PropertyPosetException;
	
	/**
	 * 
	 * @return the name of the poset 'root', or unique minimal element. Since it is supposed to be built
	 * from the {@link IPosetMaxChains} generated by a {@link ISyntaxGrove}, the poset has to be a lower 
	 * semi-lattice, and therefore have a minimum.
	 * @throws PropertyPosetException 
	 */
	String getPosetRoot() throws PropertyPosetException;	
	
	/**
	 * 
	 * @return the names of the poset 'leaves', or maximal elements. Since the poset in not necessarily an 
	 * upper semi-lattice, there can be many leaves. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getPosetleaves() throws PropertyPosetException;
	
	/**
	 * 
	 * @return the maximal rank value among all the poset elements. 
	 * @throws PropertyPosetException 
	 */
	int getMaximalRank() throws PropertyPosetException;
	
	/**
	 * After this method has been called, the name of the property given in parameter can neither be found in the 
	 * relation as an antecedent, nor as a consequent of any other property. 
	 * 
	 * Warning : if an non-maximal element of the poset is removed and any element of its upper set is not, then 
	 * the poset may not be a lower semi-lattice anymore, which can lead to inconsistent behavior. 
	 * 
	 * @see IProperty
	 * @see IPropertyPoset
	 * @param property the element to remove 
	 * @return true if the property has actually been removed. 
	 * @throws PropertyPosetException if the parameter is unknown or if it is an informative property. 
	 */
	boolean removeProperty(IProperty property) throws PropertyPosetException;
	
	 /**
	 * This method guarantees values of a dimension are independent, i.e. their intersection is empty. It also identifies 
	 * informative elements of the poset and populates the dedicated field.<br>
	 * 
	 * A dimension is a sup-reducible element of a poset. A value 'v' of a dimension is defined as follows : <br> 
	 * Let 'V' be the set of the dimension predecessors. A value 'v' can be : <br>
	 * 1/ a subset of 'V', such that it is the intersection of V with the set of consequents of (at least) one atom 'a'. 
	 * 2/ the infimum of such a subset.
	 * 
	 * @return a map of properties with the property they must encapsulate. Must be proceeded by {@link IPropertySet}
	 * @throws PropertyPosetException
	 */
	HashMap<String, String> setDimensionsAndMakeValuesIndependent() throws PropertyPosetException;
	
	/**
	 * Ensures that all data is up to date. Must be called after any modification.
	 * @throws PropertyPosetException 
	 */	
	void updateRelationData() throws PropertyPosetException;
	
}
