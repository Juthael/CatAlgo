package grammarModel.structure.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.ITreePaths;

public class SyntaxGrove extends SyntaxBranch implements ISyntaxGrove {

	private String name;
	private List<ISyntacticStructure> listOfTrees;
	
	public SyntaxGrove(String name, List<ISyntacticStructure> listOfTrees) {
		this.name = name;
		this.listOfTrees = listOfTrees;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		return listOfTrees;
	}
	
	/**
	 * Looks for redundancies within 'trees' (syntactic structures that are grove components). 
	 * A syntactic structure R is redundant if it is a component of a structure A, and if another component of A 
	 * contains a similar structure R'. 
	 * Put another way : a syntactic structure R is redundant if it is a component of a structure A, and if the string
	 * of terminals generated by R is included in the string of terminal generated by another component of A.  
	 */
	@Override
	public void markRedundancies() {
		for (ISyntacticStructure tree : listOfTrees) {
			tree.markRedundancies();
		}
	}
	
	@Override
	public void setPosetElementID() throws GrammarModelException {
		Set<ITreePaths> setOfITreePaths = getSetOfTreePaths();
		Map<String, Integer> rootToIndex = new HashMap<String, Integer>();
		Map<ITreePaths, String> pathsToIndex = new HashMap<ITreePaths, String>();
		for (ITreePaths paths : setOfITreePaths) {
			String root = paths.getRoot();
			if (!rootToIndex.containsKey(root)) {
				rootToIndex.put(root, 0);
			}
			else {
				Integer index = rootToIndex.get(root);
				index++;
				rootToIndex.put(root, index);
			}
			pathsToIndex.put(paths, rootToIndex.get(root).toString());
		}
		setPosetElementID(pathsToIndex);
	}

	@Override
	public ISyntacticStructure clone() {
		return new SyntaxGrove(getName(), getListOfComponents());
	}

}