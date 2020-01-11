package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.ReflectedParT;

/**
 * ReflectedPart represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class ReflectedPart extends SyntacticBranch implements ISyntacticBranch {

	private static final String NAME = "ReflectedPart";
	private final ReflectedParT reflectedParT;
	private IValueOrRelation valOrRel;
	
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
