package representation;

/**
 * A ITransition is a component of the transition function of a state machine. It associates an input (which consists of a 
 * state and a symbol), to an output (which is a state). <br>
 * 
 * A transition has a <i> cost </i>, reflecting the quantity of information it requires. The formula for the calculation of 
 * this cost is encapsulated in a {@link ITransitionCostCalculator}. It is based on the cost of the symbol itself, and 
 * possibly on the probability of the transition to happen while evaluating an 'accept' word. 
 * 
 * @see representation.ITransitionFunction
 * @see representation.IStateMachine
 * @see representation.IState
 * @see representation.ISymbol
 * @see representation.ITransitionCostCalculator
 * @see representation.IWord 
 * 
 * @author Gael Tregouet
 *
 */
public interface ITransition {
	
	/**
	 * 
	 * @param inputState a given state
	 * @param symbol a given symbol
	 * @return true if the specified parameters match the input elements of this transition ; false otherwise
	 */
	boolean matchInput(IState inputState, ISymbol symbol);
	
	/**
	 * 
	 * @return the input state of this transition
	 */
	IState getInputState();
	
	/**
	 * 
	 * @return the output state of this transition
	 */
	IState getOutputState();
	
	/**
	 * 
	 * @return the symbol read by the input state
	 */
	ISymbol getSymbol();
	
	/**
	 * 
	 * @return the probability that this transition will occur while evaluating an "accept" word, assuming that 
	 * the current active state in the machine is the input state of this transition  
	 */
	float getProbability();
	
	/**
	 * 
	 * @param calculator an implementation of the cost calculation formula
	 * @return the cost of this transition
	 */
	float getCost(ITransitionCostCalculator calculator);
	
	/**
	 * 
	 * @return a report on this transition displayed in a single String
	 */
	String toString();
	
	/**
	 * 
	 * @param probability the probability that this transition will occur while evaluating an "accept" word, 
	 * assuming that the current active state in the machine is the input state of this transition.
	 */
	void setProbability(float probability);

}
