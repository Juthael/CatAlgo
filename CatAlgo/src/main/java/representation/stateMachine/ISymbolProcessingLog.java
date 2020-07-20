package representation.stateMachine;

/**
 * A ISymbolProcessingLog is a set of {@link IValueAttribution}. It has a cost, representing the quantity of information
 * it contains. 
 * @author Gael Tregouet
 *
 */
public interface ISymbolProcessingLog {
	
	/**
	 * The cost of a ISymbolProcessing is the cost of its symbol, plus the number of minimal and maximal elements that are 
	 * free in the RENDU LA
	 * @return
	 */
	int getCost();
	
	String toString();
	
}
