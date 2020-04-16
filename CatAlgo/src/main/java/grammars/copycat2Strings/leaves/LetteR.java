package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class LetteR extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "letter";
	
	public LetteR() {
	}

	public LetteR(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new LetteR(super.getLeafID());
	}

}
