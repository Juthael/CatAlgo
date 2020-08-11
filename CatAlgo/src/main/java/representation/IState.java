package representation;

import java.util.Iterator;
import java.util.Set;

/**
 * A state is an element of a state machine that is defined by its particular set of <i> rules </i>.
 * When a word is entered in a machine, its symbols are read one at a time. For each new symbol, a rule 
 * must be found in the machine current state to indicate, given that symbol, what state will be active 
 * at the next step ; if no rule can be found, then the machine stops. <br>
 * 
 * @see representation.IStateMachine
 * @see representation.ITransitionFunction
 * @see representation.ITransition
 * 
 * @author Gael Tregouet
 *
 */
public interface IState {
	
	/**
	 * 
	 * @return the state ID (a random int)
	 */
	int getID();
	
	/**
	 * The name of a state is unique and reflects its particular situation in the flow chart of the machine.
	 * 
	 * @return the name of the state
	 */
	IStateName getStateName();
	
	/**
	 * The rank of a state is the minimal number of transitions between this state and an accept state
	 * 
	 * @see representation.IAcceptState
	 * @see representation.ITransition
	 * @see representation.ITransitionFunction
	 * @return the state rank
	 */
	int getRank();
	
	/**
	 * The specifications are a data structure that describes the role of a particular state in the machine it 
	 * belongs to. <br>
	 * 
	 * It is composed of a set of "constraints". Its function is to provide the prerequisite to meet for any state or 
	 * value from another machine, if they are to be defined as eligible counterparts of this state in their own machine.
	 * 
	 * @see representation.IConstraint
	 * @see representation.IValue
	 * @see representation.IValueAttribution
	 * @return the state specifications
	 */
	ISpecifications getSpecifications();
	
	/**
	 * The set of rules (or "transitions") returned is the subset of the transition function composed of all elements
	 * in which the input state is this state.  
	 * 
	 * @see representation.ITransitionFunction
	 * @return the set or rules associated with this state
	 */
	Set<ITransition> getRules();
	
	/**
	 * The local grammar is the minimal knowledge base required to proceed the description of this state intent. <br>
	 * 
	 * @return the context-free grammar associated with this state
	 */
	IGrammar getLocalGrammar();
	
	/**
	 * 
	 * @param specifiedState any state
	 * @return true if the specified state's grammar equals this state's grammar ; false otherwise
	 */
	boolean hasSameGrammarAs(IState state);
	
	/**
	 * The specifications of a state describes its role in the machine it belongs to. If they are met by another 
	 * state in another machine, it means that those two states have an equivalent place in the flow chart 
	 * of their respective machines. <br>
	 * 
	 * Thus, they can be considered as counterparts in the comparison process of the two machines. <br>
	 * 
	 * Specifications are met when the specified state's own specifications extend this state's specifications. 
	 * It means that they can be considered as equal or more precise than this states' specifications (and in 
	 * no way inconsistent with them).   
	 * 
	 * @see representation.ISpecifications
	 * @see representation.IStateMachine
	 * @param state any state
	 * @return true if this state's specifications are extended by the specified state's specifications
	 */
	boolean specificationsAreMetBy(IState state);
	
	/**
	 * 
	 * @param symbolIterator an iterator on the word entered in the machine
	 * @return a report on the evaluation of the iterator next symbol
	 */
	IEvaluation evaluateSymbol(Iterator<ISymbol> symbolIterator);
	
	/**
	 * The name of a state is unique and reflects its particular situation in the flow chart of the machine. <br>
	 * 
	 * The name <i>N</i> of a state <i>q<sub>n</sub></i> is a non-empty set of symbol strings, such that a string  
	 * <i> S = {s<sub>1</sub>, ..., s<sub>x</sub>} </i> belongs to <i> N </i> iff it meets the following requirements : <br>
	 * 1-it can be mapped to a unique sequence of transitions in the transition function <i>δ</i> such that 
	 * <i> [δ(s<sub>1</sub>, q<sub>1</sub>) = q<sub>2</sub>, ...,δ(s<sub>x</sub>, q<sub>n-1</sub>) = q<sub>n</sub>] </i> <br>
	 * 2-no sub-string <i> S' = {s<sub>p</sub>, ..., s<sub>x</sub>} </i> of <i> S </i> can be found that meets the first 
	 * requirement.
	 * 
	 * @see representation.IStateName
	 * @param transitionFunction the transition function of the machine to which this state belongs
	 */
	void setName(ITransitionFunction transitionFunction);
	
	/**
	 * 
	 * @see representation.ITransition
	 * @param transitionFunction the transition function of the machine to which this states belongs
	 */
	void setRules(ITransitionFunction transitionFunction);
	
	/**
	 * 
	 * @param state any object
	 * @return true if the specified object is a state that meets this state's specifications and has the same 
	 * local grammar ; false otherwise
	 */
	@Override
	boolean equals(Object state);
	
	/**
	 * 
	 * @return this state hashCode
	 */
	@Override
	int hashCode();

}
