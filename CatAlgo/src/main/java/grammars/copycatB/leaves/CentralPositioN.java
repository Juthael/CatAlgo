package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycatB.disjunctions.ISpecifiedProminentPosition;

public class CentralPositioN extends SyntaxLeaf implements ISyntaxLeaf, ISpecifiedProminentPosition {

	public static final String NAME = "centralPosition";
	
	public CentralPositioN() {
	}

	public CentralPositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new CentralPositioN(super.getLeafID());
	}

}
