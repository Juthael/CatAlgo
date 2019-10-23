package grammarModel.impl;

import java.util.List;

import grammarModel.ISyntacticChains;

public class SyntacticChains extends Chains implements ISyntacticChains {

	private List<Long> leafIDs;
	
	public SyntacticChains(List<List<String>> listOfChains, List<Long> leafIDs) {
		super(listOfChains);
		this.leafIDs = leafIDs;
	}

	public long getCurrentElementLeafID() {
		return leafIDs.get(currentChainIndex);
	}

}
