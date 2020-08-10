package representation;

public interface IAcceptState extends IState {
	
	IEvaluationLog acceptWord(IWord specifiedWord);

}
