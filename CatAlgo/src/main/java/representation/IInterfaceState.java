package representation;

/**
 * An interface state is a place-holder state, in a state machine that is partially undetermined. It is meant to be 
 * implemented by a value, i.e. a state or a block of state having the configuration of a sub-machine. The interface 
 * state imposes a set of constraints (its <i> specifications </i>), to which any state or sub-machine pretending to 
 * be an eligible implementing value must comply.   
 * 
 * @see representation.IStateMachine
 * @see representation.IValue
 * @see representation.ISpecifications
 * @see representation.IValueAttribution
 * @author Gael Tregouet
 *
 */
public interface IInterfaceState extends IState {
	
	/**
	 * 
	 * @param value any value
	 * @return true if the value is an eligible implementation of this interface state ; false otherwise
	 */
	boolean isImplementedBy(IValue value);

}
