package representation;

/**
 * <p>
 * A functional expression is one of the three equivalent {@link IDescription} format that can be used to describe an element 
 * of a context ; the two others being a regular language (i.e. a set of words accepted by a finite state machine) and 
 * a binary relation. Each one of this format can be translated in one of the others, in order to proceed some 
 * format-specific operations (e.g., set operations with binary relations).<br>
 * </p>
 * 
 * <p>
 * A functional expression is a statement that uses the function/argument(s) formalism to describe an object or a category. 
 * It is made up of a string of symbols provided by the alphabet of the domain at use, plus the conjunction symbol 
 * <i> " ∧ " </i>. <br>
 * </p>
 * 
 * <p>
 * Below are two examples of how a functional expression <i> F </i> can be translated in natural language : <br>
 * .<i> F = abc </i> <br>
 * => "<i> F </i> is a <i> a </i> that is <i> b </i>, and this <i> ab </i> is <i> c </i>." <br>
 * .<i> F = a(b ∧ c) </i> <br>
 * => "<i> F </i> is a <i> a </i> that is <i> b </i> and <i> d </i>." <br>
 * </p>
 * 
 * <p>
 * To give the previous examples a more realistic flavor, make the following replacements : <br>
 * - <i> a </i> -> "ball" <br>
 * - <i> b </i> -> "having a certain color", or "colored" <br>
 * - <i> c </i> -> "blue" <br>
 * - <i> d </i> -> "pierced" <br>
 * </p>
 * 
 * @author Gael Tregouet
 *
 */
public interface IFunctionalExpression extends IDescription {
	
	/**
	 * The language equivalent to this functional expression is built first, and then provides the binary relation.
	 * 
	 * @see representation.ILanguage
	 * @return the binary relation equivalent to this functional expression 
	 */
	@Override
	IBinaryRelation getBinaryRelation();
	
	/**
	 * The regular language equivalent to this functional expression is built following the procedure described 
	 * below. <br>
	 * 
	 * From a functional expression with one or more conjunction, a set of words is composed 
	 * by iterating the procedure below, until no more conjunction is left <br>
	 * 
	 * -If <i> a(b ∧ c) </i> then <i> (ab, ac) </i> <br>  
	 * 
	 * Example : <br> 
	 * <i> F = i(j(k ∧ l) ∧  m) </i> <br>
	 * 
	 * Procedure : <br>
	 * .<i> ij(k ∧ l) </i> <br>
	 * ..<i> ijk => ∈ L<sub>M</sub> </i> <br>
	 * ..<i> ijl => ∈ L<sub>M</sub> </i> <br>
	 * .<i> im => ∈ L<sub>M</sub> </i> <br>
	 * 
	 * @return the regular language equivalent to this functional expression
	 */
	@Override
	ILanguage getLanguage();
	
	/**
	 * The  array of symbols encoding the application relation in this functional expression is built according to 
	 * the procedure illustrated below. <br>
	 * 
	 * For a given functional expression <i> F = i(j(k ∧ l) ∧  m) </i>, here are the symbol coordinates in the array : <br> 
	 * .<i> i </i> : [0][0] <br>
	 * .<i> j </i> : [0][1][0] <br>
	 * .<i> k </i> : [0][1][1] <br>
	 * .<i> l </i> : [0][1][2] <br>
	 * .<i> m </i> : [0][2] <br>
	 * 
	 * @return the array of symbols that encodes the application relation in this functional expression
	 */
	ISymbol[][] getApplicationArray();
	
	/**
	 * A restricted grammar is the minimal knowledge base required to proceed the description of a 
	 * given object or category (regardless of the format at use). <br> 
	 * 
	 * The language equivalent to this functional expression is built first, and then provides the restricted grammar.
	 * 
	 * @see representation.ILanguage
	 * @return the restricted grammar associated with this description
	 */
	IGrammar getRestrictedGrammar();

}
