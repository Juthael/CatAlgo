package representation.stateMachine;

import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;

/**
 * In a representation, objects are the smallest categories in terms of their extent : they apply to only 
 * one element in the context. <br> 
 * 
 * They also are the greatest elements in terms of their extent, since they can't be further specified 
 * by any sub-category ; thus, no transition is allowed from object states, which are the accept states 
 * of a representational machine. <br>
 * 
 * @see representation.stateMachine.IAcceptState
 * @author Gael Tregouet
 *
 */
public interface IContextObject extends ICategory, IAcceptState {
	
	/**
	 * Verifies that the object has the specified property, expressed as a functional expression. <br>
	 * 
	 * As any description ( {@link IDescription} ), a functional expression can be converted into any of
	 * the other description formats. The binary relation format ( {@link IBinaryRelation} ) is particularly 
	 * useful, because categories (and therefore, objects), can also be described as binary relations. So to 
	 * find out whether a certain object has a certain property, one just has to verify that the relation 
	 * associated with the property is actually as subset of the relation associated with the object.
	 * 
	 * @param specifiedProperty the property to be sought in the object, expressed as a functional expression
	 * @return true if the specified property has been found ; false otherwise
	 */
	boolean conformsTo(IFunctionalExpression specifiedProperty);

}
