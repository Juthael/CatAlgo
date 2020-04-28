package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class GraB extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "grab";
	
	public GraB() {
	}

	public GraB(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new GraB (super.getLeafID());
	}

}
