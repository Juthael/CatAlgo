package representation.dataFormats.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
		// HERE
		return null;
	}

	@Override
	public Set<IWord> getWords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IWord> getDictionary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStateMachine getStateMachine() {
		// TODO Auto-generated method stub
		return null;
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
