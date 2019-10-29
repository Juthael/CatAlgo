package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class FirstValueLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public FirstValueLeaf() {
		super("firstValuE");
	}

	@Override
	public ISyntacticStructure clone() {
		return new FirstValueLeaf();
	}

}
