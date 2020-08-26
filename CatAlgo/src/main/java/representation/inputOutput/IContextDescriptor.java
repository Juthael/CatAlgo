package representation.inputOutput;

import java.util.Set;

import representation.dataFormats.IFunctionalExpression;
import representation.exceptions.RepresentationException;
import representation.stateMachine.IAlgorithmicDescription;
import representation.stateMachine.IRepresentation;
import representation.stateMachine.infoQuantification.ITransitionCostCalculator;

/**
 * <p>
 * A context descriptor describes a context of objects : it says what these objects are, taken individually or in 
 * relevant groupings, by making the best use of tailor-made categories discovered in the representation building 
 * process, and of the relationships that bind them. <br>
 * </p>
 * 
 * <p>
 * A context description is based on a representation, which is an information structure that determines which categories 
 * are being perceived in a given context, and how they are organized. To know how these categories can be generated, see 
 * {@link IRepresentation}. <br>
 * </p>
 * 
 * <p>
 * Once the network of categories has been established, the paths that will be used within it to describe the context 
 * still need to be determined. There usually are many possibilities, not all of which are equally efficient. For a 
 * given context input, the context descriptor can return every possible description, or only the most efficient one 
 * on the basis of different criteria : it can minimize the amount information that is used by the description, or 
 * maximize its coding efficiency. In this latter case, a context is regarded as a code, or <i> signifier </i> (following 
 * Saussure's terminology), and its <i> signified </i> is the most general category of the representation, whose extent 
 * includes every object in the context and which, therefore, summarizes it all. (To know more about information 
 * quantification and optimization criteria, see {@link IAlgorithmicDescription} and {@link ITransitionCostCalculator}). <br>
 * </p>
 * 
 * <p>
 * The main difficulty of context description, though, has to do with the fact that most of the time, one cannot be 
 * sure about which properties characterize objects in the context, because these objects have an inherent degree of 
 * ambiguity. For instance, in the context of the string <code> abbc </code>, is the object
 * <code> b </code> in second position the first letter of the "sameness" group <code> [bb] </code>, as in 
 * <code> [a][bb][c] </code> ? Or is it the second letter of the "increase by 1" group <code> [ab] </code>, as in 
 * <code> [ab][bc] </code> ? <br> Which description of <code> b </code> will the context input include ? 
 * </p>
 * 
 * <p>
 * To solve this ambiguity, I make this central assumption : whenever a context can be described in several conflicting 
 * ways, the description that a subject will perceive as being the <i> real </i> one will be the <i> most efficient </i> 
 * one. <br>
 * (It's hard to tell whether one is more "real" than the other in the previous example, by the way. Sometimes, no obvious 
 * solution emerge). <br>
 * </p>
 * 
 * <p>
 * Accordingly, here's how a context descriptor returns the "true" description of an ambiguous context (i.e., a context 
 * in which objects can be described in many conflicting ways) : <br> 
 * .for every possible set of object descriptions, a distinct context input must be instantiated ("every possible set" is likely 
 * to trigger a combinatorial explosion ; then, "every reasonable set" and a domain-specific heuristic to define "reasonable" 
 * will do). <br>
 * .this set of context inputs is given as a parameter to the {@link IContextDescriptor}'s constructor. Out of each context 
 * input, the context descriptor builds a distinct representation. Each representation returns the most efficient description 
 * of the context, given its own particular network of categories. <br>
 * .the context descriptor then ends up with a set of descriptions (one for every context input given to the constructor), 
 * whose efficiency can be compared. The "true" one, as stated above, is the most efficient. <br>
 * </p>
 * 
 * @see representation.stateMachine.ICategory
 * @see representation.stateMachine.IRepresentation
 * @see representation.inputOutput.IContextInput
 * @see representation.stateMachine.IAlgorithmicDescription
 * @see representation.stateMachine.infoQuantification.ITransitionCostCalculator
 * @see representation.stateMachine.ISignified
 * @author Gael Tregouet
 *
 */
public interface IContextDescriptor {
	
	/**
	 * Returns <i> n </i> representations, for <i> n </i> context inputs given to the constructor as parameters. 
	 * 
	 * @see representation.inputOutput.IContextInput
	 * @return a set of representations. 
	 */
	Set<IRepresentation> getRepresentations();
	
	/**
	 * Every available context representation provides its most information-efficient description ; among all 
	 * these descriptions, only the optimal one (in terms of information cost) is returned. <br>
	 * 
	 * To know more about information quantification, see {@link IAlgorithmicDescription}, 
	 * {@link ITransitionCostCalculator}. <br>
	 * 
	 * @see representation.stateMachine.IRepresentation
	 * @see representation.stateMachine.IAlgorithmicDescription
	 * @see representation.stateMachine.infoQuantification.ITransitionCostCalculator
	 * @return the most information-efficient description of a context
	 */
	IAlgorithmicDescription getLowestCostDescription();
	
