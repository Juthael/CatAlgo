package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class InspecT extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "inspect";
	
	public InspecT() {
	}

	public InspecT(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new InspecT (super.getLeafID());
	}

}
