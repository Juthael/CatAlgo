package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.disjunctions.ITermOrVarValue;
import grammars.seekWhence.leaves.ValuE;

/**
 * Value represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class Value extends SyntacticBranch implements ISyntacticBranch, ITermOrVarValue {

	public final String value;
	public final ValuE valuE;
	public ITermOrVarValue termOrVarValue;
	
	public Value(ValuE valuE, ITermOrVarValue termOrVarValue) {
		this.value = valuE.getName();
		this.valuE = valuE;
		this.termOrVarValue = termOrVarValue;
	}

	@Override
	public String getName() {
		return value;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(valuE);
		components.add(termOrVarValue);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ValuE valuEClone = (ValuE) valuE.clone();
		ITermOrVarValue termOrVarValueClone = (ITermOrVarValue) termOrVarValue.clone();
		return new Value(valuEClone, termOrVarValueClone);
	}

}
