package representation;

import java.util.Map;

/**
 * A binary relation is one of the three equivalent {@link IDescription} format that can be used to describe an element 
 * of a context ; the two others being a regular language (i.e. a set of words accepted by a finite state machine) and 
 * a functional expression. Each one of this format can be translated in one of the others, in order to proceed some 
 * format-specific operations (e.g., set operations with binary relations).<br>
 * 
 * A binary relation <i> R </i> is a set of pairs <i> (x,y) </i>, where <i> x </i> and <i> y </i> are symbols. If this 
 * relation is used to describe an object, then <i> x </i> and <i> y </i> are properties and <i> (x,y) ∈ R </i> (or 
 * <i> xRy </i>) means "<i> x </i> <b> is </b> <i> y </i>" (e.g. "color is blue").   
 * 
 * @see representation.IDescription
 * @see representation.ILanguage
 * @see representation.IFunctionalExpression
 * @see representation.ISymbol
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
	 */
	@Override
	ILanguage getLanguage();
	
	/**
	 * The language associated with this relation is determined first, and will provide its equivalent functional expression.
	 * 
	 * @see representation.ILanguage
	 * @return
	 */
	@Override
	IFunctionalExpression getFunctionalExpression();
	
	/**
	 * 
	 * @return the map of the relation, mapping <i> x </i> to <i> y </i> iff <i> xRy </i>
	 */
	Map<ISymbol, ISymbol> getRelationMap();	
	
	/**
	 * A local grammar is the minimal knowledge base required to proceed the description of a 
	 * given object or category (regardless of the format at use). <br> 
	 * 
	 * A rule <i> x ::= y </i> exists in the returned grammar iff <i> xRy </i>.
	 * 
	 * @see representation.IDescription
	 * @return the local grammar associated with this description
	 */
	@Override
	IGrammar getLocalGrammar();

}
