package representation.dataFormats;

import java.util.Set;

import representation.stateMachine.ISymbol;

/**
 * A context-free grammar is a collection of rules, each of them mapping an antecedent with a consequent. 
 * 
 * @see representation.dataFormats.IGrammaticalRule
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
	 * @param any symbol
	 * @return the number of rules with the specified symbol as an antecedent
	 */
	int getNbOfRulesWhoseAntecedentIs(ISymbol symbol);
	
	@Override
	int hashCode();
	
	/**
	 * Returns the grammar as a String.
	 * @return the grammar as a String
	 */
	@Override
	String toString();
	
	//setters
	
	/**
	 * Adds the specified rule to this grammar.
	 * @param grammaticalRule the rule to be added
	 */
	void add(IGrammaticalRule grammaticalRule);
	
	

}
