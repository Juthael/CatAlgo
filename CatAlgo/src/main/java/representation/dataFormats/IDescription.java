package representation.dataFormats;

import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

/**
 * A description can apply to any category, including objects (which are regarded as categories whose extent includes 
 * a single element). It can be displayed as a binary relation, as a regular language or as a functional expression. 
 * All of these formats are equivalent and interchangeable, although some operations are format-specific (like set 
 * operations on a binary relation, or the evaluation of a word by the state machine accepting a regular language). 
 * 
 * @see representation.stateMachine.ICategory
 * @see representation.stateMachine.IContextObject
 * @see representation.dataFormats.IRelationalDescription
 * @see representation.dataFormats.ILanguage
 * @see representation.dataFormats.IFunctionalExpression
 * 
 * @author Gael Tregouet
 *
 */
public interface IDescription {
	
	/**
	 * A functional expression is a statement that uses the function/argument(s) formalism to describe an object or a category. 
	 * As the word of a language, it is made up of a string of symbols ; but unlike a word, it makes use of the 
	 * conjunction symbol " ∧ " which allows a single function to describe a whole object or category.  
	 * @return the description as a functional expression
	 * @throws RepresentationException if an error has occurred while converting a description format into another in 
	 * order to build the functional expression
	 */
	IFunctionalExpression getFunctionalExpression() throws RepresentationException;	
	
	/**
	 * A description's grammar is the minimal knowledge base required to proceed the description of a 
	 * given object or category (regardless of the format at use). <br>
	 *  
	 * @return the grammar associated with this description
	 * @throws RepresentationException if an error has occurred while converting a description format into another in 
	 * order to build the description's grammar
	 */
	IGrammar getGrammar() throws RepresentationException;	
	
	/**
	 * A ILanguage is a regular language. It is composed of all the words (i.e., strings of symbols) accepted by a given 
	 * state machine of the 'finite state automaton' (FSA) type. For any regular language, a FSA machine can be found to accept it. 
	 * If a language is a description of an object or a category, then each of its word is a functional expression describing 
	 * an accessible successorRelation or an attainable goal on this object, or on any object of this category.  
	 * @return the description as a language
	 * @throws RepresentationException if an error has occurred while building the language out of another 
	 * description format
	 */
	ILanguage getLanguage() throws RepresentationException;	
	
	/**
	 * Returns the number of arguments that any function that ends with the specified symbol will accept as an input. <br>
	 * 
	 * @param symbol any symbol that is used by the description
	 * @return the number of arguments accepted by any function ending with the specified symbol
	 * @throws RepresentationException if the specified symbol is not actually used in the description
	 * @throws RepresentationException if an error has occurred while converting a description format into another in 
	 * order to get the number of arguments
	 */
	int getNbOfArgumentsFor(ISymbol symbol) throws RepresentationException;
	
	/**
	 * A binary relation <i> R </i> is a set of pairs <i> (x,y) </i>, where <i> x </i> and <i> y </i> are symbols. If this 
	 * relation is used to describe an object, then <i> x </i> and <i> y </i> are properties and <i> (x,y) ∈ R </i> (or 
	 * <i> xRy </i>) means "<i> x </i> <b> implies </b> <i> y </i>" (e.g. "color implies blue").  
	 * @return the description as a binary relation
	 */
	IRelationalDescription getRelationalDescription();	
	
	/**
	 * Returns true if this description is an instance of the specified description. <br>
	 * 
	 * It means that any statement from the specified description remains true in this one, although 
	 * it can be specified with additional information. <br>
	 * 
	 * The most convenient way to find out if a description <i> A </i> meets a description <i> B </i> is 
	 * to adopt a binary relation format for both, and check that <i> A </i> 's relation is a subset 
	 * of <i> B </i>'s. <br>
	 * 
	 * @see representation.dataFormats.IRelationalDescription
	 * @param description
	 * @return true if this description is an instance of the specified description
	 */
	boolean meets(IDescription description);	

}
