package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IAbstractPosition;
import grammars.copyCat.disjunctions.IValueOrRelation;
import grammars.copyCat.leaves.PositionLeaf;

public class Position extends SyntacticBranch implements ISyntacticBranch, IAbstractPosition {

	private final static PositionLeaf LABEL_LEAF = new PositionLeaf();
	private IValueOrRelation valOrRel;
	
	public Position(IValueOrRelation valOrRel) {
		super("position");
		this.valOrRel = valOrRel;
	}

	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(LABEL_LEAF);
		components.add(valOrRel);
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
