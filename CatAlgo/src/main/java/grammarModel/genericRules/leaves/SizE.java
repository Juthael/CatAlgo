package grammarModel.genericRules.leaves;

import grammarModel.genericRules.branches.Size;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class SizE extends SyntaxLeaf implements ISyntaxLeaf {

	/**
	 * <p>
	 * This class defines a generic terminal element, that can integrate context-free grammars associated with various 
	 * microworlds. <br>
	 * </p>
	 * 
	 * <p>
	 * <b>SizE</b> is the "function leaf" of the {@link Size} generic branch. <br>
	 * </p>
	 * @see grammarModel.genericRules.branches.Size
	 * @see grammarModel.structure.ISyntacticStructure
	 * @see grammarModel.structure.ISyntaxBranch
	 * @see grammarModel.structure.ISyntaxLeaf
	 * @author Gael Tregouet
	 */
	public static final String NAME="size";
	
	public SizE() {
	}

	public SizE(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME.concat(recursionMark);
	}

	@Override
	public ISyntacticStructure clone() {
		return new SizE(super.getLeafID());
	}

}
