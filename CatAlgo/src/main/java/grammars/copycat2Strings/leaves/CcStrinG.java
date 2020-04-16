package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class CcStrinG extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "ccString";
	
	public CcStrinG() {
	}

	public CcStrinG(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new CcStrinG(super.getLeafID());
	}

}
