package representation.stateMachine;

import java.util.Set;

/**
 * <p>
 * An operator attributes values to dimensions of a category. <br>
 * </p>
 * 
 * <p>
 * An operator can be described on two different levels : <br>
 * -it is a symbol on which a state of a <i> representational state machine </i> can operate ( {@link IRepresentation} ). <br> 
 * -it is also a list of value attributions, specifying which informations must be provided on a given category to get the 
 * definition of a given sub-category. <br>
 * </p>
 * 
 *  <p>
 * Categories are implemented as states of a representational state machine ( {@link IRepresentation} ), and are themselves 
 * state machines ( {@link ICategory} ). Just as usual categories are commonly described in terms of their dimensions, a 
 * <i> categorical state machine </i> contains <i> interfaces </i> ( {@link IInterfaceState}), i.e place-holder states 
 * waiting to be replaced by a value and imposing constraints on what such a value can be. 
 * Value themselves are implemented as state (sub-)machines. <br>
 * Each value attribution ( {@link IValueAttribution} ) of an IOperator maps a dimension to a value.
 * </p> 
 *   
 * @see representation.stateMachine.ISymbol
 * @see representation.stateMachine.IState
 * @see representation.stateMachine.IStateMachine
 * @see representation.stateMachine.IRepresentation
 * @see representation.stateMachine.ICategory
 * @see representation.stateMachine.IInterfaceState
 * @see representation.stateMachine.IValueAttribution    
 * 
 * @author Gael Tregouet
 *
 */
public interface IOperator extends ISymbol {
	
	/**
	 * @return the number of value attributions plus the cost of all attributed values
	 * @see representation.stateMachine.IValueAttribution
	 * @see representation.stateMachine.IStateMachine
	 */
	@Override
	int getCost();
	
	/**
	 * 
	 * @return the set of value attributions
	 */
	Set<IValueAttribution> getValueAttributions();

}