package representation.stateMachine;

import representation.orderedSet.IFreePair;
import representation.orderedSet.IOrderedSetOfAttributes;
import representation.orderedSet.ISlot;

/**
 * 
 * A IDimension is a chain of attributes (implemented as a {@link IOrderedSetOfAttributes} with a {@link IDimension} that can 
 * accept a {@link IValue}.  
 * A {@link IValue} instance can be associated with a IDimension instance in a {@link IValueAttribution} if the minimum of 
 * the {@link IValue} is the free predecessor of the dimension {@link ISlot} and, in case this {@link ISlot} is a 
 * {@link IFreePair}, if its free successor belongs to the maximal elements of the {@link IValue}. 
 * @author Gael Tregouet
 *
 */
public interface IDimension {
	
	/**
	 * A {@link IValue} instance is a value of this Dimension if the minimum of the {@link IValue} is the free predecessor of 
	 * the dimension {@link ISlot} and, in case this {@link ISlot} is a {@link IFreePair}, if its free successor belongs to 
	 * the maximal elements of the {@link IValue}. 
	 * @param value - the value to be tested
	 * @return true if the specified value is accepted by this dimension, false otherwise
	 */
	boolean acceptValue(IValue value);

}
