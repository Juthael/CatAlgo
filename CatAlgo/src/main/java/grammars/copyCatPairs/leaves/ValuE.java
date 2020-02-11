package grammars.copyCatPairs.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copyCatPairs.disjunctions.IValueOrRelation;
import grammars.copyCatPairs.disjunctions.IValueOrValueLeaf;

public class ValuE extends SyntaxLeaf implements ISyntaxLeaf, IValueOrRelation, IValueOrValueLeaf {

	public ValuE() {
		// TODO Auto-generated constructor stub
	}

	public ValuE(long leafID) {
		super(leafID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISyntacticStructure clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
