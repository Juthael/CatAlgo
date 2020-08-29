package representation.dataFormats;

import java.util.Set;

import grammarModel.structure.ISyntaxGrove;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

/**
 * <p>
 * A binary relation is one of the three equivalent description format that can be used to describe an element 
 * of a context ; the two others being a regular language (i.e. a set of words accepted by a finite state machine) and 
 * a functional expression. Each one of this format can be translated in one of the others, in order to proceed some 
 * format-specific operations (e.g., set operations with binary relations).<br>
 * </p>
 * 
 * <p>
 * A binary relation <i> R </i> is a set of pairs <i> (x,y) </i>, where <i> x </i> and <i> y </i> are symbols. If this 
 * relation is used to describe an object, then <i> x </i> and <i> y </i> are properties and <i> (x,y) ∈ R </i> (or 
 * <i> xRy </i>) means "<i> x </i> <b> is </b> <i> y </i>" (e.g. "color is blue"). <br>
 * </p> 
 * 
 * <p>
 * Warning : the {@link IBinaryRelation} type can (and should) only deal with well-founded binary relations. Otherwise, 
 * exceptions will be thrown and/or errors will occur. <br>
 * The {@link ISyntaxGrove#markRecursion()} method provides a recursion index mechanism. This allows to be sure that the 
 * descriptions in the generated context input ({@link ISyntaxGrove#getContextInput()}) will only yield well-founded relations. 
 * </p>
 * 
 * @see representation.dataFormats.IDescription
 * @see representation.dataFormats.ILanguage
 * @see representation.dataFormats.IFunctionalExpression
 * @see representation.stateMachine.ISymbol
 * 
 * @author Gael Tregouet
 *
 */
public interface IBinaryRelation extends IDescription {
	
	/**
	 * The language <i> L<sub>M</sub> </i> associated with a binary relation <i> R </i> is the set of all the 
	 * <i> maximal transitive non-redundant strings </i> in <i> R </i>. <br>
	 * 
	 * A string <i> S </i> is <i> transitive </i> in <i> R </i> if for any <i> x,y,z ∈ S </i>, if <i> xSy </i> and 
	 * <i> ySz </i>, then <i> xRz </i>. Thus, every string <i> S </i> is associated with <i> R' ⊆ R</i>, <i> R' </i> 
	 * being the minimal subset of <i> R </i> such that <i> S </i> remains transitive in <i> R' </i>. <br>
	 * 
	 * A transitive string <i> S </i> is <i> non-redundant </i> if for any non-terminal element <i> s<sub>i</sub> </i> in 
	 * <i> S </i>, the subsets <i> R'<sub>i</sub> </i> and <i> R'<sub>i+1</sub> </i> associated with 
	 * <i> (s<sub>i</sub>] </i> and <i> (s<sub>i+1</sub>] </i> verify <i> R'<sub>i</sub> ⊂ R'<sub>i+1</sub> </i>.
	 * (Notation : <i> (s<sub>i</sub>] </i> is the substring of <i> S </i> beginning with its first character and 
	 * ending with <i> s </i>). <br>
	 * 
	 * A transitive non-redundant string <i> S </i> is <i> maximal </i> if no transitive non-redundant string can be found in 
	 * <i> R </i> that contains <i> S </i>. 
	 * 
	 * @return the language associated with this relation
	 * @throws RepresentationException if an error has occurred while transforming the binary relation into a language
	 */
	@Override
	ILanguage getLanguage() throws RepresentationException;
	
	/**
	 * Returns the functional expression equivalent to this binary relation. <br>
	 * 
	 * The most convenient way to get this functional expression is to transform the relation into a language first, and 
	 * then to call {@link ILanguage#getFunctionalExpression()}.
	 * 
	 * @see representation.dataFormats.ILanguage
	 * @return the functional expression equivalent to this binary relation
	 * @throws RepresentationException if an error has occured while building the language (used as an intermediate format)
	 */
	@Override
	IFunctionalExpression getFunctionalExpression() throws RepresentationException;
	
	/**
	 * Returns the pair of symbols of the binary relation. 
	 * 
	 * @return the pairs of symbols of the binary relation
	 */
	Set<IPair> getPairs();	
	
	/**
	 * <p>
	 * A description's grammar is the minimal knowledge base required to proceed the description of a 
	 * given object or category (regardless of the format at use). <br>
	 * 
	 * A rule <i> x ::= y </i> exists in the returned grammar iff <i> xRy </i> and no <i> z </i> can be found 
	 * such that <i> xRz </i> and <i> zRy </i>. <br>
	 * </p>
	 * 
	 * <p>
	 * The most convenient way to get a grammar out of a relation is to transform the relation into a language 
	 * first, and then call {@link ILanguage#getGrammar()}.
	 * 
	 * @see representation.dataFormats.IDescription
	 * @see representation.dataFormats.ILanguage
	 * @return the grammar associated with this description
	 * @throws RepresentationException if the language equivalent to this relation, 
	 * and which is needed as an intermediate format, cannot be built
	 */
	@Override
	IGrammar getGrammar() throws RepresentationException;
	
	/**
	 * Returns the number of arguments that the specified symbol, when regarded as a function, accepts. <br>
	 * 
	 * The most convenient way to obtain this number from a binary relation is to build its equivalent language 
	 * or functional expression first, and then to call this same method on them.  
	 * 
	 * @see representation.dataFormats.ILanguage
	 * @see representation.dataFormats.IBinaryRelation
	 * @param symbol any symbol that is used by the description
	 * @return the number of arguments accepted by the specified symbol
	 * @throws RepresentationException if the specified symbol is not actually used in the description
	 * @throws RepresentationException if the language equivalent to this relation, 
	 * and which is needed as an intermediate format, cannot be built
	 */
	@Override
	int getNbOfArgumentsFor(ISymbol symbol) throws RepresentationException;	

}
