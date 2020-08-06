package representation.stateMachine;

import representation.orderedSet.IOrderedSetOfAttributes;

/**
 * A ISymbolProcessingLog is a set of {@link IValueAttribution}. 
 * It is instantiated by a {@link ITransition}, which involves an input {@link IState}, an output {@link IState} and 
 * a {@link ISymbol}.  
 * It has a cost, representing the quantity of information
 * it contains. 
 * @author Gael Tregouet
 *
 */
public interface ISymbolProcessingLog {
	
	/**
	 * The cost of a ISymbolProcessing is the number of free minimal and maximal elements in the 
	 * symbol {@link IOrderedSetOfAttributes} that remain free in the output state {@link IOrderedSetOfAttributes}, plus 1 (in order to prevent 'free' 
	 * transitions). 
	 * @return the cost of the symbol processing
	 */
	int getCost();
	
	/**
	 * 
	 * @return the symbol processing log as a String
	 */
	String toString();
	
}
