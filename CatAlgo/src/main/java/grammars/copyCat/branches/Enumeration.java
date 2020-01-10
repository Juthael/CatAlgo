package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IRule;
import grammars.copyCat.disjunctions.IValueOrRelation;
import grammars.copyCat.leaves.EnumerationLeaf;

public class Enumeration extends SyntacticBranch implements ISyntacticBranch, IRule {

	private final static EnumerationLeaf LABEL_LEAF = new EnumerationLeaf();
	private IValueOrRelation valOrRel;
	
	public Enumeration(IValueOrRelation valOrRel) {
		super("enumeration");
		this.valOrRel = valOrRel;
	}

	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(LABEL_LEAF);
		components.add(valOrRel);
		return components;
	}

	public boolean replaceComponent(ISyntacticStructure newComp, Integer compID) {
		boolean compReplaced = false;
		if (valOrRel.getListOfLeafIDs().contains(compID)) {
			if (valOrRel.getListOfLeafIDs().size() == 1) {
				valOrRel = (IValueOrRelation) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = valOrRel.replaceComponents(newComp, compID);
			}
		}
		return compReplaced;
	}

	@Override
	public ISyntacticStructure clone() {
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new Enumeration(valOrRelClone);
	}

}
