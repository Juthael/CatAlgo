package grammarModel.genericTools.impl;

import java.util.List;

import exceptions.GrammarModelException;
import grammarModel.genericTools.ISynTreeChains;

public class SynTreeChains extends Chains implements ISynTreeChains {

	private List<Integer> leafIDs;
	
	public SynTreeChains(List<List<String>> listOfChains, List<Integer> leafIDs) throws GrammarModelException {
		super(listOfChains);
		this.leafIDs = leafIDs;
	}

	public long getCurrentElementLeafID() {
		return leafIDs.get(currentChainIndex);
	}

}
