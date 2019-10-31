package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class ProminentPositionLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public ProminentPositionLeaf() {
		super("prominentPositioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new ProminentPositionLeaf();
	}

}
