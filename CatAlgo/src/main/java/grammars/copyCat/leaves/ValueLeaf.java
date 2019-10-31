package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class ValueLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public ValueLeaf(String value) {
		super(value);
	}

	@Override
	public ISyntacticStructure clone() {
		return new ValueLeaf(this.getName());
	}

}
