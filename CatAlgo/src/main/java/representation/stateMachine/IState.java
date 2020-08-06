package representation.stateMachine;

import java.util.Set;

import representation.orderedSet.ICtxtOrderedSet;
import representation.orderedSet.IOrderedSetOfAttributes;
import representation.orderedSet.ISlot;

/**
 * A IState is an element of a {@link IStateMachine}. It can be the input state or the output state of a {@link ITransition} 
 * in the {@link ITransitionFunction}. <br>
 * A IState also an equivalent of a <i> category </i>. As such, it is composed of an {@link IOrderedSetOfAttributes} called its 
 * <i>intension</i>, and of a set of {@link IAcceptState} called its <i> extension </i>. The intension is a description of 
 * the category. A category can be abstract : it means that its intension is partially undefined or, equivalently, that 
 * the {@link IOrderedSetOfAttributes} representing the intension of the category returns a non-empty set of {@link ISlot}. 
 * Every IState instance, if it is not an {@link IAcceptState} instance, represents an abstract category.
 * @author Gael Tregouet
 *
 */
public interface IState {
	
	/**
	 * A IState is a category, and as such has an intension that takes the form of an ordered set of attributes. 
	 * @return the description of the category
	 */
	IOrderedSetOfAttributes getIntension();
	
	/**
	 * A IState is a category, and as such has an extension that takes the form of the set of objects ({@link IAcceptState}) 
	 * that are reachable from this IState instance according to the {@link ITransitionFunction} of the {@link IStateMachine}.
	 * @return the set of objects belonging to this category
	 */
	Set<IAcceptState> getExtension();
	
	/**
	 * 
	 * @return the set of dimensions of this category
	 */
	Set<IDimension> getDimensions(ICtxtOrderedSet ctxtOrderedSet);
	
	/**
	 * 
	 * @param symbol - a symbol associated to this state in a {@link ITransition} mapping of the {@link ITransitionFunction} 
	 * (though this association is not verified here).
	 * @return the log of the symbol processing, containing dimension declarations, value attributions and information cost. 
	 */
	ISymbolProcessingLog processSymbol(ISymbol symbol);
	
	boolean intensionContainsSpecifiedAttribute(String attribute);
	
	boolean intensionContainsOneOfTheSpecifiedAttributes(Set<String> attributes);

}
