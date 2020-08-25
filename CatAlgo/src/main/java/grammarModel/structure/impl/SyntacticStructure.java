package grammarModel.structure.impl;

import java.util.List;
import java.util.Map;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.ITreePaths;
import grammarModel.utils.impl.TreePaths;
import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IFunctionalExpression;

public abstract class SyntacticStructure implements ISyntacticStructure, Cloneable {
	
	String name;
	protected int recursionIndex = 0;
	protected boolean recursionIndexHasBeenSet = false;
	
	public SyntacticStructure() {
	}
	
	//getters
	
	@Override
	public abstract ISyntacticStructure clone() throws CloneNotSupportedException;
	
	@Override
	public abstract IBinaryRelation getBinaryRelation();
	
	@Override
	public abstract IFunctionalExpression getFunctionalExpression();
	
	@Override
	public abstract List<ISyntacticStructure> getListOfComponents();
	
	@Override
	public abstract List<Long> getListOfLeafIDs();
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public abstract List<List<String>> getPathsAsListsOfStrings();
	
	@Override
	public int getRecursionIndex() {
		return recursionIndex;
	}
	
	@Override
	public ITreePaths getTreePaths() throws GrammarModelException {
		ITreePaths treePaths;
		List<List<String>> listOfPaths = getPathsAsListsOfStrings();
		List<Long> leafIDs = getListOfLeafIDs();
		treePaths = new TreePaths(listOfPaths, leafIDs);
		return treePaths;
	}	
	
	//setters
	
	@Override
	public abstract void markRecursion() throws GrammarModelException;
	
	@Override 
	public abstract boolean replaceComponents(ISyntacticStructure newComp, List<Long> compIDs);
	
	@Override
	public abstract Map<String, Integer> setRecursionIndex() throws GrammarModelException;

}
