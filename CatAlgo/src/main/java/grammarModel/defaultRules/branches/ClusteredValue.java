package grammarModel.defaultRules.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.defaultRules.disjunctions.IValueOrClusteredValue;
import grammarModel.defaultRules.leaves.ValuE;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;

public class ClusteredValue extends SyntaxBranch implements ISyntaxBranch, IValueOrClusteredValue {

	private final String name;
	private ValuE valuE;
	private Cluster cluster;
	
	public ClusteredValue(ValuE valuE, Cluster cluster) {
		name = valuE.getName();
		this.valuE = valuE;
		this.cluster = cluster;
	}
	
	//getters

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public ISyntaxLeaf getFunction() {
		return valuE;
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
