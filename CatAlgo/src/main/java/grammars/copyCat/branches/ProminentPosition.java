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
