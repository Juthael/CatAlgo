package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class LetterLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public LetterLeaf() {
		super("letteR");
	}

	@Override
	public ISyntacticStructure clone() {
		return new LetterLeaf();
	}

}
