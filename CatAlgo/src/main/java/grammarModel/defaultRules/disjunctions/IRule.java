package grammarModel.defaultRules.disjunctions;

import grammarModel.defaultRules.branches.ClusteredValue;
import grammarModel.defaultRules.leaves.ClusteR;
import grammarModel.defaultRules.leaves.ValuE;
import grammarModel.structure.ISyntacticStructure;

/**
 * A rule is some logic relation between values that allows to build a cluster and a to replace values by clustered values.   
 * 
 * @see ValuE
 * @see ClusteR
 * @see ClusteredValue
 * @See {@link ISyntacticStructure#replaceComponents(ISyntacticStructure, java.util.List)}
 * @author Gael Tregouet
 *
 */
public interface IRule extends ISyntacticStructure {

}
