package grammars.sphex.leaves;

import grammarModel.defaultRules.leaves.ValuE;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;

public class SteP extends ValuE implements ISyntaxLeaf {

	public SteP(String value) {
		super(value);
	}
	
	public SteP(String value, long leafID) {
		super(value, leafID);
	}
	
	@Override
	public ISyntacticStructure clone() {
		return new SteP (value, super.getLeafID());
	}	

}
