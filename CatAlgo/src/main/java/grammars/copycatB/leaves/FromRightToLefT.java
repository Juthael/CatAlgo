package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycatB.disjunctions.IDirectionValue;

public class FromRightToLefT extends SyntaxLeaf implements ISyntaxLeaf, IDirectionValue {

	public static final String NAME = "fromRightToLeft";
	
	public FromRightToLefT() {
	}

	public FromRightToLefT(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new FromRightToLefT(super.getLeafID());
	}

}
