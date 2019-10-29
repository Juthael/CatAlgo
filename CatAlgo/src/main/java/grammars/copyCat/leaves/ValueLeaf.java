package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class ValueLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public ValueLeaf(String value) {
		super(value);
	}

	@Override
	public ISyntacticStructure clone() {
		return new ValueLeaf(this.getName());
	}

}
