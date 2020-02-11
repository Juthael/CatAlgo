package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IRule;
import grammars.copycat.disjunctions.IValueOrRelation;
import grammars.copycat.leaves.PatterN;

public class Pattern extends SyntaxBranch implements ISyntaxBranch, IRule {

	private static final String NAME = "Pattern";
	private final PatterN patterN;
	private final IValueOrRelation valOrRel;
	
	public Pattern(PatterN patterN, IValueOrRelation valOrRel) {
		this.patterN = patterN;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(patterN);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		PatterN patterNClone = (PatterN) patterN.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new Pattern(patterNClone, valOrRelClone);
	}

}
