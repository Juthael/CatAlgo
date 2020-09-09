package grammars.copycat2Strings.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycat2Strings.disjunctions.IPositionAttribute;

public class NonProminentPositioN extends SyntaxLeaf implements ISyntaxLeaf, IPositionAttribute {

	public static final String NAME = "nonProminentPosition";
	
	public NonProminentPositioN() {
	}

	public NonProminentPositioN(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME.concat(recursionMark);
	}

	@Override
	public ISyntacticStructure clone() {
		return new NonProminentPositioN(super.getLeafID());
	}

}
