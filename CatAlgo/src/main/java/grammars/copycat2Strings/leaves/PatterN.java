package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class PatterN extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "pattern";
	
	public PatterN() {
	}

	public PatterN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new PatterN(super.getLeafID());
	}

}
