package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IDirectionValue;
import grammars.copycat.leaves.DirectioN;

public class Direction extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Direction";
	private final DirectioN direction;
	private final IDirectionValue directionValue;
	
	public Direction(DirectioN direction, IDirectionValue directionValue) {
		this.direction = direction;
		this.directionValue = directionValue;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(direction);
		components.add(directionValue);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		DirectioN directionClone = (DirectioN) direction.clone();
		IDirectionValue directionValueClone = (IDirectionValue) directionValue.clone();
		return new Direction(directionClone, directionValueClone);
	}

}
