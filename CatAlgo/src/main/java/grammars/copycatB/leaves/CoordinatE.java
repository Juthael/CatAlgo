package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycatB.disjunctions.ICoordinateOrCoordinatE;

public class CoordinatE extends SyntaxLeaf implements ISyntaxLeaf, ICoordinateOrCoordinatE {

	private final String value;
	
	public CoordinatE(String value) {
		this.value = value;
	}

	public CoordinatE(String value, long leafID) {
		super(leafID);
		this.value = value;
	}

	@Override
	public String getName() {
		return value;
	}

	@Override
	public ISyntacticStructure clone() {
		return new CoordinatE(value, super.getLeafID());
	}

}
