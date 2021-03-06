package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class ProminentPositioN extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "prominentPosition";
	
	public ProminentPositioN() {
	}

	public ProminentPositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new ProminentPositioN(super.getLeafID());
	}

}
