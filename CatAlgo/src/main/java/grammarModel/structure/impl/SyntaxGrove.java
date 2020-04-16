package grammarModel.structure.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.structure.ISyntaxLeaf;
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
	
	@Override
	public void markRecursion() throws GrammarModelException {
		for (ISyntacticStructure component : listOfTrees) {
			component.setRecursionIndex();
		}
		for (ISyntacticStructure component : listOfTrees) {
			component.markRecursion();
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

	/**
	 * A grove has no leaf.
	 */
	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return null;
	}

}
