package representation.stateMachine;

import representation.orderedSet.ICtxtOrderedSet;
import representation.orderedSet.IFreeAttribute;
import representation.orderedSet.IFreePair;
import representation.orderedSet.ISlot;

/**
 * A IValue is a lower semi-lattice of attributes. It can be used by the {@link ISymbolProcessingLog} to instantiate a
 * {@link IValueAttribution}, if it can be associated to a {@link IDimension} of a given {@link IState}. <br>
 * 
 * A IValue can declare intrinsic dimensions (type {@link IDimension}), containing a {@link ISlot}. For instance, there 
 * is a {@link IFreePair} slot if some pair of the IValue binary relation V may be defined as abstract : it means that they 
 * belong to the successor relation S_<sub>V</sub>, but not to the successor relation S_<sub>C</sub> associated with the 
 * {@link ICtxtOrderedSet} given as an argument of the IValue constructor. Also, an attribute can generate a 
 * {@link IFreeAttribute} slot if it is 
 *  
 * Similarly, a IValue may have abstract maxima : those are maxima elements in V, but not in the relation 
 * associated with the {@link ICtxtOrderedSet}. <br>
 * 
 * A IValue has a cost, representing the quantity of information it contains. 
 *  
 * @author Gael Tregouet
 *
 */
public interface IValue {
	
	/**
	 * @return a String describing the value
	 */
	String toString();
	
	/**
	 * The cost of a IValue can be interpreted as the quantity of information it contains. It is equal to the number of 
	 * intrinsic dimensions this value declares.  
	 * @return the cost of the IValue
	 */
	int getCost();
	
	/**
	 * An object is equal to a IValue instance if their respective OrderedSet and CtxtOrderedSet are equal. 
	 * @param object - the compared object
	 * @return true if equals, false otherwise. 
	 */
	@Override
	public boolean equals(Object object);

}
