package grammarModel;

import java.util.List;

public interface IChains {

	List<List<String>> getChains();
	
	boolean hasNextChainElement();
	
	boolean hasNextChain();
	
	boolean hasNext();
	
	String next();
	
	void resetIndexes();
	
	boolean equals();
	
}
