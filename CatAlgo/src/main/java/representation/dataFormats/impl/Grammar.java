package representation.dataFormats.impl;

import java.util.HashSet;
import java.util.Set;

import representation.dataFormats.IGrammar;
import representation.dataFormats.IGrammaticalRule;

public class Grammar implements IGrammar {

	Set<IGrammaticalRule> grammaticalRules = new HashSet<IGrammaticalRule>();
	
	public Grammar() {
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
			equals = ()
		}
		
	}
	
	@Override
	public Set<IGrammaticalRule> getGrammaticalRules() {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	public int getNbOfRulesWhoseAntecedentIs(String antecedent) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int hashCode() {
		
	}
	
	//setters
	
	@Override
	public void add(IGrammaticalRule grammaticalRule) {
		
	}

}
