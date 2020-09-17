package representation.stateMachine;

import java.util.Set;

import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.exceptions.RepresentationException;
import representation.inputOutput.IContextInput;
import representation.stateMachine.infoQuantification.ITransitionCostCalculator;

/**
 * <p>
 * A <b> representation </b> is an information structure that determines which categories are 
 * being perceived in a given context (a context is a set of objects), and which relations bind them together. 
 * For a subject, to develop a representation of a given context consists in the building of a structured 
 * and tailor-made category network. This will allow him to : <br>
 * -abstract the context, i.e. access to general categories that summarize what is true about any object 
 * to which they apply. <br>
 * -find paths in this network to access properties and goals on objects as efficiently as possible. <br>
 * </p>
 * 
 * <p> 
 * A representation of a context is constructed out of a set of object's descriptions (one for every object 
 * in the context), provided by a "context input" ( {@link IContextInput} ). <br>
 * Among several equivalent description formats (see {@link IDescription}), an object can be described by a 
 * relational description ( {@link IRelationalDescription} ) ; so can be a category. A relational description 
 * associated with an object <i> o </i> is characterized by a set of orders <i> O </i>. Each order <i> p ∈  O </i> 
 * denotes a <i> property </i> of this object or category. If an      
 * The contextually relevant 
 * categories that can be used for the description of a given context can be found by the following procedure. are to be found in the Galois lattice 
 * <i> Gal(O,P,M) </i>, where : <br>
 * -<i> O </i> is the set of objects <br>
 * -<i> P </i> is the set of <i> pairs of attributes </i> formed by the union of the binary relations contained 
 * in every relational description of an object. <br>
 * -<i> M </i> is the mapping of <i> O </i> in <i> P </i>. <br>
 * Every element in this lattice, except for its minimum, is a category that can be described in terms of its 
 * <i> intent </i> (i.e. its meaning, expressed by a binary relation) or in terms of its <i> extent </i> (the 
 * subset of objects to which it applies). The lattice's atoms are categories whose extent includes a single object, 
 * so they can be assimilated to this object. <br>
 * The lattice provides an inclusion order over the set of categories such as, <i> A </i> and <i> B </i> being two 
 * categories, <i> B </i> is a sub-category of <i> A </i> if <i> A </i>'s intent is a subset of <i> B </i>'s, or 
 * (equivalently) if <i> B </i>'s extent is a subset of <i> A </i>'s. <br>
 * </p>
 * 
 * <p>
 * An important element of this lattice is its maximum, called the <i> signified </i>. It is the most general category, 
 * the one that applies to every object in the context and tells what is true about all of them. It is named so because 
 * it emerges from the grouping of the set of objects that constitutes the context, as if it were "coded" by this context, 
 * which would then act as the signified's <i> signifier </i> (following Saussure's terminology). <br>
 * </p>
 * 
 * <p> 
 * Each representation can be verbalized following a descriptive program or <i> algorithmic description </i>. 
 * This program will first describe the signified, and then specify which additional information must be brought 
 * to it in order to obtain its relevant sub-categories. By going down the network this way, from categories to 
 * their sub-categories, one finally reach the objects of the context (which are the smallest categories, 
 * whose extent only contains themselves). <br>
 * An <i> algorithmic description </i> can then be defined as a sub-machine of the representational machine, in
 * the flow chart of which a single path can be found from the signified to any object in the context (the different 
 * paths in the flow chart can of course have common sections, which makes the description all the more efficient). The 
 * "single path constraint" is consistent with the way we verbally describe a context : a multi-path description would 
 * yield something like "this is a big blue ball, and also something blue that is big and that is a ball, and also..." <br>
 * Many algorithmic descriptions of a single representation can usually be generated. But they are assigned a cost, 
 * which is a measure of the amount of information they use ; this way, one description amongst all possible ones 
 * can be designed as the most efficient and as such, as the preferential description of the representation (see 
 * {@link IAlgorithmicDescription}). <br> 
 * </p>
 * 
 * <p>
 * In order to take into account all previous considerations about relations, it is proposed here to implement them as 
 * states machines ( {@link IRepresentation} ), with the following properties : <br>
 * -The representational machine is a finite state automaton (FSA), with the special feature that each 
 * one of its state is a contextually relevant category. Categories being themselves implemented as
 * finite state automata ( {@link ICategory} ), this makes the representational machine a <i> 
 * meta-machine </i>. <br>
 * -A transition exists in the representational machine between a state <i> A </i> and a 
 * state <i> B </i> iff <i> A </i> is a super-category of <i> B </i>. <br>
 * -As in any FSA, the transition function takes as input a state and a symbol, and returns a state. 
 * But this time the symbol is not an arbitrary element from a predetermined alphabet. It is a tailor-made 
 * operator which, by attributing values to dimensions an declaring new dimensions inside 
 * these values, is indeed a procedure that transforms one "category" machine into another (dimensions 
 * being <i> interface states </i>, acting as place holders in the categorical machine ; and values 
 * being states or "sub-machines" implementing these interfaces). <br>
 * </p> 
 * 
 * @see representation.inputOutput.IContextInput
 * @see representation.dataFormats.IDescription
 * @see representation.dataFormats.IRelationalDescription
 * @see representation.stateMachine.IAlgorithmicDescription
 * @see representation.stateMachine.IStateMachine
 * @see representation.stateMachine.ICategory
 * @see representation.stateMachine.IState
 * @see representation.stateMachine.IOperator
 * @see representation.stateMachine.IValue
 * @see representation.stateMachine.IInterfaceState
 * @see representation.stateMachine.IValueAttribution
 * @see representation.stateMachine.ISignified
 * 
 * @author Gael Tregouet
 *
 */
