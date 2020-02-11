package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IPositionAttribute;
import grammars.copycat.disjunctions.IValueOrValuE;
import grammars.copycat.leaves.PositioN;

public class Position extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Position";
	private final PositioN positioN;
	private final IPositionAttribute positionAttribute;
	private final IValueOrValuE valueOrValuE;
	
	public Position(PositioN positioN, IPositionAttribute positionAttribute, IValueOrValuE valueOrValuE) {
		this.positioN = positioN;
		this.positionAttribute = positionAttribute;
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
		components.add(positionAttribute);
		components.add(valueOrValuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		PositioN positioNClone = (PositioN) positioN.clone();
		IPositionAttribute positionAttributeClone = (IPositionAttribute) positionAttribute.clone();
		IValueOrValuE valuOrValuEClone = (IValueOrValuE) valueOrValuE.clone();
		return new Position(positioNClone, positionAttributeClone, valuOrValuEClone);
	}

}
