package representation.stateMachine.impl;

import java.util.Iterator;
import java.util.Set;

import representation.dataFormats.IGrammar;
import representation.stateMachine.IEvaluation;
import representation.stateMachine.ISpecifications;
import representation.stateMachine.IState;
import representation.stateMachine.IStateName;
import representation.stateMachine.ISymbol;
import representation.stateMachine.ITransition;
import representation.stateMachine.ITransitionFunction;

public class State implements IState {

	public State() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IStateName getStateName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRank() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ISpecifications getSpecifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ITransition> getRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGrammar getLocalGrammar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSameGrammarAs(IState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean specificationsAreMetBy(IState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IEvaluation evaluateSymbol(Iterator<ISymbol> symbolIterator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(ITransitionFunction transitionFunction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRules(ITransitionFunction transitionFunction) {
		// TODO Auto-generated method stub

	}

}