public interface IRepresentation extends IStateMachine {
	
	/**
	 * A transition function contains the rules governing the transition between states while the machine 
	 * evaluates of a word, and successively reads its symbols. It associates an input consisting in a 
	 * given state and a given symbol, to an output state. <br>
	 * 
	 * Instead of being needed as an argument to the constructor of the state machine as it would usually 
	 * be expected, the transition function of a representational state machine must be inferred from the 
	 * comparison of its "category" states. The reason is that this machine doesn't use a predetermined 
	 * alphabet of symbols to determine its transitions. Instead, this alphabet is a set of tailor-made 
	 * operators, containing the procedure by which the state machine of the input category can be 
	 * expanded and transformed into the machine of its sub- (or output) category.
	 * 
	 * To facilitate the construction of the transition function, {@link ICategory} has a method that takes 
	 * another category as an argument, and returns a transition rule ( {@link ITransition} ) if the specified 
	 * category is actually a sub-category.  
	 * 
	 * @see representation.stateMachine.ITransition
	 * @see representation.stateMachine.ICategory
	 * @see representation.stateMachine.IState
	 * @see representation.stateMachine.IStateMachine
	 * @see representation.stateMachine.IWord
	 * @see representation.IOperatord
	 * @param categories the set of states, or "categories", of the representational state machine
	 * @return the transition function of the machine
	 */
	ITransitionFunction buildTransitionFunction(Set<ICategory> categories);
	
	/**
	 * The signified state is the most general category of a representation. Its extent contains every
	 * object in the context and its intent contains everything that is true about all of them. <br>
	 * 
	 * The only possible transition to the signified state is from the start state. The only symbol that 
	 * allows this transition is an operator that substitutes the start state's intent (a single interface 
	 * state, equivalent to a free variable) by the signified's intent (a state machine with interfaces). 
	 * Thus, this "operator" symbol is a definition of the signified.  
	 * 
	 * @see representation.stateMachine.ICategory
	 * @see representation.stateMachine.IContextObject
	 * @see representation.stateMachine.IStartState
	 * @see representation.stateMachine.IOperator
	 * @see representation.stateMachine.IInterfaceState
	 * @see representation.stateMachine.IValue
	 * 
	 * @return the signified state
	 */
	ISignified getSignifiedState();
	
	/**
	 * 
	 * @return the categories, i.e. the states of the representational machine
	 */
	Set<ICategory> getCategories();
	
	/**
	 * In a representation, objects are the smallest categories in terms of their extent : they apply to only 
	 * one element in the context, of which they are an exhaustive description. <br> 
	 * 
	 * @see representation.stateMachine.ICategory
	 * @return the objects of the context
	 */
	Set<IContextObject> getObjects();
	
	/**
	 * The specified successorRelation, expressed as a functional expression, is sought on the context objects. <br>  
	 * 
	 * As any description ( {@link IDescription} ), a functional expression can be converted into any of
	 * the other description formats. The binary relation format ( {@link IRelationalDescription} ) is particularly 
	 * useful, because categories (and therefore, objects), can also be described as binary relations. So to 
	 * find out whether a certain object has a certain successorRelation, one just has to verify that the relation 
	 * associated with the successorRelation is actually as subset of the relation associated with the object. 
	 * 
	 * @see representation.dataFormats.IRelationalDescription
	 * @param specifiedProperty a successorRelation, expressed as a functional expression. 
	 * @return the set of objects on which the specified successorRelation can be verified.
	 */
	Set<IContextObject> getObjectsThatHave(IFunctionalExpression specifiedProperty);
	
	/**
	 * Returns the representation's description that uses the least amount of information.
	 * 
	 * To know more about information quantification, see {@link IAlgorithmicDescription}, 
	 * {@link ITransitionCostCalculator}. <br>
	 * 
	 * @see representation.stateMachine.IAlgorithmicDescription
	 * @see representation.stateMachine.infoQuantification.ITransitionCostCalculator
	 * @return the representation's description that uses the least amount of information
	 */
	IAlgorithmicDescription getLowestCostDescription();
	
