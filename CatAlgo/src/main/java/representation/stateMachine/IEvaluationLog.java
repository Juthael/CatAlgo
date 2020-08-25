package representation.stateMachine;

import java.util.Iterator;
import java.util.List;

/**
 * An IEvaluationLog keeps track of all transitions occurring during the evaluation of a word by a state machine. 
 * At each step, a state proceeds a symbol and reports on its activity in the log. 
 * 
 * @see representation.stateMachine.IWord
 * @see representation.stateMachine.IStateMachine
 * @see representation.stateMachine.IState
 * @see representation.stateMachine.ISymbol
 * @see representation.stateMachine.IEvaluation 
 * 
 * @author Gael Tregouet
 *
 */
public interface IEvaluationLog {
	
	/**
	 * 
	 * @return the list of evaluations
	 */
	List<IEvaluation> getEvaluations();
	
	/**
	 * 
	 * @return an iterator over the list of evaluations
	 */
	Iterator<IEvaluation> getEvaluationIterator();
	
	/**
	 * 
	 * @return true if the output {@link IState} recorded in the last {@link IEvaluation} is an {@link IAcceptState} ; 
	 * false otherwise
	 */
	boolean wordHasBeenAccepted();
	
	/**
	 * 
	 * @param evaluation reports on the proceeding of a new symbol
	 */
	void addEvaluation(IEvaluation evaluation);
	
	/**
	 * Can be called only by an {@link IAcceptState}.
	 */
	void setAsAccepted();

}
