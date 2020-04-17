package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.defaultRules.branches.Coordinate;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.disjunctions.IPositionAttribute;
import grammars.copycat2Strings.leaves.PositioN;

public class Position extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Position";
	private final PositioN positioN;
	private final IPositionAttribute positionAttribute;
	private final Coordinate coordinate;
	
	public Position(PositioN positioN, IPositionAttribute positionAttribute, Coordinate coordinate) {
		this.positioN = positioN;
		this.positionAttribute = positionAttribute;
		this.coordinate = coordinate;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return positioN;
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
		Coordinate valuOrValuEClone = (Coordinate) coordinate.clone();
		return new Position(positioNClone, positionAttributeClone, valuOrValuEClone);
	}

}
