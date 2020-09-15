package grammarModel.genericRules.leaves;

import grammarModel.genericRules.branches.Cluster;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class ClusteR extends SyntaxLeaf implements ISyntaxLeaf {

	/**
	 * <p>
	 * This class defines a generic terminal element, that can integrate context-free grammars associated with various 
	 * microworlds. <br>
	 * </p>
	 * 
	 * <p>
	 * <b>ClusteR</b> is the "function leaf" of the {@link Cluster} generic branch. <br>
	 * </p>
	 * @see grammarModel.genericRules.branches.Cluster
	 * @see grammarModel.structure.ISyntacticStructure
	 * @see grammarModel.structure.ISyntaxBranch
	 * @see grammarModel.structure.ISyntaxLeaf
	 * @author Gael Tregouet
	 */
	public static final String NAME="cluster";
	
	public ClusteR() {
	}

	public ClusteR(long leafID) {
		super(leafID);
	}

	@Override
	public String getName() {
		return NAME.concat(recursionMark);
	}

	@Override
	public ISyntacticStructure clone() {
		return new ClusteR(super.getLeafID());
	}

}
