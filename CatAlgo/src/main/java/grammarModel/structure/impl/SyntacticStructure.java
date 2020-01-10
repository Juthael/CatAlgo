package grammarModel.structure.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IPosetMaxChains;
import grammarModel.genericTools.ISyntacticChains;
import grammarModel.genericTools.impl.PosetMaxChains;
import grammarModel.genericTools.impl.SyntacticChains;
import grammarModel.structure.ISyntacticStructure;
import propertyPoset.IImplication;
import propertyPoset.impl.Implication;

public abstract class SyntacticStructure implements ISyntacticStructure {

	private String name;
	protected boolean redundant;
	
	public SyntacticStructure(String name) {
		this.name = name;
		redundant = false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ISyntacticChains getSyntacticChains() throws GrammarModelException {
		ISyntacticChains synChains;
		List<List<String>> listOfChains = getListOfSyntacticStringChains();
		List<Long> leafIDs = getListOfLeafIDs();
		synChains = new SyntacticChains(listOfChains, leafIDs);
		return synChains;
	}
	
	@Override
	public Set<ISyntacticChains> getSetOfSyntacticChains() throws GrammarModelException {
		Set<ISyntacticChains> setOfChains = new HashSet<ISyntacticChains>();
		setOfChains.add(getSyntacticChains());
		for (ISyntacticStructure component : getListOfComponents()) {
			setOfChains.addAll(component.getSetOfSyntacticChains());
		}
		return setOfChains;
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
	public boolean hasThisProperty(String prop) {
		boolean hasThisProperty = false;
		if (prop.equals(name)) {
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
			String currTerminals = components.get(i).getStringOfTerminals();
			for (int j=i+1 ; j < components.size() ; j++) {
				if (!components.get(i).isRedundant()) {
					String comparedTerminals = components.get(j).getStringOfTerminals();
					if (comparedTerminals.contains(currTerminals)) {
						components.get(i).setAsRedundant();
					}					
				}
			}
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
