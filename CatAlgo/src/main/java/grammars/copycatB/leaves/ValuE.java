package grammars.copycatB.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.copycatB.disjunctions.IValueOrClusteredValue;

public class ValuE extends SyntaxLeaf implements ISyntaxLeaf, IValueOrClusteredValue {

	public final String value;
	
	public ValuE(String value) {
		this.value = value;
	}

	public ValuE(String value, long leafID) {
		super(leafID);
		this.value = value;
	}

	@Override
	public String getName() {
		return value;
	}

	@Override
	public ISyntacticStructure clone() {
		return new ValuE(value, super.getLeafID());
	}

}
