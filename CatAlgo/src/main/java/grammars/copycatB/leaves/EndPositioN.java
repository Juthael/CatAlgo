package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class EndPositioN extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "EndPositioN";
	
	public EndPositioN() {
	}

	public EndPositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new EndPositioN(super.getLeafID());
	}

}
