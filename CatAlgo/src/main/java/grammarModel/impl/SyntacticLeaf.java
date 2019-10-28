package grammarModel.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.grammarModelException;
import grammarModel.ISyntacticChains;
import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;

public abstract class SyntacticLeaf extends SyntacticStructure implements ISyntacticLeaf {

	private final int leafID;
	private static int ID_COUNT = 0;
	
	public SyntacticLeaf(String name) {
		super(name);
		leafID = ID_COUNT;
		ID_COUNT++;
	}

	public String getPosetFullName() throws grammarModelException {
		return getName();
	}

	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> comp = new ArrayList<ISyntacticStructure>();
		return comp;
	}

	public List<List<String>> getListOfSyntacticStringChains() {
		List<List<String>> synChains = new ArrayList<List<String>>();
		List<String> synChain = new ArrayList<String>();
		synChain.add(getName());
		synChains.add(synChain);
		return synChains;
	}

	public List<List<String>> getListOfPosetMaxStringChains() throws grammarModelException {
		List<List<String>> posetChains = new ArrayList<List<String>>();
		List<String> posetChain = new ArrayList<String>();
		posetChain.add(getPosetFullName());
		posetChains.add(posetChain);
		return posetChains;
		
	}
	
	public int getLeafID() {
		return leafID;
	}

	public List<Integer> getListOfLeafIDs() {
		List<Integer> iDs = new ArrayList<Integer>();
		iDs.add(leafID);
		return iDs;
	}

	public boolean getIDHasBeenSet() {
		return true;
	}

	public void setPosetID(Map<ISyntacticChains, String> chainsToIndex) {
	}

}
