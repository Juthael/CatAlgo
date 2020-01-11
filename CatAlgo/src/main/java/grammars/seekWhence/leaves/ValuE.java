package grammars.seekWhence.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

/**
 * ValuE represents a terminal symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a leaf of a syntactic tree generated using this grammar.
 * @author Gael Tregouet
 *
 */
public final class ValuE extends SyntacticLeaf implements ISyntacticLeaf {

	public final String value;
	
	public ValuE(String value) {
		this.value = value;
	}

	public ValuE(String value, long leafID) {
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
