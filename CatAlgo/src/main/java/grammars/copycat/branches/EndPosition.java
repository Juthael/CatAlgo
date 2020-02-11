package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IEndPositionValue;
import grammars.copycat.disjunctions.ISpecifiedProminentPosition;
import grammars.copycat.leaves.EndPositioN;

public class EndPosition extends SyntaxBranch implements ISyntaxBranch, ISpecifiedProminentPosition {

	private static final String NAME = "EndPosition";
	private final EndPositioN endPositioN;
	private final IEndPositionValue endPositionValue;
	
	public EndPosition(EndPositioN endPositioN, IEndPositionValue endPositionValue) {
		this.endPositioN = endPositioN;
		this.endPositionValue = endPositionValue;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(endPositioN);
		components.add(endPositionValue);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		EndPositioN endPositioNClone = (EndPositioN) endPositioN.clone();
		IEndPositionValue endPositionValueClone = (IEndPositionValue) endPositionValue.clone();
		return new EndPosition(endPositioNClone, endPositionValueClone);
	}

}
