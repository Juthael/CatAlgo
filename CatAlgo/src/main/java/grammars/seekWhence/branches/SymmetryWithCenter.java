package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammars.seekWhence.disjunctions.ISymmetry;
import grammars.seekWhence.leaves.SymmetrY;
import grammars.seekWhence.leaves.SymmetryWithCenteR;

/**
 * SymmetryWithCenter represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class SymmetryWithCenter extends Symmetry implements ISyntacticBranch, ISymmetry {

	private final SymmetryWithCenteR symmetryWithCenteR;
	private final Center center;
	
	public SymmetryWithCenter(SymmetrY symmetrY, ReflectedPart reflectedPart, SymmetryWithCenteR symmetryWithCenteR, 
			Center center) {
		super(symmetrY, reflectedPart);
		this.symmetryWithCenteR = symmetryWithCenteR;
		this.center = center;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(super.symmetrY);
		components.add(super.reflectedPart);
		components.add(symmetryWithCenteR);
		components.add(center);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		SymmetrY symmetrYClone = (SymmetrY) super.symmetrY.clone();
		ReflectedPart reflectedPartClone = (ReflectedPart) super.reflectedPart.clone();
		SymmetryWithCenteR symmetryWithCenteRClone = (SymmetryWithCenteR) symmetryWithCenteR.clone();
		Center centerClone = (Center) center.clone();
		return new SymmetryWithCenter(symmetrYClone, reflectedPartClone, symmetryWithCenteRClone, centerClone);
	}

}
