package grammarModel.impl;

import java.util.List;

import grammarModel.IPosetMaxChains;
import grammarModel.ISyntacticChains;
import grammarModel.ISyntacticStructure;

public abstract class SyntacticStructure implements ISyntacticStructure {

	private String name;
	
	public SyntacticStructure(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ISyntacticChains getSyntacticChains() {
		List<List<String>> listOfChains = getListOfChains();
		List<Long> leafIDs = getListOfLeafIDs();
		ISyntacticChains synChains = new SyntacticChains(listOfChains, leafIDs);
		return synChains;
	}
	
	public abstract ISyntacticStructure clone();
	
	public abstract IPosetMaxChains getPosetMaxChains();
	
	public abstract String getPosetFullName();
	
	public abstract List<ISyntacticStructure> getListOfComponents();
	
	public abstract List<List<String>> getListOfChains();
	
	public abstract List<Long> getListOfLeafIDs();

}
