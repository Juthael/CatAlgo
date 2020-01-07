package grammarModel.genericTools.impl;

import java.util.List;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IChains;

/**
 * Chains contains an indexed list of indexed lists of Strings, with navigation functionalities (a 'chain' 
 * is a list of Strings). 
 * Can be extended as SyntacticChains (spanning chains of a syntactic tree) or PosetMaxChains 
 * (spanning chains of a lower semilattice).
 * 
 * @see SyntacticChains
 * @see PosetMaxChains
 *  
 * @author Gael Tregouet
 *
 */

public abstract class Chains implements IChains {

	protected List<List<String>> listOfChains;
	protected int currentChainIndex;
	protected int currentElementIndex;
	
	public Chains(List<List<String>> listOfChains) throws GrammarModelException {
		if (!listOfChains.isEmpty()) {
			this.listOfChains = listOfChains;
			currentChainIndex = 0;
			currentElementIndex = -1;
		}
		else throw new GrammarModelException("Chains constructor : parameter is empty.");
	}

	/**
	 * @return the list of chains. 
	 */
	@Override
	public List<List<String>> getChains() {
		return listOfChains;
	}
	
	
	/**
	 * @return the common first element of all chains (be it the start element of a syntactic tree, or the minimum of 
	 * a lower semilattice).
	 */
	@Override
	public String getRoot() {
		return listOfChains.get(0).get(0);
	}

	/**
	 * @return true if a new element can be accessed - either on the same chain as the previous element, or
	 * on a new one. 
	 */
	@Override
	public boolean hasNext() {
		if ( hasNextChainElement() || hasNextChain() ) {
			return true;
		}
		else return false;
	}

	
	/**
	 * @return the next element of the previous chain, or the first element of the next chain. 
	 * 
	 * @throws GrammarModelException if no next element is available. Use hasNext() to prevent this error. 
	 * 
	 */
	@Override
	public String next() throws GrammarModelException {
		String nextElement;
		if ( hasNextChainElement() )
			currentElementIndex++;
		else if ( hasNextChain() ) {
			currentElementIndex = 0;
			currentChainIndex++;
		}
		else throw new GrammarModelException("Chains.next() : no next element.");
		nextElement = listOfChains.get(currentChainIndex).get(currentElementIndex);
		return nextElement;
	}

	/**
	 * Reset indexes in order to re-initialize navigation. 	
	 */
	@Override
	public void resetIndexes() {
		currentChainIndex = 0;
		currentElementIndex = -1;
	}

	@Override
	public boolean equals(Object otherChains) {
		if (this == otherChains) {
			return true;
		}
		else if (otherChains == null || this.getClass() != otherChains.getClass()) {
			return false;
		}
		else {
			IChains other = (Chains) otherChains;
			return this.getChains().equals(other.getChains());
		}
	}
	
	/**
	 * 
	 * @return true if at least one element can still be returned on the current chain. 
	 */
	private boolean hasNextChainElement() {
		if (listOfChains.get(currentChainIndex).size() >= currentElementIndex + 2)
			return true;
		else return false;
	}

	/**
	 * 
	 * @return true if at least one more chain remains to be explored. 
	 */
	private boolean hasNextChain() {
		if (listOfChains.size() >= currentChainIndex + 2)
			return true;
		else return false;
	}	

}
