package grammarModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.grammarModelException;
import propertyPoset.IImplication;

public interface ISyntacticStructure extends Cloneable {

	String getName();
	
	ISyntacticChains getSyntacticChains();
	
	Set<ISyntacticChains> getSetOfSyntacticChains();
	
	IPosetMaxChains getPosetMaxChains() throws grammarModelException;
	
	Set<IImplication> getImplications() throws grammarModelException;
	
	ISyntacticStructure clone();
	
	String getPosetFullName() throws grammarModelException;
	
	List<ISyntacticStructure> getListOfComponents();
	
	List<List<String>> getListOfSyntacticStringChains();
	
	List<List<String>> getListOfPosetMaxStringChains() throws grammarModelException;
	
	List<Long> getListOfLeafIDs();
	
	boolean getIDHasBeenSet();
		
	void setPosetID(Map<ISyntacticChains, String> chainsToIndex);
		
}
