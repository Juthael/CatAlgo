package representation;

/**
 * A IValueAttribution is the attribution of a value to a dimension of a category. <br>
 * 
 * Categories are implemented as states of a representational state machine ( {@link IRepresentation} ), and are themselves 
 * state machines ( {@link ICategory} ). Just as usual categories are commonly described in terms of their dimensions, a 
 * <i> categorical state machine </i> contains <i> interfaces </i> ( {@link IInterfaceState}), i.e place-holder states 
 * waiting to be replaced by a value and imposing constraints on what such a value can be. 
 * Value themselves are implemented as state (sub-)machines. <br>
 * A IValueAttribution maps a dimension to a value.
 * 
 * @see representation.ISymbol
 * @see representation.IState
 * @see representation.IStateMachine
 * @see representation.IRepresentation
 * @see representation.ICategory
 * @see representation.IInterfaceState
 * @see representation.IOperator
 * 
 * @author Gael Tregouet
 *
 */
public interface IValueAttribution {
	
	/**
	 * 
	 * @return the dimension (i.e. interface state) which is given a value
	 */
	IInterfaceState getInterface();
	
	/**
	 * 
	 * @return the value (i.e. state machine) given to the dimension
	 */
	IStateMachine getValue();
	
	/**
	 * 
	 * @return a report on the value attribution displayed in a single String
	 */
	String toString();

}
