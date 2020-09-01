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
	 * Defines a lexicographic order.
	 * @param word any word
	 * @return <i>-1</i> if this word is before the specified word in the lexicographic order ; 
	 * <i>0</i> if it is the same word ;  <i>1</i> otherwise.
	 */
	@Override
	int compareTo(IWord anotherWord);	
	
	/**
	 * Returns the symbol at the specified index.
	 * @param index any integer
	 * @return the symbol at the specified index in the word's list of symbols, or <code> null </code> if the specified 
	 * index exceeds the list's size. 
	 */
	ISymbol get(int index);
	
	/**
	 * Returns the list of symbols.
	 * @return the list of symbols
	 */
	List<ISymbol> getListOfSymbols();
	
	/**
	 * Returns an iterator over the list of symbols.
	 * @return an iterator over the list of symbols
	 */
	Iterator<ISymbol> getSymbolIterator();
	
	/**
	 * Returns the size of the list of symbols that constitutes this word.
	 * @return the number of symbols in this word
	 */
	int size();

}
