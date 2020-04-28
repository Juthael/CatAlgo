package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class SupplyStatuS extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "supplyStatus";
	
	public SupplyStatuS() {
	}

	public SupplyStatuS(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new SupplyStatuS (super.getLeafID());
	}

}
