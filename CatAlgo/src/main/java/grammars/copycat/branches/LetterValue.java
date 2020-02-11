package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IValueOrRelation;
import grammars.copycat.leaves.LetterValuE;

public class LetterValue extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "LetterValue";
	private final LetterValuE letterValuE;
	private final IValueOrRelation valOrRel;
	
	public LetterValue(LetterValuE letterValuE, IValueOrRelation valOrRel) {
		this.letterValuE = letterValuE;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(letterValuE);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		LetterValuE letterValuEClone = (LetterValuE) letterValuE.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new LetterValue(letterValuEClone, valOrRelClone);
	}

}
