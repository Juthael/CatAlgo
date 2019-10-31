package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class SizeLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public SizeLeaf() {
		super("sizE");
	}

	@Override
	public ISyntacticStructure clone() {
		return new SizeLeaf();
	}

}
