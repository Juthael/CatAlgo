package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.sphex.disjunctions.ISupplyStatus;

public class SupplieD extends SyntaxLeaf implements ISyntaxLeaf, ISupplyStatus {

	private static final String NAME = "supplied";
	
	public SupplieD() {
	}

	public SupplieD(long leafID) {
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
