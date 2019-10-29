package grammarModel.impl;

import java.util.List;

import exceptions.GrammarModelException;
import grammarModel.ISyntacticChains;

public class SyntacticChains extends Chains implements ISyntacticChains {

	private List<Integer> leafIDs;
	
	public SyntacticChains(List<List<String>> listOfChains, List<Integer> leafIDs) throws GrammarModelException {
		super(listOfChains);
		this.leafIDs = leafIDs;
	}

	public long getCurrentElementLeafID() {
		return leafIDs.get(currentChainIndex);
	}

}
