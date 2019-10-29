package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IEndPosition;
import grammars.copyCat.disjunctions.ISpecifiedProminentPosition;
import grammars.copyCat.leaves.EndPositionLeaf;

public class EndPosition extends SyntacticBranch implements ISyntacticBranch, ISpecifiedProminentPosition {

	private final static EndPositionLeaf LABEL_LEAF = new EndPositionLeaf();
	private IEndPosition endPosition;
	
	public EndPosition(IEndPosition endPosition) {
		super("endPosition");
		this.endPosition = endPosition;
	}

	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(LABEL_LEAF);
		components.add(endPosition);
		return components;
	}

	@Override
	public boolean replaceComponent(ISyntacticBranch newComp, int compID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ISyntacticStructure clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
