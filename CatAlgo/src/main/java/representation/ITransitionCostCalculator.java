package representation;

/**
 * A ITransitionCostCalculator calculates the cost of a transition between two states of a state machine. <br>
 * 
 * This cost reflects the quantity of information required to proceed the transition. Its calculation is based on the 
 * symbol read by the input state (the symbol have a cost itself), and on the probability of the transition to happen 
 * (this probability depends on the language of the machine).  <br>
 * 
 * Transitions between states of a categorical state machine ( {@link ICategory} ) are always 'free', since they use an 
 * arbitrary 'free' symbol ( {@link ISymbol} ). One the opposite, transitions in a representational state machine 
 * ( {@link IRepresentation} ) have a non-zero cost, because their input symbols can also be read as procedures 
 * ( {@link IOperator} ) and therefore are not free.
 * 
 *  @see representation.IStateMachine
 *  @see representation.IState
 *  @see representation.ITransition
 *  @see representation.ISymbol
 *  @see representation.ICategory
 *  @see representation.IRepresentation
 *  @see representation.IOperator
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
	float calculateCost(ISymbol symbol, float frequency);

}
