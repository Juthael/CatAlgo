package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycatB.disjunctions.IPositionAttribute;

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
		return new NonProminentPositioN(super.getLeafID());
	}

}
