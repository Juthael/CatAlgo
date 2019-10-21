package grammarModel;

public interface ISyntacticChains extends IChains {
	
	String previous();
	
	long getCurrentElementLeafID();

}
