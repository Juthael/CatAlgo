package representation;

import java.util.Iterator;
import java.util.List;

/**
 * A IWord is a list of symbols.
 * @see representation.ISymbol
 * @author Gael Tregouet
 *
 */
public interface IWord {
	
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

}
