package representation.stateMachine;

import java.util.Set;

import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.ILanguage;

/**
 * <p> 
 * A state machine is a finite-state automaton if it is a categorical state machine ( {@link ICategory} ), and a finite-state
 * automaton with some additional features if it is a representational state machine ( {@link IRepresentation} ). <br>
 * </p> 
 * 
 * <p>
 * A finite-state automaton (or FSA) is an abstract machine that can be in exactly one of a finite number of <i> states </i> 
 * at any given time. The FSA is defined by a list of its states, its initial state, its accept states, and the inputs that 
 * trigger each transition.
 * </p>
 * 
 * <p>
 * Every state machine has a <i> language </i>, and can be described by specifying this language. It is composed of all words 
 * (i.e., string of symbols) that, when entered into the machine, result in a sequence of transitions ending with an accept 
 * state. 
 * </p>
 * 
 * <p> Every state also has an ID, which is a random integer, and a name, which is a designation of this state in a more 
 * human-like way. <br>
 * 
 * @see representation.stateMachine.ICategory
 * @see representation.stateMachine.IRepresentation
 * @see representation.stateMachine.IState
 * @see representation.stateMachine.IStartState
 * @see representation.stateMachine.IAcceptState
 * @see representation.stateMachine.ITransition
 * @see representation.dataFormats.ILanguage
 * @see representation.stateMachine.IWord
 * @see representation.stateMachine.ISymbol
 * @see representation.stateMachine.IStateName
 * @author Gael Tregouet
 *
 */
public interface IStateMachine {
	
	/**
	 * 
	 * @return the states of the machine
	 */
	Set<IState> getStates();
	
	/**
	 * A transition function accepts as an input a state and a symbol, and returns a state as an output. 
	 * By doing so, it determines which transition is allowed in the machine when a given state reads a given 
	 * symbol.
	 * @see representation.stateMachine.ITransition
	 * @see representation.stateMachine.IState
	 * @see representation.stateMachine.ISymbol
	 * @return the transition function of the machine
	 */
	ITransitionFunction ITransitionFunction();
	
	/**
	 * The language of a machine is composed of all the words it <i> accepts </i>. A word is accepted by a machine 
	 * when its input into the machine results in a sequence of state transitions that leads to an "accept" state. 
	 * @see representation.stateMachine.IAcceptState
	 * @return the language of the machine
	 */
	ILanguage getLanguage();
	
	/**
	 * Since every state machine can be defined by its language, and every language can be condensed in a single functional 
	 * expression, then every machine can be defined by a functional expression. <br>
	 * @see representation.dataFormats.ILanguage
	 * @return a description of the machine in the form of a functional expression
	 */
	IFunctionalExpression getFunctionalExpression();
	
	/**
	 * Since every state machine can be defined by its language, and every language coded in a binary relation, 
	 * then every machine can be defined by a binary relation. <br>
	 * 
	 * @see representation.dataFormats.ILanguage
	 * @return a description of the machine in the form of a binary relation
	 */
	IBinaryRelation getBinaryRelation();
	
	/**
	 * For every one of its states, the machine grammar maps every symbol that allows a transition to it with every 
	 * symbol that allows a transition from it. <br>
	 * 
	 * @see representation.stateMachine.ISymbol
	 * @see representation.stateMachine.ITransition
	 * @see representation.stateMachine.IState
	 * @return the state local context-free grammar
	 */
	IGrammar getMachineGrammar();
	
	/**
	 * Specifications are constraints that can be associated with any state of a state machine. They provide the prerequisite 
	 * to meet for any state or value from another machine, if they are to be defined as eligible counterparts of this 
	 * state. <br>
	 * @return the state's specifications
	 */
	ISpecifications getSpecifications();
	
	/**
	 * 
	 * @param word the word entered into the state machine
	 * @return true if this word belongs to the language of this machine ; false otherwise
	 */
	IEvaluationLog evaluateWord(IWord word);
	
