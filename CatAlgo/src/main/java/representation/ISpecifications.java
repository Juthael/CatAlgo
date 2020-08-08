package representation;

import java.util.Set;

/**
 * ISpecifications are a set of constraint associated with any state of a state machine. They provide the prerequisite to 
 * meet for any state or value from another machine, if they are to be defined as eligible counterparts of this state. <br>
 * 
 * Counterparts can be two states from two different machines (one being a category, and the other being a sub-category 
 * of the first). They can also be an interface state of a categorical machine, and a value implementing this interface in 
 * the 'sub-category' machine. <br>        
 * 
 * The specifications describe the role endorsed by a given state in its machine. They involve properties on 
 * the <i> path to </i> this state in the machine flow chart, and also properties on its <i> scope </i>. 
 * Theses properties must also be found in any potential counterpart of this state in another machine. It means that the 
 * counterpart of a state <i> p </i> must preserve the paths to <i> p </i> in its own machine flow chart (constraints on path), 
 * and also to give access to similar states, i.e. states accessible themselves in similar ways in both machines.
 * 
 * @see representation.IConstraint
 * @see representation.IState
 * @see representation.IStateMachine
 * @see representation.IValue
 * @see representation.ICategory
 * 
 * @author Gael Tregouet
 *
 */
public interface ISpecifications {
	
	/**
	 * 
	 * @return the constraints on path
	 */
	Set<IConstraint> getAccessSpecifications();
	
	/**
	 * 
	 * @return the constraints on scope
	 */
	Set<IConstraint> getScopeSpecifications();
	
	/**
	 * 
	 * @param constraint a constraint to add on path
	 */
	void addConstraintOnPaths(IConstraint constraint);
	
	/**
	 * 
	 * @param constraint a constraint to add on scope
	 */
	void addConstraintOnScope(IConstraint constraint);
	
	/**
	 * @see representation.IConstraint
	 * @param path a path in a machine flowchart, implemented as a word
	 * @return true if the specified path fulfills a constraint on path ; false otherwise
	 */
	boolean aConstraintOnPathIsFullfilledBy(IWord path);
	
	/**
	 * @see representation.IConstraint
	 * @param path a path in a machine flowchart, implemented as a word
	 * @return true if the specified path fulfills a constraint on scope ; false otherwise
	 */
	boolean aConstraintOnScopeIsFullfilledBy(IWord path);
	
	/**
	 * @see representation.IConstraint
	 * @param paths a set of paths in a machine flowchart, implemented as a words
	 * @return true if the specified paths fulfill all constraints on paths ; false otherwise
	 */
	boolean allConstraintsOnPathAreFullfilledBy(Set<IWord> paths);
	
	/**
	 * @see representation.IConstraint
	 * @param paths a set of paths in a machine flowchart, implemented as a words
	 * @return true if the specified paths fulfill all constraints on scope ; false otherwise
	 */
	boolean allConstraintsOnScopeAreFullfilledBy(Set<IWord> paths);
	
	/**
	 * Given two specifications, one can be said to <i> extend </i> the other. <br>
	 * 
	 * Specifications contain constraints, and each constraint is expressed as a path, possibly with some 
	 * undetermined parts. This path has the form of a word with free variables acting as placeholders for the 
	 * yet unknown parts of the path. <br>
	 * 
	 * Now, if this path is thought of as a functional expression, it can be made more precise by applying 
	 * arguments (words) to its free variables. So a constraint <i> c </i> is just a more specific version 
	 * of of a constraint <i> c' </i> if the path associated with <i> c' </i> is the result of the application 
	 * of arguments to free variables in the path associated with <i> c </i>. <br>
	 * 
	 * Finally, specifications <i> S' </i> can be said to extend specifications <i> S </i> if any constraint 
	 * in <i> S </i> can be paired with an equally or more precise constraint in <i> S' </i>.  
	 * 
	 * @param specifications any specifications
	 * @return true if the specified specifications are not contradictory to these ones, but only more precise
	 */
	boolean isExtendedBy(ISpecifications specifications);

}
