package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat.disjunctions.IEndPositionValue;

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
		// TODO Auto-generated method stub
		return null;
	}

}
