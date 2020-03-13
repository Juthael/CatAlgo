package grammars.copycatB.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycatB.disjunctions.IValueOrClusteredValue;
import grammars.copycatB.leaves.ValuE;

public class ClusteredValue extends SyntaxBranch implements ISyntaxBranch, IValueOrClusteredValue {

	private static final String NAME = "-clustered";
	private ValuE valuE;
	private Cluster cluster;
	
	public ClusteredValue(ValuE value, Cluster cluster) {
		this.valuE = value;
		this.cluster = cluster;
	}

	@Override
	public String getName() {
		String valueString = valuE.getName();
		return valueString.concat(NAME);
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(valuE);
		components.add(cluster);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ValuE valuEClone = (ValuE) valuE.clone();
		Cluster clusterClone = (Cluster) cluster.clone();
		return new ClusteredValue(valuEClone, clusterClone);
	}

}
