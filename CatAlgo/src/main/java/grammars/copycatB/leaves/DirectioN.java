package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class DirectioN extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "direction";
	
	public DirectioN() {
	}

	public DirectioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new DirectioN(super.getLeafID());
	}

}
