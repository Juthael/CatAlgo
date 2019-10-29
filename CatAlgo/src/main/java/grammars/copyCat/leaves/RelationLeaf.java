package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class RelationLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public RelationLeaf() {
		super("relatioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new RelationLeaf();
	}

}
