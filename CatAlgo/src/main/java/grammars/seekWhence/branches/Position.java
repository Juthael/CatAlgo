package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.disjunctions.IAlternation;
import grammars.seekWhence.disjunctions.ITermOrVarValue;
import grammars.seekWhence.leaves.PositioN;

/**
 * Position represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class Position extends SyntacticBranch implements ISyntacticBranch {

	private static final String NAME = "Position";
	private final PositioN positioN;
	private IAlternation iAlternation;
	private ITermOrVarValue positionValue;
	
	public Position(PositioN positioN, IAlternation iAlternation, ITermOrVarValue positionValue) {
		this.positioN = positioN;
		this.iAlternation = iAlternation;
		this.positionValue = positionValue;
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
		components.add(positionValue);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		PositioN positioNClone = (PositioN) positioN.clone();
		IAlternation iAlternationClone = (IAlternation) iAlternation.clone();
		ITermOrVarValue positionValueClone = (ITermOrVarValue) positionValue.clone();
		return new Position(positioNClone, iAlternationClone, positionValueClone);
	}

}
