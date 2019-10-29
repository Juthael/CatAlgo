package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IAbstractPosition;
import grammars.copyCat.leaves.LetterLeaf;

public class Letter extends SyntacticBranch implements ISyntacticBranch {

	private final static LetterLeaf LABEL_LEAF = new LetterLeaf();
	private LetterValue letterValue;
	private IAbstractPosition position;
	
	public Letter(LetterValue letterValue, IAbstractPosition position) {
		super("letter");
		this.letterValue = letterValue;
		this.position = position;
	}

	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(LABEL_LEAF);
		components.add(letterValue);
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
