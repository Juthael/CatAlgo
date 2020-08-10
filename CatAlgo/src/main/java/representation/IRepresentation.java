package representation;

import java.util.Set;

public interface IRepresentation extends IStateMachine {
	
	ITransitionFunction buildTransitionFunction(Set<ICategory> categories);
	
	ISignified getSignifiedState();
	
	Set<ICategory> getCategories();
	
	Set<IContextObject> getObjects();
	
	Set<IContextObject> getObjectsThatConformTo(IFunctionalExpression specifiedProperty);
	
	IStateMachine getOptimalStateMachine();
	
	IStateMachine getRepresentationRestrictedTo(Set<IContextObject> specifiedObjects);
	
	IContextObject getMostSimilarObjToTargetObjWithSpecifiedProp(IContextObject targetObject, IFunctionalExpression specifiedProperty);

}
