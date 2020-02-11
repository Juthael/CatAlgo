package grammars.copycat.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat.disjunctions.IPositionAttribute;

public class NonProminentPositioN extends SyntaxLeaf implements ISyntaxLeaf, IPositionAttribute {

	public static final String NAME = "NonProminentPositioN";
	
	public NonProminentPositioN() {
	}

	public NonProminentPositioN(long leafID) {
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