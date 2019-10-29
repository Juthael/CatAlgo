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
