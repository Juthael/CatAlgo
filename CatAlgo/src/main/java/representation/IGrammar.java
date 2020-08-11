package representation;

import java.util.Set;

/**
 * A IGrammar is a context-free grammar, implemented as a collection of rules mapping an antecedent with a consequent. 
 * 
 * @author Gael Tregouet
 *
 */
public interface IGrammar {
	
	/**
	 * 
	 * @return the set of rules
	 */
	Set<IGrammaticalRule> getGrammaticalRules();
	
	/**
	 * 
	 * @param rule a grammatical rule
	 * @return
	 */
	boolean contains(IGrammaticalRule rule);
	
	/**
	 * 
	 * @param rules a set of grammatical rules
	 * @return
	 */
	boolean contains(Set<IGrammaticalRule> rules);
	
	/**
	 * 
	 * @param grammar a context-free grammar
	 * @return true if the set of rules contained in the specified grammar is also contained in this 
	 * grammar ; false otherwise
	 * @see representation.IGrammaticalRule
	 */
	boolean contains(IGrammar grammar);
	
	/**
	 * 
	 * @param antecedent the name of a symbol
	 * @return the number of rules with the specified symbol as an antecedent
	 */
	int getNbOfRulesWhoseAntecedentIs(String antecedent);
	
	/**
	 * 
	 * @param any object
	 * @return true if the specified object is a IGrammar that contains the same set of rules ; false otherwise 
	 * @see representation.IGrammaticalRule
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
