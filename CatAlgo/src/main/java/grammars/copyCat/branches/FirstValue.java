package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IValueOrRelation;
import grammars.copyCat.leaves.FirstValueLeaf;

public class FirstValue extends SyntacticBranch implements ISyntacticBranch {

	private final static FirstValueLeaf LABEL_LEAF = new FirstValueLeaf();
	private IValueOrRelation valOrRel;
	
	public FirstValue(IValueOrRelation valOrRel) {
		super("firstValue");
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
