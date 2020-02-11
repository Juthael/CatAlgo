package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IValueOrRelation;
import grammars.copycat.leaves.IncremenT;

public class Increment extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Increment";
	private final IncremenT incremenT;
	private final IValueOrRelation valOrRel;
	
	public Increment(IncremenT incremenT, IValueOrRelation valOrRel) {
		this.incremenT = incremenT;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(incremenT);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		IncremenT incremenTClone = (IncremenT) incremenT.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new Increment(incremenTClone, valOrRelClone);
	}

}
