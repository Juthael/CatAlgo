package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class SizE extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME="SizE";
	
	public SizE() {
	}

	public SizE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new SizE(super.getLeafID());
	}

}