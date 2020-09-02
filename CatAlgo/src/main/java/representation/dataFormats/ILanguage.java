package representation.dataFormats;

import java.util.List;
import java.util.Set;

import representation.exceptions.RepresentationException;
import representation.stateMachine.IStateMachine;
import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;


/**
 * <p> 
 * A regular language is one of the three equivalent description format that can be used to describe an element of a 
 * context ; the two others being a binary relation and a functional expression. Each one of this format can be translated in 
 * one of the others, in order to proceed some format-specific operations (e.g., set operations with binary relations). <br>
 * </p>
 * 
 * <p>
 * As a regular language, a ILanguage is composed of all the words (i.e., strings of symbols) accepted by a given state 
 * machine of the 'finite state automaton' (FSA) type. For any regular language, a FSA machine can be found to accept it. 
 * If a language is a description of an object or a category, then each of its word describes an accessible 
 * property or an attainable goal on this object, or on any object of this category. <br>
 * </p>  
 * 
 * @see representation.dataFormats.IDescription
 * @see representation.stateMachine.IWord
 * @see representation.stateMachine.IStateMachine
 * @see representation.dataFormats.IBinaryRelation
 * @see representation.dataFormats.IFunctionalExpression
 * 
 * @author Gael Tregouet
 *
 */
public interface ILanguage extends IDescription {
	
	/**
	 * For any pair of symbols <i> (x,y) </i>, if there exists a word in the language <i> L<sub>M</sub> </i> in which 
	 * the symbol <i> y </i> is to the right of the symbol <i> x </i> (and not necessarily adjacent), then <i> (x,y) </i> 
	 * belongs to the relation returned.
	 * 
	 * @return a binary relation based on (and equivalent to) this language
	 */
	@Override
	IBinaryRelation getBinaryRelation();
	
	/**
	 * The functional expression equivalent to this language can be inferred from the flowchart of the machine that 
	 * accepts it. <br> 
	 * 
	 * Let <i> a, b, ... </i> be symbols. <i> a -> b </i> means that there exists three states 
	 * <i> {q<sub>i</sub>, q<sub>j</sub>, q<sub>k</sub>} </i> in the machine such that 
	 * <i> δ(q<sub>i</sub>,a) = q<sub>j</sub> </i> and <i> δ(q<sub>j</sub>,b) = q<sub>k</sub> </i>. <br> 
	 * 
	 * The functional expression <i> F </i> is built according to the following principles : <br>
	 * -if <i> a -> b </i> then <i> F = ab </i> <br>
	 * -if <i> a -> b, a -> c </i> then <i> F = a(b Λ c) </i> <br> 
	 * -if <i> a -> b, a -> c, b -> d, c -> d </i> then <i> F = a(bd Λ cd) </i> <br> 
	 * 
	 * @see representation.stateMachine.IStateMachine
	 * @return the functional expression equivalent to this language
	 */
	@Override
	IFunctionalExpression getFunctionalExpression();
	
	/**
	 * Returns the set of words that constitutes this language
	 * @return the set of words that constitutes this language
	 */
	Set<IWord> getWords();
	
	/**
	 * Returns the list of words that constitutes this language, in a lexicographic order
	 * @return the list of words that constitutes this language, in a lexicographic order
	 */
	List<IWord> getDictionary();

	/**
	 * The machine <i> M </i> is built from its language <i> L<sub>M</sub> </i>. <br>
	 * 
	 * This building is carried out according to the following principles :
	 * <b>a</b>-<i> M </i> has one <i> start </i> state and one <i> accept </i> state. <br>
	 * <b>b</b>-Each symbol in a given word of <i> L<sub>M</sub> </i> must be read by a different state. 
	 * The first symbol is read by the start state and the reading of the last symbol allows a transition to the accept 
	 * state. <br> 
	 * <b>c</b>-Let <i> w<sub>1</sub>=AXB </i> and <i> w<sub>2</sub>=AYB </i> be two words ; <i> A </i> is the longest 
	 * beginning part that is common to both words, and <i> B </i> is the longest common ending part in which every pair 
	 * of adjacent symbols <i> (i,j) </i> correspond to a rule <i> (i ::= j) </i> in the context free grammar of 
	 * the microworld at use. A symbol common to both words <i> w<sub>1</sub>=AXB </i> and <i> w<sub>2</sub>=AYB </i> 
	 * is read by the same state <i> q<sub>x</sub> </i> only if it is one unique symbol belonging to the sub-strings 
	 * <i> A </i> or <i> B </i>. 
	 * 
	 * @see grammarModel.structure.ISyntacticStructure
	 * 
	 * @return a state machine that accepts this language
	 */
	IStateMachine getStateMachine();
	
	/**
	 * A description's grammar is the minimal knowledge base required to proceed the description of a 
	 * given object or category (regardless of the format at use). <br> 
	 * 
	 * A rule <i> x ::= y </i> exists in the returned grammar iff a word <i> w </i> can be found in 
	 * this language such that <i> xy </i> is a substring of <i> w </i>.	 * .
	 * 
	 * @return the grammar associated with this description
	 */
	@Override
	IGrammar getGrammar();
	
	/**
	 * <p>
	 * Returns the number of arguments that the specified symbol, when regarded as a function, accepts. <br>
	 * </p>
	 * 
	 * <p>
	 * The number of arguments of a symbol <i> s </i> can be found using the following procedure : <br> 
	 * .find a word in the language that has the form <i> XsY </i>, with <i> X </i> and <i> Y </i> being 
	 * (possibly empty) sub-strings. <br>
	 * .count the number of words in the language beginning with the substring <i> Xs </i>. This returns the 
	 * number of arguments for <i> s </i>. <br>
	 * </p>
	 * 
	 * <p>
	 * WARNING : the result may be inconsistent if the same symbol is found both in a terminal and non-terminal 
	 * position, as <i> c </i> in <i> a/b/c, a/c/d/e, (...) </i>.
	 * </p>
	 * 
	 * @param symbol any symbol that is used by the description
	 * @return the number of arguments accepted by the specified symbol
	 * @throws RepresentationException if the specified symbol is not actually used in the description
	 */
	@Override
	int getNbOfArgumentsFor(ISymbol symbol) throws RepresentationException;
	
}
