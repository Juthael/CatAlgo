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
public interface ISymbol extends Comparable<ISymbol> {
	
	//getters
	
	/**
	 * Calls the {@link String#compareTo(String)} method on the symbol's name, with the other symbol's name in argument. 
	 * @return 0 if the symbol name is equal to this name ; a value less than 0 if this symbol's name is lexicographically 
	 * less than the specified symbol's name ; and a value grater than 0 otherwise
	 */
	@Override
	int compareTo(ISymbol anotherSymbol);	
	
	@Override
	boolean equals(Object o);
	
	/**
	 * If the symbol is an arbitrary symbol (and not an operator); its cost always equals 0.
	 * @return the cost of the symbol
	 * @see representation.stateMachine.IOperator
	 */
	int getCost();
	
	@Override
	int hashCode();
	
	/**
	 * 
	 * @return the symbol as a String
	 */
	String toString();	

}
