package representation.dataFormats.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.ITotalOrder;
import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.IGrammaticalRule;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IPair;
import representation.exceptions.RepresentationException;
import representation.stateMachine.IStateMachine;
import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;
import representation.stateMachine.impl.StateMachine;

public class Language extends Description implements ILanguage {

	private static final char WORD_SEPARATOR = '/';
	private final List<IWord> dictionary;
	
	public Language(Set<IWord> words) {
		dictionary = getWordsInLexicographicOrder(words);
		setHashCode();
	}
	
	@Override
	public IRelationalDescription getRelationalDescription() throws RepresentationException {
		IRelationalDescription relationalDescription;
		Set<ITotalOrder> propOrders = new HashSet<ITotalOrder>();
		for (IWord word : dictionary) {
			Set<IPair> pairs = new HashSet<IPair>();
			List<ISymbol> listOfSymbols = word.getListOfSymbols();
			int antecedentIdx = 0;
			int consequentIdx;
			while (antecedentIdx < listOfSymbols.size() - 1) {
				consequentIdx = antecedentIdx + 1;
				while (consequentIdx < listOfSymbols.size()) {
					pairs.add(new Pair(listOfSymbols.get(antecedentIdx), listOfSymbols.get(consequentIdx)));
					consequentIdx++;
				}
				antecedentIdx++;
			}
			ITotalOrder propOrder;
			try {
				propOrder = new TotalOrder(pairs);
			} catch (RepresentationException e) {
				throw new RepresentationException("Language.getRelationalDescription() : propOrder instantiation "
						+ "has failed." + System.lineSeparator() + e.getMessage());
			}
			propOrders.add(propOrder);
		}
		relationalDescription = new RelationalDescription(propOrders, RelationalDescription.MAX_ORDERS);
		return relationalDescription;
	}	
	
	@Override
	public List<IWord> getDictionary() {
		return dictionary;
	}	
	
	@Override
	public IFunctionalExpression getFunctionalExpression() {
		IFunctionalExpression functionalExpression;
	//Sets the required map argument for the functional expression constructor
		Map<List<Integer>, ISymbol> coordinatesOntoSymbols = new HashMap<List<Integer>, ISymbol>();
		int nbOfWords = dictionary.size();
		int longestWordLength = 0;
		for (IWord word : dictionary) {
			if (longestWordLength < word.size())
				longestWordLength = word.size();
		}
		/**
		 * The coordinates of the Nth symbol of the Mth word will be the list of 
		 * integers given by the first N values on the Mth line of the 
		 * coordinates array. If no symbol at [M-1][N-x] (because the Mth word 
		 * has less than N-(x+1) values, then array value = -1
		 * 
		 * [word(1) - symbol(1)][word(2) - symbol(1)][(...) - symbol(1)][word(i) - symbol(1)]
		 * [word(1) - symbol(2)][word(2) - symbol(2)][(...) - symbol(2)][word(i) - symbol(2)]
		 * 		[(...)]				[(...)]				 [(...)]			[(...)]
		 * [word(1) - symbol(j)][word(2) - symbol(j)][(...) - symbol(j)][word(i) - symbol(j)]
		 */
		int[][] coordinatesArray = new int[nbOfWords][longestWordLength];
	//initializes the array with -1
		for (int i = 0 ; i < nbOfWords ; i++) {
			for (int j = 0 ; j < longestWordLength ; j++) {
				coordinatesArray[i][j] = -1;
			}
		}
	//populates the array from the first row to the last
		int rowIndex = 0;
		//what the next coordinate value will be
		int newCoordinateVal = -1;
		//what the prefix (=coordinates minus last value) was the last time coordinates have changed
		int[] lastCoordPrefix = new int[0];
		//what the symbol was the last time coordinates have changed
		ISymbol lastSymbol = null;
		while (rowIndex < longestWordLength) {
			for (int i = 0 ; i < dictionary.size() ; i++) {
				if (dictionary.get(i).size() > rowIndex) {
					ISymbol currentSymbol = dictionary.get(i).get(rowIndex);
					//find current coordinates prefix
					int[] currentCoordPrefix = new int[rowIndex];
					for (int j = 0 ; j < rowIndex ; j++) {
						currentCoordPrefix[j] = coordinatesArray[i][j];
					}
					if (Arrays.equals(lastCoordPrefix, currentCoordPrefix)) {
						//same prefix, different symbol
						if (!currentSymbol.equals(lastSymbol)) {
							lastSymbol = currentSymbol;
							newCoordinateVal++;
						}
					}
					else {
						//different prefix
						lastCoordPrefix = currentCoordPrefix;
						lastSymbol = currentSymbol;
						newCoordinateVal = 0;
					}
					coordinatesArray[i][rowIndex] = newCoordinateVal;
				}
			}
			rowIndex++;
			newCoordinateVal = -1;
			lastSymbol = null;
		}
	//populates the map
		for (int i = 0 ; i < dictionary.size() ; i++) {
			List<Integer> coordinate = new ArrayList<Integer>();
			rowIndex = 0;
			do {
				newCoordinateVal = coordinatesArray[i][rowIndex];
				if (newCoordinateVal != -1) {
					/*
					 * If rowIndex = 0 then the symbol is the function leaf and its coordinates are an emptylist. 
					 * See ISyntaxLeaf.getFunctionalExpression().
					 */
					if (rowIndex > 0)
						coordinate.add(newCoordinateVal);
					ISymbol symbol = dictionary.get(i).get(rowIndex);
					coordinatesOntoSymbols.put(new ArrayList<Integer>(coordinate), symbol);
					rowIndex++;
				}
			} while (newCoordinateVal != -1 && rowIndex < longestWordLength);
		}
		functionalExpression = new FunctionalExpression(coordinatesOntoSymbols);
		return functionalExpression;
	}	
	
