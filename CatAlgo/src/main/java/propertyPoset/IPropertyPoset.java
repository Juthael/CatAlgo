package propertyPoset;

import java.util.Set;

import fca.LatticeMiner;
import fca.core.context.binary.BinaryContext;
import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IPropertyPoset is a partially ordered set whose elements are 'properties'. <br>
 * 
 * As such, it is composed of a {@link IPropertySet} object (unordered set of properties), and of a 
 * {@link IRelation} object (partial order relation on the set of properties). <br>
 * 
 * It may also contain 'sub-contexts', i.e. IPropertyPoset components instantiated by the 'extractSubContexts()' 
 * methods (only if the method is called, and if 'sub-contexts' can be found in the poset). 
 * 
 * @author Gael Tregouet
 *
 */
public interface IPropertyPoset {
	
	/**
	 * @return the set of properties.
	 */
	IPropertySet getProperties();
	
	/**
	 * @return the relation on the set of properties, implemented as an object mapping every property with the set of its 
	 * consequents and offering a few more functionalities to identify special elements. 
	 */
	IRelation getRelation();
	
	/**
	 * 
	 * The 'binary context' is a binary relation over the set of properties such that (x,y) means 'x implies y'. 
	 * It can be 
	 * used as an argument for the construction of a lattice with {@link LatticeMiner}. Before generating the binary 
	 * context, this method ensures that the sub-contexts of this poset have been extracted (extractSubContexts()), and that 
	 * the poset has been reduced (reducePoset()).  
	 * @return a binary context
	 * @throws PropertyPosetException 
	 */
	BinaryContext getBinaryContext() throws PropertyPosetException;
	
	/**
	 * A 'sub-context' is the set of consequents of a sub-context root. <br>
	 * 
	 * A 'sub-context root' is a minimal element in the set of 'all dimension roots minus the poset root'. If this 
	 * set is empty, then there is no sub-context root and no sub-context. <br>
	 * A 'dimension root' is the infimum of the set of elements that precede a dimension. <br> 
	 * A 'dimension' is a sup-reducible element of the poset. <br>
	 * @return a set of posets, each one being a 'sub-context' of this poset.
	 * @throws PropertyPosetException 
	 */
	Set<IPropertyPoset> getSubContexts() throws PropertyPosetException;
	
	/**
	 * This method guarantees that the poset is displayed in a reduced form, i.e. rid of its 'non-informative' elements. <br>
	 * 
	 * A 'non-informative element' is a property B that has a property A as its unique predecessor (and A is not the poset root). 
	 * In this case, when any other element than B implies B, then one knows for sure that it also implies A. Since the set of 
	 * non-B elements implying the property B is the same than the set of elements implying the property A, B provides no 
	 * additional information and can therefore be removed. It must however be kept as an 'encapsulated property' of A in the 
	 * dedicated field of the class {@link IProperty}. <br>
	 * 
	 * The poset reduction can be achieved by removing any element P that does not fulfill one of the following 
	 * conditions : <br> 
	 * 1/ P is sup-reducible ( = is a 'dimension'). <br>
	 * 2/ P is the infimum of the immediate predecessors of (at least) one dimension ( = is the 
	 * 'contextual basis' of this dimension, or its 'root'). <br>
	 * 3/ P is an immediate successor of a contextual basis ( = is a 'dimension atom'). <br> 
	 * 
	 * @throws PropertyPosetException 
	 */
	void reducePoset() throws PropertyPosetException ;
	
	/**
	 * This method ensures that all 'sub-contexts' have been extracted and that there exists no more sub-context root 
	 * in the original poset. 
	 * 
	 * This can be achieved as follows : <br>
	 * 1/ Identify potential sub-context roots. A 'sub-context root' is a minimal element in the set of all 'dimension 
	 * roots' minus the poset root. A 'dimension root' is the infimum of the set of properties that precede a dimension 
	 * (i.e. a sup-reducible element). <br>
	 * 2/ For every sub-context root, build a set made up of all its consequents. That is a 'sub-context'. <br>
	 * 3/ Let 'O' be the root of the original (lower semi-lattice) poset. In this poset, turn every sub-context root into 
	 * a leaf, so that it doesn't have any consequent anymore apart from itself. Now the poset is no longer connected ; 
	 * remove every element that isn't a consequent of O.   
	 * 4/ Repeat the operation on every sub-context built, until no more-sub context root is found. 
	 * 
	 * This method is called recursively on sub-context posets. 
	 */
	void extractSubContexts() throws PropertyPosetException ;


}
