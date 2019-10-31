package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
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

	public boolean replaceComponent(ISyntacticBranch newComp, Integer compID) {
		boolean compReplaced = false;
		if (endPosition.getListOfLeafIDs().contains(compID)) {
			if (endPosition.getListOfLeafIDs().size() == 1) {
				endPosition = (IEndPosition) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = endPosition.replaceComponent(newComp, compID);
			}
		}
		return compReplaced;
	}

	@Override
	public ISyntacticStructure clone() {
		IEndPosition endPositionClone = (IEndPosition) endPosition.clone();
		return new EndPosition(endPositionClone);
	}

}
