package representation.dataFormats.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.IGrammaticalRule;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IRelationalDescription;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;
import representation.stateMachine.impl.Word;

public class FunctionalExpression implements IFunctionalExpression {

	private final Map<List<Integer>, ISymbol> coordinatesOntoSymbols;
	
	public FunctionalExpression(Map<List<Integer>, ISymbol> coordinatesOntoSymbols) {
		this.coordinatesOntoSymbols = coordinatesOntoSymbols;
	}
	
	@Override
	public IRelationalDescription getRelationalDescription() throws RepresentationException {
		IRelationalDescription relationalDescription;
		ILanguage language = getLanguage();
		try {
			relationalDescription = language.getRelationalDescription();
		} catch (RepresentationException e) {
			throw new RepresentationException("FunctionalExpression.getRelationalDescription() : the conversion from "
					+ "language to relational description has failed." + System.lineSeparator() + e.getMessage());
		}
		return relationalDescription;
	}	
	
	@Override
	public Map<List<Integer>, ISymbol> getCoordinatesOntoSymbols() {
		return coordinatesOntoSymbols;
	}	

	@Override
	public IFunctionalExpression getFunctionalExpression() {
		return this;
	}
	
	@Override
	public IGrammar getGrammar() {
		IGrammar grammar = new Grammar();
		List<List<Integer>> listOfCoordinates = new ArrayList<List<Integer>>(coordinatesOntoSymbols.keySet());
		for (List<Integer> antCoordinate : listOfCoordinates) {
			ISymbol antecedent = coordinatesOntoSymbols.get(antCoordinate);
			for (List<Integer> otherCoordinate : listOfCoordinates) {
				if ((otherCoordinate.size() == antCoordinate.size() + 1)
						&& antCoordinate.equals(otherCoordinate.subList(0, antCoordinate.size()))) {
					ISymbol consequent = coordinatesOntoSymbols.get(otherCoordinate);
					IGrammaticalRule newRule = new GrammaticalRule(antecedent, consequent);
					grammar.add(newRule);
				}
			}
		}
		return grammar;
	}	
	
	@Override
	public ILanguage getLanguage() {
		//This is an implementation of the alternative procedure (cf. interface doc)
		ILanguage language;
		Set<IWord> setOfWords = new HashSet<IWord>();
		//searches for the coordinates of the terminal elements
		List<List<Integer>> coordinatesOfTerminals = new ArrayList<List<Integer>>();
		for (List<Integer> currentCoordinates : coordinatesOntoSymbols.keySet()) {
			int coordOfTermIdx = 0;
			boolean currentIsTerminalSoFar = true;
			while ((coordOfTermIdx < coordinatesOfTerminals.size() && currentIsTerminalSoFar)) {
				List<Integer> termCoordinates = coordinatesOfTerminals.get(coordOfTermIdx);
				if (currentCoordinates.size() < termCoordinates.size()	
						&& currentCoordinates.equals(termCoordinates.subList(0, currentCoordinates.size()))) {
					currentIsTerminalSoFar = false;
				}
				coordOfTermIdx++;
			}
			if (currentIsTerminalSoFar) {
				//ensures that the current list of terminal coordinates doesn't contain sublists of current coordinates
				List<List<Integer>> notTerminalAfterAll = new ArrayList<List<Integer>>();
				for (List<Integer> termCoord : coordinatesOfTerminals) {
					if ((currentCoordinates.size() > termCoord.size())
							&& termCoord.equals(currentCoordinates.subList(0, termCoord.size())))
						notTerminalAfterAll.add(termCoord);
				}
				coordinatesOfTerminals.removeAll(notTerminalAfterAll);
				coordinatesOfTerminals.add(currentCoordinates);
			}
		}
		//populates the set of words
		for (List<Integer> termCoord : coordinatesOfTerminals) {
			List<ISymbol> listOfSymbols = new ArrayList<ISymbol>();
			List<Integer> symbolsCoord = new ArrayList<Integer>();
			for (Integer coordValue : termCoord) {
				symbolsCoord.add(coordValue);
				listOfSymbols.add(coordinatesOntoSymbols.get(symbolsCoord));
			}
			setOfWords.add(new Word(listOfSymbols));
		}
		//returns the language
		language = new Language(setOfWords);
		return language;
	}	
	
