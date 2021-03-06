package grammarModel.structure.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.ITreePaths;
import grammarModel.utils.impl.TreePaths;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;
import propertyPoset.utils.impl.Implication;
import propertyPoset.utils.impl.PosetMaxChains;

public abstract class SyntacticStructure implements ISyntacticStructure {
	
	protected int recursionIndex = 0;
	protected boolean recursionIndexHasBeenSet = false;
	
	public SyntacticStructure() {
	}
	
	@Override
	public Map<String, Integer> setRecursionIndex() throws GrammarModelException {
		Map<String, Integer> propNameToRecursionIdx = new HashMap<String, Integer>();
		if (!recursionIndexHasBeenSet) {
			for (ISyntacticStructure component : getListOfComponents()) {
				Map<String, Integer> compPropNameToRecursIdx = component.setRecursionIndex();
				for (String propName : compPropNameToRecursIdx.keySet()) {
					if (!propNameToRecursionIdx.containsKey(propName) 
							|| (propNameToRecursionIdx.get(propName) < compPropNameToRecursIdx.get(propName))) {
						propNameToRecursionIdx.put(propName, compPropNameToRecursIdx.get(propName));
					}
				}
			}
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
	public ITreePaths getTreePaths() throws GrammarModelException {
		ITreePaths treePaths;
		List<List<String>> listOfPaths = getListOfTreeStringPaths();
		List<Long> leafIDs = getListOfLeafIDs();
		treePaths = new TreePaths(listOfPaths, leafIDs);
		return treePaths;
	}
	
	@Override
	public Set<ITreePaths> getSetOfTreePaths() throws GrammarModelException {
		Set<ITreePaths> setOfPaths = new HashSet<ITreePaths>();
		setOfPaths.add(getTreePaths());
		for (ISyntacticStructure component : getListOfComponents()) {
			setOfPaths.addAll(component.getSetOfTreePaths());
				
		}
		return setOfPaths;
	}
	
	@Override
	public IPosetMaxChains getPosetMaxChains() throws GrammarModelException {
		IPosetMaxChains posetMaxChains;
		List<List<String>> listOfPosetMaxChains = getListOfPosetMaxStringChains();
		Set<IImplication> relation = getImplications();
		posetMaxChains = new PosetMaxChains(listOfPosetMaxChains, relation);
		return posetMaxChains;
	}
	
	@Override
	public Set<IImplication> getImplications() throws GrammarModelException {
		Set<IImplication> implications = new HashSet<IImplication>();
		String posetFullName = getPosetElementName();
		IImplication reflexive = new Implication(posetFullName, posetFullName);
		implications.add(reflexive);
		for (ISyntacticStructure component : getListOfComponents()) {
			Set<IImplication> compImplications = component.getImplications();
			implications.addAll(compImplications);
			for (IImplication compImplication : compImplications) {
				IImplication newImpl = new Implication(posetFullName, compImplication.getConsequent());
				implications.add(newImpl);
			}
		}
		return implications;
	}
	
	@Override
	public int getRecursionIndex() {
		return recursionIndex;
	}	
	
	@Override
	public boolean hasThisProperty(String prop) {
		boolean hasThisProperty = false;
		if (prop.equals(getName())) {
			hasThisProperty = true;
		}
		else {
			for (ISyntacticStructure component : getListOfComponents()) {
				if (component.hasThisProperty(prop)) {
					hasThisProperty = true;
				}
			}
		}
		return hasThisProperty;
	}
	
	@Override
	public abstract ISyntacticStructure clone();

}
