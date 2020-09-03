package representation.dataFormats;

import java.util.List;
import java.util.Map;

import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

/**
 * <p>
 * A functional expression is one of the three equivalent description format that can be used to describe an element 
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
 * => "<i> F </i> is a <i> a </i> that is <i> b </i> and <i> c </i>." <br>
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
 * @see representation.dataFormats.IDescription
 * @see representation.dataFormats.ILanguage
 * @see representation.dataFormats.IBinaryRelation
 * @author Gael Tregouet
 *
 */
public interface IFunctionalExpression extends IDescription {
	
	/**
	 * <p>
	 * Returns the binary relation equivalent to this functional expression. <br>
	 * </p>
	 * 
	 * <p>
	 * Let <i> x </i> and <i> y </i> be two symbols that can respectively be found in the functional expression at coordinates
	 * <i>c<sub>1</sub></i> = [ <i>a, b, (...), i</i> ] and <i>c<sub>2</sub></i> = [ <i>a, b, (...), i, (...) </i> ], such as 
	 * <i>c<sub>1</sub></i> is a substring of <i>c<sub>2</sub></i> ; then <i> xRy </i>.
	 * </p> 
	 * 
	 * @see representation.dataFormats.ILanguage
	 * @return the binary relation equivalent to this functional expression 
	 */
	@Override
	IBinaryRelation getBinaryRelation();
	
	/**
	 * The functional expression is encoded as a mapping of coordinates (that allow to locate every element in the expression) 
	 * to symbols (that can be found on this location). <br>  
	 * 
	 * For a given functional expression <i> F = i(j(k ∧ l) ∧  m) </i>, here the resulting mapping : <br> 
	 * [0] => <i> i </i> <br>
	 * [0, 0] => <i> j </i> <br>
	 * [0, 0, 0] => <i> k </i> <br>
	 * [0, 0, 1] => <i> l </i> <br>
	 * [0, 1] => <i> m </i> <br>
	 * 
	 * @return the map that encodes the functional expression
	 */
	Map<List<Integer>, ISymbol> getCoordinatesOntoSymbols();	
	
	/**
	 * <p>
	 * A description's grammar is the minimal knowledge base required to proceed the description of a 
	 * given object or category (regardless of the format at use). <br>
	 * </p>
	 * 
	 * Let <i> x </i> and <i> y </i> be two symbols that can respectively be found in the functional expression at coordinates
	 * <i>c<sub>1</sub></i> = [ <i>a, b, (...), i</i> ] and <i>c<sub>2</sub></i> = [ <i>a, b, (...), i, j </i> ], such as 
	 * <i>c<sub>1</sub></i> is a substring of <i>c<sub>2</sub></i> and |<i>c<sub>2</sub></i>| = |<i>c<sub>1</sub></i>+1| ; 
	 * then <i>x</i> ::= <i>y</i>.
	 * 
	 * @see representation.dataFormats.ILanguage
	 * @return the grammar associated with this description
	 */
	@Override
	IGrammar getGrammar();	
	
	/**
	 * <p>
	 * The regular language equivalent to this functional expression is built following the procedure described 
	 * below. <br>
	 * </p>
	 * 
	 * <p>
	 * <b>Example :</b> <br> 
	 * <i> F = i(j(k ∧ l) ∧  m) </i> <br>
	 * </p>
	 * 
	 * <p>
	 * <b>'Natural' procedure : </b> <br> 
	 * Out of a functional expression with one or more conjunction, a set of words is built 
	 * by iterating the procedure below, until no more conjunction is left <br>
	 * 
	 * -If <i> a(b ∧ c) </i> then <i> (ab, ac) </i> <br>  
	 * 

	 * 
	 * Procedure : <br>
	 * .<i> ij(k ∧ l) </i> <br>
	 * ..<i> ijk => ∈ L<sub>M</sub> </i> <br>
	 * ..<i> ijl => ∈ L<sub>M</sub> </i> <br>
	 * .<i> im => ∈ L<sub>M</sub> </i> <br>
	 * </p>
	 * 
	 * <p>
	 * <b> Alternative procedure : </b> <br>
	 * .Search for the terminal elements (in the example : <i>k</i>, <i>l</i> and <i>m</i>). Their coordinates are a sublist 
	 * of no other coordinate. <br>
	 * .for each terminal <i>t</i> of coordinates [<i>a, b, c, (...), i</i>], <br>
	 * ..build the word <i> m </i> = [<i>o, p, q, (...), t</i>] with <i> o </i> having the coordinates [<i>a</i>] ; <i> p </i> 
	 * -> [<i>a, b</i>] ; <i> q </i> -> [<i>a, b, c</i>], etc. <br>
	 * 
	 * @return the regular language equivalent to this functional expression
	 */
	@Override
	ILanguage getLanguage();

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * The number of arguments for a symbol <i> s </i> can be found using the following procedure : <br>
	 * .find an occurrence of <i>s</i> in the functional expression. Let <i>c<sub>s</sub></i> be its
	 * coordinates. <br>
	 * .any symbol <i>x</i> of coordinates <i>c<sub>x</sub></i>, such as <i>c<sub>s</sub></i> is a substring of 
	 *  <i>c<sub>x</sub></i> and |<i>c<sub>x</sub></i>| = |<i>c<sub>s</sub></i>| + 1 is an argument of the same function 
	 *  ending with <i>s</i>. <br>
	 * .if <i>n</i> such symbols have been found, then this function has <i>n</i> arguments, and any other function ending 
	 * with <i>s</i> will have <i>n</i> arguments.
	 * </p>
	 * 
	 */
	@Override
	int getNbOfArgumentsFor(ISymbol symbol) throws RepresentationException;	
	
	/**
	 * The functional expression as a String.
	 * @return the functional expression as a String
	 */
	@Override
	String toString();
	
}
