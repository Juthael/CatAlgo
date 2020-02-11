package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IValueOrValuE;
import grammars.copycat.leaves.ValuE;

public class Value extends SyntaxBranch implements ISyntaxBranch, IValueOrValuE {

	private final String value;
	private final ValuE valuE;
	private final IValueOrValuE valueOrValuE;
	
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
		IValueOrValuE valueOrValuEClone = (IValueOrValuE) valueOrValuE.clone();
		return new Value(valuEClone, valueOrValuEClone);
	}

}
