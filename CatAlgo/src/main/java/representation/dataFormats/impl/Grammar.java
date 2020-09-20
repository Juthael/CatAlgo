package representation.dataFormats.impl;

import java.util.HashSet;
import java.util.Set;

import representation.dataFormats.IGrammar;
import representation.dataFormats.IGrammaticalRule;
import representation.stateMachine.ISymbol;
import representation.utils.HashCodeUtil;

public class Grammar implements IGrammar {

	private final Set<IGrammaticalRule> grammaticalRules;
	
	public Grammar() {
		grammaticalRules = new HashSet<IGrammaticalRule>();
	}
	
	public Grammar(Set<IGrammaticalRule> grammaticalRules) {
		this.grammaticalRules = grammaticalRules;
	}

	//getters

	@Override
	public boolean contains(IGrammaticalRule rule) {
		return grammaticalRules.contains(rule);
	}

	@Override
	public boolean contains(Set<IGrammaticalRule> rules) {
		return grammaticalRules.containsAll(rules);
	}

	@Override
	public boolean contains(IGrammar grammar) {
		return grammaticalRules.containsAll(grammar.getGrammaticalRules());
	}
	
	@Override
	public boolean equals(Object o) {
		boolean equals;
		if (o == this)
			equals = true;
		else if (!(o instanceof Grammar))
			equals = false;
		else {
			IGrammar other = (IGrammar) o;
			equals = ((grammaticalRules.size() == other.getGrammaticalRules().size()) 
					&& (grammaticalRules.containsAll(other.getGrammaticalRules())));
		}
		return equals;
	}
	
	@Override
	public Set<IGrammaticalRule> getGrammaticalRules() {
		return grammaticalRules;
	}	

	@Override
	public int getNbOfRulesWhoseAntecedentIs(ISymbol symbol) {
		Set<IGrammaticalRule> rulesWithSpecifiedAnt = new HashSet<IGrammaticalRule>();
		for (IGrammaticalRule rule : grammaticalRules) {
			if (rule.getAntecedent().equals(symbol))
				rulesWithSpecifiedAnt.add(rule);
		}
		return rulesWithSpecifiedAnt.size();
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (IGrammaticalRule rule : grammaticalRules) {
			hashCode += (rule.hashCode() + HashCodeUtil.SEED);
		}
		return hashCode;
	}
	
	@Override
	public String toString() {
		String grammar;
		StringBuilder sB = new StringBuilder();
		for (IGrammaticalRule rule : grammaticalRules) {
			sB.append(rule.toString());
			sB.append(System.lineSeparator());
		}
		grammar = sB.toString();
		return grammar;
	}
	
	//setters
	
	@Override
	public void add(IGrammaticalRule grammaticalRule) {
		grammaticalRules.add(grammaticalRule);
	}

}
