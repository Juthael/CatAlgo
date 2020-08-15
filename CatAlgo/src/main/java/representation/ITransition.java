package representation;

/**
 * A transition is a component of the transition function of a state machine. It associates an input (which consists of a 
 * state and a symbol), to an output (which is a state). <br>
 * 
 * A transition may have a <i> cost </i>, if it occurs between states of a descriptive state machine 
 * ( {@link IAlgorithmicDescription} ). This cost reflects the quantity of information the transition requires. The 
 * formula for its calculation is encapsulated in a {@link ITransitionCostCalculator} and is based on the cost of the 
 * symbol itself, since this symbol can also be read as a procedure ( {@link IOperator} ) that contains a certain number of 
 * instructions. The formula also makes use of the probability of the transition to happen, provided that the active state 
 * of the machine is the input state of the transition, and that the evaluated word will be accepted.  
 * 
 * @see representation.ITransitionFunction
 * @see representation.IStateMachine
 * @see representation.IState
 * @see representation.IAlgorithmicDescription
 * @see representation.ISymbol
 * @see representation.IOperator
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
	 * This probability only makes sense for a transition between states of a descriptive state machine 
	 * ( {@link IAlgorithmicDescription} ). It is never used otherwise. 
	 * 
	 * @see representation.IAlgorithmicDescription
	 * @return the probability that this transition will occur while evaluating an "accept" word, provided that 
	 * the current active state in the machine is the input state of this transition  
	 */
	float getProbability();
	
	/**
	 * A transition is always free (cost = 0) if it doesn't occur between two states of a descriptive state 
	 * machine ( {@link IAlgorithmicDescription} ). <br> 
	 * 
	 * The reason for that is that the calculator formula makes use of the transition's input symbol cost, 
	 * and of the "amount of surprise" this transition represent. But a symbol is always free if it is not 
	 * an operator (as it is in a representational state machine only : {@link IRepresentation}, 
	 * {@link IAlgorithmicDescription}), and the probability of a transition to happen is arbitrarily set to 
	 * 1 (amount of surprise = -log<sub>2</sub>(1) = 0), except for transitions inside a descriptive machine. 
	 * <br> 
	 * 
	 * Consequently, there is no reason this method should be called, except for transitions inside an 
	 * algorithmic descriptive machine.  
	 * 
	 * @see representation.IAlgorithmicDescription
	 * @see representation.ITransitionCostCalculator
	 * @see representation.ISymbol
	 * @see representation.IOperator
	 * @see representation.IRepresentation
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
	 * This probability only makes sense for a transition between states of a descriptive state machine 
	 * ( {@link IAlgorithmicDescription} ) ; it is never used otherwise. If the probability is not set, 
	 * then it is equal to one. 
	 * 
	 * @see representation.IAlgorithmicDescription
	 * @param probability the probability that this transition will occur while evaluating an "accept" word, 
	 * provided that the current active state in the machine is the input state of this transition.
	 */
	void setProbability(float probability);

}
