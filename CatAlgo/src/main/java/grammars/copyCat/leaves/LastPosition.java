package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;
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
