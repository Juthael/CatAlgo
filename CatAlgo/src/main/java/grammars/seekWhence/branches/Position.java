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
 * Position represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
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
