package grammars.sphex.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class DragPreyIN extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "dragPreyIn";
	
	public DragPreyIN() {
	}

	public DragPreyIN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new DragPreyIN (super.getLeafID());
	}

}
