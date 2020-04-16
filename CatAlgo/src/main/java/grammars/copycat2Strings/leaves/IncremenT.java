package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class IncremenT extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "increment";
	
	public IncremenT() {
	}

	public IncremenT(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new IncremenT(super.getLeafID());
	}

}
