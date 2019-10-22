package grammarModel;

import java.util.List;

public interface ISyntacticStructure extends Cloneable {
	
	String getName();
	
	List<List<String>> getListOfStringChains();
	
	List<Long> getListOfListIDs();
	
	ISyntacticChains getSyntacticChains();
	
	ISyntacticStructure clone();
	
	ISyntacticChains getChains();
	
	String getPosetFullName();
	
	List<ISyntacticStructure> getListOfComponents();

}
