package representation.dataFormats.impl;

import representation.dataFormats.IGrammaticalRule;
import representation.stateMachine.ISymbol;
import representation.utils.HashCodeUtil;

public class GrammaticalRule implements IGrammaticalRule {

	private final ISymbol antecedent;
	private final ISymbol consequent;
	
	public GrammaticalRule(ISymbol antecedent, ISymbol consequent) {
		this.antecedent = antecedent;
		this.consequent = consequent;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean equals;
		if (o == this)
			equals = true;
		else if (!(o instanceof GrammaticalRule))
			equals = false;
		else {
			IGrammaticalRule other = (IGrammaticalRule) o;
			equals = (antecedent.equals(other.getAntecedent()) && consequent.equals(other.getConsequent()));
		}
		return equals;
	}	

	@Override
	public ISymbol getAntecedent() {
		return antecedent;
	}

	@Override
	public ISymbol getConsequent() {
		return consequent;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		char[] antecedentArray = antecedent.toString().toCharArray();
		char[] consequentArray = consequent.toString().toCharArray();
		for (char antCharac : antecedentArray)
			hashCode += HashCodeUtil.hash(HashCodeUtil.SEED, antCharac);
		for (char consCharac : consequentArray)
			hashCode += HashCodeUtil.hash(HashCodeUtil.SEED, consCharac);
		return hashCode;
	}
	
	@Override
	public String toString() {
		String rule;
		StringBuilder sB = new StringBuilder();
		sB.append(antecedent.toString());
		sB.append(" ::= ");
		sB.append(consequent.toString());
		rule = sB.toString();
		return rule;
	}

}
