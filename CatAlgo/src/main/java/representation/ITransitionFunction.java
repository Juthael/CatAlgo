package representation;

import java.util.Set;

/**
 * <p>
 * A ITransitionFunction determines which transition is allowed in a state machine when a given state reads a given 
 * symbol. Put differently : it specifies, for any state of the state machine, which rules are associated with it. 
 * <br>
 * </p>
 * 
 * <p>
 * The transition function consists in a set of transitions ( {@link ITransition} ). Each transition associates an 
 * input state and an input symbol with an output state. A transition function has a cost, reflecting the quantity 
 * of information it contains. This cost is the sum of all the transitions' costs.
 * </p> 
 * 
 * @see representation.ITransition
 * @see representation.IState
 * @see representation.IStateMachine
 * @see representation.ISymbol
 * 
 * @author Gael Tregouet
 *
 */
public interface ITransitionFunction {
	
	/**
	 * 
	 * @param inputState the state reading the symbol
	 * @param symbol the symbol read by the input state
	 * @return an output state if a transition is allowed ; <b> null </b> otherwise 
	 */
	ITransition getOutput(IState inputState, ISymbol symbol);
	
	/**
	 * 
	 * @param symbol any symbol
	 * @return the set of all the transitions with the specified symbol in input, regardless of the input state
	 */
	Set<ITransition> getTransitionsWithSymbol(ISymbol symbol);
	
	/**
	 * 
	 * @param stateName the name of a state
	 * @return the set of all the transitions with the specified state in input, regardless of the input symbol
	 */
	Set<ITransition> getTransitionsFromState(IStateName stateName);
	
	/**
	 * 
	 * @param stateName the name of a state
	 * @return the set of all the transitions to the specified state, regardless of the input
	 */
	Set<ITransition> getTransitionsToState(IStateName stateName);
	
	/**
	 * @see representation.IState
	 * @param stateID the state ID
	 * @return the name of the state
	 */
	IStateName getStateName(int stateID);
	
	/**
	 * @see representation.IState
	 * @see representation.ITransitionFunction
	 * @param stateName the name of the state
	 * @return the rules of the state, displayed as a subset of the transition function
	 */
	Set<ITransition> getStateRules(IStateName stateName);
	
	/**
	 * 
	 * @param stateName the name of the state
	 * @return the specifications of the state
	 */
	ISpecifications getStateSpecifications(IStateName stateName);
	
	/**
	 * Any transition function can be translated into a context-free grammar. <br>
	 * 
	 * If a transition exists from <i> q<sub>1</sub> </i> to <i> q<sub>2</sub> </i> with the symbol <i> s<sub>1</sub> </i>, 
	 * and from <i> q<sub>2</sub> </i> to <i> q<sub>3</sub> </i> with the symbol <i> s<sub>2</sub> </i>, then the grammar 
	 * associated with the transition function contains the rule <i> s<sub>1</sub> ::= s<sub>2</sub></i>.
	 * 
	 * @return the context-free grammar associated with this transition function 
	 */
	IGrammar getRestrictedGrammar();	

}
