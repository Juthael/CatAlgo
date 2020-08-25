package representation.dataFormats;

/**
 * A IGrammaticalRule is a component of a context-free grammar. A rule states that IF *antecedent* THEN *consequent*.
 * @author Gael Tregouet
 *
 */
public interface IGrammaticalRule {

	/**
	 * 
	 * @return the antecedent
	 */
	String getAntecedent();
	
	/**
	 * 
	 * @return the consequent
	 */
	String getConsequent();
	
	/**
	 * 
	 * @param rule any object
	 * @return true if antecedents are equal and consequents are equal ; false otherwise
	 */
	@Override
	boolean equals(Object object);
	
	/**
	 * 
	 * @return the hashcode
	 */
	@Override
	int hashCode();
	
}
