package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class PositioN extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "position";
	
	public PositioN() {
	}

	public PositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new PositioN (super.getLeafID());
	}

}
