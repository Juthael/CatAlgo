package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class LetterValueLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public LetterValueLeaf() {
		super("letterValuE");
	}

	@Override
	public ISyntacticStructure clone() {
		return new LetterValueLeaf();
	}

}
