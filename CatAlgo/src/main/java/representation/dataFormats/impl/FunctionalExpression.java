package representation.dataFormats.impl;

import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.ILanguage;
import representation.stateMachine.ISymbol;

public class FunctionalExpression implements IFunctionalExpression {

	public FunctionalExpression(ISymbol[][] applicationArray) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IFunctionalExpression getFunctionalExpression() {
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
	public ILanguage getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISymbol[][] getApplicationArray() {
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
