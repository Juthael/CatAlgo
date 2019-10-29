package grammarModel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import exceptions.GrammarModelException;
import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticChains;
import grammarModel.ISyntacticStructure;
import grammarModel.ISyntacticTree;

public abstract class SyntacticTree extends SyntacticBranch implements ISyntacticTree {

	private List<ISyntacticStructure> listOfComponents;
	
	public SyntacticTree(String name, List<ISyntacticStructure> listOfComponents) {
		super(name);
		this.listOfComponents = listOfComponents;
	}

	public List<ISyntacticStructure> getListOfComponents() {
		return listOfComponents;
	}

	public boolean replaceComponent(ISyntacticBranch newComp, Integer compID) {
		boolean compReplaced = false;
		ListIterator<ISyntacticStructure> compIt = listOfComponents.listIterator();
		while (compIt.hasNext() && compReplaced == false) {
			ISyntacticStructure comp = compIt.next();
			List<Integer> compLeafIDs = comp.getListOfLeafIDs();
			if (compLeafIDs.contains(compID)) {
				if (compLeafIDs.size() == 1) {
					compIt.set(newComp);
					compReplaced = true;
				}
				else {
					compReplaced = comp.replaceComponent(newComp, compID);
				}
			}
		}
		return compReplaced;
	}
	
	public void setPosetID() throws GrammarModelException {
		Set<ISyntacticChains> setOfSynChains = getSetOfSyntacticChains();
		Map<String, Integer> rootToIndex = new HashMap<String, Integer>();
		Map<ISyntacticChains, String> chainsToIndex = new HashMap<ISyntacticChains, String>();
		for (ISyntacticChains chains : setOfSynChains) {
			String root = chains.getRoot();
			if (!rootToIndex.containsKey(root)) {
				rootToIndex.put(root, 0);
			}
			else {
				Integer index = rootToIndex.get(root);
				index++;
				rootToIndex.put(root, index);
			}
			chainsToIndex.put(chains, rootToIndex.get(root).toString());
		}
		setPosetID(chainsToIndex);
	}

}
