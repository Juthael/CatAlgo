package grammarModel.structure;

import java.util.List;
import java.util.Map;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.utils.ITreePaths;

/**
 * ISyntaxLeaf is a {@link ISyntacticStructure} representing a terminal symbol in a context-free grammar. <br>
 * Thus, any instance of ISyntaxLeaf is a leaf in a syntax tree. <br> 
 * A ISyntaxLeaf instance has no component and is only characterized by its name and ID. 
 * @author Gael Tregouet
 *
 */
public interface ISyntaxLeaf extends ISyntacticStructure {
	
	
	@Override
	public String getPosetElementName() throws GrammarModelException;
	
	@Override
	public List<ISyntacticStructure> getListOfComponents();
	
	@Override
	public List<List<String>> getListOfTreeStringPaths();
	
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
	 * @return true, since the poset element ID of a syntax leaf is its name.
	 */
	@Override
	public boolean getIDHasBeenSet();
	
	/**
	 * The poset element ID of a syntax leaf is its name, set by its constructor ; so this inherited method
	 * is overridden in order to have no-effect.
	 */
	@Override
	public void setPosetElementID(Map<ITreePaths, String> pathsToIndex);
	
	/**
	 * This inherited method is irrelevant for a syntax leaf, since it has no component. Has no reason to be 
	 * called, but if so, does not cause any error. 
	 */
	@Override
	public boolean replaceComponents(ISyntacticStructure newComp, List<Long> compID);
	
}
