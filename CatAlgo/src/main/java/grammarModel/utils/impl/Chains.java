package grammarModel.utils.impl;

import java.util.List;

import grammarModel.GrammarModelConstants;
import grammarModel.exceptions.GrammarModelException;
import grammarModel.utils.IChains;

/**
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

	@Override
	public List<List<String>> getChains() {
		return listOfChains;
	}
	
	
	@Override
	public String getRoot() {
		return listOfChains.get(0).get(0);
	}

	@Override
	public boolean hasNext() {
		if ( hasNextChainElement() || hasNextChain() ) {
			return true;
		}
		else return false;
	}

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

	@Override
	public void resetIndexes() {
		currentChainIndex = 0;
		currentElementIndex = -1;
	}
	
	/**
	 * Auto-generated by Eclipse + made final.
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listOfChains == null) ? 0 : listOfChains.hashCode());
		return result;
	}

	/**
	 * Auto-generated by Eclipse + made final.
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chains other = (Chains) obj;
		if (listOfChains == null) {
			if (other.listOfChains != null)
				return false;
		} else if (!listOfChains.equals(other.listOfChains))
			return false;
		return true;
	}		
	
	public String getChainsInASingleString() {
		String chains;
		StringBuilder sB = new StringBuilder();
		for (List<String> chain : listOfChains) {
			for (int i=0 ; i < chain.size() ; i++) {
				sB.append(chain.get(i));
				if (i < chain.size() - 1)
					sB.append(GrammarModelConstants.SEPARATOR);
			}
			sB.append(System.lineSeparator());
		}
		chains = sB.toString();
		return chains;
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
