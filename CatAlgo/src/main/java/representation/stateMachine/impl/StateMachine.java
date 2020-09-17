package representation.stateMachine.impl;

import java.util.Set;

import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.ILanguage;
import representation.stateMachine.IAcceptState;
import representation.stateMachine.IEvaluationLog;
import representation.stateMachine.IInterfaceState;
import representation.stateMachine.ISpecifications;
import representation.stateMachine.IStartState;
import representation.stateMachine.IState;
import representation.stateMachine.IStateMachine;
import representation.stateMachine.IStateName;
import representation.stateMachine.IWord;

public class StateMachine implements IStateMachine {

	public StateMachine(ILanguage language) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<IState> getStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public representation.stateMachine.ITransitionFunction getTransitionFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILanguage getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFunctionalExpression getFunctionalExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGrammar getMachineGrammar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRelationalDescription getRelationalDescription() {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	public ISpecifications getSpecifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEvaluationLog evaluateWord(IWord word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IState getState(IStateName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IState getState(int iD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStartState getStartState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IInterfaceState> getInterfaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbOfInterfaces() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<IAcceptState> getAcceptStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStateNames(representation.stateMachine.ITransitionFunction tFunction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStateRules(representation.stateMachine.ITransitionFunction tFunction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStateSpecifications(representation.stateMachine.ITransitionFunction tFunction) {
		// TODO Auto-generated method stub

	}

}
