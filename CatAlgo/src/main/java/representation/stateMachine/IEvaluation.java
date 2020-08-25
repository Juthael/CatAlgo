package representation.stateMachine;

/**
 * An evaluation is a detailed report on a single step in the evaluation process of a word entered in a finite state machine.
 * All evaluations are kept in the evaluation log. 
 * 
 * @see representation.stateMachine.IStateMachine
 * @see representation.stateMachine.IWord
 * @see representation.stateMachine.IEvaluationLog
 * 
 * @author Gael Tregouet
 *
 */
public interface IEvaluation {
	
	/**
	 * 
	 * @return the informations available via other methods displayed in a single String
	 */
	String toString();

}
