package grammarModel.structure;

import java.util.List;
import java.util.Map;

import grammarModel.exceptions.GrammarModelException;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;

/**
 * <p>
 * Any <i> class </i> implementing the syntax branch interface defines a derivation rule of a context-free 
 * grammar. As any syntactic structure does, it uses the composition relationship between classes ("any instance 
 * of class <i> A </i> is composed of an instance of class <i> B </i> and an instance of class <i> C </i>") 
 * as an equivalent of the derivation relationship between symbols ("the symbol <i> A </i> can be substituted 
 * by the string of symbols <i> BC </i> "). <br>
 * </p>
 * 
 * <p>
 * Thus, any <i> instance </i> of a class implementing the syntax branch interface represents a derivation of a 
 * nonterminal symbol (the one that gives the structure its name) in the form of a syntax tree. As a result of this 
 * derivation, any syntax branch instance yields a string of terminals that forms a <i> functional expression </i>, 
 * which is a descriptive statement about the object to which this structure applies. 
 * The functional expression of a syntax branch consists in the application of a function to a list of arguments. 
 * This function is given by the "function leaf" component that characterizes every syntax branch instance ; the 
 * function's arguments are the functional expressions and free variables returned by the other components.  <br>
 * A syntax branch can also provide a description of its associated object in the form of a <i> relational 
 * description </i>, using order relations over a set of symbols. (see {@link IDescription}) <br>  
 * </p> 
 * 
 * @see grammarModel.structure.ISyntacticStructure
 * @see grammarModel.structure.ISyntaxLeaf
 * @see representation.dataFormats.IFunctionalExpression
 * @author Gael Tregouet
 *
 */
public interface ISyntaxBranch extends ISyntacticStructure {
	
	//getters
	
	/**
	 * Returns the components whose generated functional expressions are the arguments of the syntax branch's function. 
	 * 
	 * @see representation.dataFormats.IFunctionalExpression
	 * @return the components that generate the arguments of this branch's function
	 */
	List<ISyntacticStructure> getArguments();
	

	
	/**
	 * Returns the branch's function, in the form of a syntax leaf. 
	 * 
	 * @return the branch's function
	 */
	ISyntaxLeaf getFunction();	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * A functional expression consists in the application of a <i> function </i> to a list of <i> arguments </i>. <br>
	 * The function is the branch's <i> function leaf </i>. <br> 
	 * The arguments are the functional expressions returned by the branch's <i> argument </i> components. <br>
	 * </p>
	 * 
	 * @see grammarModel.structure.ISyntaxLeaf
	 */
	@Override
	IFunctionalExpression getFunctionalExpression();
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * The returned list is a concatenation of the lists returned by every component. <br>
	 * </p>
	 * 
	 * @see grammarModel.structure.ISyntaxLeaf
	 */
	@Override
	List<Long> getListOfLeafIDs();
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * The list is constructed out of every component's paths lists. They are all included in a single list 
	 * of paths and the structure's name is inserted at the beginning of every path.
	 * </p> 
	 */
	@Override
	List<List<String>> getPathsAsListsOfStrings();
	
	/**
	 * {@inheritDoc}
	 * 
	 * REWRITE DOC
	 */
	@Override
	IRelationalDescription getRelationalDescription() throws GrammarModelException ;	
	
	/**
	 * Returns true if this syntax branch is a tree, i.e. if its name is the start element of the context-free
	 * grammar at use.
	 * 
	 * @return true if this syntax branch is a tree
	 */
	boolean isATree();	
	
	//setters
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * The branch's recursion index must have been set previously. If this index equals <i> n </i>, then the 
	 * function leaf of this branch has <i> n </i> recursion marks added at the end of its name. <br> 
	 * 
	 * This method is recursively called in every component of the branch. 
	 * </p>
	 * 
	 * @see grammarModel.structure.ISyntaxLeaf
	 */
	@Override
	void markRecursion() throws GrammarModelException;
	
	/**
	 * <p>
	 * Replaces a terminal ({@link ISyntaxLeaf}) argument by a new argument, provided that the terminal is 
	 * declared as implementing an interface whose the new argument is also an implementation of. <br>
	 * </p>
	 * 
	 * <p>
	 * Syntax tree 'growth' involves the replacement of leaves by new branches ({@link ISyntaxBranch}) ; 
	 * or, rarely, by new leaves. <br> 
	 * These new argument are injected in the structure with the IDs of the leaf they are meant to replace. If one 
	 * of the structure argument is a target leaf, then it is replaced ; otherwise, the same method is recursively 
	 * called on every argument, with the same parameters. <br>
	 * </p>
	 * 
	 * @see grammarModel.structure.ISyntaxLeaf
	 * @param newArg new argument
	 * @param targetIDs IDs of the leaves to be replaced
	 * @throws GrammarModelException if a replacement is attempted although the recursion index has already been set.
	 * (See {@link ISyntaxBranch#setRecursionIndex()})
	 * @return true if a replacement has occurred. 
	 */
	boolean replaceComponents(ISyntacticStructure newArg, List<Long> targetIDs) throws GrammarModelException;	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * This method must be called first on every <i> argument </i> component of the branch. The returned maps are merged 
	 * in such a way that, if a symbol is mapped to different values, only the biggest value is kept. <br>
	 * If this branch's name is already mapped to a value, this value is incremented by one in the map and retained as 
	 * this branch's index value. If not, the map is updated with this branch's name mapped to its current recursion index 
	 * (which has to be 0). Finally, the map is returned. <br> 
	 * </p>
	 * 
	 * @see grammarModel.structure.ISyntaxLeaf
	 */
	@Override
	Map<String, Integer> setRecursionIndex();

}
