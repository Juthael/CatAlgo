package grammarModel;

public interface ISyntacticBranch extends ISyntacticStructure {
	
	boolean replaceComponent(ISyntacticBranch newComp, int compID);

}
