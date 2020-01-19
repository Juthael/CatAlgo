package grammarModel.structure;

import java.util.List;
import java.util.Map;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.utils.ISyntacticChains;

/**
 * ISyntacticBranch is a syntactic structure that represents the derivation of a non-terminal node in a syntactic
 * tree. If this generating node (that gives the structure its name) is the start element of the context-free grammar 
 * at use, then the syntactic branch represents a whole tree. 
 * @author Gael Tregouet
 *
 */
public interface ISyntacticBranch extends ISyntacticStructure {
	
	@Override
	boolean replaceComponents(ISyntacticStructure newComp, List<Long> compIDs);
	
	@Override
	public String getPosetElementName() throws GrammarModelException;
	
	@Override
	public List<List<String>> getListOfSyntacticStringChains();
	
	@Override
	public List<List<String>> getListOfPosetMaxStringChains() throws GrammarModelException;
	
	@Override
	public List<Long> getListOfLeafIDs();	
	
	@Override
	public String getStringOfTerminals();
	
	@Override
	public boolean getIDHasBeenSet();
	
	/**
	 * 
	 * @return true if this syntactic branch is a tree, i.e. if its name is the start element of the context-free
	 * grammar at use.
	 */
	public boolean isATree();
	
	@Override
	public void setPosetElementID(Map<ISyntacticChains, String> chainsToString) throws GrammarModelException;
	
}
