package grammars.seekWhence.leaves;

import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxLeaf;

/**
 * IncremenT represents a terminal symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class is a leaf ({@link ISyntaxLeaf}) of a syntax tree generated using this grammar.
 * @author Gael Tregouet
 *
 */
public final class IncremenT extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "IncremenT";
	
	/**
	 * All {@link ISyntaxLeaf} types have a no-argument constructor.
	 * 
	 * This is quite logical, since : <br>
	 * 1/ the derivation relationship of a context-free grammar is implemented as a composition relationship. <br>
	 * 2/ this allows the constructor of a {@link ISyntacticStructure} to express a replacement rule, its list of 
	 * arguments being the right-hand side of the rule.
	 * 3/ {@link ISyntaxLeaf} are terminals (they can't be replaced), and therefore must be instantiated without any 
	 * argument. 
	 */
	public IncremenT() {
	}
	
	private IncremenT(long leafID) {
		super(leafID);
	}
	
	@Override
	public String getName() {
		return NAME;
	}		

	@Override
	public ISyntacticStructure clone() {
		return new IncremenT(super.getLeafID());
	}

}
