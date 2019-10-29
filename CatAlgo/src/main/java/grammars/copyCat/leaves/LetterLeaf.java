package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class LetterLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public LetterLeaf() {
		super("letter");
	}

	@Override
	public ISyntacticStructure clone() {
		return new LetterLeaf();
	}

}
