package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IRule;
import grammars.seekWhence.leaves.CyclE;
import grammars.seekWhence.leaves.ValuE;

/**
 * Cycle, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Cycle extends SyntaxBranch implements ISyntaxBranch, IRule {
	
	private static final String NAME = "Cycle";
	private final CyclE cyclE;
	private final ValuE valuE;

	/**
	 * As any {@link ISyntaxBranch}, Cycle is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
	public Cycle(CyclE cyclE, ValuE valuE) {
		this.cyclE = cyclE;
		this.valuE = valuE;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(cyclE);
		components.add(valuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		CyclE cyclEClone = (CyclE) cyclE.clone();
		ValuE valuEClone = (ValuE) valuE.clone();
		return new Cycle(cyclEClone, valuEClone);
	}

}
