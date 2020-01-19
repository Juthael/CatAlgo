package grammars.seekWhence.leaves;

import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxLeaf;

/**
 * BouncingCyclE represents a terminal symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a leaf of a syntax tree generated using this grammar.
 * @author Gael Tregouet
 *
 */
public final class BouncingCyclE extends SyntaxLeaf implements ISyntaxLeaf {

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
