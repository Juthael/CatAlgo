package representation;

/**
 * <p> 
 * Each representation can be described following a descriptive program or <i> algorithmic description </i>. 
 * This program will first describe the signified, and then specify which additional information must be brought 
 * to it in order to obtain its relevant sub-categories. By going down the network this way, from categories to 
 * their sub-categories, one finally reach the objects of the context (which are the smallest categories, 
 * whose extent only contains themselves). <br>
 * </p>
 * 
 * <p>
 * An <i> algorithmic description </i> can then be defined as a sub-machine of the representational machine, in
 * the flow chart of which a single path can be found from the start state to any object in the context (the different 
 * paths in the flow chart can of course have common sections, which makes the description more efficient). The 
 * "single path constraint" is consistent with the way we verbally describe a context : a multi-path description would 
 * yield something like "this is a big blue ball, and also something blue that is big and that is a ball, and also..." <br>
 * Many algorithmic descriptions of a single representation can usually be generated. But they are assigned a cost, 
 * which is a measure of the amount of information they use ; this way, one description amongst all possible ones 
 * can be designed as the most efficient and as such, as the preferential description of the representation. <br> 
 * </p>
 * 
 * <p> 
 * Although being representational machines, algorithmic descriptions have a specific transition function 
 * ( {@link IAlgorithmicDescriptionTF} ) that ensures that the "one path constraint" is effectively met, and that 
 * allows the calculation of a "cost" for every description. 
 * </p>
 * 
 * @see representation.IRepresentation
 * @see representation.ISignified
 * @see representation.ICategory
 * @see representation.IContextObject
 * @see representation.ITransitionFunction
 * @author Gael Tregouet
 *
 */
public interface IAlgorithmicDescription extends IRepresentation {
	
	/**
	 * Returns the cost of this description, which is a measure of the amount of information it uses. 
	 * @return the cost of the description
	 */
	float getDescriptionCost();
	
	/**
	 * Measures how much a description is a suitable encoding of its signified. <br>
	 * 
	 * Assuming that the relevant information that summarizes a representation is its signified, and that 
	 * the objects of the represented context are a mean to encode this information (and make the signified 
	 * emerge), one can infer a coding efficiency by calculating the ratio between the amount of information 
	 * that the signified holds, and the amount of information needed to deliver an exhaustive description 
	 * of the context's objects.. 
	 * 
	 * @see representation.IRepresentation
	 * @see representation.ISignified
	 * @see representation.IContextObject
	 * @return the coding efficiency of this description
	 */
	float getCodingEfficiency();
	
	/**
	 * Returns a string in which every step of an algorithmic description is detailed.
	 *  
	 * @return the description program as a string
	 */
	String toString();

}
