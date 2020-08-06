package representation;

import java.util.Set;

/**
 * A IOperator can be described on two different levels : <br>
 * -it is a symbol on which a state of a <i> representation state machine </i> can operate. <br> 
 * -it is also a list of value attributions, specifying which informations must be provided on a given category to get the 
 * definition of a given sub-category (categories are implemented as states of a representation state machine, and values 
 * as 'ordinary' state machines).
 * @see representation.ISymbol
 * @see representation.IState
 * @see representation.IStateMachine
 * @see representation.IRepresentation
 * @see representation.ICategory
 * @see representation.IValueAttribution    
 * 
 * @author Gael Tregouet
 *
 */
public interface IOperator extends ISymbol {
	
	/**
	 * @return the number of value attributions plus the cost of all attributed values (implemented as 'ordinary' state machines)
	 * @see representation.IValueAttribution
	 * @see representation.IStateMachine
	 */
	@Override
	int getCost();
	
	/**
	 * 
	 * @return the set of value attributions
	 */
	Set<IValueAttribution> getValueAttributions();

}
