package representation.dataFormats.impl;

import java.util.HashSet;
import java.util.Iterator;
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
	
	private final Set<IPair> pairs;

	public BinaryRelation(Set<IPair> pairs) {
		this.pairs = pairs;
	}

	@Override
	public IBinaryRelation getBinaryRelation() {
		return this;
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
	public ILanguage getLanguage() throws RepresentationException {
		ILanguage language;
		Set<MaxTransitiveChain> knownTransChains = new HashSet<MaxTransitiveChain>();
		//maximal transitive chains that can't be extended
		MaxTransitiveChains finalTransChains = new MaxTransitiveChains();
		ISymbol root;
		try {
			root = getRoot();
		}
		catch (RepresentationException e) {
			throw new RepresentationException("BinaryRelation.getLanguage() : root search failed. " + System.lineSeparator() 
				+ e.getMessage());
		}
		MaxTransitiveChain rootChain = new MaxTransitiveChain(root);
		//maximal transitive chains known so far, but these chains may be extended
		knownTransChains.add(rootChain);
		while (!knownTransChains.isEmpty()) {
			//new chains built on this run of the 'while' loop, by extending known transitive chains
			Set<MaxTransitiveChain> newTransChains = new HashSet<MaxTransitiveChain>();
			for (MaxTransitiveChain currentChain : knownTransChains) {
				ISymbol lastSymbol = currentChain.getLast();
				Set<IPair> newChainPairs = getPairsStartingWith(lastSymbol);
				if (newChainPairs.isEmpty())
					//this chain cannot be extended, so put in final chains
					finalTransChains.add(currentChain);
				else {
					/*
					 * pairs exist to extend this chain; but until proven otherwise, the associated transitive 
					 * relation does not and the proposed chain extensions do not comply with the transitivity 
					 * constraint.  
					 */
					boolean currentChainHasBeenExtended = false;
					/* 
					 * for each new pair that could extend the chain, searches for the pairs that must be added to the 
					 * chain's associated relation, in order for it to remain transitive after the extension
					 */
					for (IPair newChainPair : newChainPairs) {
						//symbol that could be put at the end of the current chain
						ISymbol newSymbol = newChainPair.getConsequent();
						//pairs that must be added to the chain's associated relation if the chain is to be extended
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
						finalTransChains.add(currentChain);
				}
			}
			knownTransChains = newTransChains;
		}
		Set<IWord> words = new HashSet<IWord>();
		for (MaxTransitiveChain chain : finalTransChains.getMaxTransChains())
			words.add(new Word(chain.getChain()));
		language = new Language(words);
		return language;
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
	
	@Override
	public Set<IPair> getPairs() {
		return pairs;
	}	

	@Override
	public boolean meets(IDescription description) {
		boolean otherDescMeetsThis;
		Set<IPair> otherSetOfPairs = description.getBinaryRelation().getPairs();
		otherDescMeetsThis = (otherSetOfPairs.containsAll(pairs));
		return otherDescMeetsThis;
	}
	
	@Override
	public String toString() {
		String binaryRelationString;
		StringBuilder sB = new StringBuilder();
		Iterator<IPair> pairIterator = pairs.iterator();
		int newLineIndex = 0;
		while (pairIterator.hasNext()) {
			IPair currentPair = pairIterator.next();
			sB.append("(" 
				+ currentPair.getAntecedent().toString() 
				+ ", " 
				+ currentPair.getConsequent().toString()
				+ ")");
			if (pairIterator.hasNext())
				sB.append(", ");
			if (newLineIndex == 8) {
				sB.append(System.lineSeparator());
				newLineIndex = 0;
			}
			else newLineIndex++;
		}
		binaryRelationString = sB.toString();
		return binaryRelationString;
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
