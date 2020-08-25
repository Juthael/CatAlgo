package representation.stateMachine;

/**
 * A ISymbol is evaluated by a state of a state machine to determine if a transition can proceed. A list of symbols makes 
 * a word. 
 * @see representation.stateMachine.IState
 * @see representation.stateMachine.IStateMachine
 * @see representation.stateMachine.IWord 
 * @author Gael Tregouet
 *
 */
public interface ISymbol {
	
	/**
	 * If the symbol is an arbitrary symbol (and not an operator); its cost always equals 0.
	 * @return the cost of the symbol
	 * @see representation.stateMachine.IOperator
	 */
	int getCost();
	
	/**
	 * 
	 * @return the symbol as a String
	 */
	String toString();	

}
