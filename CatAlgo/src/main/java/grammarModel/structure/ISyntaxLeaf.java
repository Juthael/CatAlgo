package grammarModel.structure;

import java.util.List;

import grammarModel.exceptions.GrammarModelException;

/**
 * <p>
 * Any <i> class </i> implementing the syntax leaf interface represents a terminal symbol of a context-free 
 * grammar. As is the case for any syntactic structure, the composition relationship that defines 
 * a leaf <i> L </i> as <i> contained </i> by a syntax branch <i> B </i> is an equivalent of a context-free 
 * grammar derivation rule such as <i> B ::= L | (...) </i>. <br>
 * </p>
 * 
 * <p>
 * Any syntax branch instance yields a string of terminals that forms a <i> functional expression </i>, which
 * consists in the application of a function to a list of arguments. Accordingly, a leaf can either be a 
 * "function" leaf, or a simple variable. This has no consequence on its implementation, since both types of 
 * leaves refer to the same interface and the same implementation. <br>   
 * </p> 
 * 
 * <p>
 * The tree generation process imposes that a (variable) leaf can be replaced by a new branch. To allow this, 
 * any leaf must be associated with a unique ID number, which is used to identify it as the target of a replacement 
 * procedure (see {@link ISyntaxBranch#replaceComponents(ISyntacticStructure, List)}). <br>
 * </p>  
 * 
 * @see grammarModel.structure.ISyntacticStructure
 * @see grammarModel.structure.ISyntaxBranch
 * @see representation.dataFormats.IFunctionalExpression
 * @author Gael Tregouet
 *
 */
public interface ISyntaxLeaf extends ISyntacticStructure {
	
	//getters
	
	/**
	 * Returns the leaf ID.
	 * @return the leaf ID. 
	 */
	public long getLeafID();
	
	/**
	 * Returns an empty list, sinc a leaf can't have any component
	 * 
	 * @return an empty list
	 */
	@Override
	public List<ISyntacticStructure> getListOfComponents();
	
	/**
	 * Returns a list that only contains the leaf ID.
	 * 
	 * @return a list with only the leaf ID
	 */
	@Override
	public List<Long> getListOfLeafIDs();	
	
	/**
	 * Returns a list that contains a single list, that contains a single string : this leaf's name. 
	 * 
	 * @return this structure's unique path, with this leaf as a single element
	 */
	@Override
	public List<List<String>> getPathsAsListsOfStrings();
	
	//setters
	
	/**
	 * Since a leaf has no component, there is nothing to mark, so this method doesn't do anything. <br>
	 * It only exists so it can be called on any syntactic structure component without throwing errors.  
	 */
	@Override
	public void markRecursion() throws GrammarModelException;	
	
	/**
	 * Since a leaf has no component, this method doesn't do anything. <br>
	 * It only exists so it can be called on any syntactic structure component without throwing errors. 
	 */
	@Override
	public boolean replaceComponents(ISyntacticStructure newComp, List<Long> compID);	
	
	/**
	 * The recursion mark on a function leaf indicate the degree of recursion of this function. It can 
	 * only be called by its containing branch. The recursion mark ought to be a static final String value.   
	 * 
	 * @see grammarModel.structure.ISyntaxBranch
	 * @throws GrammarModelException 
	 */
	public void setRecursionMark(int recursionIndex) throws GrammarModelException;

}
