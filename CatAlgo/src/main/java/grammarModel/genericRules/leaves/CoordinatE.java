package grammarModel.genericRules.leaves;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class CoordinatE extends SyntaxLeaf implements ISyntaxLeaf {

	private static final String NAME = "coordinate";
	
	/**
	 * <p>
	 * This class defines a generic terminal element, that can integrate context-free grammars associated with various 
	 * microworlds. <br>
	 * </p>
	 * 
	 * <p>
	 * <b>CoordinatE</b> is the "function leaf" of the {@link CoordinatE} generic branch. <br>
	 * </p>
	 * @see grammarModel.genericRules.branches.Coordinate
	 * @see grammarModel.structure.ISyntacticStructure
	 * @see grammarModel.structure.ISyntaxBranch
	 * @see grammarModel.structure.ISyntaxLeaf
	 * @author Gael Tregouet
	 */
	public CoordinatE() {
	}

	public CoordinatE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME.concat(recursionMark);
	}

	@Override
	public ISyntacticStructure clone() {
		return new CoordinatE(super.getLeafID());
	}

}
