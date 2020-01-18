package grammarModel.genericTools.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
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

	@Override
	public List<List<String>> getMaximalChains() {
		return super.getChains();
	}
	
	@Override
	public Set<String> getProperties() {
		Set<String> properties = new HashSet<String>();
		for (List<String> chain : listOfChains) {
			for (String prop : chain) {
				properties.add(prop);
			}
		}
		return properties;
	}

	@Override
	public Set<IImplication> getImplications() {
		return implications;
	}
	
	@Override
	public void resetIndexes() {
		super.resetIndexes();
		currentConsequentIndex = -1;
	}
	
	/**
	 * 
	 * @return the set of implications associated with the semilattice, if it hasn't been given as a constructor parameter.
	 * @throws GrammarModelException
	 */
	private Set<IImplication> setSetOfImplications() throws GrammarModelException{
		Set<IImplication> implications = new HashSet<IImplication>();
		while (this.hasNextImplication()) {
			implications.add(getNextImplication());
		}
		resetIndexes();
		return implications;
	}
	
	/**
	 * 
	 * @return true if another implication is to be found by navigating the chains (even if it is a redundant one, 
	 * because the set of implications already contains it)
	 */
	private boolean hasNextImplication() {
		return super.hasNext();
	}
	
	/**
	 * 
	 * @return the next implication found by navigating the chains (may be redundant, if the set of implications already 
	 * contains it)
	 * @throws GrammarModelException
	 */
	private IImplication getNextImplication() throws GrammarModelException {
		IImplication currentImplication;
		try {
			if (this.hasNextConsequent()) {
				if (currentAntecedent == null)
					currentAntecedent = super.next();
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
	
	/**
	 * 
	 * @return true if another consequent is to be found for the current antecedent in the current chain. 
	 * @throws GrammarModelException
	 */
	private boolean hasNextConsequent() {
		if (super.listOfChains.get(currentChainIndex).size() >= currentConsequentIndex +2)
			return true;
		else return false;
	}

}
