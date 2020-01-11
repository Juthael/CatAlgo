package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.disjunctions.ISymmetry;
import grammars.seekWhence.leaves.SymmetrY;

/**
 * Symmetry represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public class Symmetry extends SyntacticBranch implements ISyntacticBranch, ISymmetry {

	private static final String NAME = "Symmetry";
	protected final SymmetrY symmetrY;
	protected final ReflectedPart reflectedPart;
	
	public Symmetry(SymmetrY symmetrY, ReflectedPart reflectedPart) {
		this.symmetrY = symmetrY;
		this.reflectedPart = reflectedPart;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(symmetrY);
		components.add(reflectedPart);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		SymmetrY symmetrYClone = (SymmetrY) symmetrY.clone();
		ReflectedPart reflectedPartClone = (ReflectedPart) reflectedPart.clone();
		return new Symmetry(symmetrYClone, reflectedPartClone);
	}

}
