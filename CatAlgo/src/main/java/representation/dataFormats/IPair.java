package representation.dataFormats;

import representation.stateMachine.ISymbol;

/**
 * A pair is an element of a binary relation. It associates a symbol (the <i> antecedent </i>) to another symbol 
 * (the <i> consequent </i>)
 * 
 * @see representation.dataFormats.IBinaryRelation
 * @see representation.stateMachine.ISymbol
 * @author Gael Tregouet
 *
 */
public interface IPair {
	
	@Override
	boolean equals(Object o);
	
	ISymbol getAntecedent();
	
	ISymbol getConsequent();
	
	@Override
	int hashCode();

}
