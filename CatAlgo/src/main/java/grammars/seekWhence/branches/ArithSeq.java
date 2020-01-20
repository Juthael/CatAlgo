package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IRule;
import grammars.seekWhence.leaves.ArithSeQ;

/**
 * ArithSeq, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class ArithSeq extends SyntaxBranch implements ISyntaxBranch, IRule {
	
	private static final String NAME = "ArithSeq";
	private final ArithSeQ arithSeQ;
	private final FirstValue firstValue;
	private final Increment increment;

	/**
	 * As any {@link ISyntaxBranch}, ArithSeq is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
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
