package representation;

import java.util.Set;

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
 * to it in order to obtain each one of its sub-categories. By going down the network this way, from one category to 
 * all of its sub-categories, one finally reach the objects of the context (which are the smallest categories, 
 * whose extent only contain themselves). <br>
 * An <i> algorithmic description </i> can then be defined as a sub-network of the representation's network, in 
 * which a single path can be found from the signified to any object in the context (the different paths in the 
 * network can of course have common sections, which makes the description more efficient). The "single path 
 * constraint" is consistent with the way we verbally describe a context : a multi-path description would yield 
 * something like "this is a big blue ball, and also a blue object that is big and that is a ball, and also..." <br>
 * Many algorithmic descriptions of a single representation can usually be generated. But they are assigned a cost, 
 * which is a measure of the amount of information they use ; this way, one description amongst all possible ones 
 * can be designed as the most efficient and as such, as the preferential description of the representation. <br> 
 * </p>
 * 
 * <p>
 * I propose here to implement such representations as states machines ( {@link IRepresentation} ), with the 
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
	
	ITransitionFunction buildTransitionFunction(Set<ICategory> categories);
	
	ISignified getSignifiedState();
	
	Set<ICategory> getCategories();
	
	Set<IContextObject> getObjects();
	
	Set<IContextObject> getObjectsThatConformTo(IFunctionalExpression specifiedProperty);
	
	IAlgorithmicDescription getOptimalAlgorithmicDescription();
	
	Set<IAlgorithmicDescription> getAllAlgorithmicDescriptions();
	
	IRepresentation getRepresentationRestrictedTo(Set<IContextObject> specifiedObjects);
	
	IContextObject getMostSimilarObjToTargetObjWithSpecifiedProp(IContextObject targetObject, IFunctionalExpression specifiedProperty);

}
