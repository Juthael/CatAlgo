package grammarModel.structure;

import java.util.List;
import java.util.Map;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.utils.ITreePaths;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;
import representation.IBinaryRelation;

/**
 * <p>
 * Any <i> class </i> implementing the syntactic structure interface defines an element (derivation rule or 
 * terminal symbol) of a context-free grammar. <br> 
 * The composition relationship that defines a type in object-oriented programming ("any instance of class A 
 * has an instance of class B and an instance of class C for components") is used here as an equivalent to the 
 * derivation rule of a context-free grammar ("the symbol A can be substituted by the string of symbols "BC").
 * </p>
 * 
 * <p>
 * Thus, any <i> instance </i> of a class implementing the syntactic structure interface represents a 
 * derivation of a given symbol in the form of a syntax tree, whose this symbol is the root of (and its unique 
 * element if it is a terminal). Any such instance yields a string of terminals that forms a <i> functional 
 * expression </i>, which is a descriptive statement about the object to which this structure is associated. 
 * This description can also be provided as a <i> binary relation </i>. (see {@link representation.IDescription}) <br>
 * </p> 
 * 
 * <p> 
 * Accordingly, the syntactic structure interface can be extended in order to describe : <br> 
 * 1/ a <i> syntax leaf </i>, which is a syntax tree terminal node : {@link ISyntaxLeaf}. A leaf can either be a 
 * function, or a variable given as an argument to a function. <br> 
 * 2/ a <i> syntax branch </i>, which is a derivation from a non-terminal node : {@link ISyntaxBranch}. A syntax branch 
 * has two components or more. The first component is a 'function' leaf ; its arguments are the strings of 
 * terminals resulting from the derivation of every other components.  <br>
 * 3/ a <i> syntax tree </i>, which is a syntax branch whose name is the start element of the context-free 
 * grammar at use <br>
 * 4/ a <i> syntax grove </i>, which is a list of syntax trees : {@link ISyntaxGrove}. <br>
 * </p>
 * 
 * s
 * @author Gael Tregouet
 *
 */
public interface ISyntacticStructure extends Cloneable {

	//Getters
	
	/**
	 * 
	 * @return a clone object
	 */
	ISyntacticStructure clone();
	
	IBinaryRelation getBinaryRelation
	
	/**
	 * Returns the list of this structure's components. Each component name is one of the right-terms of the grammatical 
	 * rule to which this structure is associated. <br>
	 * 
	 * @return the list of this structure's components.
	 */
	List<ISyntacticStructure> getListOfComponents();
	
	/**
	 * Returns the list of IDs associated with any terminal ({@link ISyntaxLeaf}) derived from this structure's root.
	 * @return the list of IDs associated with any terminal ({@link ISyntaxLeaf}) derived from this structure's root. 
	 */
	List<Long> getListOfLeafIDs();	
	
	/**
	 * Returns the name of the syntactic structure, which is the left-term of the grammatical rule to which it is 
	 * associated. <br> 
	 * 
	 * A syntactic structure instance can be regarded as a syntax tree ; in this case, the structure's name is the 
	 * tree's root. <br>
	 * 
	 * @return the name of the syntactic structure
	 */
	String getName();
	
	/**
	 * Returns the list of paths (as a list of lists) in this structure's tree, from the symbol that gives 
	 * the syntactic structure its name to any reachable terminal. <br>
	 * @return  the list of paths from the symbol that gives the syntactic structure its name to any reachable terminal.
	 */
	List<List<String>> getPathsAsListOfStrings();	
	
	/**
	 * <p>
	 * The recursion index equals to the maximum number of occurrences of the structure's name can be found in a single path 
	 * of its syntax tree, at any place but the beginning.. <br>
	 * 
	 * A recursion index is used to make sure that two different structures can never yield the same 
	 * binary relation. It allows to add a distinctive mark to recursive symbols with the markRecursion() method. <br>
	 * </p>
	 * 
	 * <p>
	 * How it works (<i> X </i> and <i> Y </i> are any substring) : <br>
	 * <i> a -> X -> a -> Y -> a </i> : first <i> A </i>'s recursion index is 2
	 * </p>
	 * 
	 * @see representation.IBinaryRelation
	 * @return the recursion index of this structure
	 */
	int getRecursionIndex();	
	
	/**
	 * Returns the list of paths (with navigating functionalities) in this structure's tree, from the symbol that gives 
	 * the syntactic structure its name to any reachable terminal. <br>
	 * 
	 * @return the list of paths from the symbol that gives the syntactic structure its name to any reachable terminal.
	 * @throws GrammarModelException
	 */
	ITreePaths getTreePaths() throws GrammarModelException;	
	
	//Setters
	
	/**
	 * If 'a' is a branch of type {A} that leads to a sub-branch of the same type : a(A#, (...(a(A,(...)))...)
	 * @param recursionIndex
	 */
	void markRecursion() throws GrammarModelException;	
	
	/**
	 * <p>
	 * Replaces a terminal ({@link ISyntaxLeaf}) component by a new component. <br>
	 * </p>
	 * 
	 * <p>
	 * Syntax tree 'growth' involves the replacement of leaves by new branches ({@link ISyntaxBranch}) ; 
	 * or, rarely, by new leaves. <br> 
	 * These new components are injected in the structure with the IDs of the leaf they are meant to replace. If one 
	 * of the structure component is a target leaf, then it is replaced ; otherwise, the same method is recursively 
	 * called on every non-terminal component, with the same parameters. <br>
	 * </p>
	 * 
	 * @param newComp new syntactic component
	 * @param compIDs IDs of the leaves to be replaced
	 * @return true if a replacement has occurred. 
	 */
	boolean replaceComponents(ISyntacticStructure newComp, List<Long> compIDs);
	
	/**
	 * The recursion index equals the maximum number of occurrences of the structure's name that can be found in 
	 * a single <i> argument </i> path of its syntax tree, minus one. <br>
	 * 
	 * An argument path is a path does not lead to the structure's <i> function </i> (see {@link ISyntaxBranch}). <br>
	 * 
	 * <p>
	 * How it works (<i> X </i> and <i> Y </i> are any substring) : <br>
	 * <i> a -> X -> a -> Y -> a </i> : first <i> A </i>'s recursion index is 2 <br>
	 * </p>.
	 * 
	 * @return
	 * @throws GrammarModelException 
	 */
	Map<String, Integer> setRecursionIndex() throws GrammarModelException;
		
}
