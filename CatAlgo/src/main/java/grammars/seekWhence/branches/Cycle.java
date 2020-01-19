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
 * Cycle represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Cycle extends SyntaxBranch implements ISyntaxBranch, IRule {
	
	private static final String NAME = "Cycle";
	private final CyclE cyclE;
	private final ValuE valuE;

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
