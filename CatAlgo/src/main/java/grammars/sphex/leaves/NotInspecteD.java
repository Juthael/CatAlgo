package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.sphex.disjunctions.IInspectionStatus;

public class NotInspecteD extends SyntaxLeaf implements ISyntaxLeaf, IInspectionStatus {

	private static final String NAME = "notInspected";
	
	public NotInspecteD() {
	}

	public NotInspecteD(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new NotInspecteD (super.getLeafID());
	}

}
