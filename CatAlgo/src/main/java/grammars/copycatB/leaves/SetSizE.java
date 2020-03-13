package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class SetSizE extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME="SetSizE";
	
	public SetSizE() {
	}

	public SetSizE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new SetSizE(super.getLeafID());
	}

}
