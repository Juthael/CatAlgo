package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycatB.disjunctions.IEndPositionValue;

public class LastPositioN extends SyntaxLeaf implements ISyntaxLeaf, IEndPositionValue {

	public static final String NAME = "lastPosition";
	
	public LastPositioN() {
	}

	public LastPositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new LastPositioN(super.getLeafID());
	}

}
