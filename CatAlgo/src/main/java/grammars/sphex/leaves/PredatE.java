package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class PredatE extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "predate";
	
	public PredatE() {
	}

	public PredatE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new PredatE (super.getLeafID());
	}

}
