package grammarModel.defaultRules.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class ClusteR extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME="cluster";
	
	public ClusteR() {
	}

	public ClusteR(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new ClusteR(super.getLeafID());
	}

}
