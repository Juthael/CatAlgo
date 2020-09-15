package grammarModel.utils;

import java.util.List;

import grammarModel.exceptions.GrammarModelException;
/**
 * IChains contains an indexed list of indexed lists of Strings, with navigation functionalities (a 'chain' 
 * is a list of Strings, so IChains is a list of chains).
 *  
 * @author Gael Tregouet
 *
 */
public interface IChains {
	
	/**
	 * @return the list of chains. 
	 */
	List<List<String>> getChains();
	
	/**
	 * @return the common first element of all chains (be it the start element of a syntax tree, or the minimum of 
	 * a lower semilattice).
	 */
	String getRoot();
	
	/**
	 * @return true if a new element can be accessed - either on the same chain as the previous element, or
	 * on a new one. 
	 */
	boolean hasNext();
	
	/**
	 * @return the next element of the previous chain, or the first element of the next chain. 
	 * 
	 * @throws GrammarModelException if no next element is available. Use hasNext() to prevent this error. 
	 * 
	 */
	String next() throws GrammarModelException;
	
	/**
	 * For debugging use only.
	 * @return chains concatenated in a single string.
	 */
	String getChainsInASingleString();		
	
	/**
	 * Reset indexes in order to re-initialize navigation. 	
	 */
	void resetIndexes();
	
	@Override
	String toString();	
	
	boolean equals(Object otherChains);

}
