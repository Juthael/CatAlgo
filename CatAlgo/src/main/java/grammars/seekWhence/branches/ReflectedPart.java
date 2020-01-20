package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.ReflectedParT;

/**
 * ReflectedPart, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class ReflectedPart extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "ReflectedPart";
	private final ReflectedParT reflectedParT;
	private IValueOrRelation valOrRel;
	
	/**
	 * As any {@link ISyntaxBranch}, ReflectedPart is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
	public ReflectedPart(ReflectedParT reflectedParT, IValueOrRelation valOrRel) {
		this.reflectedParT = reflectedParT;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(reflectedParT);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ReflectedParT reflectedParTClone = (ReflectedParT) reflectedParT.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new ReflectedPart(reflectedParTClone, valOrRelClone);
	}

}
