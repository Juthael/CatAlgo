package representation;

import java.util.Iterator;
import java.util.Set;

public interface IState {
	
	int getID();
	
	IStateName getStateName();
	
	int getRank();
	
	ISpecifications getSpecifications();
	
	Set<ITransition> getRules();
	
	IGrammar getRestrictedGrammar();
	
	boolean hasSameGrammarAs(IState specifiedState);
	
	boolean specificationsAreMetBy(IState specifiedState);
	
	IEvaluationLog evaluateSymbol(Iterator<ISymbol> symbolIterator);
	
	boolean isTheEquivalentTo(IState specifiedState);
	
	void setName(ITransitionFunction transitionFunction);
	
	void setRules(ITransitionFunction transitionFunction);
	
	@Override
	boolean equals(Object state);
	
	@Override
	int hashCode();

}
