package grammarModel.impl;

import java.util.List;

import exceptions.grammarModelException;
import grammarModel.ISyntacticChains;

public class SyntacticChains implements ISyntacticChains {

	protected List<List<String>> listOfChains;
	protected List<Long> leafIDs;
	protected int currentChainIndex;
	protected int currentElementIndex;
	
	public SyntacticChains(List<List<String>> listOfChains) throws grammarModelException {
		this.listOfChains = listOfChains;
		currentChainIndex = 0;
		currentElementIndex = 0;
	}

	public List<List<String>> getChains() {
		return listOfChains;
	}

	public boolean hasNext() {
		if ( hasNextChainElement() || hasNextChain() ) {
			return true;
		}
		else return false;
	}

	public String next() throws grammarModelException {
		String nextElement;
		if ( hasNextChainElement() )
			currentElementIndex++;
		else if ( hasNextChain() ) {
			currentElementIndex = 0;
			currentChainIndex++;
		}
		else throw new grammarModelException("Chains.next() : no next element.");
		nextElement = listOfChains.get(currentChainIndex).get(currentElementIndex);
		return nextElement;
	}

	public long getCurrentElementLeafID() {
		return leafIDs.get(currentChainIndex);
	}
	
	public void resetIndexes() {
		currentChainIndex = 0;
		currentElementIndex = 0;

	}

	public boolean equals(Object otherChains) {
		if (this == otherChains) {
			return true;
		}
		else if (otherChains == null || this.getClass() != otherChains.getClass()) {
			return false;
		}
		else {
			SyntacticChains other = (SyntacticChains) otherChains;
			return this.getChains().equals(other.getChains());
		}
	}
	
	private boolean hasNextChainElement() {
		if (listOfChains.get(currentChainIndex).size() >= currentElementIndex + 1)
			return true;
		else return false;
	}

	private boolean hasNextChain() {
		if (listOfChains.size() >= currentChainIndex + 1)
			return true;
		else return false;
	}

}
