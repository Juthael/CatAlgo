package representation.dataFormats;

import representation.stateMachine.ISymbol;

/**
 * A grammatical rule of a context-free grammar states that <i> IF </i> *antecedent* <i> THEN </i> *consequent*.
 * @author Gael Tregouet
 *
 */
public interface IGrammaticalRule {

	/**
	 * 
	 * @param rule any object
	 * @return true if antecedents are equal and consequents are equal ; false otherwise
	 */
	@Override
	boolean equals(Object object);
	
	/**
	 * Returns the antecedent symbol. 
	 * @return the antecedent
	 */
	ISymbol getAntecedent();
	
	/**
	 * Returns the consequent symbol. 
	 * @return the consequent
	 */
	ISymbol getConsequent();
	

	@Override
	int hashCode();
	
	/**
	 * Returns the grammatical rule as a String. 
	 * @return the grammatical rule as a String
	 */
	@Override
	String toString();
	
}
