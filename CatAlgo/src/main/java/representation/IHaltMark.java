package representation;

/**
 * A halt mark is a notification that, while evaluating a word, the machine halted prematurely. It means that in the last 
 * active state, no rule was available to proceed a state transition given the last symbol read. 
 * @author Gael Tregouet
 *
 */
public interface IHaltMark extends IEvaluation {

}
