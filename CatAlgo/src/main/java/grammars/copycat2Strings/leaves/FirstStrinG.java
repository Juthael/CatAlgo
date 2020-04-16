package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat2Strings.disjunctions.IStringName;

public class FirstStrinG extends SyntaxLeaf implements ISyntaxLeaf, IStringName {

	public static final String NAME = "firstString";
	
	public FirstStrinG() {
	}

	public FirstStrinG(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new FirstStrinG(super.getLeafID());
	}

}
