package representation;

import java.util.Set;

public interface ICategory extends IStateMachine, IState {
	
	ITransitionFunction buildTransitionFunction(IBinaryRelation relation);
	
	void setStateNamesAndRules();
	
	IDescription getIntent();
	
	Set<IContextObject> getExtent();
	
	float getContextualFrequency();
	
	ITransition getTransitionTo(ICategory specifiedCategory);	

}
