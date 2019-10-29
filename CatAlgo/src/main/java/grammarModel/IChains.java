package grammarModel;

import java.util.List;

import exceptions.GrammarModelException;

public interface IChains {
	
	List<List<String>> getChains();
	
	String getRoot();
	
	boolean hasNext();
	
	String next() throws GrammarModelException;
	
	void resetIndexes();
	
	boolean equals(Object otherChains);

}
