package representation;

/**
 * The start state is the state that reads the first symbol of the word entered in the state machine. It triggers 
 * the evaluation of the word. 
 * 
 * @author Gael Tregouet
 *
 */
public interface IStartState extends IState {
	
	/**
	 * 
	 * @param specifiedWord the word just entered in the machine
	 * @return the closed report on the evaluation of the whole word
	 */
	IEvaluationLog initiateEvaluationOf(IWord specifiedWord);

}
