package representation.dataFormats.impl;

import java.util.HashSet;
import java.util.Set;

import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IPair;
import representation.dataFormats.impl.utils.utilsBR.MaxTransitiveChain;
import representation.dataFormats.impl.utils.utilsBR.MaxTransitiveChains;
import representation.dataFormats.impl.utils.utilsBR.Pair;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;
import representation.stateMachine.impl.Word;

public class BinaryRelation implements IBinaryRelation {
	
	Set<IPair> pairs;

	public BinaryRelation(Set<IPair> pairs) {
		this.pairs = pairs;
	}

	@Override
	public IBinaryRelation getBinaryRelation() {
		return this;
	}

	@Override
	public boolean meets(IDescription description) {
		boolean otherDescMeetsThis;
		Set<IPair> otherSetOfPairs = description.getBinaryRelation().getPairs();
		otherDescMeetsThis = (otherSetOfPairs.containsAll(pairs));
		return otherDescMeetsThis;
	}

	@Override
	public ILanguage getLanguage() throws RepresentationException {
		ILanguage language;
		Set<MaxTransitiveChain> knownTransChain = new HashSet<MaxTransitiveChain>();
		MaxTransitiveChains finalTransChain = new MaxTransitiveChains();
		ISymbol root;
		try {
			root = getRoot();
		}
		catch (RepresentationException e) {
			throw new RepresentationException("BinaryRelation.getLanguage() : root search failed. " + System.lineSeparator() 
				+ e.getMessage());
		}
		MaxTransitiveChain rootChain = new MaxTransitiveChain(root);
		knownTransChain.add(rootChain);
		while (!knownTransChain.isEmpty()) {
			Set<MaxTransitiveChain> newTransChains = new HashSet<MaxTransitiveChain>();
			for (MaxTransitiveChain currentChain : knownTransChain) {
				ISymbol lastSymbol = currentChain.getLast();
				Set<IPair> newChainPairs = getPairsStartingWith(lastSymbol);
				if (newChainPairs.isEmpty())
					finalTransChain.add(currentChain);
				else {
					boolean currentChainHasBeenExtended = false;
					for (IPair newChainPair : newChainPairs) {
						ISymbol newSymbol = newChainPair.getConsequent();
						Set<IPair> relationExtensForTransitivity = new HashSet<IPair>();
						boolean remainsTransitive = true;
						int chainIndex = 0;
						while (remainsTransitive && chainIndex < currentChain.size()) {
							ISymbol indSymbol = currentChain.get(chainIndex);
							Pair pairForTransitivity = new Pair(indSymbol, newSymbol);
							if (pairs.contains(pairForTransitivity))
								relationExtensForTransitivity.add(pairForTransitivity);
							else remainsTransitive = false;
							chainIndex++;
						}
						if (remainsTransitive) {
							MaxTransitiveChain newChain = currentChain.clone();
							newChain.extend(newSymbol, relationExtensForTransitivity);
							newTransChains.add(newChain);
							currentChainHasBeenExtended = true;
						}
					}
					if (!currentChainHasBeenExtended)
						finalTransChain.add(currentChain);
				}
			}
			knownTransChain = newTransChains;
		}
		Set<IWord> words = new HashSet<IWord>();
		for (MaxTransitiveChain chain : finalTransChain.getMaxTransChains())
			words.add(new Word(chain.getChain()));
		language = new Language(words);
		return language;
	}

	@Override
	public IFunctionalExpression getFunctionalExpression() throws RepresentationException {
		IFunctionalExpression functionalExpression;
		try {
			functionalExpression =  getLanguage().getFunctionalExpression();
		}
		catch (RepresentationException e) {
			throw new RepresentationException("BinaryRelation.getFunctionalExpression() : the language is needed as "
					+ "an intermediate format ; an error has occured while building it." + System.lineSeparator() 
					+ e.getMessage());
		}
		return functionalExpression;
	}

	@Override
	public Set<IPair> getPairs() {
		return pairs;
	}

	@Override
	public IGrammar getGrammar() throws RepresentationException {
		IGrammar grammar;
		try {
			grammar =  getLanguage().getGrammar();
		}
		catch (RepresentationException e) {
			throw new RepresentationException("BinaryRelation.getGrammar() : the language is needed as "
					+ "an intermediate format ; an error has occured while building it." + System.lineSeparator() 
					+ e.getMessage());
		}
		return grammar;
	}

	@Override
	public int getNbOfArgumentsFor(ISymbol symbol) throws RepresentationException {
		int nbOfArguments;
		if (getPairsStartingWith(symbol).isEmpty() && getPairsEndingWith(symbol).isEmpty())
			throw new RepresentationException("BinaryRelation.getNbOfArguments(ISymbol) : the specified symbol "
					+ "cannot be found");
		else {
			try {
				nbOfArguments =  getLanguage().getNbOfArgumentsFor(symbol);
			}
			catch (RepresentationException e) {
				throw new RepresentationException("BinaryRelation.getNbOfArgumentsFor(ISymbol) : the language is needed as "
						+ "an intermediate format ; an error has occured while building it." + System.lineSeparator() 
						+ e.getMessage());
			}
		}
		return nbOfArguments;
	}
	
	private ISymbol getRoot() throws RepresentationException {
		ISymbol root;
		Set<ISymbol> antecedents = new HashSet<ISymbol>();
		Set<ISymbol> consequents = new HashSet<ISymbol>();
		for (IPair pair : pairs) {
			antecedents.add(pair.getAntecedent());
			consequents.add(pair.getConsequent());
		}
		antecedents.removeAll(consequents);
		if (antecedents.size() != 1) {
			throw new RepresentationException("BinaryRelation.getRoot() : there should be one (and only one) antecedent "
					+ "that is the consequent of no other");
		}
		else root = antecedents.iterator().next();
		return root;
	}
	
	private Set<IPair> getPairsStartingWith(ISymbol symbol){
		Set<IPair> pairsWithSpecAnt = new HashSet<IPair>();
		for (IPair currentPair : pairs) {
			if (currentPair.getAntecedent().equals(symbol))
				pairsWithSpecAnt.add(currentPair);
		}
		return pairsWithSpecAnt;
	}
	
	private Set<IPair> getPairsEndingWith(ISymbol symbol){
		Set<IPair> pairsWithSpecCsqt = new HashSet<IPair>();
		for (IPair currentPair : pairs) {
			if (currentPair.getConsequent().equals(symbol))
				pairsWithSpecCsqt.add(currentPair);
		}
		return pairsWithSpecCsqt;
	}	
	

}
