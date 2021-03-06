package grammarModel.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
/**
 * A ITreePaths is a list (endowed with navigation functionalities) of all the paths of a syntactic structure 
 * from its root to one of its terminals.
 * @see ISyntacticStructure
 * @author Gael Tregouet
 *
 */
public interface ITreePaths extends IChains {

	/**
	 * @return the ID of the current path terminal (or 'leaf'). 
	 */
	long getCurrentLeafID();
	
	/** 
	 * @return a mapping of all the paths minus their terminal elements, with the IDs 
	 * of all the terminals that can be attained by following such a path. 
	 * @throws GrammarModelException
	 */
	Map<List<String>, Set<Long>> getPathToLeafIDs() throws GrammarModelException;
	
	/**
	 * 
	 * @param leafID the ID of the target terminal (or 'leaf').
	 * @return the name of the target terminal.
	 * @throws GrammarModelException
	 */
	String getLeaf(Long leafID) throws GrammarModelException;
	
	/**
	 * 
	 * @param property the name of the property that is looked for. 
	 * @return true if any of the paths owns this property. 
	 * @throws GrammarModelException
	 */
	boolean hasProperty(String property) throws GrammarModelException;
	
}
