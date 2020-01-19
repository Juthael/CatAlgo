package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.DigiT;

/**
 * Digit, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Digit extends SyntaxBranch implements ISyntaxBranch {
	
	private static final String NAME = "Digit";
	private final DigiT digiT;
	private IValueOrRelation valOrRel;
	private final Position position;

	public Digit(DigiT digiT, IValueOrRelation valOrRel, Position position) {
		this.digiT = digiT;
		this.valOrRel = valOrRel;
		this.position = position;
		super.tree = true;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(digiT);
		components.add(valOrRel);
		components.add(position);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		DigiT digiTClone = (DigiT) digiT.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		Position positionClone = (Position) position.clone();
		return new Digit(digiTClone, valOrRelClone, positionClone);
	}

}
