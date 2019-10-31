package grammarModel.structure.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IPosetMaxChains;
import grammarModel.genericTools.ISynTreeChains;
import grammarModel.genericTools.impl.PosetMaxChains;
import grammarModel.genericTools.impl.SynTreeChains;
import grammarModel.structure.ISyntacticStructure;
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

	public ISynTreeChains getSyntacticChains() throws GrammarModelException {
		List<List<String>> listOfChains = getListOfSyntacticStringChains();
		List<Integer> leafIDs = getListOfLeafIDs();
		ISynTreeChains synChains = new SynTreeChains(listOfChains, leafIDs);
		return synChains;
	}
	
	public Set<ISynTreeChains> getSetOfSyntacticChains() throws GrammarModelException {
		Set<ISynTreeChains> setOfChains = new HashSet<ISynTreeChains>();
		setOfChains.add(getSyntacticChains());
		for (ISyntacticStructure component : getListOfComponents()) {
			setOfChains.addAll(component.getSetOfSyntacticChains());
		}
		return setOfChains;
	}
	
	public IPosetMaxChains getPosetMaxChains() throws GrammarModelException {
		IPosetMaxChains posetMaxChains;
		List<List<String>> listOfPosetMaxChains = getListOfPosetMaxStringChains();
		Set<IImplication> relation = getImplications();
		posetMaxChains = new PosetMaxChains(listOfPosetMaxChains, relation);
		return posetMaxChains;
	}
	
	public Set<IImplication> getImplications() throws GrammarModelException {
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
