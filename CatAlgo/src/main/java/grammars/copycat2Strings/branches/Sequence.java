package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.defaultRules.disjunctions.IRule;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.leaves.SequencE;

public class Sequence extends SyntaxBranch implements ISyntaxBranch, IRule {

	private static final String NAME = "Sequence";
	private final SequencE sequencE;
	private final FirstValue firstValue;
	private final Increment increment;
	
	public Sequence(SequencE sequencE, FirstValue firstValue, Increment increment) {
		this.sequencE = sequencE; 
		this.firstValue = firstValue;
		this.increment = increment;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public ISyntaxLeaf getFunction() {
		return sequencE;
	}	

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(sequencE);
		components.add(firstValue);
		components.add(increment);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		SequencE sequencEClone = (SequencE) sequencE.clone();
		FirstValue firstValueClone = (FirstValue) firstValue.clone();
		Increment incrementClone = (Increment) increment.clone();
		return new Sequence(sequencEClone, firstValueClone, incrementClone);
	}

}
