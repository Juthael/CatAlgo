package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class DragInTheBurroW extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "dragInTheBurrow";
	
	public DragInTheBurroW() {
	}

	public DragInTheBurroW(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new DragInTheBurroW (super.getLeafID());
	}

}
