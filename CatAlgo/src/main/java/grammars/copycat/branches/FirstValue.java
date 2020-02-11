package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IValueOrRelation;
import grammars.copycat.leaves.FirstValuE;

public class FirstValue extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "FirstValue";
	private final FirstValuE firstValuE;
	private final IValueOrRelation valOrRel;
	
	public FirstValue(FirstValuE firstValuE, IValueOrRelation valOrRel) {
		this.firstValuE = firstValuE;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(firstValuE);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		FirstValuE firstValuEClone = (FirstValuE) firstValuE.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new FirstValue(firstValuEClone, valOrRelClone);
	}

}
