package propertyPoset;

import java.util.Set;

import grammarModel.structure.ISyntaxGrove;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;
import propertyPoset.utils.impl.DimensionAnalysis;

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
	 * Modifies the 'relation' map by removing some elements from the set of predecessors of a specified 
	 * property, and then modifying the relation in order to maintain its transitivity. <br> 
	 * 
	 * A property 'p' is then included in the set of consequents of a property 'q' iff q=p or 
	 * if at least one of the specified new predecessors is a consequent of q.
	 * 
	 * @param targetProperty the property for which new predecessors are specified
	 * @param newPredecessors the new predecessors
	 * @throws PropertyPosetException 
	 */
	public void modifyRelation(String targetProperty, Set<String> newPredecessors) throws PropertyPosetException;
	
	/**
	 * Adds a new property in the relation, specifying some existent properties as its predecessors and some other 
	 * as its consequents. 
	 * Modifies the relation in order to maintain transitivity.  
	 * @param newProperty
	 * @param predecessors
	 * @throws PropertyPosetException 
	 */
	public void addNewProperty(String newProperty, Set<String> predecessors, Set<String> consequents) 
			throws PropertyPosetException;
	
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
	 * In order to build the set of {@link DimensionAnalysis}, calculates how many dimension instances are necessary 
	 * to make sure that every dimension has independent values. <br>
	 * To do so, this method calculate the set of values of a dimension and checks the intersection between two values
	 * is always an empty set. If this is not the case, the properties in that intersection are set aside and the values 
	 * of the current instance are recalculated without them. Then the remaining properties are processed to build an 
	 * additional instance of the dimension, and so on until all properties have been attributed to an instance.    
	 * @return a {@link DimensionAnalysis} for every sup-reducible element of the poset.
	 * @throws PropertyPosetException
	 */
	Set<DimensionAnalysis> getDimensionAnalyzes() throws PropertyPosetException;
	
	/**
	 * Ensures that all data is up to date. Must be called after any modification.
	 * @throws PropertyPosetException 
	 */	
	void updateRelationData() throws PropertyPosetException;
	
}
