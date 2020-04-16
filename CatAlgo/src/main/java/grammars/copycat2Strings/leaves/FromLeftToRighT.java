package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat2Strings.disjunctions.IDirectionValue;

public class FromLeftToRighT extends SyntaxLeaf implements ISyntaxLeaf, IDirectionValue {

	public static final String NAME = "fromLeftToRight";
	
	public FromLeftToRighT() {
	}

	public FromLeftToRighT(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new FromLeftToRighT(super.getLeafID());
	}

}
