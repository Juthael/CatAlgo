package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IRule;
import grammars.copyCat.disjunctions.IValueOrRelation;
import grammars.copyCat.leaves.RelationLeaf;

public class Relation extends SyntacticBranch implements ISyntacticBranch, IValueOrRelation {

	private final static RelationLeaf LABEL_LEAF = new RelationLeaf();
	private Size size;
	private IRule rule;
	
	public Relation(Size size, IRule rule) {
		super("relation");
		this.size = size;
		this.rule = rule;
	}

	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(LABEL_LEAF);
		components.add(size);
		components.add(rule);
		return components;
	}

	public boolean replaceComponent(ISyntacticBranch newComp, Integer compID) {	
		boolean compReplaced = false;
		if (size.getListOfLeafIDs().contains(compID)) {
			if (size.getListOfLeafIDs().size() == 1) {
				size = (Size) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = size.replaceComponent(newComp, compID);
			}
		}
		else if (rule.getListOfLeafIDs().contains(compID)) {
			if (rule.getListOfLeafIDs().size() == 1) {
				rule = (IRule) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = rule.replaceComponent(newComp, compID);
			}
		}
		return compReplaced;
	}

	@Override
	public ISyntacticStructure clone() {
		Size sizeClone = (Size) size.clone();
		IRule ruleClone = (IRule) rule.clone();
		return new Relation(sizeClone, ruleClone);
	}

}
