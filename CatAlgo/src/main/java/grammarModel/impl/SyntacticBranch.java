package grammarModel.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.grammarModelException;
import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticChains;
import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;

public abstract class SyntacticBranch extends SyntacticStructure implements ISyntacticBranch {

	@SuppressWarnings("unused")
	private ISyntacticLeaf labelLeaf;
	private String posetID = "";
	private boolean iDHasBeenSet = false;
	
	
	public SyntacticBranch(String name) {
		super(name);
	}

	public String getPosetFullName() throws grammarModelException {
		if (iDHasBeenSet == false) {
			throw new grammarModelException("SyntacticBranch.getPosetFullName() : "
					+ "cannot return poset full name since ID hasn't been set.");
		}
		else return getName().concat(posetID);
	}
	
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
	
	public List<List<String>> getListOfPosetMaxStringChains() throws grammarModelException{
		List<List<String>> posetChains = new ArrayList<List<String>>();
		for (ISyntacticStructure component : getListOfComponents()) {
			List<List<String>> compPosetChains = component.getListOfPosetMaxStringChains();
			for (List<String> chain : compPosetChains) {
				chain.add(0, getPosetFullName());
				posetChains.add(chain);
			}
		}
		return posetChains;
	}

	public List<Long> getListOfLeafIDs() {
		List<Long> leafIDs = new ArrayList<Long>();
		for (ISyntacticStructure component : getListOfComponents()) {
			leafIDs.addAll(component.getListOfLeafIDs());
		}
		return leafIDs;
	}
	
	public boolean getIDHasBeenSet() {
		return iDHasBeenSet;
	}
	
	public void setPosetID(Map<ISyntacticChains, String> chainsToIndex) {
		posetID = chainsToIndex.get(getSyntacticChains());
		for (ISyntacticStructure component : getListOfComponents()) {
			component.setPosetID(chainsToIndex);
		}
		iDHasBeenSet = true;
	}
	
	public abstract boolean replaceComponent(ISyntacticBranch newComp, long compID);	

}
