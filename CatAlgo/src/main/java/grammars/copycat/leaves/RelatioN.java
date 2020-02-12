package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class RelatioN extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME="RelatioN";
	
	public RelatioN() {
	}

	public RelatioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new RelatioN(super.getLeafID());
	}

}
