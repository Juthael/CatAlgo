package grammars.copycatB.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycatB.disjunctions.IValueOrClusteredValue;
import grammars.copycatB.leaves.FirstValuE;

public class FirstValue extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "FirstValue";
	private final FirstValuE firstValuE;
	private final IValueOrClusteredValue value;
	
	public FirstValue(FirstValuE firstValuE, IValueOrClusteredValue valOrCluster) {
		this.firstValuE = firstValuE;
		this.value = valOrCluster;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(firstValuE);
		components.add(value);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		FirstValuE firstValuEClone = (FirstValuE) firstValuE.clone();
		IValueOrClusteredValue valOrRelClone = (IValueOrClusteredValue) value.clone();
		return new FirstValue(firstValuEClone, valOrRelClone);
	}

}
