package propertyPoset.impl;

import propertyPoset.IImplication;

public class Implication implements IImplication {

	private String antecedent;
	private String consequent;
	
	public Implication(String antecedent, String consequent) {
		this.antecedent = antecedent;
		this.consequent = consequent;
	}

	public String getAntecedent() {
		return antecedent;
	}

	public String getConsequent() {
		return consequent;
	}
	
	public boolean equals(Object otherImpl) {
		if (this == otherImpl) {
			return true;
		}
		else if (otherImpl == null || this.getClass() != otherImpl.getClass()) {
			return false;
		}
		else {
			IImplication otherImplication = (IImplication) otherImpl;
			return (this.getAntecedent().equals(otherImplication.getAntecedent()) && 
					this.getConsequent().equals(otherImplication.getConsequent()));
		}
	}

}
