package representation;

/**
 * <p>
 * The signified state is the most general category of a representation. Its extent contains every
 * object in the context and its intent contains everything that is true about all of them. <br>
 * </p>
 * 
 * <p>
 * The signified is named so because because it emerges from the grouping of the set of objects that 
 * constitutes the context, as if it were "coded" by this context, which would then act as 
 * its signifier (following Saussure's terminology). <br>
 * </p>
 * 
 * <p>
 * The only possible transition to the signified state is from the start state. The only symbol that 
 * allows this transition is an operator that substitutes the start state's intent (a single interface 
 * state, equivalent to a free variable) by the signified's intent (a state machine with interfaces). 
 * Thus, this "operator" symbol is a definition of the signified. <br>
 * </p>  
 * 
 * @author Gael Tregouet
 *
 */
public interface ISignified extends ICategory {
	
	/**
	 * Returns a description of the signified, in any description format (binary relation, regular 
	 * language or functional expression). <br> 
	 * 
	 * A description of the signified is a description of what is true about any object in the 
	 * representation this signified belongs to. 
	 * 
	 * @see representation.IBinaryRelation
	 * @see representation.ILanguage
	 * @see representation.IFunctionalExpression
	 * @see representation.IRepresentation
	 * @return a description of the signified
	 */
	IDescription getSignifiedDescription();

}
