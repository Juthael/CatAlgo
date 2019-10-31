package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
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

	public boolean replaceComponent(ISyntacticBranch newComp, Integer compID) {
		boolean compReplaced = false;
		if (valOrRel.getListOfLeafIDs().contains(compID)) {
			if (valOrRel.getListOfLeafIDs().size() == 1) {
				valOrRel = (IValueOrRelation) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = valOrRel.replaceComponent(newComp, compID);
			}
		}
		return compReplaced;
	}

	@Override
	public ISyntacticStructure clone() {
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new Position(valOrRelClone);
	}

}
