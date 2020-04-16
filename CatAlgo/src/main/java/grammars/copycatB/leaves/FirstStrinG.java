package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycatB.disjunctions.IStringName;

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
