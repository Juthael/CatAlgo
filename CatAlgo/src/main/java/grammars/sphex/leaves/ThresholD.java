package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.sphex.disjunctions.IPosition;

public class ThresholD extends SyntaxLeaf implements ISyntaxLeaf, IPosition {

	private static final String NAME = "threshold";
	
	public ThresholD() {
	}

	public ThresholD(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new ThresholD (super.getLeafID());
	}

}
