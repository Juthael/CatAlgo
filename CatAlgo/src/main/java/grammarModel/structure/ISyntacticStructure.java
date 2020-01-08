package grammarModel.structure;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IPosetMaxChains;
import grammarModel.genericTools.ISyntacticChains;
import propertyPoset.IImplication;

/**
 * ISyntacticStructure is an abstract class that can be extended in order to represent : 1/ a syntactic tree terminal node 
 * (or 'syntactic leaf') 2/ the derivation from a non-terminal node ('syntactic branch') 3/ a whole syntactic tree 
 * (which is also a 'syntactic branch', but not contained in any other syntactic branch) 4/ a list of syntactic trees 
 * (called a 'syntactic glove').
 *  
 * @author Gael Tregouet
 *
 */
public interface ISyntacticStructure extends Cloneable {

	/**
	 * 
	 * @return the name (or type) of the node from which the syntactic structure is derivated. 
	 * This name refers to a grammatical element of the context-free grammar at use. 
	 * Two different syntactic structures can have the same name if this name is a non-terminal element (or 'variable') 
	 * of a context-free grammar (for it can lead to different derivations).
	 */
	String getName();
	
	/**
	 * 
	 * @return the set of spanning chains that can be extracted from this structure. All those chains start with the 
	 * structure name (which designate its generating node) and end with a terminal element of the context-free grammar.  
	 * @throws GrammarModelException
	 */
	ISyntacticChains getSyntacticChains() throws GrammarModelException;
	
	/**
	 * Recursive method.
	 * @return the set of spanning chains that can be extracted from this structure, as well as the sets of spanning chains 
	 * extracted from all this structure components, sub-components. etc. 
	 * @throws GrammarModelException
	 */
	Set<ISyntacticChains> getSetOfSyntacticChains() throws GrammarModelException;
	
	/**
	 * 
	 * @return the set of spanning chains of the lower semilattice associated with this syntactic structure. These chains
	 * are used as a parameter for the property poset constructor. 
	 * @see IOriginalPropertyPoset
	 * @throws GrammarModelException
	 */
	IPosetMaxChains getPosetMaxChains() throws GrammarModelException;
	
	/**
	 * 
	 * @return the set of implications derived from the lower semilattice associated with this syntactic structure. These
	 * implications can be used as a parameter for the property poset constructor.
	 * @throws GrammarModelException // pas clair
	 */
	Set<IImplication> getImplications() throws GrammarModelException;
	
	boolean isRedundant();
	
	void markRedundancies();
	
	ISyntacticStructure clone();
	
	String getPosetFullName() throws GrammarModelException;
	
	List<ISyntacticStructure> getListOfComponents();
	
	List<List<String>> getListOfSyntacticStringChains();
	
	List<List<String>> getListOfPosetMaxStringChains() throws GrammarModelException;
	
	List<Long> getListOfLeafIDs();
	
	String getStringOfTerminals();
	
	boolean getIDHasBeenSet();
	
	boolean hasThisProperty(String prop);
	
	void setAsRedundant();
		
	void setPosetID(Map<ISyntacticChains, String> chainsToIndex) throws GrammarModelException;
	
	boolean replaceComponent(ISyntacticStructure newComp, Integer compID);
		
}
