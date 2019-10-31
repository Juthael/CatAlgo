package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
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

	public boolean replaceComponent(ISyntacticBranch newComp, Integer compID) {	
		boolean compReplaced = false;
		if (letterValue.getListOfLeafIDs().contains(compID)) {
			if (letterValue.getListOfLeafIDs().size() == 1) {
				letterValue = (LetterValue) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = letterValue.replaceComponent(newComp, compID);
			}
		}
		else if (position.getListOfLeafIDs().contains(compID)) {
			if (position.getListOfLeafIDs().size() == 1) {
				position = (IAbstractPosition) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = position.replaceComponent(newComp, compID);
			}
		} 
		return compReplaced;
	}

	@Override
	public ISyntacticStructure clone() {
		LetterValue letterValueClone = (LetterValue) letterValue.clone();
		IAbstractPosition positionClone = (IAbstractPosition) position.clone();
		return new Letter(letterValueClone, positionClone);
	}

}
