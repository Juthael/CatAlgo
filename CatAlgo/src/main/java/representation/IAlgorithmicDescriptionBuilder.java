package representation;

import java.util.Set;

/**
 * An algorithmic description builder is in charge of finding all possible ways to describe a representation. <br> 
 * 
 * Each description is a program, whose size (i.e. the amount of information it uses) 
 * can be calculated. This allows a description builder to provide the most efficient description of a representation. 
 * 
 * An <i> algorithmic description </i> ( {@link IAlgorithmicDescription} ) can be defined as a sub-machine of the 
 * representational machine, in the flow chart of which a single path can be found from the start state to any object 
 * in the context (the different paths in the flow chart can of course have common sections, which makes the 
 * description more efficient). The set of descriptions can therefore be built this way : <br>
 * .for each object in a context of <i> n </i> objects, 
 * ..for every path leading to the object state from the start state in the machine's flow chart, determine the 
 * sequence of transitions in the transition function that allows it. Each sequence is a subset of the transition 
 * function <br>
 * .build all possible algorithmic descriptions such as it is formed by the union of <i> n </i> subsets, 
 * each one of these subsets allowing a path (i.e. a sequence of transitions) from the start state to one
 * of the object states (there must be one path for each object in each description). HERE    
 * 
 * @author Gael Tregouet
 *
 */
public interface IAlgorithmicDescriptionBuilder {
	
	IAlgorithmicDescription getOptimalAlgorithmicDescription();
	
	Set<IAlgorithmicDescription> getAllAlgorithmicDescriptions();

}
