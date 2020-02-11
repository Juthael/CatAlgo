package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat.disjunctions.IDirectionValue;

public class FromRightToLefT extends SyntaxLeaf implements ISyntaxLeaf, IDirectionValue {

	public static final String NAME = "FromRightToLefT";
	
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
		// TODO Auto-generated method stub
		return null;
	}

}
