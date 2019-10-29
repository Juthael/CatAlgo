package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class PositionLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public PositionLeaf() {
		super("positioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new PositionLeaf();
	}

}
