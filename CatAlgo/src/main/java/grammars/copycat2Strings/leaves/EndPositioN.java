package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class EndPositioN extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "endPosition";
	
	public EndPositioN() {
	}

	public EndPositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME.concat(recursionMark);
	}

	@Override
	public ISyntacticStructure clone() {
		return new EndPositioN(super.getLeafID());
	}

}
