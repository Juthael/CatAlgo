package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.leaves.ElementValuE;
import grammars.copycat.leaves.ValuE;

public class ElementValue extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "ElementValue";
	private final ElementValuE elementValuE;
	private final ValuE valuE;
	
	public ElementValue(ElementValuE elementValuE, ValuE value) {
		this.elementValuE = elementValuE;
		this.valuE = value;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(elementValuE);
		components.add(valuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ElementValuE elementValuEClone =  (ElementValuE) elementValuE.clone();
		ValuE valuEClone = (ValuE) valuE.clone();
		return new ElementValue(elementValuEClone, valuEClone);
	}

}
