package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.sphex.disjunctions.ISupplyStatus;

public class NotSupplieD extends SyntaxLeaf implements ISyntaxLeaf, ISupplyStatus {

	private static final String NAME = "notSupplied";
	
	public NotSupplieD() {
	}

	public NotSupplieD(long leafID) {
		super(leafID);
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new NotSupplieD (super.getLeafID());
	}

}
