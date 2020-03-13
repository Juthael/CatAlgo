package grammars.copycatB.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycatB.disjunctions.ICoordinateOrCoordinatE;
import grammars.copycatB.disjunctions.IPositionAttribute;
import grammars.copycatB.leaves.PositioN;

public class Position extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Position";
	private final PositioN positioN;
	private final IPositionAttribute positionAttribute;
	private final ICoordinateOrCoordinatE coordinate;
	
	public Position(PositioN positioN, IPositionAttribute positionAttribute, ICoordinateOrCoordinatE coordinate) {
		this.positioN = positioN;
		this.positionAttribute = positionAttribute;
		this.coordinate = coordinate;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(positioN);
		components.add(positionAttribute);
		components.add(coordinate);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		PositioN positioNClone = (PositioN) positioN.clone();
		IPositionAttribute positionAttributeClone = (IPositionAttribute) positionAttribute.clone();
		ICoordinateOrCoordinatE valuOrValuEClone = (ICoordinateOrCoordinatE) coordinate.clone();
		return new Position(positioNClone, positionAttributeClone, valuOrValuEClone);
	}

}
