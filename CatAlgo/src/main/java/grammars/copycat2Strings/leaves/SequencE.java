package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class SequencE extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME="sequence";
	
	public SequencE() {
	}

	public SequencE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME.concat(recursionMark);
	}

	@Override
	public ISyntacticStructure clone() {
		return new SequencE(super.getLeafID());
	}

}
