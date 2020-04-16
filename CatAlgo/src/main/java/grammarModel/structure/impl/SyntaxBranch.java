package grammarModel.structure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import grammarModel.GrammarModelConstants;
import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.ITreePaths;

public abstract class SyntaxBranch extends SyntacticStructure implements ISyntaxBranch {

	@SuppressWarnings("unused")
	private ISyntaxLeaf labelLeaf;
	private String posetID = "";
	private boolean iDHasBeenSet = false;
	protected boolean tree = false;
	protected int recursionIndex = 0;
	protected boolean recursionIndexHasBeenSet = false;
	
	public SyntaxBranch() {
	}

	@Override
	public boolean replaceComponents(ISyntacticStructure newComp, List<Long> compIDs) {
		boolean compReplaced = false;
		ListIterator<ISyntacticStructure> compIt = getListOfComponents().listIterator();
		while (compIt.hasNext() && compReplaced == false) {
			ISyntacticStructure comp = compIt.next();
			List<Long> compLeafIDs = comp.getListOfLeafIDs();
			if (compLeafIDs.removeAll(compIDs) == true) {
				if (compLeafIDs.size() == 1) {
					compIt.set(newComp);
					compReplaced = true;
				}
				else {
					compReplaced = comp.replaceComponents(newComp, compIDs);
				}
			}
		}
		return compReplaced;
	}
	
	@Override
	public Map<String, Integer> setRecursionIndex() throws GrammarModelException {
		Map<String, Integer> propNameToRecursionIdx = new HashMap<String, Integer>();
		if (!recursionIndexHasBeenSet) {
			for (ISyntacticStructure component : getListOfComponents()) {
				Map<String, Integer> compPropNameToRecursIdx = component.setRecursionIndex();
				for (String propName : compPropNameToRecursIdx.keySet()) {
					if (!propNameToRecursionIdx.containsKey(propName) 
							|| (propNameToRecursionIdx.get(propName) < compPropNameToRecursIdx.get(propName))) {
						propNameToRecursionIdx.put(propName, compPropNameToRecursIdx.get(propName));
					}
				}
			}
			if (propNameToRecursionIdx.containsKey(this.getName())) {
				recursionIndex = propNameToRecursionIdx.get(this.getName()) + 1;
				propNameToRecursionIdx.put(this.getName(), recursionIndex);
			}
			else {
				propNameToRecursionIdx.put(this.getName(), recursionIndex);
			}
		}
		else throw new GrammarModelException("SyntacticStructure.eliminateRecursion() : this method has already "
				+ "been called.");
		recursionIndexHasBeenSet = true;
		return propNameToRecursionIdx;
	}	
	
	@Override
	public void markRecursion() throws GrammarModelException {
		if (recursionIndexHasBeenSet) {
			ISyntaxLeaf eponymLeaf = getEponymLeaf();
			eponymLeaf.setRecursionMark(recursionIndex);
		}
		else throw new GrammarModelException("SyntaxBranch.markRecursion() : recursion cannot be marked if "
				+ "the recursion index hasn't been set beforehand.");
	}
	
	@Override
	public String getPosetElementName() throws GrammarModelException {
		if (iDHasBeenSet == false) {
			throw new GrammarModelException("SyntaxBranch.getPosetFullName() : "
					+ "cannot return poset full name since ID hasn't been set.");
		}
		else return getName().concat(posetID);
	}
	
	@Override
	public List<List<String>> getListOfTreeStringPaths(){
		List<List<String>> synChains = new ArrayList<List<String>>();
		for (ISyntacticStructure component : getListOfComponents()) {
			List<List<String>> compSynChains = component.getListOfTreeStringPaths();
			for (List<String> chain : compSynChains) {
				chain.add(0, getName());
				synChains.add(chain);
			}
		}
		return synChains;
	}
	
	@Override
	public List<List<String>> getListOfPosetMaxStringChains() throws GrammarModelException {
		List<List<String>> posetChains = new ArrayList<List<String>>();
		for (ISyntacticStructure component : getListOfComponents()) {
			List<List<String>> compPosetChains = component.getListOfPosetMaxStringChains();
			for (List<String> chain : compPosetChains) {
				chain.add(0, getPosetElementName());
				posetChains.add(chain);
			}
		}
		return posetChains;
	}

	@Override
	public List<Long> getListOfLeafIDs() {
		List<Long> leafIDs = new ArrayList<Long>();
		for (ISyntacticStructure component : getListOfComponents()) {
			leafIDs.addAll(component.getListOfLeafIDs());
		}
		return leafIDs;
	}
	
	@Override
	public String getStringOfTerminals() {
		String stringOfTerminals;
		StringBuilder sB = new StringBuilder();
		List<ISyntacticStructure> components = getListOfComponents();
		for (int i=0 ; i < components.size() ; i++) {
			sB.append(components.get(i).getStringOfTerminals());
			if (i < components.size() - 1) {
				sB.append(GrammarModelConstants.SEPARATOR);
			}
		}
		stringOfTerminals = sB.toString();
		return stringOfTerminals;
	}
	
	@Override
	public boolean getIDHasBeenSet() {
		return iDHasBeenSet;
	}
	
	@Override
	public boolean isATree() {
		return tree;
	}
	
	@Override
	public void setPosetElementID(Map<ITreePaths, String> chainsToIndex) throws GrammarModelException {
		posetID = chainsToIndex.get(getTreePaths());
		for (ISyntacticStructure component : getListOfComponents()) {
			component.setPosetElementID(chainsToIndex);
		}
		iDHasBeenSet = true;
	}

}
