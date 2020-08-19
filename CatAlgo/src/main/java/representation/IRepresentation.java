package representation;

import java.util.Set;

import representation.exceptions.RepresentationException;

/**
 * <p>
 * A <i> representation </i> is an information structure that determines which categories are being perceived 
 * in a given context (a context is a set of objects), and what relationships exist between them. 
 * For a subject, to develop a representation of a given context consists in the building of a category 
 * network structured by inheritance relationships. This will allow him to : <br>
 * -abstract the context, i.e. access to general categories that summarize what is true about any object 
 * to which they apply. <br>
 * -find paths in this network to access properties and goals on objects as efficiently as possible. <br>
 * </p>
 * 
 * <p>
 * Any representation has a <i> signified </i>, which is its most general category. The signified tells what 
 * is true about any object in the context. It is named so because because it emerges from the grouping of the 
 * set of objects that constitutes the context, as if it were "coded" by this context, which would then act as 
 * its signifier (following Saussure's terminology). <br>
 * </p>
 * 
 * <p> 
 * Each representation can be described following a descriptive program or <i> algorithmic description </i>. 
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
 * can be designed as the most efficient and as such, as the preferential description of the representation. <br> 
 * </p>
 * 
 * <p>
 * It is proposed here to implement such representations as states machines ( {@link IRepresentation} ), with the 
 * following properties : <br>
 * -The representational machine is a finite state automaton (FSA), with the special feature that each 
 * one of its state is a contextually-relevant category. Categories being themselves implemented as
 * finite state automata ( {@link ICategory} ), this makes the representational machine a <i> 
 * meta-machine </i>. <br>
 * -A transition exists in the representational machine between a state <i> A </i> and a 
 * state <i> B </i> iff <i> A </i> is a super-category of <i> B </i> (which makes <i> B </i>. <br>
 * -As in any FSA, the transition function takes as input a state and a symbol, and returns a state. 
 * But this time the symbol is not an arbitrary element from a predetermined alphabet. It is a tailor-made 
 * operator which, by attributing values to dimensions an declaring new dimensions inside 
 * these values, is indeed a procedure that transforms one "category" machine into another (dimensions 
 * being <i> interface states </i>, acting as place holders in the categorical machine ; and values 
 * being states or "sub-machines" implementing these interfaces). <br>
 * </p> 
 * 
 * @see representation.IStateMachine
 * @see representation.ICategory
 * @see representation.IState
 * @see representation.IOperator
 * @see representation.IValue
 * @see representation.IInterfaceState
 * @see representation.IValueAttribution
 * @see representation.ISignified
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
	 * @see representation.ITransition
	 * @see representation.ICategory
	 * @see representation.IState
	 * @see representation.IStateMachine
	 * @see representation.IWord
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
	 * @see representation.ICategory
	 * @see representation.IContextObject
	 * @see representation.IStartState
	 * @see representation.IOperator
	 * @see representation.IInterfaceState
	 * @see representation.IValue
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
	 * @see representation.ICategory
	 * @return the objects of the context
	 */
	Set<IContextObject> getObjects();
	
	/**
	 * The specified property, expressed as a functional expression, is sought on the context objects. <br>  
	 * 
	 * As any description ( {@link IDescription} ), a functional expression can be converted into any of
	 * the other description formats. The binary relation format ( {@link IBinaryRelation} ) is particularly 
	 * useful, because categories (and therefore, objects), can also be described as binary relations. So to 
	 * find out whether a certain object has a certain property, one just has to verify that the relation 
	 * associated with the property is actually as subset of the relation associated with the object. 
	 * 
	 * @see representation.IBinaryRelation
	 * @param specifiedProperty a property, expressed as a functional expression. 
	 * @return the set of objects on which the specified property can be verified.
	 */
	Set<IContextObject> getObjectsThatHave(IFunctionalExpression specifiedProperty);
	
	/**
	 * Returns the representation's description that uses the least amount of information. 
	 * 
	 * @return the representation's description that uses the least amount of information
	 */
	IAlgorithmicDescription getLowestCostDescription();
	
	/**
	 * Returns the representation's description that uses the least amount of information, amongst
	 * all descriptions matching the target object with an eligible counterpart. <br>
	 * 
	 * The target object is the only object whose intent meets the constraint expressed in the first 
	 * parameter (in the form of a functional expression). The target is matched if the description 
	 * contains a state whose extent includes only two objects : the target, and any other object 
	 * meeting the constraint expressed in the second parameter. 
	 * 
	 * Having one category of their own in the representation's description, and sharing this property
	 * with no other object, makes these two objects (target and match) counterparts. 
	 * 
	 * @param constraintOnTargetObj a property that can be found in a single object in the context
	 * @param constraintOnMatch a property that can be found in at least one object in the context
	 * @return the optimal (with regard to information cost) description matching the target object 
	 * with a counterpart
	 * @throws RepresentationException if more than one target object, or no match object, have 
	 * been found.  
	 */
	IAlgorithmicDescription getLowestCostDescriptionWithMatch(
			IFunctionalExpression constraintOnTarget, IFunctionalExpression constraintOnMatch);
	
	/**
	 * 
	 * @return the representation's description that achieves the best encoding of its signified.
	 */
	IAlgorithmicDescription getBestEncodingDescription();
	
	/**
	 * Returns the representation's description that best encodes its signified, amongst
	 * all descriptions matching the target object with an eligible counterpart. <br>
	 * 
	 * The target object is the only object whose intent meets the constraint expressed in the first 
	 * parameter (in the form of a functional expression). The target is matched if the description 
	 * contains a state whose extent includes only two objects : the target, and any other object 
	 * meeting the constraint expressed in the second parameter. 
	 * 
	 * Having one category of their own in the representation's description, and sharing this property
	 * with no other object, these to objects (target and match) are regarded as counterparts. 
	 * 
	 * @param constraintOnTargetObj a property that can be found in a single object in the context
	 * @param constraintOnMatch a property that can be found in at least one object in the context
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
	 * Having one category of their own in the representation's description, and sharing this property
	 * with no other object, these to objects (target and match) are regarded as counterparts. 
	 * 
	 * @param constraintOnTargetObj a property that can be found in a single object in the context
	 * @param constraintOnMatch a property that can be found in at least one object in the context
	 * @return all descriptions matching the target object with a counterpart
	 * @throws RepresentationException if more than one target object, or no match object, have 
	 * been found.  
	 */
	Set<IAlgorithmicDescription> getAllDescriptionsWithMatch(
			IFunctionalExpression constraintOnTargetObj, IFunctionalExpression constraintOnMatch);

}
