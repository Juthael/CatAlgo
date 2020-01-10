package grammarModel.structure.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.GrammarModelException;
import grammarModel.genericTools.ISyntacticChains;
import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;

public abstract class SyntacticLeaf extends SyntacticStructure implements ISyntacticLeaf {

	private final long leafID;
	private static int ID_COUNT = 0;
	
	public SyntacticLeaf(String name) {
		super(name);
		leafID = ID_COUNT;
		ID_COUNT++;
	}

	@Override
	public String getPosetElementName() throws GrammarModelException {
		return getName();
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> comp = new ArrayList<ISyntacticStructure>();
		return comp;
	}

	@Override
	public List<List<String>> getListOfSyntacticStringChains() {
		List<List<String>> synChains = new ArrayList<List<String>>();
		List<String> synChain = new ArrayList<String>();
		synChain.add(getName());
		synChains.add(synChain);
		return synChains;
	}

	@Override
	public List<List<String>> getListOfPosetMaxStringChains() throws GrammarModelException {
		List<List<String>> posetChains = new ArrayList<List<String>>();
		List<String> posetChain = new ArrayList<String>();
		posetChain.add(getPosetElementName());
		posetChains.add(posetChain);
		return posetChains;
		
	}
	
	@Override
	public long getLeafID() {
		return leafID;
	}

	@Override
	public List<Long> getListOfLeafIDs() {
		List<Long> iDs = new ArrayList<Long>();
		iDs.add(leafID);
		return iDs;
	}
	
	@Override
	public String getStringOfTerminals() {
		return getName();
	}

	@Override
	public boolean getIDHasBeenSet() {
		return true;
	}
	
	@Override
	public void setPosetElementID(Map<ISyntacticChains, String> chainsToIndex) {
	}
	
	@Override
	public boolean replaceComponents(ISyntacticStructure newComp, List<Long> compID) {
		return false;
	}

}
