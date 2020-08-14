package representation;

/**
 * A transition cost calculator is a formula that is used to determine the cost of a transition between two states of 
 * a descriptive state machine ( {@link IAlgorithmicDescription} ). <br>
 * 
 * This cost reflects the quantity of information required to proceed the transition. Its calculation is based on the 
 * cost of the symbol itself, since this symbol can also be read as a procedure ( {@link IOperator} ) that contains 
 * a certain number of instructions. The formula also makes use of the probability of the transition to happen, provided 
 * that the active state of the machine is the input state of the transition and that the evaluated word will be 
 * accepted.
 * 
 *  @see representation.IAlgorithmicDescription
 *  @see representation.IStateMachine
 *  @see representation.IState
 *  @see representation.ITransition
 *  @see representation.ISymbol
 *  @see representation.IOperator
 *  @see representation.IWord
 * 
 * @author Gael Tregouet
 *
 */
public interface ITransitionCostCalculator {
	
	/**
	 * 
	 * @param symbol the input symbol
	 * @param frequency the probability of the transition to happen during the evaluation of an accepted word.
	 * @return the cost of the transition
	 */
	float calculateTransitionCost(ISymbol symbol, float probability);

}
