package representation;

public interface IStartState extends IState {
	
	IEvaluationLog evaluateWord(IWord specifiedWord);

}
