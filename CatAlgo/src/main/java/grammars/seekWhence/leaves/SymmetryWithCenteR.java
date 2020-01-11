package grammars.seekWhence.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;

/**
 * SymmetryWithCenteR represents a terminal symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a leaf of a syntactic tree generated using this grammar.
 * @author Gael Tregouet
 *
 */
public final class SymmetryWithCenteR extends SyntacticLeaf implements ISyntacticLeaf {

	public static final String NAME = "SymmetryWithCenteR";
	
	public SymmetryWithCenteR() {
	}

	public SymmetryWithCenteR(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public ISyntacticStructure clone() {
		return new SymmetryWithCenteR(super.getLeafID());
	}

}
