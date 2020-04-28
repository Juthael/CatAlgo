package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IPosition;
import grammars.sphex.leaves.PositioN;

public class Position extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Position";
	private PositioN positioN;
	private IPosition position;
	
	public Position(PositioN positioN, IPosition position) {
		this.positioN = positioN;
		this.position = position;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(positioN);
		components.add(position);
		return components;
	}

	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return positioN;
	}

	@Override
	public ISyntacticStructure clone() {
		PositioN positioNClone = (PositioN) positioN.clone();
		IPosition positionClone = (IPosition) position.clone();
		return new Position(positioNClone, positionClone);
	}

}
