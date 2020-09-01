package representation.dataFormats.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IPair;
import representation.dataFormats.impl.utils.utilsBR.Pair;
import representation.stateMachine.IStateMachine;
import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;
import representation.stateMachine.impl.StateMachine;

public class Language implements ILanguage {

	private List<IWord> dictionary;
	
	public Language(Set<IWord> words) {
		dictionary = getWordsInLexicographicOrder(words);
	}

	@Override
	public ILanguage getLanguage() {
		return this;
	}

	@Override
	public boolean meets(IDescription description) {
		boolean specifiedDescIsMet;
		IBinaryRelation thisRelation = getBinaryRelation();
		IBinaryRelation otherRelation = description.getBinaryRelation();
		specifiedDescIsMet = otherRelation.getPairs().containsAll(thisRelation.getPairs());
		return specifiedDescIsMet;
	}

	@Override
	public IBinaryRelation getBinaryRelation() {
		IBinaryRelation relation;
		Set<IPair> pairs = new HashSet<IPair>();
		for (IWord word : dictionary) {
			List<ISymbol> listOfSymbols = word.getListOfSymbols();
			int antecedentIdx = 0;
			int consequentIdx;
			while (antecedentIdx < listOfSymbols.size()) {
				consequentIdx = antecedentIdx + 1;
				while (consequentIdx < listOfSymbols.size()) {
					pairs.add(new Pair(listOfSymbols.get(antecedentIdx), listOfSymbols.get(consequentIdx)));
					consequentIdx++;
				}
				antecedentIdx++;
			}
		}
		relation = new BinaryRelation(pairs);
		return relation;
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
		 * integers given by the first (N) values on the (M)th line of the 
		 * coordinates array. If no symbol at [M-1][N-1] (because the Mth word 
		 * has less than N values), then array value = -1
		 */
		int[][] coordinatesArray = new int[longestWordLength][nbOfWords];
		//initializes the array with -1
		for (int i = 0 ; i < nbOfWords ; i++) {
			for (int j = 0 ; j < longestWordLength ; j++) {
				coordinatesArray[i][j] = -1;
			}
		}
		//populates the array from the first row to the last
		int rowIndex = 0;
		int newCoordinateVal = 0;
		int[] lastCoordPrefix = new int[0];
		ISymbol lastSymbol = null;
		while (rowIndex < longestWordLength) {
			for (int i = 0 ; i < dictionary.size() ; i++) {
				if (dictionary.get(i).size() > rowIndex) {
					ISymbol currentSymbol = dictionary.get(i).get(rowIndex);
					int[] currentCoordPrefix = new int[rowIndex];
					for (int j = 0 ; j < rowIndex ; j++) {
						currentCoordPrefix[j] = coordinatesArray[j][i];
					}
					if (Arrays.equals(lastCoordPrefix, currentCoordPrefix)) {
						if (!currentSymbol.equals(lastSymbol)) {
							lastSymbol = currentSymbol;
							newCoordinateVal++;
						}
					}
					else {
						lastCoordPrefix = currentCoordPrefix;
						lastSymbol = currentSymbol;
						newCoordinateVal = 0;
					}
					coordinatesArray[rowIndex][i] = newCoordinateVal;
				}
			}
			rowIndex++;
		}
		//populates the map
		for (int i = 0 ; i < dictionary.size() ; i++) {
			List<Integer> coordinate = new ArrayList<Integer>();
			rowIndex = 0;
			newCoordinateVal = coordinatesArray[i][rowIndex];
			while (newCoordinateVal != -1) {
				coordinate.add(newCoordinateVal);
				ISymbol symbol = dictionary.get(i).get(rowIndex);
				coordinatesOntoSymbols.put(new ArrayList<Integer>(coordinate), symbol);
				rowIndex++;
			}
		}
		functionalExpression = new FunctionalExpression(coordinatesOntoSymbols);
		return functionalExpression;
	}

	@Override
	public Set<IWord> getWords() {
		Set<IWord> words = new HashSet<IWord>(dictionary);
		return words;
	}

	@Override
	public List<IWord> getDictionary() {
		return dictionary;
	}

	@Override
	public IStateMachine getStateMachine() {
		IStateMachine stateMachine = new StateMachine(this);
		return stateMachine;
	}

	@Override
	public IGrammar getGrammar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbOfArgumentsFor(ISymbol symbol) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private List<IWord> getWordsInLexicographicOrder(Set<IWord> words){
		List<IWord> dictionary = new ArrayList<IWord>(words);
		Collections.sort(dictionary);
		return dictionary;
	}

}
