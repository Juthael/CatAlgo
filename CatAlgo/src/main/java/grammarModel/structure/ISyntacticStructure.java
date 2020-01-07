package grammarModel.structure;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IPosetMaxChains;
import grammarModel.genericTools.ISyntacticChains;
import propertyPoset.IImplication;

public interface ISyntacticStructure extends Cloneable {

	String getName();
	
	ISyntacticChains getSyntacticChains() throws GrammarModelException;
	
	Set<ISyntacticChains> getSetOfSyntacticChains() throws GrammarModelException;
	
	IPosetMaxChains getPosetMaxChains() throws GrammarModelException;
	
	Set<IImplication> getImplications() throws GrammarModelException;
	
	boolean isRedundant();
	
	void markRedundancies();
	
	ISyntacticStructure clone();
	
	String getPosetFullName() throws GrammarModelException;
	
	List<ISyntacticStructure> getListOfComponents();
	
	List<List<String>> getListOfSyntacticStringChains();
	
	List<List<String>> getListOfPosetMaxStringChains() throws GrammarModelException;
	
	List<Long> getListOfLeafIDs();
	
	String getStringOfTerminals();
	
	boolean getIDHasBeenSet();
	
	boolean hasThisProperty(String prop);
	
	void setAsRedundant();
		
	void setPosetID(Map<ISyntacticChains, String> chainsToIndex) throws GrammarModelException;
	
	boolean replaceComponent(ISyntacticStructure newComp, Integer compID);
		
}
