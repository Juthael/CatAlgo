package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.sphex.disjunctions.IPosition;

public class GrabbeD extends SyntaxLeaf implements ISyntaxLeaf, IPosition {

	private static final String NAME = "grabbed";
	
	public GrabbeD() {
	}

	public GrabbeD(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new GrabbeD (super.getLeafID());
	}

}
