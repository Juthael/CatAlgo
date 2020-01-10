package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
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
		return new LetterValue(valOrRelClone);
	}

}
