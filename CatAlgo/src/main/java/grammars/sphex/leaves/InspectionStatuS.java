package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class InspectionStatuS extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "inspectionStatus";
	
	public InspectionStatuS() {
	}

	public InspectionStatuS(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new InspectionStatuS (super.getLeafID());
	}

}
