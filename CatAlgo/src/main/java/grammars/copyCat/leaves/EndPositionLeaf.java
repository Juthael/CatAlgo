package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class EndPositionLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public EndPositionLeaf() {
		super("endPositioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new EndPositionLeaf();
	}

}