	/**
	 * The name of a state is a designation of this state in a roughly human-like way. <br> 
	 * It makes use of the flow chart of the state's machine, and contains the smallest sequences of symbols in this 
	 * flow chart that leads unequivocally to the state at hand. <br>
	 * 
	 * @see representation.stateMachine.ISymbol
	 * @param name the name of a state
	 * @return the state of the machine having the specified name if it can be found ; null otherwise
	 */
	IState getState(IStateName name);
	
	/**
	 * 
	 * @param iD a random integer
	 * @return the state of the machine having the specified ID if it can be found ; null otherwise
	 */
	IState getState (int iD);
	
	/**
	 * The start state of the machine is the one that reads the first symbol of any input word. 
	 * 
	 * @see representation.stateMachine.ISymbol
	 * @see representation.stateMachine.IWord
	 * @return the start state of the machine
	 */
	IStartState getStartState();
	
	/**
	 * An interface state is a place-holder state, in a state machine that is partially undetermined. <br> 
	 * It is meant to be implemented by a value, i.e. a state or a block of state having the configuration of 
	 * a sub-machine.
	 * 
	 * @see representation.stateMachine.IValue
	 * @see representation.stateMachine.IValueAttribution
	 * @return the interface states
	 */
	Set<IInterfaceState> getInterfaces();
	
	/**
	 * An interface state is a place-holder state, in a state machine that is partially undetermined. <br> 
	 * It is meant to be implemented by a value, i.e. a state or a block of state having the configuration of 
	 * a sub-machine.
	 * 
	 * @see representation.stateMachine.IInterfaceState
	 * @see representation.stateMachine.IValue
	 * @see representation.stateMachine.IValueAttribution
	 * @return
	 */
	int getNbOfInterfaces();
	
	/**
	 * An accept state is state that, if it becomes active as a result of a transition triggered by the reading 
	 * of the last symbol of a word, defines this word as part of the machine's language.
	 * 
	 * @see representation.stateMachine.ITransition
	 * @see representation.stateMachine.ISymbol
	 * @see representation.stateMachine.IWord
	 * @see representation.dataFormats.ILanguage
	 * @return the set of all the accept states in this machine
	 */
	Set<IAcceptState> getAcceptStates();
	
	/**
	 * The cost of a machine is a measure of the amount of information it contains. It equals the cost of its 
	 * transition function.  
	 * 
	 * @see ITransitionFunction
	 * @return the cost of the machine
	 */
	float getCost();
	
	/**
	 * Since the name of a state depends on the structure of the machine it belongs to, state names are derived 
	 * from the transition function. <br>
	 * 
	 * @see representation.stateMachine.IState
	 * @see representation.stateMachine.IStateName
	 * @see representation.stateMachine.ITransitionFunction
	 * @param tFunction the machine's transition function
	 */
	void setStateNames(ITransitionFunction tFunction);
	
	/**
	 * The rules associated with a given state are a subset of the transition function, composed of all the 
	 * transitions that have this state as their input state. 
	 * 
	 * @see representation.stateMachine.IState
	 * @see representation.stateMachine.ITransitionFunction
	 * @see representation.stateMachine.ITransition
	 * @param tFunction the machine's transition function
	 */
	void setStateRules(ITransitionFunction tFunction);
	
	/**
	 * Specifications are constraints that can be associated with any state of a state machine. They provide the 
	 * prerequisite to meet for any state or value from another machine, if they are to be defined as eligible 
	 * counterparts of this state. <br>
	 * 
	 * Since the specifications of a state depend on the structure of the machine it belongs to, specifications are 
	 * derived from the transition function. <br>
	 * 
	 * @see representation.stateMachine.IState
	 * @see representation.stateMachine.ISpecifications
	 * @see representation.stateMachine.ITransitionFunction
	 * @param tFunction the machine's transition function
	 */
	void setStateSpecifications(ITransitionFunction tFunction);

}
