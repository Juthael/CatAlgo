package grammars.copycatB.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycatB.disjunctions.IValueOrClusteredValue;
import grammars.copycatB.leaves.LetterValuE;

public class LetterValue extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "LetterValue";
	private final LetterValuE letterValuE;
	private final IValueOrClusteredValue value;
	
	public LetterValue(LetterValuE letterValuE, IValueOrClusteredValue value) {
		this.letterValuE = letterValuE;
		this.value = value;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(letterValuE);
		components.add(value);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		LetterValuE letterValuEClone = (LetterValuE) letterValuE.clone();
		IValueOrClusteredValue valOrRelClone = (IValueOrClusteredValue) value.clone();
		return new LetterValue(letterValuEClone, valOrRelClone);
	}

}
