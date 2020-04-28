package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.sphex.disjunctions.IInspectionStatus;

public class InspecteD extends SyntaxLeaf implements ISyntaxLeaf, IInspectionStatus {

	private static final String NAME = "inspected";
	
	public InspecteD() {
	}

	public InspecteD(long leafID) {
		super(leafID);
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new InspecteD (super.getLeafID());
	}

}
