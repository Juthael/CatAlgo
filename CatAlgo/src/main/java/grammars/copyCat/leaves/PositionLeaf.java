package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class PositionLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public PositionLeaf() {
		super("positioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new PositionLeaf();
	}

}
