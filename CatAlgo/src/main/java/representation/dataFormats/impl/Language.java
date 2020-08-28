package representation.dataFormats.impl;

import java.util.List;
import java.util.Set;

import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.ILanguage;
import representation.stateMachine.IStateMachine;
import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;

public class Language implements ILanguage {

	public Language(Set<IWord> words) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ILanguage getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean meets(IDescription description) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IBinaryRelation getBinaryRelation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFunctionalExpression getFunctionalExpression() {
		// TODO Auto-generated method stub
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

}
