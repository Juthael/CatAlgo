package grammarModel.utils.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.utils.ITreePaths;

public class TreePaths extends Chains implements ITreePaths {

	private List<Long> leafIDs;
	
	public TreePaths(List<List<String>> listOfChains, List<Long> leafIDs) throws GrammarModelException {
		super(listOfChains);
		this.leafIDs = leafIDs;
	}

	@Override
	public long getCurrentLeafID() {
		return leafIDs.get(currentChainIndex);
	}
	
	@Override
	public Map<List<String>, List<Long>> getPathToLeafIDs() throws GrammarModelException {
		Map<List<String>, List<Long>> pathToLeafID = new HashMap<List<String>, List<Long>>();
		List<List<String>> paths = super.getChains();
		try {
			for (int i=0 ; i < paths.size() ; i++) {
				List<String> path = new ArrayList<String>();
				path.addAll(paths.get(i));
				path.remove(path.size() - 1);
				if (pathToLeafID.containsKey(path)) {
					pathToLeafID.get(path).add(leafIDs.get(i));
				}
				else {
					List<Long> newListOfIDs = new ArrayList<Long>();
					newListOfIDs.add(leafIDs.get(i));
					pathToLeafID.put(path, newListOfIDs);
				}
			}	
		}
		catch (Exception e) {
			throw new GrammarModelException("TreePaths.getDimensionToLeafIDs() : error. " 
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
		else throw new GrammarModelException("TreePaths.getLeaf() : leaf not found.");
		return leaf;
	}

}
