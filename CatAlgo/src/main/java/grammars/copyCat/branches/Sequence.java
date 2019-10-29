package grammars.copyCat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.ISyntacticBranch;
import grammarModel.ISyntacticStructure;
import grammarModel.impl.SyntacticBranch;
import grammars.copyCat.disjunctions.IRule;
import grammars.copyCat.leaves.SequenceLeaf;

public class Sequence extends SyntacticBranch implements ISyntacticBranch, IRule {

	private final static SequenceLeaf LABEL_LEAF = new SequenceLeaf();
	private FirstValue firstValue;
	private Increment increment;
	
	public Sequence(FirstValue firstValue, Increment increment) {
		super("sequence");
		this.firstValue = firstValue;
		this.increment = increment;
	}

	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(LABEL_LEAF);
		components.add(firstValue);
		components.add(increment);
		return components;
	}

	public boolean replaceComponent(ISyntacticBranch newComp, Integer compID) {	
		boolean compReplaced = false;
		if (firstValue.getListOfLeafIDs().contains(compID)) {
			if (firstValue.getListOfLeafIDs().size() == 1) {
				firstValue = (FirstValue) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = firstValue.replaceComponent(newComp, compID);
			}
		}
		else if (increment.getListOfLeafIDs().contains(compID)) {
			if (increment.getListOfLeafIDs().size() == 1) {
				increment = (Increment) newComp;
				compReplaced = true;
			}
			else {
				compReplaced = increment.replaceComponent(newComp, compID);
			}
		}
		return compReplaced;
	}

	@Override
	public ISyntacticStructure clone() {
		FirstValue firstValueClone = (FirstValue) firstValue.clone();
		Increment incrementClone = (Increment) increment.clone();
		return new Sequence(firstValueClone, incrementClone);
	}

}
