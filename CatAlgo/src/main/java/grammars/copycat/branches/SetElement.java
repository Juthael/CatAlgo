package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IValueOrRelation;
import grammars.copycat.leaves.SetElemenT;

public class SetElement extends SyntaxBranch implements ISyntaxBranch, IValueOrRelation {

	private static final String NAME = "SetElement";
	private final SetElemenT setElemenT;
	private final SetSize setSize;
	private final ElementValue elementValue;
	
	public SetElement(SetElemenT setElemenT, SetSize setSize, ElementValue elementValue) {
		this.setElemenT = setElemenT;
		this.setSize = setSize;
		this.elementValue = elementValue;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(setElemenT);
		components.add(setSize);
		components.add(elementValue);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		SetElemenT setElementClone = (SetElemenT) setElemenT.clone();
		SetSize setSizeClone = (SetSize) setSize.clone();
		ElementValue elementValueClone = (ElementValue) elementValue.clone();
		return new SetElement(setElementClone, setSizeClone, elementValueClone);
	}

}
