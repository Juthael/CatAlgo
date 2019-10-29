package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class SequenceLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public SequenceLeaf() {
		super("sequencE");
	}

	@Override
	public ISyntacticStructure clone() {
		return new SequenceLeaf();
	}

}
