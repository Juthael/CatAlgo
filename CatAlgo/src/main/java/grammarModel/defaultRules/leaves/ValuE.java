package grammarModel.defaultRules.leaves;

import grammarModel.defaultRules.disjunctions.IValuEOrCoordSubValue;
import grammarModel.defaultRules.disjunctions.IValueOrClusteredValue;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class ValuE extends SyntaxLeaf implements ISyntaxLeaf, IValueOrClusteredValue, IValuEOrCoordSubValue {

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
