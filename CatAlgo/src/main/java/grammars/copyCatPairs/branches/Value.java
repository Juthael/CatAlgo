package grammars.copyCatPairs.branches;

import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copyCatPairs.disjunctions.IValueOrValueLeaf;

public class Value extends SyntaxBranch implements ISyntaxBranch, IValueOrValueLeaf {

	public Value() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISyntacticStructure clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