	@Override
	public int getNbOfArgumentsFor(ISymbol symbol) throws RepresentationException {
		int nbOfArguments;
		Set<ISymbol> arguments = new HashSet<ISymbol>();
		if (coordinatesOntoSymbols.containsValue(symbol)) {
			List<Integer> symbolCoordinates = null;
			int coordinatesIdx = 0;
			List<List<Integer>> listOfCoordinates = new ArrayList<List<Integer>>(coordinatesOntoSymbols.keySet());
			while (symbolCoordinates == null) {
				List<Integer> currentCoordinates = listOfCoordinates.get(coordinatesIdx);
				if (coordinatesOntoSymbols.get(currentCoordinates).equals(symbol))
					symbolCoordinates = currentCoordinates;
				else coordinatesIdx++;
			}
			for (List<Integer> currentCoordinates : listOfCoordinates) {
				if ((currentCoordinates.size() == symbolCoordinates.size() + 1) 
						&& (symbolCoordinates.equals(currentCoordinates.subList(0, currentCoordinates.size())))) {
					arguments.add(coordinatesOntoSymbols.get(currentCoordinates));
				}
			}
			nbOfArguments = arguments.size();
		}
		else throw new RepresentationException("FunctionalExpression.getNbOfArgumentsFor(ISymbol) : the specified "
				+ "symbol '" + symbol.toString() + "'cannot be found.");
		return nbOfArguments;
	}	

	@Override
	public boolean meets(IDescription description) throws RepresentationException {
		boolean thisFunctExpMeetsSpecifiedDesc;
		IRelationalDescription equivalentRelation;
		try {
			equivalentRelation = getRelationalDescription();
		} catch (RepresentationException e) {
			throw new RepresentationException("FunctionalExpression.meets(IDescription) : an error has "
					+ "occurred while converting this expression into a relational description." 
					+ System.lineSeparator() + e.getMessage());
		}
		try {
			thisFunctExpMeetsSpecifiedDesc = equivalentRelation.meets(description);
		} catch (RepresentationException e) {
			throw new RepresentationException("FunctionalExpression.meets(IDescription) : an error has occurred "
					+ "while evaluating compliance to specified description" + System.lineSeparator() 
					+ e.getMessage());
		}
		return thisFunctExpMeetsSpecifiedDesc;
	}
	
	@Override
	public String toString() {
		String functionalExpressionString;
		/*
		 * The "coordinates" of the minimum are an empty list (coordinates are meant to localize arguments, 
		 * which the minimum is not)
		 */
		List<Integer> rootCoordinates = new ArrayList<Integer>();
		functionalExpressionString = toString(rootCoordinates);
		return functionalExpressionString;
	}
	
	//recursive
	private String toString(List<Integer> coordinates) {
		String functionalExpressionString;
		List<List<Integer>> argumentsCoordinates = new ArrayList<List<Integer>>();
		for (List<Integer> currentCoordinates : coordinatesOntoSymbols.keySet()) {
			if ((currentCoordinates.size() == coordinates.size() + 1)
					&& coordinates.equals(currentCoordinates.subList(0, coordinates.size()))) {
				argumentsCoordinates.add(currentCoordinates);
			}
		}
		if (argumentsCoordinates.isEmpty())
			functionalExpressionString = coordinatesOntoSymbols.get(coordinates).toString();
		else {
			StringBuilder sB = new StringBuilder();
			sB.append(coordinatesOntoSymbols.get(coordinates).toString());
			sB.append(" ");
			if (argumentsCoordinates.size() == 1) {
				sB.append(toString(argumentsCoordinates.get(0)));
			}
			else {
				sB.append("(( ");
				for (int i = 0 ; i < argumentsCoordinates.size() ; i++) {
					List<Integer> argCoordinate = argumentsCoordinates.get(i);
					sB.append(toString(argCoordinate));
					if (i < argumentsCoordinates.size() - 1)
						sB.append(") Λ (");
				}
				sB.append(")) ");
			}
			functionalExpressionString = sB.toString();
		}
		return functionalExpressionString;
	}

}
