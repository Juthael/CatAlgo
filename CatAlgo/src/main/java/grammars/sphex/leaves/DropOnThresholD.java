package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class DropOnThresholD extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "dropOnThreshold";
	
	public DropOnThresholD() {
	}

	public DropOnThresholD(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new DropOnThresholD (super.getLeafID());
	}

}
