package grammarModel.structure.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.ITreePaths;
import grammarModel.utils.impl.TreePaths;

public abstract class SyntacticStructure implements ISyntacticStructure, Cloneable {
	
	protected String name;
	protected int recursionIndex = 0;
	protected boolean recursionIndexHasBeenSet = false;
	
	public SyntacticStructure() {
	}
	
	//getters
	
	@Override
	public abstract ISyntacticStructure clone();		
	
	@Override
	public int getRecursionIndex() {
		return recursionIndex;
	}
	
	//For unit testing use only
	public Set<ISyntacticStructure> getSetOfComponentsAndSubComponents() {
		Set<ISyntacticStructure> comp = new HashSet<ISyntacticStructure>();
		comp.add(this);
		for (ISyntacticStructure currComp : getListOfComponents()) {
			SyntacticStructure component = (SyntacticStructure) currComp;
			comp.addAll(component.getSetOfComponentsAndSubComponents());
		}
		return comp;
	}	
	
	@Override
	public ITreePaths getTreePaths() throws GrammarModelException {
		ITreePaths treePaths;
		List<List<String>> listOfPaths = getPathsAsListsOfStrings();
		List<Long> leafIDs = getListOfLeafIDs();
		treePaths = new TreePaths(listOfPaths, leafIDs);
		return treePaths;
	}	

}
