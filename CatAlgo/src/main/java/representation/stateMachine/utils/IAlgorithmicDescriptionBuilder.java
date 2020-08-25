package representation.stateMachine.utils;

import java.util.Set;

import representation.stateMachine.IAlgorithmicDescription;

/**
 * <p>
 * An algorithmic description builder is in charge of finding all possible ways to describe a representation. <br>
 * </p> 
 * 
 * <p>
 * Each description is a program, whose size (i.e. the amount of information it uses) 
 * can be calculated. This allows a description builder to provide the most efficient description of a representation.
 * </p>
 * 
 * <p>
 * An <i> algorithmic description </i> ( {@link IAlgorithmicDescription} ) can be defined as a restricted 
 * representational machine, in the flow chart of which a single path can be found from the start state to any object 
 * in the context (the different paths in the flow chart can of course have common sections, which makes the 
 * description more efficient). The set of descriptions can therefore be built this way : <br>
 * .associate each of the <i> n </i> object states in the representation to any subset of the transition function that 
 * forms a sequence of transitions (i.e., a path) from the start state to the object state.   <br>
 * .calculate all possible unions of <i> n </i> subsets of the transition function, such as every object state has been 
 * previously mapped with one of these subsets. This results in a set of descriptions. <br>
 * </p>  
 * 
 * @author Gael Tregouet
 *
 */
public interface IAlgorithmicDescriptionBuilder {
	
	/**
	 * 
	 * @return the description that uses the least amount of information
	 */
	IAlgorithmicDescription getAlgorithmicDescriptionWithLowestCost();
	
	/**
	 * 
	 * @return the description that achieves the best encoding of its signified. 
	 */
	IAlgorithmicDescription getAlgorithmicDescriptionWithMostEfficientEncoding();
	
	/**
	 * 
	 * @return any possible description of the representation
	 */
	Set<IAlgorithmicDescription> getAllAlgorithmicDescriptions();

}
