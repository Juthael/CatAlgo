package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IValueOrRelation;
import grammars.copyCat.leaves.SizeLeaf;

public class Size extends SyntacticBranch implements ISyntacticBranch {

	private final static SizeLeaf LABEL_LEAF = new SizeLeaf();
	private IValueOrRelation valOrRel;
	
	public Size(IValueOrRelation valOrRel) {
		super("size");
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
		return new Size(valOrRelClone);
	}

}
