package grammarModel.genericRules.disjunctions;

import grammarModel.structure.ISyntacticStructure;

/**
 * <p>
 * <b>IValueOrClusteredValue</b> is a generic marker interface that can be used to implement disjunctive rules in context-free 
 * grammars associated with various micro-worlds. <br>
 * </p>
 * 
 * <p>
 * To implement a disjunctive rule like <i>A = B|C</i> ("The symbol <i>A</i> can be substituted by the symbol <i>B</i> or the 
 * symbol <i>C</i>"), a "marker" interface <i>i</i> is built that is only implemented by classes <i>B</i> and <i>C</i>, and 
 * the class <i>A</i> is defined as having a component of type <i>i</i>.<br>
 * </p>
 * 
 * <p>
 * <b>IValueOrClusteredValue</b> is used to denote that a given grammar symbol can either be replaced by a simple value, 
 * or by an element belonging to a structured group. <br>
 * </p>
 * @see grammarModel.genericRules.leaves.ValuE
 * @see grammarModel.genericRules.branches.ClusteredValue
 * @see grammarModel.genericRules.branches.Cluster
 * @author Gael Tregouet
 *
 */
public interface IValueOrClusteredValue extends ISyntacticStructure {

}
