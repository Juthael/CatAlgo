package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;

public class EnumerationLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public EnumerationLeaf() {
		super("enumeratioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new EnumerationLeaf();
	}

}
