package grammarModel.structure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IPair;
import representation.dataFormats.impl.BinaryRelation;
import representation.dataFormats.impl.FunctionalExpression;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;

public abstract class SyntaxLeaf extends SyntacticStructure implements ISyntaxLeaf {

	public static final String RECURSION_SYMBOL = "#";
	private static int ID_COUNT = 0;
	protected String recursionMark = "";
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
	public IBinaryRelation getBinaryRelation() {
		IBinaryRelation relation;
		Set<IPair> relationPairs = new HashSet<IPair>();
		relation = new BinaryRelation(relationPairs);
		return relation;
	}
	
	@Override
	public IFunctionalExpression getFunctionalExpression() {
		IFunctionalExpression expression;
		ISymbol leafSymbol = new Symbol(name);
		Map<List<Integer>, ISymbol> coordinatesOntoSymbols = new HashMap<List<Integer>, ISymbol>();
		coordinatesOntoSymbols.put(new ArrayList<Integer>(), leafSymbol);
		expression = new FunctionalExpression(coordinatesOntoSymbols);
		return expression;
	}
	
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
