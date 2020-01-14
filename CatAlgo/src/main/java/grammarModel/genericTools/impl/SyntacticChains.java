package grammarModel.genericTools.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.genericTools.ISyntacticChains;

public class SyntacticChains extends Chains implements ISyntacticChains {

	private List<Long> leafIDs;
	
	public SyntacticChains(List<List<String>> listOfChains, List<Long> leafIDs) throws GrammarModelException {
		super(listOfChains);
		this.leafIDs = leafIDs;
	}

	@Override
	public long getCurrentLeafID() {
		return leafIDs.get(currentChainIndex);
	}
	
	@Override
	public Map<List<String>, Set<Long>> getPathToLeafIDs() throws GrammarModelException {
		Map<List<String>, Set<Long>> pathToLeafID = new HashMap<List<String>, Set<Long>>();
		List<List<String>> chains = super.getChains();
		try {
			for (int i=0 ; i < chains.size() ; i++) {
				List<String> path = new ArrayList<String>();
				path.addAll(chains.get(i));
				path.remove(path.size() - 1);
				if (pathToLeafID.containsKey(path)) {
					pathToLeafID.get(path).add(leafIDs.get(i));
				}
				else {
					Set<Long> newSetOfIDs = new HashSet<Long>();
					newSetOfIDs.add(leafIDs.get(i));
					pathToLeafID.put(path, newSetOfIDs);
				}
			}	
		}
		catch (Exception e) {
			throw new GrammarModelException("SyntacticChains.getDimensionToLeafIDs() : error. " 
					+ System.lineSeparator() + e.getMessage());
		}
		return pathToLeafID;
	}
	
	@Override
	public String getLeaf(Long leafID) throws GrammarModelException {
		String leaf;
		int IDindex = 0;
		boolean indexFound = false;
		while(indexFound == false && IDindex < leafIDs.size()) {
			if (leafID == leafIDs.get(IDindex)) 
				indexFound = true;
			else IDindex++;
		}
		if(indexFound == true) {
			List<String> targetChain = listOfChains.get(IDindex);
			leaf = targetChain.get(targetChain.size() - 1);
		}
		else throw new GrammarModelException("SyntacticChains.getLeaf() : leaf not found.");
		return leaf;
	}
	
	@Override
	public boolean hasProperty(String property) throws GrammarModelException {
		boolean propertyFound = false;
		resetIndexes();
		while (propertyFound == false && this.hasNext()) {
			propertyFound = this.next().equals(property);
		}
		return propertyFound;
	}

}
