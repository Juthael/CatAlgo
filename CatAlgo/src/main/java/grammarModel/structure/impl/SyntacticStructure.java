package grammarModel.structure.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import grammarModel.GrammarModelConstants;
import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.ITreePaths;
import grammarModel.utils.impl.TreePaths;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;
import propertyPoset.utils.impl.Implication;
import propertyPoset.utils.impl.PosetMaxChains;

public abstract class SyntacticStructure implements ISyntacticStructure {

	protected boolean redundant;
	
	public SyntacticStructure() {
		redundant = false;
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
			if (!GrammarModelConstants.REDUNDANCIES_REMOVED || !component.isRedundant())
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
			if (!GrammarModelConstants.REDUNDANCIES_REMOVED || !component.isRedundant()) {
				Set<IImplication> compImplications = component.getImplications();
				implications.addAll(compImplications);
				for (IImplication compImplication : compImplications) {
					IImplication newImpl = new Implication(posetFullName, compImplication.getConsequent());
					implications.add(newImpl);
				}
			}
		}
		return implications;
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
	public boolean isRedundant() {
		return redundant;
	}
	
	@Override
	public void markRedundancies() {
		List<ISyntacticStructure> components = getListOfComponents();
		for (int i=0 ; i < components.size() ; i++) {
			String currTerminals = 
					GrammarModelConstants.SEPARATOR.concat(components.get(i).getStringOfTerminals());
			for (int j=i+1 ; j < components.size() ; j++) {
				if (!components.get(i).isRedundant()) {
					String comparedTerminals = 
							GrammarModelConstants.SEPARATOR.concat(components.get(j).getStringOfTerminals());
					if (comparedTerminals.contains(currTerminals)) {
						components.get(i).setAsRedundant();
					}					
				}
			}
		}
		for (ISyntacticStructure component : components) {
			if (!component.isRedundant())
				component.markRedundancies();
		}
	}
	
	@Override
	public void setAsRedundant() {
		redundant = true;
		for (ISyntacticStructure component : getListOfComponents()) {
			component.setAsRedundant();
		}
	}
	
	@Override
	public abstract ISyntacticStructure clone();

}
