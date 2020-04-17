package grammarModel.defaultRules.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class CoordinatE extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "coordinate";
	
	public CoordinatE() {
	}

	public CoordinatE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new CoordinatE(super.getLeafID());
	}

}
