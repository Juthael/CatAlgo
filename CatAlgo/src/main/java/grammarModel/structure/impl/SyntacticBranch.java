package grammarModel.structure.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import exceptions.GrammarModelException;
import grammarModel.GrammarModelConstants;
import grammarModel.genericTools.ISyntacticChains;
import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;

public abstract class SyntacticBranch extends SyntacticStructure implements ISyntacticBranch {

	@SuppressWarnings("unused")
	private ISyntacticLeaf labelLeaf;
	private String posetID = "";
	private boolean iDHasBeenSet = false;
	protected boolean tree = false;
	
	public SyntacticBranch() {
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
	public String getPosetElementName() throws GrammarModelException {
		if (iDHasBeenSet == false) {
			throw new GrammarModelException("SyntacticBranch.getPosetFullName() : "
					+ "cannot return poset full name since ID hasn't been set.");
		}
		else return getName().concat(posetID);
	}
	
	@Override
	public List<List<String>> getListOfSyntacticStringChains(){
		List<List<String>> synChains = new ArrayList<List<String>>();
		for (ISyntacticStructure component : getListOfComponents()) {
			List<List<String>> compSynChains = component.getListOfSyntacticStringChains();
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
	public void setPosetElementID(Map<ISyntacticChains, String> chainsToIndex) throws GrammarModelException {
		posetID = chainsToIndex.get(getSyntacticChains());
		for (ISyntacticStructure component : getListOfComponents()) {
			component.setPosetElementID(chainsToIndex);
		}
		iDHasBeenSet = true;
	}

}
