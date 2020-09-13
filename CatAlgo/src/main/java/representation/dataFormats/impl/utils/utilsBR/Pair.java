package representation.dataFormats.impl.utils.utilsBR;

import representation.dataFormats.IPair;
import representation.stateMachine.ISymbol;
import representation.utils.HashCodeUtil;

public class Pair implements IPair {

	private final ISymbol antecedent;
	private final ISymbol consequent;
	
	public Pair(ISymbol antecedent, ISymbol consequent) {
		this.antecedent = antecedent;
		this.consequent = consequent;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean oEqualsThis;
		if (o == this)
			oEqualsThis = true;
		else if (!(o instanceof IPair)) {
			oEqualsThis = false;
		}
		else {
			IPair other = (IPair) o;
			oEqualsThis = (antecedent.equals(other.getAntecedent()) && consequent.equals(other.getConsequent()));
		}
		return oEqualsThis;
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
		for (char antChar : antecedent.toString().toCharArray()) {
			hashCode += HashCodeUtil.hash(HashCodeUtil.SEED, antChar);
		}
		for (char conChar : consequent.toString().toCharArray()) {
			hashCode += HashCodeUtil.hash(HashCodeUtil.SEED, conChar);
		}
		return hashCode;
	}

}
