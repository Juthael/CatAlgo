package representation;

/**
 * A IConstraint is used to check that a certain state or value (i.e. block of states) in a state machine associated with 
 * a category ( {@link ICategory} ) is an eligible counterpart of another state in the machine associated with a 
 * super-category. 
 * 
 * A IConstraint contains a word (i.e. a string of symbols) representing a path in the flow chart of a state machine. 
 * This word is meant to be an address of a certain state of this machine. Besides, if the machine contains interface 
 * states waiting for an implementation, then the path may contain free variables, which are place holders for yet 
 * unknown steps. <br> 
 *   
 * As an example, let <i> p </i> be the path in a constraint associated to a state <i> S </i> in a category-machine, 
 * and <i> p' </i> the path to another state (or value) <i> S' </i> in the machine associated with a sub-category. 
 * If <i> p' </i> can be expressed as the result of the application of arguments <i> 
 * {a<sub>1</sub>, ..., a<sub>x</sub>} </i> to free variables of <i> p </>, such that 
 * <i> (λi<sub>1</sub>(...)i<sub>x</sub>.p)a<sub>1</sub>...a<sub>x</sub> = p' </i>, then the word <i> p' </i> fulfills 
 * the constraint <i> p </i>. It means that the states <i> S </i> and <i> S' </i> can be accessed via the same kind of 
 * paths in the flow charts of both machines, even though the path associated with the sub-category state <i> S' </i> 
 * can be more specific because some interfaces have been implemented.
 * 
 * In this example, the constraint is on the path to <i> access </i> a certain state. But a constraint can also involve 
 * what must be reachable from this same state : if states and values of different machines are to be equivalent, 
 * then they have to be accessible in similar ways (constraints on path), and also to give access to similar states, i.e. 
 * states accessible themselves in similar ways. These requirements are <i> constraints on scope </i>.
 * 
 * @see representation.IWord
 * @see representation.IState
 * @see representation.IStateMachine
 * @see representation.ICategory
 * @see representation.IInterfaceState
 * @see representation.IFreeVariable
 * @see representation.ISpecifications
 * 
 * @author Gael Tregouet
 *
 */
public interface IConstraint {
	
	/**
	 * See below for a formal definition of the fulfillment of a constraint. <br> 
	 * 
	 * Let <i> p </i> be the path (i.e., word) associated with this constraint and <i> p' </i> the specified path. 
	 * If <i> p' </i> can be expressed as the result of the application of arguments 
	 * <i> {a<sub>1</sub>, ..., a<sub>x</sub>} </i> to free variables of <i> p </>, such that 
	 * <i> (λi<sub>1</sub>(...)i<sub>x</sub>.p)a<sub>1</sub>...a<sub>x</sub> = p' </i>, then the word <i> p' </i> fulfills 
	 * this constraint.
	 * 
	 * @param path a word representing a path
	 * @return true if the specified path can be expressed as an 'equivalent' (even if more specific) of the constraint path 
	 */
	boolean fulfilledBy(IWord path);

}
