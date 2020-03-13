package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class PositioN extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "PositioN";
	
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
		return new PositioN(super.getLeafID());
	}

}
