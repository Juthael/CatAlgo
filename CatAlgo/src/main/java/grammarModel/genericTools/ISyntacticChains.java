package grammarModel.genericTools;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.GrammarModelException;

public interface ISyntacticChains extends IChains {

	long getCurrentLeafID();
	
	Map<List<String>, Set<Long>> getPathToLeafIDs() throws GrammarModelException;
	
	String getLeaf(Long leafID) throws GrammarModelException;
	
	boolean hasProperty(String property) throws GrammarModelException;
	
}
