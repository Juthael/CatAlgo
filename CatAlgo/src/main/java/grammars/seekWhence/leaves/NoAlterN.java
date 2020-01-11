package grammars.seekWhence.leaves;

import grammarModel.structure.ISyntacticLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticLeaf;
import grammars.seekWhence.disjunctions.IAlternation;

/**
 * NoAlterN represents a terminal symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a leaf of a syntactic tree generated using this grammar.
 * @author Gael Tregouet
 *
 */
public final class NoAlterN extends SyntacticLeaf implements ISyntacticLeaf, IAlternation {

	private static final String NAME = "NoAlterN";
	
	public NoAlterN() {
	}
	
	public NoAlterN(long leafID) {
		super(leafID);
	}
	
	@Override
	public String getName() {
		return NAME;
	}	

	@Override
	public ISyntacticStructure clone() {
		return new NoAlterN(super.getLeafID());
	}

}
