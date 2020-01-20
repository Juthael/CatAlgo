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
 * Relation, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
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
	
	/**
	 * As any {@link ISyntaxBranch}, Relation is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
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
