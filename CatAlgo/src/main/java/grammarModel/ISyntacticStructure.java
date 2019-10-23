package grammarModel;

import java.util.List;

public interface ISyntacticStructure extends Cloneable {

	String getName();
	
	List<List<String>> getListOfChains();
	
	List<Long> getListOfLeafIDs();
	
	ISyntacticChains getSyntacticChains();
	
	ISyntacticStructure clone();
	
	IPosetMaxChains getPosetMaxChains();
	
	String getPosetFullName();
	
	List<ISyntacticStructure> getListOfComponents();
	
}
