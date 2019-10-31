package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class IncrementLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public IncrementLeaf() {
		super("incremenT");
	}

	@Override
	public ISyntacticStructure clone() {
		return new IncrementLeaf();
	}

}
