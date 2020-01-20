package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.ISymmetry;
import grammars.seekWhence.leaves.SymmetrY;

/**
 * Symmetry, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public class Symmetry extends SyntaxBranch implements ISyntaxBranch, ISymmetry {

	private static final String NAME = "Symmetry";
	protected final SymmetrY symmetrY;
	protected final ReflectedPart reflectedPart;
	
	/**
	 * As any {@link ISyntaxBranch}, Symmetry is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
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
