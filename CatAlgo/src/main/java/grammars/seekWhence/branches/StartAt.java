package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.leaves.StartAT;
import grammars.seekWhence.leaves.ValuE;

/**
 * StartAt represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class StartAt extends SyntacticBranch implements ISyntacticBranch {

	private static final String NAME = "StartAt";
	private final StartAT startAT = new StartAT();
	private final ValuE valuE;
	
	public StartAt(StartAT startAT, ValuE valuE) {
		this.valuE = valuE;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(startAT);
		components.add(valuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		StartAT startATClone = (StartAT) startAT.clone();
		ValuE valuEClone = (ValuE) valuE.clone();
		return new StartAt(startATClone, valuEClone);		
	}

}