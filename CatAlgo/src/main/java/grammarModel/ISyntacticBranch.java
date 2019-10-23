package grammarModel;

import java.util.Map;

public interface ISyntacticBranch extends ISyntacticStructure {
	
	void setPosetID(Map<IChains, String> chainsToIndex);
	
	boolean replaceComponent(ISyntacticBranch newComp, long compID);

}
