package grammars.seekWhence.disjunctions;

import grammarModel.structure.ISyntacticStructure;

/**
 * IAlternation is an interface used for the implementation of a disjunctive rule in the context-free grammar 
 * associated with the microworld 'SeekWhence'. <br>
 * To implement a disjunctive rule like "A = B|C" ("The symbol B can be substituted by the symbol B or the symbol C"), 
 * a "marker" interface "i" is built that is only implemented by classes B and C, and the class A is defined as having 
 * a component of type "i".
 * @author Gael Tregouet
 *
 */
public interface IAlternation extends ISyntacticStructure {

}
