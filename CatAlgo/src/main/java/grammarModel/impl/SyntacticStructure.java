package grammarModel.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.grammarModelException;
import grammarModel.IPosetMaxChains;
import grammarModel.ISyntacticChains;
import grammarModel.ISyntacticStructure;
import propertyPoset.IImplication;
import propertyPoset.impl.Implication;

public abstract class SyntacticStructure implements ISyntacticStructure {

	private String name;
	
	public SyntacticStructure(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ISyntacticChains getSyntacticChains() {
		List<List<String>> listOfChains = getListOfSyntacticStringChains();
		List<Integer> leafIDs = getListOfLeafIDs();
		ISyntacticChains synChains = new SyntacticChains(listOfChains, leafIDs);
		return synChains;
	}
	
	public Set<ISyntacticChains> getSetOfSyntacticChains() {
		Set<ISyntacticChains> setOfChains = new HashSet<ISyntacticChains>();
		setOfChains.add(getSyntacticChains());
		for (ISyntacticStructure component : getListOfComponents()) {
			setOfChains.addAll(component.getSetOfSyntacticChains());
		}
		return setOfChains;
	}
	
	public IPosetMaxChains getPosetMaxChains() throws grammarModelException {
		IPosetMaxChains posetMaxChains;
		List<List<String>> listOfPosetMaxChains = getListOfPosetMaxStringChains();
		Set<IImplication> relation = getImplications();
		posetMaxChains = new PosetMaxChains(listOfPosetMaxChains, relation);
		return posetMaxChains;
	}
	
	public Set<IImplication> getImplications() throws grammarModelException {
		Set<IImplication> implications = new HashSet<IImplication>();
		String posetFullName = getPosetFullName();
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
	
	public abstract ISyntacticStructure clone();

}
