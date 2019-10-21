package grammarModel;

import propertyPoset.IPosetMaxChains;

public interface ISyntacticBranch extends ISyntacticStructure {
	
	String getPosetFullName();
	
	IPosetMaxChains getPosetMaxChains();
	
	String setPosetID();
	
	boolean replaceComponent(ISyntacticBranch newComp, long compID);

}
