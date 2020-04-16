package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat2Strings.disjunctions.IStringName;

public class SecondStrinG extends SyntaxLeaf implements ISyntaxLeaf, IStringName {

	public static final String NAME="secondString";
	
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
