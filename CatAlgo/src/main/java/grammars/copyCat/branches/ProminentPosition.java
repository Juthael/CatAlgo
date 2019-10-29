package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IAbstractPosition;
import grammars.copyCat.disjunctions.ISpecifiedProminentPosition;
import grammars.copyCat.leaves.ProminentPositionLeaf;

public class ProminentPosition extends SyntacticBranch implements ISyntacticBranch, IAbstractPosition {

	private final static ProminentPositionLeaf LABEL_LEAF = new ProminentPositionLeaf();
	private ISpecifiedProminentPosition promPosition;
	private Position position;
	
	public ProminentPosition(ISpecifiedProminentPosition promPosition, Position position) {
		super("prominentPosition");
		this.promPosition = promPosition;
		this.position = position;
	}

	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(LABEL_LEAF);
		components.add(promPosition);
		components.add(position);
		return components;
	}

	public boolean replaceComponent(ISyntacticBranch newComp, Integer compID) {	
		boolean compReplaced = false;
		if (promPosition.getListOfLeafIDs().contains(compID)) {
			if (promPosition.getListOfLeafIDs().size() == 1) {
				promPosition = (ISpecifiedProminentPosition) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = promPosition.replaceComponent(newComp, compID);
			}
		}
		else if (position.getListOfLeafIDs().contains(compID)) {
			if (position.getListOfLeafIDs().size() == 1) {
				position = (Position) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = position.replaceComponent(newComp, compID);
			}
		}
		return compReplaced;
	}

	@Override
	public ISyntacticStructure clone() {
		ISpecifiedProminentPosition promPosClone = (ISpecifiedProminentPosition) promPosition.clone();
		Position positionClone = (Position) position.clone();
		return new ProminentPosition(promPosClone, positionClone);
	}

}
