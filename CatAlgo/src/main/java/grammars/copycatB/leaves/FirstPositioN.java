package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycatB.disjunctions.IEndPositionValue;

public class FirstPositioN extends SyntaxLeaf implements ISyntaxLeaf, IEndPositionValue {

	public static final String NAME = "FirstPositioN";
	
	public FirstPositioN() {
	}

	public FirstPositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new FirstPositioN(super.getLeafID());
	}

}
