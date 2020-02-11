package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IRule;
import grammars.copycat.disjunctions.IValueOrRelation;
import grammars.copycat.leaves.RelatioN;

public class Relation extends SyntaxBranch implements ISyntaxBranch, IValueOrRelation {

	private static final String NAME = "Relation";
	private final RelatioN relatioN;
	private final SetSize setSize;
	private final IRule rule;
	
	public Relation(RelatioN relatioN, SetSize setSize, IRule rule) {
		this.relatioN = relatioN;
		this.setSize = setSize;
		this.rule = rule;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(relatioN);
		components.add(setSize);
		components.add(rule);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		RelatioN relatioNClone = (RelatioN) relatioN.clone();
		SetSize setSizeclone = (SetSize) setSize.clone();
		IRule ruleClone = (IRule) rule.clone();
		return new Relation(relatioNClone, setSizeclone, ruleClone);
	}

}
