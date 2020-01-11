package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.leaves.EveryXEleM;
import grammars.seekWhence.leaves.ValuE;

/**
 * EveryXElem represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class EveryXElem extends SyntacticBranch implements ISyntacticBranch {
	
	private static final String NAME = "EveryXElem";
	private final EveryXEleM everyXEleM;
	private final ValuE value;

	public EveryXElem(EveryXEleM everyXEleM, ValuE value) {
		this.everyXEleM = everyXEleM;
		this.value = value;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(everyXEleM);
		components.add(value);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		EveryXEleM everyXEleMClone = (EveryXEleM) everyXEleM.clone();
		ValuE valuEClone = (ValuE) value.clone();
		return new EveryXElem(everyXEleMClone, valuEClone);
	}

}
