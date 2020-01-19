package grammarModel.structure.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import grammarModel.GrammarModelConstants;
import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.ITreePaths;

public abstract class SyntaxLeaf extends SyntacticStructure implements ISyntaxLeaf {

	private final long leafID;
	private static int ID_COUNT = 0;
	
	public SyntaxLeaf() {
		leafID = ID_COUNT;
		ID_COUNT++;
	}
	
	public SyntaxLeaf(long leafID) {
		this.leafID = leafID;
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
	public List<List<String>> getListOfTreeStringPaths() {
		List<List<String>> treePaths = new ArrayList<List<String>>();
		if (!GrammarModelConstants.REDUNDANCIES_REMOVED || !this.isRedundant()) {
			List<String> treePath = new ArrayList<String>();
			treePath.add(getName());
			treePaths.add(treePath);
		}
		return treePaths;
	}

	@Override
	public List<List<String>> getListOfPosetMaxStringChains() throws GrammarModelException {
		List<List<String>> posetChains = new ArrayList<List<String>>();
		if (!GrammarModelConstants.REDUNDANCIES_REMOVED || !this.isRedundant()) {
			List<String> posetChain = new ArrayList<String>();
			posetChain.add(getPosetElementName());
			posetChains.add(posetChain);
		}
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
	public void setPosetElementID(Map<ITreePaths, String> chainsToIndex) {
	}
	
	@Override
	public boolean replaceComponents(ISyntacticStructure newComp, List<Long> compID) {
		return false;
	}

}
