package grammarModel.structure;

import java.util.List;
import java.util.Map;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.utils.ITreePaths;

/**
 * ISyntaxBranch represents a derivation rule of a context-free grammar, whose 'left' term is a 
 * non-terminal symbol. To do so, it uses the composition relationship that defines a type in 
 * object-oriented programming as an equivalent of the derivation relationship. 
 * Thus, any instance of ISyntaxBranch constitutes an actual derivation of a non-terminal symbol 
 * in a syntax tree. 
 * If this symbol (that gives the structure its name) is the start element of the grammar at use, 
 * then the syntax branch represents a whole tree. 
 * @author Gael Tregouet
 *
 */
public interface ISyntaxBranch extends ISyntacticStructure {
	
	@Override
	boolean replaceComponents(ISyntacticStructure newComp, List<Long> compIDs);
	
	@Override
	public String getPosetElementName() throws GrammarModelException;
	
	@Override
	public List<List<String>> getListOfTreeStringPaths();
	
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
	 * @return true if this syntax branch is a tree, i.e. if its name is the start element of the context-free
	 * grammar at use.
	 */
	public boolean isATree();
	
	@Override
	public void setPosetElementID(Map<ITreePaths, String> pathsToString) throws GrammarModelException;
	
}
