package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class SequenceLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public SequenceLeaf() {
		super("sequencE");
	}

	@Override
	public ISyntacticStructure clone() {
		return new SequenceLeaf();
	}

}
