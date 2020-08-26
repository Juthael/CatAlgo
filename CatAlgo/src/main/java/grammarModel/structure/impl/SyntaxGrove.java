package grammarModel.structure.impl;

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
		return new SyntaxGrove(getName(), getListOfTrees());
	}
	
	@Override
	public IContextInput getContextInput() {
		IContextInput contextInput;
		Set<IDescription> objectDescriptions = new HashSet<IDescription>();
		for (ISyntaxBranch tree : listOfTrees) {
			objectDescriptions.add(tree.getBinaryRelation());
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