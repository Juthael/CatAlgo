package representation.stateMachine;

import representation.ICtxtOrderedSet;

/**
 * A IValue is a lower semi-lattice of attributes. It can be used by the {@link ISymbolProcessingLog} to instantiate a
 * {@link IValueAttribution}, if it can be associated to a {@link IDimension} of a given {@link IState}. <br>
 * 
 * As a lower semi-lattice, it has one minimum and possibly many maximal elements. Some pairs of its binary relation V on 
 * attributes may be defined as abstract : it means that they belong to the successor relation S_<sub>V</sub>, but not to 
 * the successor relation S_<sub>C</sub> associated with the {@link ICtxtOrderedSet} given as an argument of the IValue 
 * constructor. Similarly, a IValue may have abstract maxima : those are maxima elements in V, but not in the relation 
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
	 * The cost of a IValue can be interpreted as the quantity of information it contains. It is equal to its number of 
	 * abstract pairs.  
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
