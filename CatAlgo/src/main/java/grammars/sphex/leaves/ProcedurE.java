package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class ProcedurE extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "procedure";
	
	public ProcedurE() {
	}

	public ProcedurE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new SupplieD (super.getLeafID());
	}

}