	@Override
	public IGrammar getGrammar() {
		IGrammar grammar = new Grammar();
		for (IWord word : dictionary) {
			List<ISymbol> listOfSymbols = word.getListOfSymbols();
			int antIndex;
			int consIndex;
			for (antIndex = 0 ; antIndex < word.size() - 1 ; antIndex++) {
				consIndex = antIndex + 1;
				IGrammaticalRule rule = new GrammaticalRule(listOfSymbols.get(antIndex), listOfSymbols.get(consIndex));
				grammar.add(rule);
			}
		}
		return grammar;
	}	

	@Override
	public ILanguage getLanguage() {
		return this;
	}
	
	@Override
	public int getNbOfArgumentsFor(ISymbol symbol) throws RepresentationException {
		int nbOfArguments = 0;
		//finds the first occurrence of a word containing the specified symbol
		int firstWordWithSymbolIndex = -1;
		int wordIndex = 0;
		while ((firstWordWithSymbolIndex == -1) && (wordIndex < dictionary.size())) {
			if (dictionary.get(wordIndex).getListOfSymbols().contains(symbol))
				firstWordWithSymbolIndex = wordIndex;
			else wordIndex++;
		}
		/*
		 * If word found, retrieves the arguments given to the function ending with the specified symbol
		 * (this function is the sublist of the word's list of symbols, that ends with the 
		 * specified symbol)
		 */
		if (firstWordWithSymbolIndex != -1) {
			Set<ISymbol> arguments = new HashSet<ISymbol>();
			//gets the function ending with the specified symbol
			List<ISymbol> function = new ArrayList<ISymbol>();
			ISymbol currSymbol;
			int symbolIndex = 0;
			do {
				currSymbol = dictionary.get(wordIndex).get(symbolIndex);
				function.add(currSymbol);
				if (!currSymbol.equals(symbol))
					symbolIndex++;
			} while (!currSymbol.equals(symbol));
			/*
			 * If the symbol is not terminal, finds the arguments in every word containing this function ; 
			 * else, the number of arguments is 0.
			 */
			if (symbolIndex < dictionary.get(wordIndex).size() - 1) {
				boolean functionFoundInThisWord = true;
				while (functionFoundInThisWord && wordIndex < dictionary.size()) {
					arguments.add(dictionary.get(wordIndex).get(symbolIndex + 1));
					wordIndex++;
					if (wordIndex < dictionary.size()) {
						functionFoundInThisWord = 
								((dictionary.get(wordIndex).size() >  function.size()) 
										&& (function.equals(
												dictionary.get(wordIndex).getListOfSymbols().subList(0, function.size()))));
					}
				}
			}
			nbOfArguments = arguments.size();
		}
		//if no word found with the specified symbol, throws new Exception
		else throw new RepresentationException("Language.getNbOfArgumentsFor(ISymbol) : the specified symbol " 
				+ System.lineSeparator() + "cannot be found in the words of this language");
		return nbOfArguments;
	}	
	
	@Override
	public IStateMachine getStateMachine() {
		IStateMachine stateMachine = new StateMachine(this);
		return stateMachine;
	}	
	
	@Override
	public Set<ISymbol> getSymbols(){
		Set<ISymbol> symbols = new HashSet<ISymbol>();
		for (IWord word : dictionary)
			symbols.addAll(word.getListOfSymbols());
		return symbols;
	}	
	
	@Override
	public Set<IWord> getWords() {
		Set<IWord> words = new HashSet<IWord>(dictionary);
		return words;
	}	

	@Override
	public boolean meets(IDescription description) throws RepresentationException {
		boolean specifiedDescIsMet;
		IRelationalDescription thisRelation = null;
		try {
			thisRelation = getRelationalDescription();
		} catch (RepresentationException e) {
			throw new RepresentationException("Language.meets(IDescription) : failed to convert this language into "
					+ "a relational description." + System.lineSeparator() + e.getMessage());
		}
		try {
			specifiedDescIsMet = thisRelation.meets(description);
		} catch (RepresentationException e) {
			throw new RepresentationException("Language.meets(IDescription) : an error has occured during relational "
					+ "descriptions compliance test." + System.lineSeparator() + e.getMessage());
		}
		return specifiedDescIsMet;
	}
	
	@Override
	public String toString() {
		String languageString;
		StringBuilder sB = new StringBuilder();
		for (IWord word : dictionary) {
			Iterator<ISymbol> symbolIterator = word.getListOfSymbols().iterator();
			while (symbolIterator.hasNext()) {
				ISymbol currentSymbol = symbolIterator.next();
				sB.append(currentSymbol.toString());
				if (symbolIterator.hasNext())
					sB.append(WORD_SEPARATOR);
			}
			sB.append(System.lineSeparator());
		}
		languageString = sB.toString();
		return languageString;
	}
	
	private List<IWord> getWordsInLexicographicOrder(Set<IWord> words){
		List<IWord> dictionary = new ArrayList<IWord>(words);
		Collections.sort(dictionary);
		return dictionary;
	}

}
