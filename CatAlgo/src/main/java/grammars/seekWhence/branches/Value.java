package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IValueOrValuE;
import grammars.seekWhence.leaves.ValuE;

/**
 * Value represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Value extends SyntaxBranch implements ISyntaxBranch, IValueOrValuE {

	public final String value;
	public final ValuE valuE;
	public IValueOrValuE valueOrValuE;
	
	public Value(ValuE valuE, IValueOrValuE valueOrValuE) {
		this.value = valuE.getName();
		this.valuE = valuE;
		this.valueOrValuE = valueOrValuE;
	}

	@Override
	public String getName() {
		return value;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(valuE);
		components.add(valueOrValuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ValuE valuEClone = (ValuE) valuE.clone();
		IValueOrValuE termOrVarValueClone = (IValueOrValuE) valueOrValuE.clone();
		return new Value(valuEClone, termOrVarValueClone);
	}

}
