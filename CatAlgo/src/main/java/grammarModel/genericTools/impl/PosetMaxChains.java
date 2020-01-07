package grammarModel.genericTools.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IPosetMaxChains;
import propertyPoset.IImplication;
import propertyPoset.impl.Implication;

public class PosetMaxChains extends Chains implements IPosetMaxChains {
	
	private Set<IImplication> implications = new HashSet<IImplication>();
	private int currentConsequentIndex;
	private String currentAntecedent;
	
	public PosetMaxChains(List<List<String>> listOfChains) throws GrammarModelException {
		super(listOfChains);
		currentConsequentIndex = -1;
		this.implications = setSetOfImplications();
	}
	
	public PosetMaxChains(List<List<String>> listOfChains, Set<IImplication> implications) throws GrammarModelException {
		super(listOfChains);
		currentConsequentIndex = -1;
		this.implications = implications;
	}	

	public List<List<String>> getMaximalChains() {
		return super.getChains();
	}
	
	public Set<String> getProperties() {
		Set<String> properties = new HashSet<String>();
		for (List<String> chain : listOfChains) {
			for (String prop : chain) {
				properties.add(prop);
			}
		}
		return properties;
	}

	public Set<IImplication> getImplications() {
		return implications;
	}
	
	public void resetIndexes() {
		super.resetIndexes();
		currentConsequentIndex = -1;
	}
	
	private Set<IImplication> setSetOfImplications() throws GrammarModelException{
		Set<IImplication> implications = new HashSet<IImplication>();
		while (this.hasNextImplication()) {
			implications.add(getNextImplication());
		}
		resetIndexes();
		return implications;
	}
	
	private boolean hasNextImplication() {
		return super.hasNext();
	}
	
	private IImplication getNextImplication() throws GrammarModelException {
		IImplication currentImplication;
		try {
			if (this.hasNextConsequent()) {
				currentConsequentIndex++;
			}
			else {
				currentAntecedent = super.next();
				currentConsequentIndex = super.currentElementIndex;
				
			}
			currentImplication = 
					new Implication(currentAntecedent, listOfChains.get(currentChainIndex).get(currentConsequentIndex));
		}
		catch (GrammarModelException g) {
			throw g;
		}
		catch (Exception e) {
			throw new GrammarModelException("PosetMaxChains.getNextImplication() : error");
		}
		return currentImplication;
	}
	
	private boolean hasNextConsequent() {
		if (super.listOfChains.get(currentChainIndex).size() >= currentConsequentIndex +2)
			return true;
		else return false;
	}

}
