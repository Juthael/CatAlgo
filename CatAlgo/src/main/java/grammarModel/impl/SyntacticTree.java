package grammarModel.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import exceptions.GrammarModelException;
import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticChains;
import grammarModel.ISyntacticLeaf;
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

	@Override
	public boolean replaceComponent(ISyntacticBranch newComp, int compID) {
		boolean componentReplaced = false;
		ListIterator<ISyntacticStructure> compIt = listOfComponents.listIterator();
		while (compIt.hasNext()) {
			ISyntacticStructure comp = compIt.next();
			if (comp instanceof grammarModel.ISyntacticLeaf) {
				ISyntacticLeaf compLeaf = (ISyntacticLeaf) comp;
				if (compLeaf.getLeafID() == compID) {
					compIt.set(newComp);
					componentReplaced = true;
				}
			}
			else {
				if (comp.getListOfLeafIDs().contains(compID)) {
					ISyntacticBranch compBranch = (ISyntacticBranch) comp;
					componentReplaced = compBranch.replaceComponent(newComp, compID);
				}
			}
		}
		return componentReplaced;
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
