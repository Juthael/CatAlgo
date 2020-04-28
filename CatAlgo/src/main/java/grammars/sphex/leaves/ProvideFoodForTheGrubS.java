package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class ProvideFoodForTheGrubS extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "provideFoodForTheGrubs";
	
	public ProvideFoodForTheGrubS() {
	}

	public ProvideFoodForTheGrubS(long leafID) {
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
