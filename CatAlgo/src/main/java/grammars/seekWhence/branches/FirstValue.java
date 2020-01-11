package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.FirstValuE;

/**
 * FirstValue represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class FirstValue extends SyntacticBranch implements ISyntacticBranch {
	
	private static final String NAME = "FirstValue";
	private final FirstValuE firstValuE;
	private IValueOrRelation valOrRel;

	public FirstValue(FirstValuE firstValuE, IValueOrRelation valOrRel) {
		this.firstValuE = firstValuE;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(firstValuE);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		FirstValuE firstValuEClone = (FirstValuE) firstValuE.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new FirstValue(firstValuEClone, valOrRelClone);
	}

}
