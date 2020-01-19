package grammars.seekWhence.leaves;

import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxLeaf;

/**
 * SymmetrY represents a terminal symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a leaf of a syntax tree generated using this grammar.
 * @author Gael Tregouet
 *
 */
public final class SymmetrY extends SyntaxLeaf implements ISyntaxLeaf {

	public static final String NAME = "SymmetrY";
	
	public SymmetrY() {
	}

	public SymmetrY(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new SymmetrY(super.getLeafID());
	}

}
