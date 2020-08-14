package representation;

/**
 * An evaluation is a detailed report on a single step in the evaluation process of a word entered in a finite state machine.
 * All evaluations are kept in the evaluation log. 
 * 
 * @see representation.IStateMachine
 * @see representation.IWord
 * @see representation.IEvaluationLog
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
