package grammarModel.structure;

import java.util.List;
import java.util.Map;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.utils.ITreePaths;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;

/**
 * The ISyntacticStructure abstract type defines an element (derivation rule or terminal symbol) of a context-free grammar. 
 * The composition relationship that defines a type in object-oriented programming ("any instance of class A has 
 * an instance of class B and an instance of class C for components") is used here as an equivalent to the derivation 
 * relationship of a context-free grammar ("the symbol A can be substituted by the string of symbols "BC").  
 * Thus, any instance of ISyntacticStructure is truly a derivation of a given symbol in a syntax 
 * tree, from a node to its generated terminals (if the symbol is not a terminal itself). So ISyntacticStructure can then 
 * be extended in order to be instantiated as : <br> 
 * 1/ a syntax tree terminal node, or syntax 'leaf' : {@link ISyntaxLeaf} <br> 
 * 2/ the derivation from a non-terminal node, or syntax 'branch' : {@link ISyntaxBranch} <br>
 * 3/ a whole syntax tree  : also a {@link ISyntaxBranch}, but whose name is the start element of the context-free 
 * grammar at use <br>
 * 4/ also, a list of syntax trees : {@link ISyntaxGrove}. <br>
 *  
 * @author Gael Tregouet
 *
 */
public interface ISyntacticStructure extends Cloneable {

	/**
	 * @return the name (or type) of this structure, which is also the name of the generating node in the syntax tree 
	 * from which its components are derived : it therefore refers to a grammatical element of the context-free grammar 
	 * at use. 
	 * Two different syntactic structures may have the same name, if this name is a non-terminal element (or 'variable') 
	 * of the context-free grammar that can lead to different derivations.
	 */
	String getName();
	
	/**
	 * @return a list of paths that contains all the spanning paths of the syntactic structure (from its 
	 * generating node to one of its terminals).   
	 * @throws GrammarModelException
	 */
	ITreePaths getTreePaths() throws GrammarModelException;
	
	/**
	 * @return the set of spanning paths associated with this structure, plus all the sets associated with any of 
	 * its components, sub-components. etc. 
	 * @throws GrammarModelException
	 */
	Set<ITreePaths> getSetOfTreePaths() throws GrammarModelException;
	
	/**
	 * @return the set of spanning chains of the poset (lower semilattice) that can be derived from this 
	 * syntactic structure.
	 * @see IOriginalPropertyPoset
	 * @throws GrammarModelException
	 */
	IPosetMaxChains getPosetMaxChains() throws GrammarModelException;
	
	/**
	 * 
	 * @return the set of implications associated with the poset (lower semilattice) that can be derived from this
	 * syntactic structure.
	 * @see IPosetMaxChains
	 * @throws GrammarModelException
	 */
	Set<IImplication> getImplications() throws GrammarModelException;		
	
	ISyntacticStructure clone();
	
	/**
	 * A 'poset element name' is used to generate the property poset spanning chains from the syntactic structure, 
	 * and ultimately the property poset itself.
	 * It is a concatenation of the syntactic element 'name' with an index generated to make sure that that structures 
	 * belonging to the same 'syntactic glove' can't return the same 'poset element name' if they don't have the same 
	 * components. 
	 * Put another way : two nodes from syntax trees belonging to the same glove (and possibly having the same name) 
	 * can't generate the same 'poset element name' if they don't also generate the same derivation.
	 * 
	 * @see IPosetMaxChains, IOriginalPropertyPoset
	 * 
	 * @return a 'poset element name'.   
	 * @throws GrammarModelException
	 */
	String getPosetElementName() throws GrammarModelException;
	
	/**
	 * @return the list of component structures. The composition relationship between ISyntacticStructure objects 
	 * being an equivalent to the derivation relationship between nodes of a syntax tree, component structures 
	 * represent the nodes of a syntax tree that are directly derived from the generating node of this structure
	 * (the one that gives this structure its name). 
	 */
	List<ISyntacticStructure> getListOfComponents();
	
	/**
	 * @return  a list of lists of strings, representing all the spanning paths of the syntactic structure 
	 * (leading from its generating node to one of its terminals).
	 */
	List<List<String>> getListOfTreeStringPaths();
	
	/**
	 * @return  a list of lists of strings, representing the set of spanning chains of the poset (lower semilattice) 
	 * that can be derived from this syntactic structure.
	 */
	List<List<String>> getListOfPosetMaxStringChains() throws GrammarModelException;
	
	/**
	 * @return the list of IDs associated with every syntax leaf ({@link ISyntaxLeaf}) that can be derived from this 
	 * structure's generating node. 
	 * (All {@link ISyntaxLeaf} have a leafID). 
	 */
	List<Long> getListOfLeafIDs();
	
	/**
	 * @return a concatenation (with a separator) of the names of the syntax leaves (or terminals) that can be derived from 
	 * this structure's generating node. If the node is itself a terminal (because the structure {@link ISyntaxLeaf}),  
	 * its name is returned.  
	 */
	String getStringOfTerminals();
	
	/**
	 * @return true if this structure has been provided with an ID, and can subsequently generate a 'poset element name'.
	 */
	boolean getIDHasBeenSet();
	
	/**
	 * A-> A -> A : first A's recursion index is 2
	 * @return
	 */
	int getRecursionIndex();
	
	/**
	 * @param prop property name, referring to an element of the context-free grammar at use. 
	 * @return true if the parameter is the name of this structure generating node, or the name of any derived
	 * node in the syntax tree.  
	 */
	boolean hasThisProperty(String prop);
		
	/**
	 * Provides this structure (and its components, sub-components, etc.) with a 'poset element ID', mapped with 
	 * its generated list of paths. This method is called by {@link ISyntaxGrove}.
	 * @param pathsToIndex a map that associates tree paths with a unique ID. 
	 * @throws GrammarModelException
	 */
	void setPosetElementID(Map<ITreePaths, Integer> pathsToIndex) throws GrammarModelException;
	
	/**
	 * Replaces a target leaf ({@link ISyntaxLeaf}) component by a new component. 
	 * Syntax tree 'growth' involves the replacement of leaves by new branches ({@link ISyntaxBranch}) ; 
	 * or, rarely, by new leaves. 
	 * These new components are injected in the structure whith the IDs of the leaf they are meant to replace. If one 
	 * of the structure component is a target leaf, then it is replaced ; otherwise, the same method is recursively 
	 * called on every non-terminal component, with the same parameters.  
	 * @param newComp new syntactic component
	 * @param compIDs IDs of the leaves to be replaced
	 * @return true if a replacement has occurred. 
	 */
	boolean replaceComponents(ISyntacticStructure newComp, List<Long> compIDs);
	
	/**
	 * The recursion index of a structure of type A is the maximal number of sub-structures of type A it contains in 
	 * a single chain. 
	 * May need to be overloaded if the structure is a clustered value.
	 * @return
	 * @throws GrammarModelException 
	 */
	Map<String, Integer> setRecursionIndex() throws GrammarModelException;
	
	/**
	 * If 'a' is a branch of type {A} that leads to a sub-branch of the same type : a(A#, (...(a(A,(...)))...)
	 * @param recursionIndex
	 */
	void markRecursion() throws GrammarModelException;
		
}
