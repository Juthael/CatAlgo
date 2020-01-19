package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IRule;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.RelatioN;

/**
 * Relation represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Relation extends SyntaxBranch implements ISyntaxBranch, IValueOrRelation {

	private static final String NAME = "Relation";
	private final RelatioN relatioN;
	private final Size size;
	private final IRule rule;
	
	public Relation(RelatioN relatioN, Size size, IRule rule) {
		this.relatioN = relatioN;
		this.size = size;
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
		components.add(size);
		components.add(rule);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		RelatioN relatioNClone = (RelatioN) relatioN.clone();
		Size sizeClone = (Size) size.clone();
		IRule ruleClone = (IRule) rule.clone();
		return new Relation(relatioNClone, sizeClone, ruleClone);
	}

}
