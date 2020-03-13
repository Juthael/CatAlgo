package grammars.copycatB.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycatB.disjunctions.IValueOrClusteredValue;
import grammars.copycatB.leaves.SizE;

public class Size extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "SetSize";
	private final SizE sizE;
	private final IValueOrClusteredValue value;
	
	public Size(SizE sizE, IValueOrClusteredValue value) {
		this.sizE = sizE;
		this.value = value;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(sizE);
		components.add(value);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		SizE sizEClone = (SizE) sizE.clone();
		IValueOrClusteredValue valueClone = (IValueOrClusteredValue) value.clone();
		return new Size(sizEClone, valueClone);
	}

}
