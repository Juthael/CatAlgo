package representation.stateMachine.impl;

import representation.stateMachine.ISymbol;
import representation.utils.HashCodeUtil;

public class Symbol implements ISymbol {

	private String name;
	
	public Symbol(String name) {
		this.name = name;
	}

	//getters
	
	@Override
	public boolean equals(Object o) {
		boolean oEqualsThis;
		if (o == this)
			oEqualsThis = true;
		else if (!(o instanceof ISymbol))
			oEqualsThis = false;
		else {
			ISymbol other = (ISymbol) o;
			oEqualsThis = (this.toString().equals(other.toString()));
		}
		return oEqualsThis;
	}
	
	@Override
	public int getCost() {
		return 0;
	}
	
	@Override
	public final int hashCode() {
		int hashCode = 0;
		for (char thisChar : name.toCharArray()) {
			hashCode += HashCodeUtil.hash(HashCodeUtil.SEED, thisChar);
		}
		return hashCode;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
