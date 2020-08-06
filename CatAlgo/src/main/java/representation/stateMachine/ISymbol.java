package representation.stateMachine;

import representation.orderedSet.ICtxtOrderedSet;
import representation.orderedSet.IOrderedSetOfAttributes;

/**
 * A ISymbol is a subset of a {@link ICtxtOrderedSet} that may not be connected. Every maximal connected subset of a ISymbol is 
 * by construction a lower semi-lattice and allows the instantiation of a {@link IValue}. Therefore, a ISymbol is also a set 
 * of {@link IValue}, with an associated cost (or quantity of information). 
 * @author Gael Tregouet
 *
 */
public interface ISymbol {
	
	/**
	 * 
	 * @return the representation of the symbol as a String
	 */
	String toString();
	
	/**
	 * The cost of a symbol can be interpreted as the quantity of information it contains. It is equal to the sum of its 
	 * values' costs. 
	 * @return the cost of the ISymbol
	 */
	int getCost();
	
	/**
	 * @return the number of IValues it contains or, equivalently, the number of maximal connected subsets that can be found 
	 * in its {@link IOrderedSetOfAttributes}. 
	 */
	int nbOfValues();

}
