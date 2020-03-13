package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycatB.disjunctions.IStringName;

public class SecondStrinG extends SyntaxLeaf implements ISyntaxLeaf, IStringName {

	public static final String NAME="SecondStrinG";
	
	public SecondStrinG() {
	}

	public SecondStrinG(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new SecondStrinG(super.getLeafID());
	}

}
