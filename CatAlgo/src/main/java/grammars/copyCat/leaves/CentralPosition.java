package grammars.copyCat.leaves;

import grammarModel.ISyntacticLeaf;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticLeaf;
import grammars.copyCat.disjunctions.ISpecifiedProminentPosition;

public class CentralPosition extends SyntacticLeaf implements ISyntacticLeaf, ISpecifiedProminentPosition {

	public CentralPosition() {
		super("centralPositioN");
	}

	@Override
	public ISyntacticStructure clone() {
		return new CentralPosition();
	}

}
