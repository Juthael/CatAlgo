package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class TimePositioN extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "timePosition";
	
	public TimePositioN() {
	}

	public TimePositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new TimePositioN (super.getLeafID());
	}

}
