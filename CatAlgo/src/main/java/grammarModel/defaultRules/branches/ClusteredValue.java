package grammarModel.defaultRules.branches;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grammarModel.defaultRules.disjunctions.IValueOrClusteredValue;
import grammarModel.defaultRules.leaves.ValuE;
import grammarModel.exceptions.GrammarModelException;
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
	
	@Override
	public Map<String, Integer> setRecursionIndex() throws GrammarModelException {
		Map<String, Integer> propNameToRecursionIdx = new HashMap<String, Integer>();
		if (!recursionIndexHasBeenSet) {
			Map<String, Integer> clusterPropNameToRecursIdx = cluster.setRecursionIndex();
			for (String propName : clusterPropNameToRecursIdx.keySet()) {
				if (!propNameToRecursionIdx.containsKey(propName) 
						|| (propNameToRecursionIdx.get(propName) < clusterPropNameToRecursIdx.get(propName))) {
					propNameToRecursionIdx.put(propName, clusterPropNameToRecursIdx.get(propName));
				}
			}
			valuE.setRecursionIndex();
			if (propNameToRecursionIdx.containsKey(this.getName())) {
				recursionIndex = propNameToRecursionIdx.get(this.getName()) + 1;
				propNameToRecursionIdx.put(this.getName(), recursionIndex);
			}
			else propNameToRecursionIdx.put(this.getName(), recursionIndex);
		}
		else throw new GrammarModelException("SyntacticStructure.setRecursionIndex() : this method has already "
				+ "been called.");
		recursionIndexHasBeenSet = true;
		return propNameToRecursionIdx;
	}	

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return valuE;
	}	
	
	@Override
	public String getPosetElementName() throws GrammarModelException {
		if (iDHasBeenSet == false) {
			throw new GrammarModelException("SyntaxBranch.getPosetFullName() : "
					+ "cannot return poset full name since ID hasn't been set.");
		}
		else return getName().concat("_" + posetID);
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
