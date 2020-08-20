package representation;

import java.util.Set;

/**
 * <p>
 * A context descriptor describes a context of objects : its says what these objects are and, to do so, makes the best 
 * use of tailor-made categories discovered in the representation building process, and of the relationships that bind 
 * them. <br>
 * </p>
 * 
 * <p>
 * A representation is an information structure that determines which categories are being perceived in a given context, 
 * and how they are organized. Thus, the main assignment of a representation's constructor is to generate these contextually 
 * relevant categories. To know how it can be done, see {@link IRepresentation}. <br>
 * </p>
 * 
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
 * solution emerges). <br>
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
 * @see representation.ICategory
 * @see representation.IRepresentation
 * @see representation.IContextInput
 * @see representation.IAlgorithmicDescription
 * @see representation.ITransitionCostCalculator
 * @see representation.ISignified
 * @author Gael Tregouet
 *
 */
public interface IContextDescriptor {
	
	/**
	 * Returns <i> n </i> representations, for <i> n </i> context inputs given to the constructor as parameters. 
	 * @return a set of representations. 
	 * @see representation.IContextInput
	 */
	Set<IRepresentation> getRepresentations();
	
	/**
	 * Every available context representation provides its most information-efficient description ; among which only 
	 * the optimal one (in terms of information cost) is returned. <br>
	 * 
	 * To know more about information quantification, see {@link IAlgorithmicDescription}, 
	 * {@link ITransitionCostCalculator}. <br>
	 * @return the most information-efficient description of a context
	 * @see representation.IAlgorithmicDescription
	 * @see representation.ITransitionCostCalculator
	 */
	IAlgorithmicDescription getLowestCostDescription();
	
	IAlgorithmicDescription getLowestCostDescriptionWithMatch(
			IFunctionalExpression constraintOnTarget, IFunctionalExpression constraintOnMatch);
	
	IAlgorithmicDescription getBestEncodingDescription();
	
	IAlgorithmicDescription getBestEncodingDescriptionWithMatch(
			IFunctionalExpression constraintOnTarget, IFunctionalExpression constraintOnMatch);
	
	Set<IAlgorithmicDescription> getAllAlgorithmicDescriptions();
	
	Set<IAlgorithmicDescription> getAllAlgorithmicDescriptionsWithMatch(
			IFunctionalExpression constraintOnTarget, IFunctionalExpression constraintOnMatch);
	
	IContextInput getContextInputThatGenerated(IAlgorithmicDescription description);
	
	IRepresentation getRepresentationThatGenerated(IAlgorithmicDescription description);
	
	

}
