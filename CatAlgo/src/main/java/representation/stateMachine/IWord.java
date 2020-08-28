package representation.stateMachine;

import java.util.Iterator;
import java.util.List;

/**
 * A IWord is a list of symbols.
 * @see representation.stateMachine.ISymbol
 * @author Gael Tregouet
 *
 */
public interface IWord extends Comparable<IWord> {
	
	/**
	 * 
	 * @return the list of symbols
	 */
	List<ISymbol> getListOfSymbols();
	
	/**
	 * 
	 * @return an iterator over the list of symbols
	 */
	Iterator<ISymbol> getSymbolIterator();
	
	/**
	 * Defines a lexicographic order.
	 * @param word any word
	 * @return <i>-1</i> if this word is before the specified word in the lexicographic order ; <i>0</i> if it is the same word ;  <i>1</i> otherwise.
	 */
	@Override
	int compareTo(IWord word);

}
