package representation.stateMachine;

import java.util.Set;

import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IDescription;

/**
 * <p> 
 * A category is the conjunction of an <i> intent </i> that defines what this category <i> is </i>, 
 * and of an <i> extent </i> that specifies which objects conform to its definition. 
 * A category can inherit a part of its intent from a super-category (i.e. a category that is more 
 * generic) ; at the same time, the category's definition can itself be extended and refined by a 
 * sub-category. <br>
 * </p>
 * 
 * <p>
 * In order to implement these features, it is proposed here to regard categories both as 
 * <i> machines </i> and as <i> states </i> of a (meta-)machine : <br>
 * </p>
 * 
 * <p>
 * The definition of a category (i.e. its intent) is a description ( {@link IDescription} ) that can 
 * equally be provided as a binary relation, as a functional expression or as a regular language, i.e. 
 * a set of words ( {@link IWord} ) accepted by a finite state machine (which can then be inferred). 
 * From this point of view, a category (or its intent) is indeed a finite state automaton. The <i> 
 * words </i> it takes as inputs are strings of <i> symbols </i> which, in this case, are attributes 
 * ("blue", "pleasing", "havingAnAppearance", "boy", "deflated", "ball"). The words are to be read 
 * following the convention of left-associativity, as in lambda calculus: <br>
 * - [boy blue] => <i> ((boy) blue) </i> : "the boy that is blue." <br>
 * - [girl havingAnAppearance pleasing] => <i> (((girl) havingAnAppearance) pleasing) </i> : the 
 * girl that is having an appearance, that is pleasing.  <br>
 * </p> 
 * 
 * <p>
 * From another point of view, one can observe that the usual way to verbally describe a category is 
 * to describe a super-category first, and then specify which additional features characterizes the 
 * target category (<i> genus-differentia </i> definition : "cats are felines that can be petted"). 
 * So if the category definition is a finite state automaton, this additional information provided 
 * about the super category has the form of a procedure (i.e. a sequence of instructions) that 
 * performs, through a series of local extensions and gap fillings, the transformation of one machine 
 * (the super-category's one) into another (the category's one). <br> 
 * Following these observations, a "representational" machine ( {@link IRepresentation} ) can then be 
 * devised with the following properties : <br>
 * -The representational machine is a finite state automaton (FSA), with the special feature that each 
 * one of its state is a contextually relevant category, and therefore is itself a regular finite state 
 * automaton ( {@link ICategory} ). This makes the representational machine a <i> meta-machine </i>. <br>
 * -A transition exists in the representational machine between a state <i> A </i> and a 
 * state <i> B </i> iff <i> A </i> is a super-category of <i> B </i>. <br>
 * -As in any FSA, the transition function takes as input a state and a symbol, and returns a state. 
 * But this time the symbol is not an arbitrary element from a predetermined alphabet. It is a tailor-made 
 * operator which, by attributing values to dimensions an declaring new dimensions inside 
 * these values, display a procedure that transforms one "category" machine into another (dimensions 
 * being <i> interface states </i>, acting as place holders in the categorical machine ; and values 
 * being states or "sub-machines" implementing these interfaces). <br>
 * </p>
 * 
 * @see representation.stateMachine.IStateMachine
 * @see representation.stateMachine.IState
 * @see representation.dataFormats.IDescription
 * @see representation.stateMachine.IRepresentation
 * @see representation.stateMachine.ICategory
 * @see representation.stateMachine.ITransition
 * @see representation.stateMachine.ITransitionFunction
 * @see representation.stateMachine.IOperator
 * @see representation.stateMachine.IInterfaceState
 * @see representation.stateMachine.IValue
 * 
 * @author Gael Tregouet
 *
 */
public interface ICategory extends IStateMachine, IState {
	
	/**
	 * 
	 * @see representation.dataFormats.IDescription
	 * @see representation.stateMachine.IStateMachine
	 * @param relation the description of the category as a binary relation
	 * @return the transition function of the category state machine
	 */
	ITransitionFunction buildTransitionFunction(IBinaryRelation relation);
	
	/**
	 * Descriptions can have the form of binary relations, functional expressions, or regular languages. 
	 * 
	 * @see representation.dataFormats.IBinaryRelation
	 * @see representation.dataFormats.IFunctionalExpression
	 * @see representation.dataFormats.ILanguage
	 * @return the intent of the category (i.e. its meaning) 
	 */
	IDescription getIntent();
	
	/**
	 * 
	 * @return the extent of the category, i.e. the set of objects in the context that are instances 
	 * of the category
	 */
	Set<IContextObject> getExtent();
	
	/**
	 * <p>
	 * A transition is a component of the transition function of a state machine. It associates an input 
	 * (which consists of a state and a symbol), to an output (which is a state). <br>
	 * </p>
	 * 
	 * <p>
	 * The transition returned here is a transition between two categories, i.e. between two states of 
	 * a representational state machine ( {@link IRepresentation} ). In this machine, a transition can 
	 * only exist from a category to one of its sub-categories. Thus, if the specified category is 
	 * not a sub-category of this category, <code> null </code> is returned. It can be checked, for 
	 * instance, that the specified category's extent is a subset of this category's.<br>
	 * </p>
	 * 
	 * <p> 
	 * If the specified category is truly a sub-category, the transition is built. The input state will 
	 * be the category instance on which the method is called, and the output state will be the specified 
	 * category. <br> 
	 * As for the symbol, it will not be an element of a predetermined alphabet as it usually is the 
	 * case ; rather, it will consist in a tailor-made operator, inferred from the comparison of the two 
	 * categories, and representing the procedure by which the category's state machine ( {@link ICategory} ) 
	 * can be expanded and transformed into the machine of its sub-category. <br>
	 * </p>
	 * 
	 * <p>
	 * This way, instead of being needed as an argument to the constructor of the state machine, the 
	 * transition function of a representational state machine can be inferred from the comparison of 
	 * its "category" states. <br>
	 * </p>
	 * 
	 * @see representation.stateMachine.ITransitionFunction
	 * @see representation.stateMachine.IRepresentation
	 * @see representation.stateMachine.IOperator
	 * @param category
	 * @return a transition between this category and the specified category if the specified category is 
	 * a sub-category ; <code> null </code> otherwise 
	 */
	ITransition getTransitionTo(ICategory category);	

}
