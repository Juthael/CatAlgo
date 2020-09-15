package grammarModel.genericRules.leaves;

import grammarModel.genericRules.branches.Cluster;
import grammarModel.genericRules.disjunctions.IValuEOrCoordSubValue;
import grammarModel.genericRules.disjunctions.IValueOrClusteredValue;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxLeaf;

public class ValuE extends SyntaxLeaf implements ISyntaxLeaf, IValueOrClusteredValue, IValuEOrCoordSubValue {

	public final String value;
	
	/**
	 * <p>
	 * This class defines a generic terminal element, that can integrate context-free grammars associated with various 
	 * microworlds. <br>
	 * </p>
	 * 
	 * <p>
	 * <b>ValuE</b> defines any value that is not (or not yet) included in a broader group (or {@link Cluster}). <br>
	 * </p>
	 * 
	 * @see grammarModel.genericRules.branches.Cluster
	 * @see grammarModel.genericRules.branches.ClusteredValue
	 * @see grammarModel.structure.ISyntacticStructure
	 * @see grammarModel.structure.ISyntaxBranch
	 * @see grammarModel.structure.ISyntaxLeaf
	 * @author Gael Tregouet
	 */
	public ValuE(String value) {
		this.value = value;
	}

	public ValuE(String value, long leafID) {
		super(leafID);
		this.value = value;
	}

	@Override
	public String getName() {
		return value.concat(recursionMark);
	}

	@Override
	public ISyntacticStructure clone() {
		return new ValuE(value, super.getLeafID());
	}

}
