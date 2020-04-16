package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.disjunctions.IEndPositionValue;
import grammars.copycat2Strings.disjunctions.ISpecifiedProminentPosition;
import grammars.copycat2Strings.leaves.EndPositioN;

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
	public ISyntaxLeaf getEponymLeaf() {
		return endPositioN;
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
