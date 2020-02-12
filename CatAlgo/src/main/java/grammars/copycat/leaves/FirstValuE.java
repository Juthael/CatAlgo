package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class FirstValuE extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "FirstValuE";
	
	public FirstValuE() {
	}

	public FirstValuE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new FirstValuE(super.getLeafID());
	}

}
