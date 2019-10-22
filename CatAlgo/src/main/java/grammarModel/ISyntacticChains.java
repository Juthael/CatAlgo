package grammarModel;

import java.util.List;

import exceptions.grammarModelException;

public interface ISyntacticChains {

	List<List<String>> getChains();
	
	boolean hasNext();
	
	String next() throws grammarModelException;
	
	long getCurrentElementLeafID();	
	
	void resetIndexes();
	
	boolean equals(Object otherChains);
	
}
