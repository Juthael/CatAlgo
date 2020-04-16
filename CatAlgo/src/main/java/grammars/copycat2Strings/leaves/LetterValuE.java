package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class LetterValuE extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "letterValue";
	
	public LetterValuE() {
	}

	public LetterValuE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new LetterValuE(super.getLeafID());
	}

}
