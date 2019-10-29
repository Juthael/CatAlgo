package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticBranch;
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
