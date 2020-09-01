package representation.dataFormats;

import java.util.Set;

/**
 * A IGrammar is a context-free grammar, implemented as a collection of rules mapping an antecedent with a consequent. 
 * 
 * @author Gael Tregouet
 *
 */
public interface IGrammar {
	
	//getters
	
	/**
	 * Checks that the specified rule belongs to this grammar.
	 * @param rule a grammatical rule
	 * @return true if the specified rule belongs to this grammar
	 */
	boolean contains(IGrammaticalRule rule);
	
	/**
	 * Checks that every rule among the specified set of rules belongs to this grammar.
	 * @param rules a set of grammatical rules
	 * @return true if every rule from the specified set belongs to this grammar ; false otherwise
	 */
	boolean contains(Set<IGrammaticalRule> rules);
	
	/**
	 * Checks that the specified grammar is a subset of this grammar.
	 * @param grammar a context-free grammar
	 * @return true if the set of rules contained in the specified grammar is also contained in this 
	 * grammar ; false otherwise
	 * @see representation.dataFormats.IGrammaticalRule
	 */
	boolean contains(IGrammar grammar);
	
	@Override
	boolean equals(Object object);	
	
	/**
	 * Return this grammar's set of rules.
	 * @return the set of rules
	 */
	Set<IGrammaticalRule> getGrammaticalRules();	
	
	/**
	 * Returns the number of rules in this grammar with the specified symbol as an antecedent.
	 * @param antecedent the name of a symbol
	 * @return the number of rules with the specified symbol as an antecedent
	 */
	int getNbOfRulesWhoseAntecedentIs(String antecedent);
	
	@Override
	int hashCode();
	
	//setters
	
	void add(IGrammaticalRule grammaticalRule);
	
	

}
