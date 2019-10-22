package grammarModel;

public interface ISyntacticBranch extends ISyntacticStructure {
	
	String getPosetFullName();
	
	ISyntacticChains getPosetMaxChains();
	
	String setPosetID();
	
	boolean replaceComponent(ISyntacticBranch newComp, long compID);

}
