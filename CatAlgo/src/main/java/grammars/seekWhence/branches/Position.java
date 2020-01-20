package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IAlternation;
import grammars.seekWhence.disjunctions.IValueOrValuE;
import grammars.seekWhence.leaves.PositioN;

/**
 * Position, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Position extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Position";
	private final PositioN positioN;
	private IAlternation iAlternation;
	private IValueOrValuE valueOrValuE;
	
	/**
	 * As any {@link ISyntaxBranch}, Position is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
	public Position(PositioN positioN, IAlternation iAlternation, IValueOrValuE valueOrValuE) {
		this.positioN = positioN;
		this.iAlternation = iAlternation;
		this.valueOrValuE = valueOrValuE;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(positioN);
		components.add(iAlternation);
		components.add(valueOrValuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		PositioN positioNClone = (PositioN) positioN.clone();
		IAlternation iAlternationClone = (IAlternation) iAlternation.clone();
		IValueOrValuE positionValueClone = (IValueOrValuE) valueOrValuE.clone();
		return new Position(positioNClone, iAlternationClone, positionValueClone);
	}

}
