package grammarModel.structure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;

public abstract class SyntaxLeaf extends SyntacticStructure implements ISyntaxLeaf {

	private static final String RECURSION_SYMBOL = "#";
	private static int ID_COUNT = 0;
	private String recursionMark = "";
	private final long leafID;
	
	public SyntaxLeaf() {
		leafID = ID_COUNT;
		ID_COUNT++;
	}
	
	public SyntaxLeaf(long leafID) {
		this.leafID = leafID;
		ID_COUNT++;
	}
	
	//getters
	
	@Override
	public long getLeafID() {
		return leafID;
	}	
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> comp = new ArrayList<ISyntacticStructure>();
		return comp;
	}	
	
	@Override
	public List<Long> getListOfLeafIDs() {
		List<Long> iDs = new ArrayList<Long>();
		iDs.add(leafID);
		return iDs;
	}
	
	@Override
	public List<List<String>> getPathsAsListsOfStrings() {
		List<List<String>> treePaths = new ArrayList<List<String>>();
		List<String> treePath = new ArrayList<String>();
		treePath.add(getName());
		treePaths.add(treePath);
		return treePaths;
	}	
	
	//setters
	
	@Override
	public void markRecursion() throws GrammarModelException { 
	}	
	
	@Override
	public boolean replaceArguments(ISyntacticStructure newComp, List<Long> compID) {
		return false;
	}	
	
	@Override
	public Map<String, Integer> setRecursionIndex() {
		Map<String, Integer> nameOntoRecursionIndex = new HashMap<String, Integer>();
		nameOntoRecursionIndex.put(name, 0);
		return nameOntoRecursionIndex;
	}
	
	@Override
	public void setRecursionMark(int recursionIndex) throws GrammarModelException {
		if (recursionMark.isEmpty()) {
			for (int i=0 ; i<recursionIndex ; i++) {
				recursionMark = recursionMark.concat(RECURSION_SYMBOL);
			}
		}
		else throw new GrammarModelException("SyntaxLeaf.setRecursionMark() : the mark has already been set.");
	}	

}
