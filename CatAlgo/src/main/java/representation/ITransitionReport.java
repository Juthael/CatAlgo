package representation;

/**
 * A transition report keeps track of a transition that has occurred when a state has evaluated a given symbol of 
 * a given word. Transition reports are recorded in the evaluation log.
 * 
 * @see representation.IState
 * @see representation.ISymbol
 * @see representation.IWord
 * @see representation.IEvaluationLog
 * 
 * @author Gael Tregouet
 *
 */
public interface ITransitionReport extends IEvaluation {
	
	/**
	 * 
	 * @return the ID of the input {@link IState}
	 */
	int getInputStateID();
	
	/**
	 * 
	 * @return the evaluated {@link ISymbol}
	 */
	ISymbol getSymbol();
	
	/**
	 * 
	 * @return the ID of the output {@link IState}
	 */
	int getOutputStateID();
	
	/**
	 * 
	 * @return the informations available via other methods displayed in a single String
	 */
	String toString();

}
