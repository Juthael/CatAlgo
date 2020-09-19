package representation.dataFormats.impl;

import representation.dataFormats.IDescription;
import representation.dataFormats.IRelationalDescription;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

public abstract class Description implements IDescription {

	private int hashCode;
	
	public Description() {
	}
	
	@Override
	public boolean equals(Object o) {
		boolean thisEqualsOther;
		if (o == this)
			thisEqualsOther = true;
		else if (!(o instanceof representation.dataFormats.IDescription))
			thisEqualsOther = false;
		else {
			IDescription otherUnknownFormatDesc = (IDescription) o;
			if (!this.getSymbols().equals(otherUnknownFormatDesc.getSymbols()))
				thisEqualsOther = false;
			else {
				IRelationalDescription otherDesc = null;
				IRelationalDescription thisDesc = null;
				try {
					otherDesc = otherUnknownFormatDesc.getRelationalDescription();
					thisDesc = getRelationalDescription();
				} catch (RepresentationException e) {
					System.out.println("Description.equals() : an error has occurred." 
							+ System.lineSeparator() + e.getMessage());
				}
				thisEqualsOther = (thisDesc.getMaxTotalOrders().equals(otherDesc.getMaxTotalOrders())); 				
			}
		}
		return thisEqualsOther;
	}
	
	@Override
	public int hashCode() {
		return hashCode;
	}
	
	protected int setHashCode() {
		int hashCode = 0;
		for (ISymbol symbol : getSymbols()) {
			hashCode += symbol.hashCode();
		}
		return hashCode;
	}

	

}
