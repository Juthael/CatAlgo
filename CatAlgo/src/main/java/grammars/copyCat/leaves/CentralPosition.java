package grammars.copyCat.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;
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
