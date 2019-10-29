package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class LetterValueLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public LetterValueLeaf() {
		super("letterValuE");
	}

	@Override
	public ISyntacticStructure clone() {
		return new LetterValueLeaf();
	}

}
