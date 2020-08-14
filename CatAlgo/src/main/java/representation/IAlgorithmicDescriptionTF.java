package representation;

/**
 * An algorithmic description transition function is a transition function such that, for any state in the machine, 
 * there exists only one sequence of transitions that leads to this state from the start state. <br>
 * 
 * @see representation.IStateMachine
 * @see representation.IState
 * @see representation.IStartState
 * @author Gael Tregouet
 *
 */
public interface IAlgorithmicDescriptionTF extends ITransitionFunction {
	
	/**
	 * 
	 * @return the sum of all the transitions' costs
	 */
	float getCost();
	
	/**
	 * For every transition, calculates the probability that it will occur while evaluating an "accept" word, 
	 * assuming that the current active state in the machine is the input state of this transition ; then calls the 
	 * ITransition.setProbability() method.
	 */
	void setTransitionProbabilities();
	
	/**
	 * 
	 * @return true if setTransitionProbabilities() has already been called. 
	 */
	boolean transitionsProbabilitiesAreSet();
}
