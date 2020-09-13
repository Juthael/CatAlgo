package grammarModel.structure.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxGrove;
import representation.dataFormats.IDescription;
import representation.inputOutput.IContextInput;
import representation.inputOutput.impl.ContextInput;

public class SyntaxGrove implements ISyntaxGrove {

	private String name;
	private List<ISyntaxBranch> listOfTrees;
	
	public SyntaxGrove(String name, List<ISyntaxBranch> listOfTrees) {
		this.name = name;
		this.listOfTrees = listOfTrees;
	}
	
	//getters
	
	@Override
	public ISyntaxGrove clone() {
		List<ISyntaxBranch> listOfTreesClone = new ArrayList<ISyntaxBranch>();
		for (ISyntaxBranch tree : listOfTrees)
			listOfTreesClone.add((ISyntaxBranch) tree.clone());
		return new SyntaxGrove(getName(), listOfTreesClone);
	}
	
	@Override
	public IContextInput getContextInput() throws GrammarModelException {
		IContextInput contextInput;
		Set<IDescription> objectDescriptions = new HashSet<IDescription>();
		for (ISyntaxBranch tree : listOfTrees) {
			try {
				objectDescriptions.add(tree.getRelationalDescription());
			} catch (GrammarModelException e) {
				throw new GrammarModelException("SyntaxGrove.getContextInput() : failed to get tree's relational "
						+ "descriptions." + System.lineSeparator() + e.getMessage());
			}
		}
		contextInput = new ContextInput(objectDescriptions);
		return contextInput;
	}
	
	@Override
	public List<ISyntaxBranch> getListOfTrees() {
		return listOfTrees;
	}	
	
	//setters

	@Override
	public String getName() {
		return name;
	}
	

	
	@Override
	public void markRecursion() throws GrammarModelException {
		for (ISyntaxBranch tree : listOfTrees) {
			tree.setRecursionIndex();
		}
		for (ISyntaxBranch tree : listOfTrees) {
			tree.markRecursion();
		}
	}
	
}