	/**
	 * Returns the representation's description that uses the least amount of information, among
	 * all descriptions matching the target object with an eligible counterpart. <br>
	 * 
	 * The target object is the only object whose intent meets the constraint expressed in the first 
	 * parameter (in the form of a functional expression). The target is matched if the description 
	 * contains a state whose extent includes only two objects : the target, and any other object 
	 * meeting the constraint expressed in the second parameter. <br>
	 * 
	 * Having one category of their own in the representation's description, and sharing this successorRelation
	 * with no other object, makes these two objects (target and match) counterparts. <br> 
	 * 
	 * To know more about information quantification, see {@link IAlgorithmicDescription}, 
	 * {@link ITransitionCostCalculator}. <br>
	 * 
	 * @see representation.stateMachine.IAlgorithmicDescription
	 * @see representation.stateMachine.infoQuantification.ITransitionCostCalculator
	 * @param constraintOnTargetObj a successorRelation that can be found in a single object in the context
	 * @param constraintOnMatch a successorRelation that can be found in at least one object in the context
	 * @return the optimal (with regard to information cost) description matching the target object 
	 * with a counterpart
	 * @throws RepresentationException if more than one target object, or no match object, have 
	 * been found.  
	 */
	IAlgorithmicDescription getLowestCostDescriptionWithMatch(
			IFunctionalExpression constraintOnTarget, IFunctionalExpression constraintOnMatch);
	
	/**
	 * Returns the representation's description that achieves the best encoding of its signified. <br>
	 * 
	 * To know more about the signified's encoding, see {@link IAlgorithmicDescription}.
	 * 
	 * To know more about information quantification, see {@link IAlgorithmicDescription}, 
	 * {@link ITransitionCostCalculator}. <br>
	 * 
	 * @see representation.stateMachine.ISignified
	 * @see representation.stateMachine.IAlgorithmicDescription
	 * @see representation.stateMachine.infoQuantification.ITransitionCostCalculator
	 * @return the representation's description that achieves the best encoding of its signified.
	 */
	IAlgorithmicDescription getBestEncodingDescription();
	
	/**
	 * Returns the representation's description that best encodes its signified, among
	 * all descriptions matching the target object with an eligible counterpart. <br>
	 * 
	 * The target object is the only object whose intent meets the constraint expressed in the first 
	 * parameter (in the form of a functional expression). The target is matched if the description 
	 * contains a state whose extent includes only two objects : the target, and any other object 
	 * meeting the constraint expressed in the second parameter. <br>
	 * 
	 * Having one category of their own in the representation's description, and sharing this successorRelation
	 * with no other object, these to objects (target and match) are regarded as counterparts. <br>
	 * 
	 * To know more about the signified's encoding, see {@link IAlgorithmicDescription}.
	 * 
	 * To know more about information quantification, see {@link IAlgorithmicDescription}, 
	 * {@link ITransitionCostCalculator}. <br>
	 * 
	 * @see representation.stateMachine.ISignified
	 * @see representation.stateMachine.IAlgorithmicDescription
	 * @see representation.stateMachine.infoQuantification.ITransitionCostCalculator
	 * @param constraintOnTargetObj a successorRelation that can be found in a single object in the context
	 * @param constraintOnMatch a successorRelation that can be found in at least one object in the context
	 * @return the optimal (with regard to coding efficiency) description matching the target object 
	 * with a counterpart
	 * @throws RepresentationException if more than one target object, or no match object, have 
	 * been found.  
	 */
	IAlgorithmicDescription getBestEncodingDescriptionWithMatch(
			IFunctionalExpression constraintOnTargetObj, IFunctionalExpression constraintOnMatch);
	
	/**
	 * Returns all possible descriptions of the representation.
	 * 
	 * @return all possible descriptions of the representation
	 */
	Set<IAlgorithmicDescription> getAllDescriptions();
	
	/**
	 * Returns all descriptions matching the target object with an eligible counterpart. <br>
	 * 
	 * The target object is the only object whose intent meets the constraint expressed in the first 
	 * parameter (in the form of a functional expression). The target is matched if the description 
	 * contains a state whose extent includes only two objects : the target, and any other object 
	 * meeting the constraint expressed in the second parameter. 
	 * 
	 * Having one category of their own in the representation's description, and sharing this successorRelation
	 * with no other object, these to objects (target and match) are regarded as counterparts. 
	 * 
	 * @param constraintOnTargetObj a successorRelation that can be found in a single object in the context
	 * @param constraintOnMatch a successorRelation that can be found in at least one object in the context
	 * @return all descriptions matching the target object with a counterpart
	 * @throws RepresentationException if more than one target object, or no match object, have 
	 * been found.  
	 */
	Set<IAlgorithmicDescription> getAllDescriptionsWithMatch(
			IFunctionalExpression constraintOnTargetObj, IFunctionalExpression constraintOnMatch);

}
