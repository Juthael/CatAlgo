package representation;

/**
 * A IValue is a (sub-)state machine that can be given as an implementation of an 'interface' state in a categorical state 
 * machine ( {@link ICategory} ). This operation constitutes a <i> value attribution </i>. 
 * 
 * @see representation.IStateMachine
 * @see representation.IInterfaceState
 * @see representation.ICategory
 * @see representation.IValueAttribution
 * 
 * @author Gael Tregouet
 *
 */
public interface IValue extends IStateMachine {

}
