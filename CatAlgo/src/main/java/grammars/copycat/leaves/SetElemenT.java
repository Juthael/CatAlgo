package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class SetElemenT extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME="SetElemenT";
	
	public SetElemenT() {
	}

	public SetElemenT(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new SetElemenT(super.getLeafID());
	}

}
