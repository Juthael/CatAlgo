package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.disjunctions.IRule;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.EnumeratioN;

/**
 * Enumeration represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class Enumeration extends SyntacticBranch implements ISyntacticBranch, IRule {
	
	private static final String NAME = "Enumeration";
	private final EnumeratioN enumeratioN;
	private IValueOrRelation valOrRel;

	public Enumeration(EnumeratioN enumeratioN, IValueOrRelation valOrRel) {
		this.enumeratioN = enumeratioN;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(enumeratioN);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		EnumeratioN enumeratioNClone = (EnumeratioN) enumeratioN.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new Enumeration(enumeratioNClone, valOrRelClone);
	}

}
