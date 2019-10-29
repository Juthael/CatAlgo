package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class EndPositionLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public EndPositionLeaf() {
		super("endPositioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new EndPositionLeaf();
	}

}
