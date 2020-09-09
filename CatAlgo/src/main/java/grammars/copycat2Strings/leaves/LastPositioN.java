package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat2Strings.disjunctions.IEndPositionValue;

public class LastPositioN extends SyntaxLeaf implements ISyntaxLeaf, IEndPositionValue {

	public static final String NAME = "lastPosition";
	
	public LastPositioN() {
	}

	public LastPositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME.concat(recursionMark);
	}

	@Override
	public ISyntacticStructure clone() {
		return new LastPositioN(super.getLeafID());
	}

}
