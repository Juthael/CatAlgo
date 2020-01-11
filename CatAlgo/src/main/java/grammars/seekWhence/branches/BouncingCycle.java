package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.disjunctions.IRule;
import grammars.seekWhence.leaves.BouncingCyclE;
import grammars.seekWhence.leaves.ValuE;

/**
 * BouncingCycle represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class BouncingCycle extends SyntacticBranch implements ISyntacticBranch, IRule {
	
	private static final String NAME = "BouncingCycle";
	private final BouncingCyclE bouncingCyclE;
	private final ValuE valuE;

	public BouncingCycle(BouncingCyclE bouncingCyclE, ValuE valuE) {
		this.bouncingCyclE = bouncingCyclE;
		this.valuE = valuE;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(bouncingCyclE);
		components.add(valuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		BouncingCyclE bouncingCyclEClone = (BouncingCyclE) bouncingCyclE.clone();
		ValuE valuEClone = (ValuE) valuE.clone();
		return new BouncingCycle(bouncingCyclEClone, valuEClone);
	}

}
