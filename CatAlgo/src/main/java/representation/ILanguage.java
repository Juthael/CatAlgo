package representation;

import java.util.Set;


/**
 * A regular language is one of the three equivalent format that can be used to describe an element of a context ; the two 
 * others being a binary relation and a functional expression. Each one of this format can be translated in one of the others, 
 * in order to proceed some format-specific operations (e.g., set operations with binary relations).
 * 
 * A ILanguage is a regular language. It is composed of all the words (i.e., strings of symbols) accepted by a given state 
 * machine of the 'finite state automaton' (FSA) type. For any regular language, a FSA machine can be found to accept it. 
 * 
 * @see representation.IWord
 * @see representation.IStateMachine
 * @see representation.IBinaryRelation
 * @see representation.IFunctionalExpression
 * 
 * @author Gael Tregouet
 *
 */
public interface ILanguage extends IDescription {

	/**
	 * The machine <i> M </i> is built from its language <i> L<sub>M</sub> </i> according to the principles below : <br>
	 * 
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
	 * For any pair of symbols <i> (x,y) </i>, if there exists a word in the language <i> L<sub>M</sub> </i> in which 
	 * the symbol <i> y </i> is to the right of the symbol <i> x </i> (and not necessarily adjacent), then <i> (x,y) </i> 
	 * belongs to the relation associated with <i> L<sub>M</sub> </i>.
	 * 
	 * @return a binary relation based on (and equivalent to) this language
	 */
	IBinaryRelation getBinaryRelation();
	
	/**
	 * 
	 * @return the set of words that constitutes this language
	 */
	Set<IWord> getWords();
	
}
