package grammarModel;

import java.util.List;

import exceptions.grammarModelException;

public interface IChains {
	
	List<List<String>> getChains();
	
	String getRoot();
	
	boolean hasNext();
	
	String next() throws grammarModelException;
	
	void resetIndexes();
	
	boolean equals(Object otherChains);

}
