package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class ProminentPositionLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public ProminentPositionLeaf() {
		super("prominentPositioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new ProminentPositionLeaf();
	}

}
