package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IValueOrRelation;
import grammars.copyCat.leaves.LetterValueLeaf;

public class LetterValue extends SyntacticBranch implements ISyntacticBranch {

	private final static LetterValueLeaf LABEL_LEAF = new LetterValueLeaf();
	private IValueOrRelation valOrRel;
	
	public LetterValue(IValueOrRelation valOrRel) {
		super("letterValue");
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
