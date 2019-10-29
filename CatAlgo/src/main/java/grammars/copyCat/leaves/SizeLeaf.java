package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class SizeLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public SizeLeaf() {
		super("sizE");
	}

	@Override
	public ISyntacticStructure clone() {
		return new SizeLeaf();
	}

}
