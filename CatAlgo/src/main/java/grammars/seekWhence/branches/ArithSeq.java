package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.disjunctions.IRule;
import grammars.seekWhence.leaves.ArithSeQ;

/**
 * ArithSeq represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class ArithSeq extends SyntacticBranch implements ISyntacticBranch, IRule {
	
	private static final String NAME = "ArithSeq";
	private final ArithSeQ arithSeQ;
	private final FirstValue firstValue;
	private final Increment increment;

	public ArithSeq(ArithSeQ arithSeQ, FirstValue firstValue, Increment increment) {
		this.arithSeQ = arithSeQ;
		this.firstValue = firstValue;
		this.increment = increment;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(arithSeQ);
		components.add(firstValue);
		components.add(increment);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ArithSeQ arithSeQClone = (ArithSeQ) arithSeQ.clone();
		FirstValue firstValueClone = (FirstValue) firstValue.clone();
		Increment incrementClone = (Increment) increment.clone();
		return new ArithSeq(arithSeQClone, firstValueClone, incrementClone);
	}

}
