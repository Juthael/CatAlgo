package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class BurroW extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "burrow";
	
	public BurroW() {
	}

	public BurroW(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new BurroW (super.getLeafID());
	}

}
