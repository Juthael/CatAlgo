package grammarModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.grammarModelException;
import propertyPoset.IImplication;

public interface ISyntacticStructure extends Cloneable {

	String getName();
	
	ISyntacticChains getSyntacticChains() throws grammarModelException;
	
	Set<ISyntacticChains> getSetOfSyntacticChains() throws grammarModelException;
	
	IPosetMaxChains getPosetMaxChains() throws grammarModelException;
	
	Set<IImplication> getImplications() throws grammarModelException;
	
	ISyntacticStructure clone();
	
	String getPosetFullName() throws grammarModelException;
	
	List<ISyntacticStructure> getListOfComponents();
	
	List<List<String>> getListOfSyntacticStringChains();
	
	List<List<String>> getListOfPosetMaxStringChains() throws grammarModelException;
	
	List<Integer> getListOfLeafIDs();
	
	boolean getIDHasBeenSet();
		
	void setPosetID(Map<ISyntacticChains, String> chainsToIndex) throws grammarModelException;
		
}
