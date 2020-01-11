package grammars.seekWhence.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

/**
 * BouncingCyclE represents a terminal symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a leaf of a syntactic tree generated using this grammar.
 * @author Gael Tregouet
 *
 */
public final class BouncingCyclE extends SyntacticLeaf implements ISyntacticLeaf {

	private static final String NAME = "BouncingCyclE";
	
	public BouncingCyclE() {
	}
	
	public BouncingCyclE(long leafID) {
		super(leafID);
	}	
	
	@Override
	public String getName() {
		return NAME;
	}		

	@Override
	public ISyntacticStructure clone() {
		return new BouncingCyclE(super.getLeafID());
	}

}
