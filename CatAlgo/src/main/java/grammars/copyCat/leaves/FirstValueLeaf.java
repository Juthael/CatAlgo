package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class FirstValueLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public FirstValueLeaf() {
		super("firstValuE");
	}

	@Override
	public ISyntacticStructure clone() {
		return new FirstValueLeaf();
	}

}
