package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;
import grammars.copyCat.disjunctions.IEndPosition;

public class FirstPosition extends SyntacticLeaf implements ISyntacticLeaf, IEndPosition {

	public FirstPosition() {
		super("firstPositioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new FirstPosition();
	}

}
