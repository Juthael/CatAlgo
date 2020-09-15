package grammarModel.structure;

import java.util.List;
import java.util.Map;

import grammarModel.exceptions.GrammarModelException;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.IFunctionalExpression;

/**
 * <p>
 * Any <i> class </i> implementing the syntax leaf interface represents a terminal symbol of a context-free 
 * grammar. As is the case for any syntactic structure, the composition relationship that defines 
 * a leaf <i> L </i> as a <i> component </i> of a syntax branch <i> B </i> is an equivalent of a context-free 
 * grammar derivation rule such as <i> B ::= L | (...) </i>, with <i> L </i> being a terminal. <br>
 * </p>
 * 
 * <p>
 * Any syntax branch instance yields a string of terminals that forms a <i>functional expression</i>, which
 * consists in the application of a function to a list of arguments. Accordingly, a <i> leaf instance </i> can either 
 * be a "function" leaf, or a simple variable. This has no consequence on its implementation, since both types of 
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
	 * {@inheritDoc}
	 * 
	 * <p>
	 * The functional expression returned by a leaf contains a single term : the leaf's name. <br>
	 * A leaf is a component of a syntax branch. As such, this single term will either be : <br> 
	 * -a function applying to the the branch's arguments, if the leaf is the branch's <i> function leaf. </i> <br>
	 * -an element of the branch's list of arguments. <br>
	 * </p>  
	 * 
	 * <p>
	 * Until it is declared as an argument (or part of an argument) of a function, the term returned has no coordinate (i.e., 
	 * its coordinate list remains empty). <br> 
	 * </p>
	 * 
	 * @see grammarModel.structure.ISyntaxBranch
	 */
	@Override
	public IFunctionalExpression getFunctionalExpression();	
	
	/**
	 * Returns the leaf unique ID.
	 * @return the leaf ID. 
	 */
	public long getLeafID();	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * However, since a leaf is a terminal, the rule it is associated with has no right term, and therefore a leaf has no 
	 * component. The returned list is empty. <br>
	 * </p> 
	 * 
	 * @return an empty list
	 */
	@Override
	public List<ISyntacticStructure> getListOfComponents();	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * In the case of a leaf, since it can't be derived, the returned list only contains the leaf's own ID.
	 * </p>
	 * 
	 * @return a list with only the leaf ID
	 */
	@Override
	public List<Long> getListOfLeafIDs();		
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * If the syntactic structure is a leaf, it is a terminal element of the grammar (and a tree with a single node), 
	 * and therefore associated to a rule with no right-term. A recursion mark can be appended to its name (see 
	 * {@link ISyntaxBranch#setRecursionIndex()}).
	 * </p>  
	 */
	@Override
	public String getName();	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * In the case of a leaf, there is only one path, that only contains one element. So the returned list contains a single 
	 * list, that contains a single string : this leaf's name.
	 * </p> 
	 * 
	 * @return this structure's unique path, with this leaf as a single element
	 */
	@Override
	public List<List<String>> getPathsAsListsOfStrings();	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * However, a leaf being composed of a single element, the relation returned is an empty set of pairs. 
	 * </p>
	 * 
	 * @see grammarModel.structure.ISyntaxBranch#getRelationalDescription()
	 */
	@Override
	public IRelationalDescription getRelationalDescription();
	
	//setters
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Since a leaf has no component, there is nothing to mark, so this method doesn't do anything. 
	 * It only exists so it can be called on any syntactic structure component without throwing errors. <br>
	 * </p>  
	 */
	@Override
	public void markRecursion() throws GrammarModelException;
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Obviously, if the structure is a leaf, the only mapping returned is that of the leaf's name to the index 
	 * <code> 0 </code>. <br>
	 * </p> 
	 */
	@Override
	public Map<String, Integer> setRecursionIndex();
	
	/**
	 * The recursion mark on a function leaf indicate the degree of recursion of this function. It can 
	 * only be called by its containing branch. The recursion mark ought to be a static final String value.   
	 * 
	 * @see grammarModel.structure.ISyntaxBranch
	 * @throws GrammarModelException 
	 */
	public void setRecursionMark(int recursionIndex) throws GrammarModelException;

}
