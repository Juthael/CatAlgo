package grammarModel.genericTools.impl;

import java.util.List;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IChains;

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
	
	private boolean hasNextChainElement() {
		if (listOfChains.get(currentChainIndex).size() >= currentElementIndex + 2)
			return true;
		else return false;
	}

	private boolean hasNextChain() {
		if (listOfChains.size() >= currentChainIndex + 2)
			return true;
		else return false;
	}	

}
