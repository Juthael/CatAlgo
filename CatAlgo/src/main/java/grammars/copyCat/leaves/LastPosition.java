package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;
import grammars.copyCat.disjunctions.IEndPosition;

public class LastPosition extends SyntacticLeaf implements ISyntacticLeaf, IEndPosition {

	public LastPosition() {
		super("lastPositioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new LastPosition();
	}

}
