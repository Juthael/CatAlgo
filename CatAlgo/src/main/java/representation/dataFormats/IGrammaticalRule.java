package representation.dataFormats;

import representation.stateMachine.ISymbol;

/**
 * A IGrammaticalRule is a component of a context-free grammar. A rule states that IF *antecedent* THEN *consequent*.
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
	 * 
	 * @return the antecedent
	 */
	ISymbol getAntecedent();
	
	/**
	 * 
	 * @return the consequent
	 */
	ISymbol getConsequent();
	
	/**
	 * 
	 * @return the hashcode
	 */
	@Override
	int hashCode();
	
	/**
	 * 
	 * @return the grammatical rule as a String
	 */
	@Override
	String toString();
	
}
