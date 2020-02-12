package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class ElementValuE extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "ElementValuE";
	
	public ElementValuE() {
	}

	public ElementValuE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new ElementValuE(super.getLeafID());
	}

}
