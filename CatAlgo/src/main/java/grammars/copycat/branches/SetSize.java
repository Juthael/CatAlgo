package grammars.copycat.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat.disjunctions.IValueOrRelation;
import grammars.copycat.leaves.SetSizE;

public class SetSize extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "SetSize";
	private final SetSizE setSizE;
	private final IValueOrRelation valOrRel;
	
	public SetSize(SetSizE setSizE, IValueOrRelation valOrRel) {
		this.setSizE = setSizE;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(setSizE);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		SetSizE setSizEClone = (SetSizE) setSizE.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new SetSize(setSizEClone, valOrRelClone);
	}

}
