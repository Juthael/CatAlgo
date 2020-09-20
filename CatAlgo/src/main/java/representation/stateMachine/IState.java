package representation.stateMachine;

import java.util.Iterator;
import java.util.Set;

import representation.dataFormats.IGrammar;

/**
 * <p>
 * A <b>state</b> is an element of a state machine that is defined by its particular set of <i> rules </i>.
 * When a word is entered in a machine, its symbols are read one at a time. For each new symbol, a rule 
 * must be found in the machine current state to indicate, given that symbol, what state will be active 
 * at the next step ; if no rule can be found, then the machine stops. <br>
 * </p>
 * 
 * @see representation.stateMachine.IStateMachine
 * @see representation.stateMachine.ITransitionFunction
 * @see representation.stateMachine.ITransition
 * 
 * @author Gael Tregouet
 *
 */
public interface IState {
	
	/**
	 * Returns the state ID. 
	 * @return the state ID (a random integer)
	 */
	int getID();
	
	/**
	 * <p>
	 * Returns the state's name. <br>
	 * </p>
	 * 
	 * <p>
	 * The name of a state is unique and reflects its particular situation in the flow chart of the machine. <br>
	 * </p>
	 * 
	 * @return the name of the state
	 */
	IStateName getStateName();
	
	/**
	 * <p>
	 * Returns the state's rank. <br>
	 * </p> 
	 * 
	 * <p>
	 * The rank of a state is the minimal number of transitions between this state and an accept state. <br>
	 * </p>
	 * 
	 * @see representation.stateMachine.IAcceptState
	 * @see representation.stateMachine.ITransition
	 * @see representation.stateMachine.ITransitionFunction
	 * @return the state rank
	 */
	int getRank();
	
	/**
	 * <p>
	 * Returns the specifications of this state. <br>
	 * </p>
	 * 
	 * <p>
	 * The specifications are a data structure that describes the role of a particular state in the machine it 
	 * belongs to. <br>
	 * </p>
	 * 
	 * <p>
	 * It is composed of a set of "constraints". Its function is to provide the prerequisite to meet for any state or 
	 * value from another machine, if they are to be defined as eligible counterparts of this state in their own machine.
	 * </p>
	 * 
	 * @see representation.stateMachine.IConstraint
	 * @see representation.stateMachine.IValue
	 * @see representation.stateMachine.IValueAttribution
	 * @return the state specifications
	 */
	ISpecifications getSpecifications();
	
	/**
	 * <p>
	 * Returns the set of rules associated with this state. <br>
	 * </p>
	 * 
	 * <p>
	 * The set of rules (or "transitions") returned is the subset of the transition function composed of all elements
	 * in which the input state is this state. <br>
	 * </p>  
	 * 
	 * @see representation.stateMachine.ITransitionFunction
	 * @return the set or rules associated with this state
	 */
	Set<ITransition> getRules();
	
	/**
	 * <p>
	 * Returns the context-free grammar associated with this state. <br>
	 * </p>
	 * 
	 * <p>
	 * The local grammar maps every symbol that allows a transition to this state with every symbol that allows a 
	 * transition from this state. <br>
	 * </p>
	 * 
	 * @return the context-free grammar associated with this state
	 */
	IGrammar getLocalGrammar();
	
	/**
	 * Checks if the specified state has the same local grammar than this state. 
	 * 
	 * @param specifiedState any state
	 * @return true if the specified state's grammar equals this state's grammar ; false otherwise
	 */
	boolean hasSameGrammarAs(IState state);
	
	/**
	 * <p>
	 * Checks if the specified state meets this state's specifications. <br>
	 * </p>
	 * 
	 * <p>
	 * The specifications of a state describe its role in the machine it belongs to. If they are met by another 
	 * state in another machine, it means that those two states have an equivalent place in the flow chart 
	 * of their respective machines. Thus, they can be considered as counterparts. <br>
	 * </p>
	 * 
	 * <p>
	 * Specifications are met when the specified state's own specifications extend this state's specifications. 
	 * It means that they can be considered as equal or more precise than this states' specifications (and in 
	 * no way inconsistent with them). <br>
	 * </p>   
	 * 
	 * @see representation.stateMachine.ISpecifications
	 * @see representation.stateMachine.IStateMachine
	 * @see representation.stateMachine.utils.ICategoryTransitionBuilder
	 * @param state any state
	 * @return true if this state's specifications are extended by the specified state's specifications
	 */
	boolean specificationsAreMetBy(IState state);
	
	/**
	 * Returns a report on the evaluation of the specified symbol by this state. 
	 * 
	 * @param symbolIterator an iterator on the word entered in the machine
	 * @return a report on the evaluation of the iterator next symbol
	 */
	IEvaluation evaluateSymbol(Iterator<ISymbol> symbolIterator);
	
	/**
	 * <p>
	 * Sets the name of this state. <br> 
	 * </p>
	 * 
	 * <p>
	 * The name of a state is unique and reflects its particular situation in the flow chart of the machine. <br>
	 * </p>
	 * 
	 * <p>
	 * The name <i>N</i> of a state <i>q<sub>n</sub></i> is a non-empty set of symbol strings, such that a string  
	 * <i> S = {s<sub>1</sub>, ..., s<sub>x</sub>} </i> belongs to <i> N </i> iff it meets the following requirements : <br>
	 * 1-it can be mapped to a unique sequence of transitions in the transition function <i>δ</i> such that 
	 * <i> [δ(s<sub>1</sub>, q<sub>1</sub>) = q<sub>2</sub>, ...,δ(s<sub>x</sub>, q<sub>n-1</sub>) = q<sub>n</sub>] </i> <br>
	 * 2-no sub-string <i> S' = {s<sub>p</sub>, ..., s<sub>x</sub>} </i> of <i> S </i> can be found that meets the first 
	 * requirement. <br>
	 * </p>
	 * 
	 * @see representation.stateMachine.IStateName
	 * @param transitionFunction the transition function of the machine to which this state belongs
	 */
	void setName(ITransitionFunction transitionFunction);
	
	/**
	 * <p>
	 * Sets the set of rules associated with this state. <br>
	 * </p>
	 * 
	 * <p>
	 * This set of rules (or <i>transitions</i>) is the subset of the specified transition function composed 
	 * of all transitions in which the input state is this state. <br>
	 * </p>  
	 * 
	 * @see representation.stateMachine.ITransition
	 * @param transitionFunction the transition function of the machine to which this states belongs
	 */
	void setRules(ITransitionFunction transitionFunction);
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 * 
	 * @return true if the specified object is a state that meets this state's specifications and has the same 
	 * local grammar ; false otherwise
	 */
	@Override
	boolean equals(Object state);
	
	@Override
	int hashCode();

}
