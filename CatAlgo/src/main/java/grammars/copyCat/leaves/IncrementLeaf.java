package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class IncrementLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public IncrementLeaf() {
		super("incremenT");
	}

	@Override
	public ISyntacticStructure clone() {
		return new IncrementLeaf();
	}

}
