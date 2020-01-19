package grammarModel.structure;

import java.util.List;
import java.util.Map;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.utils.ISyntacticChains;

/**
 * ISyntacticLeaf is a terminal syntactic structure, representing the terminal node of a syntactic tree. 
 * It has no component and is only characterized by its name and ID. 
 * @author Gael Tregouet
 *
 */
public interface ISyntacticLeaf extends ISyntacticStructure {
	
	
	@Override
	public String getPosetElementName() throws GrammarModelException;
	
	@Override
	public List<ISyntacticStructure> getListOfComponents();
	
	@Override
	public List<List<String>> getListOfSyntacticStringChains();
	
	@Override
	public List<List<String>> getListOfPosetMaxStringChains() throws GrammarModelException;
	
	/**
	 * 
	 * @return the ID long value that characterizes every terminal ('leaf') element. 
	 */
	public long getLeafID();
	
	@Override
	public List<Long> getListOfLeafIDs();
	
	@Override
	public String getStringOfTerminals();
	
	/**
	 * @return true, since the poset element ID of a syntactic leaf is set by its constructor.
	 */
	@Override
	public boolean getIDHasBeenSet();
	
	/**
	 * The poset element ID of a syntactic leaf is set by its constructor ; so this inherited method has no effect.
	 */
	@Override
	public void setPosetElementID(Map<ISyntacticChains, String> chainsToIndex);
	
	/**
	 * This inherited method is irrelevant for a syntactic leaf, since it has no component. Has no reason to be 
	 * called, but if so, does not cause any error. 
	 */
	@Override
	public boolean replaceComponents(ISyntacticStructure newComp, List<Long> compID);
	
}
