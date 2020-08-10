package representation;

/**
 * A IDescription is the description of an object in a given context, or the description (intent) of a category. 
 * It can be displayed as a binary relation, as a regular language or as a functional expression. 
 * All of these formats are equivalent and interchangeable, although some operations are format-specific (like set 
 * operations on a binary relation, or the evaluation of a word by a state machine). 
 * 
 * @see representation.ICategory
 * @see representation.IBinaryRelation
 * @see representation.ILanguage
 * @see representation.IFunctionalExpression
 * 
 * @author Gael Tregouet
 *
 */
public interface IDescription {
	
	/**
	 * A binary relation <i> R </i> is a set of pairs <i> (x,y) </i>, where <i> x </i> and <i> y </i> are symbols. If this 
	 * relation is used to describe an object, then <i> x </i> and <i> y </i> are properties and <i> (x,y) ∈ R </i> (or 
	 * <i> xRy </i>) means "<i> x </i> <b> implies </b> <i> y </i>" (e.g. "color implies blue").  
	 * @return the description as a binary relation
	 */
	IBinaryRelation getBinaryRelation();
	
	/**
	 * A ILanguage is a regular language. It is composed of all the words (i.e., strings of symbols) accepted by a given 
	 * state machine of the 'finite state automaton' (FSA) type. For any regular language, a FSA machine can be found to accept it. 
	 * If a language is a description of an object or a category, then each of its word is a functional expression describing 
	 * an accessible property or an attainable goal on this object, or on any object of this category.  
	 * @return the description as a language
	 */
	ILanguage getLanguage();
	
	/**
	 * A functional expression is a statement that uses the function/argument(s) formalism to describe an object or a category. 
	 * As the word of a language, it is made up of a string of symbols ; but unlike a word, it makes use of the 
	 * conjunction symbol <i> " ∧ " </i>  which allows a single function to describe a whole object or category.  
	 * @return
	 */
	IFunctionalExpression getFunctionalExpression();
	
	/**
	 * A restricted grammar is the minimal knowledge base required to proceed the description of a 
	 * given object or category (regardless of the format at use). <br>
	 *  
	 * @return the restricted grammar associated with this description
	 */
	IGrammar getRestrictedGrammar();

}
