package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

public class EnumerationLeaf extends SyntacticLeaf implements ISyntacticLeaf {

	public EnumerationLeaf() {
		super("enumeratioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new EnumerationLeaf();
	}

}
