package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.sphex.disjunctions.IPosition;

public class RandomPlacE extends SyntaxLeaf implements ISyntaxLeaf, IPosition {

	private static final String NAME = "randomPlace";
	
	public RandomPlacE() {
		// TODO Auto-generated constructor stub
	}

	public RandomPlacE(long leafID) {
		super(leafID);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new RandomPlacE (super.getLeafID());
	}

}
