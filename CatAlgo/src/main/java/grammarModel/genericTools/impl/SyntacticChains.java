package grammarModel.genericTools.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.GrammarModelException;
import grammarModel.genericTools.ISyntacticChains;

public class SyntacticChains extends Chains implements ISyntacticChains {

	private List<Long> leafIDs;
	
	public SyntacticChains(List<List<String>> listOfChains, List<Long> leafIDs) throws GrammarModelException {
		super(listOfChains);
		this.leafIDs = leafIDs;
	}

	public long getCurrentLeafID() {
		return leafIDs.get(currentChainIndex);
	}
	
	public Map<String, Long> getDimensionToLeafID(){
		Map<String, Long> dimensionToLeafID = new HashMap<String, Long>();
		List<List<String>> chains = super.getChains();
		for (int i=0 ; i < chains.size() ; i++) {
			List<String> dimension = new ArrayList<String>();
			dimension.addAll(chains.get(i));
			dimension.remove(dimension.size() - 1);
			dimen
			// Il peut y avoir plusieurs ID de la même dimension ???
		}
	}

}
