package representation.stateMachine.impl;

import java.util.Set;

import representation.dataFormats.IGrammar;
import representation.stateMachine.ISpecifications;
import representation.stateMachine.IState;
import representation.stateMachine.IStateName;
import representation.stateMachine.ISymbol;
import representation.stateMachine.ITransition;
import representation.stateMachine.ITransitionFunction;

public class TransitionFunction implements ITransitionFunction {

	public TransitionFunction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ITransition getOutput(IState inputState, ISymbol symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ITransition> getTransitionsWithSymbol(ISymbol symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ITransition> getTransitionsFromState(IStateName stateName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ITransition> getTransitionsToState(IStateName stateName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStateName getStateName(int stateID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ITransition> getStateRules(IStateName stateName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISpecifications getStateSpecifications(IStateName stateName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGrammar getRestrictedGrammar() {
		// TODO Auto-generated method stub
		return null;
	}

}
