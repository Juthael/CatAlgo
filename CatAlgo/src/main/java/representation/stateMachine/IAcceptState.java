package representation.stateMachine;

import java.util.Iterator;

/**
 * If an accept state is active in the machine after the last symbol of a word entered has been read, it means that this 
 * word belongs to the language associated with this machine. 
 * 
 * Because of the way IStateMachine instances are constructed (whether they implement {@link ICategory} or 
 * {@link IRepresentation} interface), no transition is ever allowed from an accept state.  
 * 
 * @see representation.stateMachine.IStateMachine
 * @see representation.stateMachine.IWord
 * @see representation.stateMachine.ISymbol
 * @author Gael Tregouet
 *
 */
public interface IAcceptState extends IState {
	
	/**
	 * 
	 * @param symbolIterator an iterator on the word evaluated by the machine 
	 * @return the closed report on the evaluation of the whole word, ending with an acceptance mark
	 */
	IEvaluationLog notifyAcceptanceInLog(Iterator<String> symbolIterator);

}
