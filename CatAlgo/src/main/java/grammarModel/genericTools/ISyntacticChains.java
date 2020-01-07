package grammarModel.genericTools;

import java.util.Map;

public interface ISyntacticChains extends IChains {

	long getCurrentLeafID();
	
	Map<String, Long> getDimensionToLeafID();
	
	String getLeaf(Long leafID);
	
	boolean hasProperty(String property);
	
}
