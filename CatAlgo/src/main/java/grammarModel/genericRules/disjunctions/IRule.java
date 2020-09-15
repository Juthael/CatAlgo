package grammarModel.genericRules.disjunctions;

import grammarModel.genericRules.branches.ClusteredValue;
import grammarModel.genericRules.leaves.ClusteR;
import grammarModel.genericRules.leaves.ValuE;
import grammarModel.structure.ISyntacticStructure;

/**
 * <p>
 * <b>IRule</b> is a generic marker interface that can be used to implement disjunctive rules in context-free grammars associated 
 * with various micro-worlds. <br>
 * </p>
 * 
 * <p>
 * To implement a disjunctive rule like <i>A = B|C</i> ("The symbol <i>A</i> can be substituted by the symbol <i>B</i> or the 
 * symbol <i>C</i>"), a "marker" interface <i>i</i> is built that is only implemented by classes <i>B</i> and <i>C</i>, and 
 * the class <i>A</i> is defined as having a component of type <i>i</i>.<br>
 * </p>
 * 
 * <p>
 * A rule is some logic relation between values that organizes a cluster and allows to replace values by clustered values. <br>
 * </p>
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
