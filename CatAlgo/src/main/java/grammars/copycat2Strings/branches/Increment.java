package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.defaultRules.disjunctions.IValueOrClusteredValue;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.leaves.IncremenT;

public class Increment extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Increment";
	private final IncremenT incremenT;
	private final IValueOrClusteredValue value;
	
	public Increment(IncremenT incremenT, IValueOrClusteredValue value) {
		this.incremenT = incremenT;
		this.value = value;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return incremenT;
	}		

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(incremenT);
		components.add(value);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		IncremenT incremenTClone = (IncremenT) incremenT.clone();
		IValueOrClusteredValue valOrRelClone = (IValueOrClusteredValue) value.clone();
		return new Increment(incremenTClone, valOrRelClone);
	}

}