	/**
	 * Every available context representation provides its most cost-efficient description, among all descriptions 
	 * matching the target object with an eligible counterpart. From this set of descriptions, only the optimal one (
	 * the one using the least amount of information) is returned. <br>
	 * 
	 * To know more about information quantification, see {@link IAlgorithmicDescription}, 
	 * {@link ITransitionCostCalculator}. <br>
	 * 
	 * To know more about object matching, see {@link IRepresentation}. <br>
	 * 
	 * @see representation.stateMachine.IRepresentation
	 * @see representation.stateMachine.IAlgorithmicDescription
	 * @see representation.stateMachine.infoQuantification.ITransitionCostCalculator
	 * @param constraintOnTarget a constraint that is satisfied by a single object in the context (the target object)
	 * @param constraintOnMatch a constraint that is satisfied by at least one object in the context (the matchable objects)
	 * @return the most cost-efficient description among those that meet the matching constraint
	 * @throws RepresentationException if the matching constraint cannot be met in a given representation  
	 */
	IAlgorithmicDescription getLowestCostDescriptionWithMatch(
			IFunctionalExpression constraintOnTarget, IFunctionalExpression constraintOnMatch);
	
	/**
	 * Every available context representation provides the description that best encodes its signified. 
	 * Among all these descriptions, only the optimal one (in terms of coding efficiency) is returned. <br>
	 * 
	 * To know more about the signified's encoding, see {@link IAlgorithmicDescription}. <br>
	 * 
	 * To know more about information quantification, see {@link IAlgorithmicDescription}, 
	 * {@link ITransitionCostCalculator}. <br>
	 * 
	 * @see representation.stateMachine.IRepresentation
	 * @see representation.stateMachine.ISignified
	 * @see representation.stateMachine.IAlgorithmicDescription
	 * @see representation.stateMachine.infoQuantification.ITransitionCostCalculator
	 * @return the most cost-efficient description among those that meet the matching constraint  
	 */
	IAlgorithmicDescription getBestEncodingDescription();
	
	/**
	 * Every available context representation provides its description that best encodes its signified, among all descriptions 
	 * matching the target object with an eligible counterpart. From this set of descriptions, only the optimal one (in terms 
	 * of coding efficiency) is returned. <br>
	 * 
	 * To know more about the signified's encoding, see {@link IAlgorithmicDescription}. <br>
	 * 
	 * To know more about information quantification, see {@link IAlgorithmicDescription}, 
	 * {@link ITransitionCostCalculator}. <br>
	 * 
	 * To know more about object matching, see {@link IRepresentation}.
	 * 
	 * @see representation.stateMachine.IRepresentation
	 * @see representation.stateMachine.ISignified
	 * @see representation.stateMachine.IAlgorithmicDescription
	 * @see representation.stateMachine.infoQuantification.ITransitionCostCalculator
	 * @param constraintOnTarget a constraint that is satisfied by a single object in the context (the target object)
	 * @param constraintOnMatch a constraint that is satisfied by at least one object in the context (the matchable objects)
	 * @return the best encoding description among those that meet the matching constraint  
	 * @throws RepresentationException if the matching constraint cannot be met in a given representation  
	 */
	IAlgorithmicDescription getBestEncodingDescriptionWithMatch(
			IFunctionalExpression constraintOnTarget, IFunctionalExpression constraintOnMatch);
	
	/**
	 * Every available context representation provides the set of all its alternative descriptions ; the union of these 
	 * sets is returned. 
	 * 
	 * @see representation.stateMachine.IRepresentation
	 * @return every possible description of this context, regardless of the representation on which it is based
	 */
	Set<IAlgorithmicDescription> getAllAlgorithmicDescriptions();
	
	/**
	 * Every available context representation provides the set of all its descriptions matching the target object with an 
	 * eligible counterpart. <br>
	 * 
	 * To know more about object matching, see {@link IRepresentation}. <br>
	 * 
	 * @see representation.stateMachine.IRepresentation
	 * @param constraintOnTarget a constraint that is satisfied by a single object in the context (the target object)
	 * @param constraintOnMatch a constraint that is satisfied by at least one object in the context (the matchable objects)
	 * @return every possible description of this context, regardless of the representation on which it is based, provided that
	 * this description matches the target object with a counterpart
	 * @throws RepresentationException if the matching constraint cannot be met in a given representation
	 */
	Set<IAlgorithmicDescription> getAllAlgorithmicDescriptionsWithMatch(
			IFunctionalExpression constraintOnTarget, IFunctionalExpression constraintOnMatch);
	
	/**
	 * Every description being generated by a representation, and every representation by a context input, returns the 
	 * context input that led to the specified description.
	 * 
	 * @see representation.stateMachine.IRepresentation
	 * @param description any description
	 * @return the context input that generated the specified description
	 * @throws RepresentationException if no context input has been found
	 */
	IContextInput getContextInputThatGenerated(IAlgorithmicDescription description);
	
	/**
	 * Returns the representation that generated the specified description. 
	 * 
	 * @param description any description
	 * @return the representation that generated the specified description
	 * @throws RepresentationException if no representation has been found
	 */
	IRepresentation getRepresentationThatGenerated(IAlgorithmicDescription description);
	
	

}
