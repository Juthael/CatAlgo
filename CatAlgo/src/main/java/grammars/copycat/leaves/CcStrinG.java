package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class CcStrinG extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "CcStrinG";
	
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
		// TODO Auto-generated method stub
		return null;
	}

}
