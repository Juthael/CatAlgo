package propertyPoset;

import java.util.Set;

import grammarModel.structure.ISyntaxGrove;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;

/**
 * A IRelation is a binary relation on a set of properties. It is endowed with some additional 
 * functionalities, especially methods that can return some special elements of the 
 * set (such as 'dimensions', 'dimension roots', 'dimension atoms'). 
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
	 * Modifies the 'relation' map to take into account the new implication. 
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
	 * consequent apart from itself.   
	 * @param subContextRoot the name of a property
	 */
	public void setPropAsALeaf(String subContextRoot);	
	
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
	 * in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getPredecessors(String propName) throws PropertyPosetException;
	
	/**
	 * The rank of a property is the maximal length of a spanning chain bounded by the root of the (lower 
	 * semi-lattice) poset and this property. 
	 * @param propName the name of a property
	 * @return the rank of the property whose name has been given in parameter.
	 * @throws PropertyPosetException 
	 */
	int getRank(String propName) throws PropertyPosetException;
	
	/**
	 * A 'dimension' is a sup-reducible element of the property poset. 
	 * @param propName the name of a property
	 * @return true if the property whose name was given in parameter is a dimension, false otherwise. 
	 * @throws PropertyPosetException 
	 */
	boolean checkIfDimension(String propName) throws PropertyPosetException;
	
	/**
	 * A 'dimension root' is the infimum of the immediate predecessors of at least one dimension.
	 * A 'dimension' is a sup-reducible element of the property poset.
	 * @param propName the name of a property
	 * @return true if the property whose name was given in parameter is a dimension root, false otherwise. 
	 * @throws PropertyPosetException 
	 */
	boolean checkIfDimensionRoot(String propName) throws PropertyPosetException;
	
	/**
	 * A 'dimension atom' is a successor of a dimension root.
	 * @param propName the name of a property
	 * @return true if the property whose name was given in parameter is a dimension atom, false otherwise. 
	 * @throws PropertyPosetException 
	 */
	boolean checkIfDimensionAtom(String propName) throws PropertyPosetException;	
	
	/**
	 * 
	 * @return the name of the poset 'root', or unique minimal element. Since it is supposed to be built
	 * from the {@link IPosetMaxChains} generated by a {@link ISyntaxGrove}, the poset has to be a lower 
	 * semi-lattice, and therefore have a minimum.
	 * @throws PropertyPosetException 
	 */
	String getPosetRoot() throws PropertyPosetException;	
	
	/**
	 * A 'dimension root' is the infimum of the immediate predecessors of at least one dimension.
	 * A 'dimension' is a sup-reducible element of the property poset.
	 * @param dimension the name of a 'dimension' property
	 * @return the dimension root of a the dimension whose name has been given in parameter.
	 * @throws PropertyPosetException 
	 */
	String getDimensionRoot(String dimension) throws PropertyPosetException;	
	
	/**
	 * In the set of 'dimension roots minus the poset root', a sub-context root is an minimal element. 
	 * Every dimension (i.e., sup-reducible element) has a dimension root, defined as the infimum of its 
	 * predecessors. A dimension root can be associated with many dimensions. The poset root may or 
	 * may not be a dimension root. 
	 * @return the (possibly empty) set of sub-context roots. 
	 * @throws PropertyPosetException
	 */
	Set<String> getSubContextRoots() throws PropertyPosetException;	
	
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
	 * After this method has proceeded, the name of the property given in parameter can neither be found in the 
	 * relation as an antecedent, nor as a consequent of any other property. 
	 * However, a {@link IProperty} object can be protected from removal (because it is the root of a 
	 * {@link IPropertyPoset} instance's subcontext). In this case, nothing happens.    
	 * @see IProperty
	 * @see IPropertyPoset
	 * @param property the element to remove 
	 * @return true if the property has actually been removed. 
	 * @throws PropertyPosetException 
	 */
	boolean removeProperty(IProperty property) throws PropertyPosetException;
	
	/**
	 * Makes sure that all data are up to date by recalculating them. Must be called after one (or more) 
	 * implication has been added or removed.
	 * @throws PropertyPosetException 
	 */	
	void updateRelationData() throws PropertyPosetException;
	
}
