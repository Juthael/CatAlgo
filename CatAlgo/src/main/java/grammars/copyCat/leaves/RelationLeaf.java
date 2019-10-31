package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class RelationLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public RelationLeaf() {
		super("relatioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new RelationLeaf();
	}

}
