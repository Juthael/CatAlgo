package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammars.seekWhence.disjunctions.ISymmetry;
import grammars.seekWhence.leaves.SymmetrY;
import grammars.seekWhence.leaves.SymmetryWithCenteR;

/**
 * SymmetryWithCenter, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class SymmetryWithCenter extends Symmetry implements ISyntaxBranch, ISymmetry {

	private final SymmetryWithCenteR symmetryWithCenteR;
	private final Center center;
	
	/**
	 * As any {@link ISyntaxBranch}, SymmetryWithCenter is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
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
