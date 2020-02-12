package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat.disjunctions.IDirectionValue;

public class FromLeftToRighT extends SyntaxLeaf implements ISyntaxLeaf, IDirectionValue {

	public static final String NAME = "FromLeftToRighT";
	
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
