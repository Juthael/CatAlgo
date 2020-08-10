package representation;

import java.util.Set;

public interface IStateMachine {
	
	Set<IState> getStates();
	
	ITransitionFunction ITransitionFunction();
	
	ILanguage getLanguage();
	
	IFunctionalExpression getFunctionalExpression();
	
	IBinaryRelation getBinaryRelation();
	
	IGrammar getRestrictedGrammar();
	
	ISpecifications getSpecifications();
	
	IEvaluationLog evaluateWord(IWord word);
	
	IState getState(IStateName name);
	
	IState getState (int iD);
	
	IStartState getStartState();
	
	Set<IInterfaceState> getInterfaces();
	
	int getNbOfInterfaces();
	
	Set<IAcceptState> getAcceptStates();
	
	float getCost();
	
	void setStateNamesAndRules();

}
