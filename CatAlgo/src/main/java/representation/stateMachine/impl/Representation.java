package representation.stateMachine.impl;

import java.util.Set;

import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.ILanguage;
import representation.stateMachine.IAlgorithmicDescription;
import representation.stateMachine.ICategory;
import representation.stateMachine.IContextObject;
import representation.stateMachine.IRepresentation;
import representation.stateMachine.ISignified;

public class Representation extends StateMachine implements IRepresentation {

	public Representation(ILanguage language) {
		super(language);
		// TODO Auto-generated constructor stub
	}

	@Override
	public representation.stateMachine.ITransitionFunction buildTransitionFunction(Set<ICategory> categories) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISignified getSignifiedState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ICategory> getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IContextObject> getObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IContextObject> getObjectsThatHave(IFunctionalExpression specifiedProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAlgorithmicDescription getLowestCostDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAlgorithmicDescription getLowestCostDescriptionWithMatch(IFunctionalExpression constraintOnTarget,
			IFunctionalExpression constraintOnMatch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAlgorithmicDescription getBestEncodingDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAlgorithmicDescription getBestEncodingDescriptionWithMatch(IFunctionalExpression constraintOnTargetObj,
			IFunctionalExpression constraintOnMatch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IAlgorithmicDescription> getAllDescriptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IAlgorithmicDescription> getAllDescriptionsWithMatch(IFunctionalExpression constraintOnTargetObj,
			IFunctionalExpression constraintOnMatch) {
		// TODO Auto-generated method stub
		return null;
	}

}
