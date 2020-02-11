package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat.disjunctions.IStringName;

public class FirstStrinG extends SyntaxLeaf implements ISyntaxLeaf, IStringName {

	public static final String NAME = "FirstStrinG";
	
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
		// TODO Auto-generated method stub
		return null;
	}

}
