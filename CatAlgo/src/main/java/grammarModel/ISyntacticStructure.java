package grammarModel;

import java.util.List;

import propertyPoset.IPosetMaxChains;

public interface ISyntacticStructure extends Cloneable {
	
	String getName();
	
	List<List<String>> getListOfChains();
	
	List<Long> getListOfListIDs();
	
	ISyntacticChains getSyntacticChains();
	
	ISyntacticStructure clone();
	
	IPosetMaxChains getPosetMaxChains();
	
	String getPosetFullName();
	
	List<ISyntacticStructure> getListOfComponents();

}
