package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class SupplyWithAPreY extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "supplyWithAPrey";
	
	public SupplyWithAPreY() {
	}

	public SupplyWithAPreY(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new SupplyWithAPreY (super.getLeafID());
	}

}
