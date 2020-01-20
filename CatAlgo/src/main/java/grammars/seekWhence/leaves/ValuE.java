package grammars.seekWhence.leaves;

import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxLeaf;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.disjunctions.IValueOrValuE;

/**
 * ValuE represents a terminal symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class is a leaf ({@link ISyntaxLeaf}) of a syntax tree generated using this grammar.
 * @author Gael Tregouet
 *
 */
public final class ValuE extends SyntaxLeaf implements ISyntaxLeaf, IValueOrRelation, IValueOrValuE {

	public final String value;
	
	/**
	 * All {@link ISyntaxLeaf} types have a no-argument constructor. ValuE is an exception to this rule since 
	 * its argument is not a {@link ISyntacticStructure} but a simple String that is used as the instance's name. 
	 */
	public ValuE(String value) {
		this.value = value;
	}

	private ValuE(String value, long leafID) {
		super(leafID);
		this.value = value;
	}

	@Override
	public String getName() {
		return value;
	}

	@Override
	public ISyntacticStructure clone() {
		return new ValuE(value, super.getLeafID());
	}

}
