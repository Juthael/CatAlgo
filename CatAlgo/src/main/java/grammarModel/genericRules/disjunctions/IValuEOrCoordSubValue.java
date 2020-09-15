package grammarModel.genericRules.disjunctions;

import grammarModel.genericRules.branches.CoordSubValue;
import grammarModel.genericRules.leaves.ValuE;
import grammarModel.structure.ISyntacticStructure;

/**
 * <p>
 * <b>IValuEOrCoordSubValue</b> is a generic marker interface that can be used to implement disjunctive rules in context-free 
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
 * <b>IValuEOrCoordSubValue</b> is used to denote that a given grammar symbol can either be replaced by an absolute position 
 * value ({@link ValuE}), or by a relative position value ({@link CoordSubValue}) that gives an object position inside a group 
 * of objects (itself characterized by a position value).
 * </p>
 * 
 * @see grammarModel.genericRules.leaves.ValuE
 * @see grammarModel.genericRules.branches.CoordSubValue
 * @see grammarModel.genericRules.branches.Coordinate
 * @author Gael Tregouet
 *
 */
public interface IValuEOrCoordSubValue extends ISyntacticStructure {

}
