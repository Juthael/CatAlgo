package grammarModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.GrammarModelException;
import propertyPoset.IImplication;

public interface ISyntacticStructure extends Cloneable {

	String getName();
	
	ISynTreeChains getSyntacticChains() throws GrammarModelException;
	
	Set<ISynTreeChains> getSetOfSyntacticChains() throws GrammarModelException;
	
	IPosetMaxChains getPosetMaxChains() throws GrammarModelException;
	
	Set<IImplication> getImplications() throws GrammarModelException;
	
	ISyntacticStructure clone();
	
	String getPosetFullName() throws GrammarModelException;
	
	List<ISyntacticStructure> getListOfComponents();
	
	List<List<String>> getListOfSyntacticStringChains();
	
	List<List<String>> getListOfPosetMaxStringChains() throws GrammarModelException;
	
	List<Integer> getListOfLeafIDs();
	
	boolean getIDHasBeenSet();
		
	void setPosetID(Map<ISynTreeChains, String> chainsToIndex) throws GrammarModelException;
	
	boolean replaceComponent(ISyntacticBranch newComp, Integer compID);
		
}
