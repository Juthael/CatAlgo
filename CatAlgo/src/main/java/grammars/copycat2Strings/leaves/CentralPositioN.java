package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat2Strings.disjunctions.ISpecifiedProminentPosition;

public class CentralPositioN extends SyntaxLeaf implements ISyntaxLeaf, ISpecifiedProminentPosition {

	public static final String NAME = "centralPosition";
	
	public CentralPositioN() {
	}

	public CentralPositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME.concat(recursionMark);
	}

	@Override
	public ISyntacticStructure clone() {
		return new CentralPositioN(super.getLeafID());
	}

}
