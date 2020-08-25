package representation.stateMachine;

import java.util.Set;

/**
 * <p>
 * The name of a state is a designation of this state in a roughly human-like way. It makes use of the flow chart of the 
 * state's machine, and contains the smallest sequences of symbols in this flow chart that leads unequivocally to the 
 * designated state. <br>
 * </p>
 * 
 * <p> 
 * The name <i>N</i> of a state <i>q<sub>n</sub></i> is a non-empty set of symbol strings, such that a string  
 * <i> S = {s<sub>1</sub>, ..., s<sub>x</sub>} </i> belongs to <i> N </i> iff it meets the following requirements : <br>
 * 1-it can be mapped to a unique sequence of transitions in the transition function <i>δ</i> such that 
 * <i> [δ(s<sub>1</sub>, q<sub>1</sub>) = q<sub>2</sub>, ...,δ(s<sub>x</sub>, q<sub>n-1</sub>) = q<sub>n</sub>] </i> <br>
 * 2-no sub-string <i> S' = {s<sub>p</sub>, ..., s<sub>x</sub>} </i> of <i> S </i> can be found that meets the first requirement.
 * </p>   
 * 
 * @see representation.stateMachine.IState
 * @see representation.stateMachine.ITransitionFunction
 * @see representation.stateMachine.ITransition
 * 
 * @author Gael Tregouet
 *
 */
public interface IStateName {
	
	/**
	 * 
	 * @return the name as a single String
	 */
	String getStateName();
	
	/**
	 * 
	 * @return the name as a set of words, i.e. strings of symbols
	 */
	Set<IWord> getNameComponents();

}
