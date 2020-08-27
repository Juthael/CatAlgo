package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.defaultRules.disjunctions.IRule;
import grammarModel.defaultRules.disjunctions.IValueOrClusteredValue;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.leaves.PatterN;

public class Pattern extends SyntaxBranch implements ISyntaxBranch, IRule {

	private static final String NAME = "Pattern";
	private final PatterN patterN;
	private final IValueOrClusteredValue value;
	
	public Pattern(PatterN patterN, IValueOrClusteredValue value) {
		this.patterN = patterN;
		this.value = value;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public ISyntaxLeaf getFunction() {
		return patterN;
	}		

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(patterN);
		components.add(value);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		PatterN patterNClone = (PatterN) patterN.clone();
		IValueOrClusteredValue valOrRelClone = (IValueOrClusteredValue) value.clone();
		return new Pattern(patterNClone, valOrRelClone);
	}

}
