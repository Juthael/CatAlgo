package propertyPoset;

import fca.LatticeMiner;
import fca.core.context.binary.BinaryContext;
import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IPropertyPoset is a partially ordered set whose elements are 'properties'. <br>
 * 
 * As such, it is composed of a {@link IPropertySet} object (unordered set of properties), and of a 
 * {@link IRelation} object (partial order relation on the set of properties). <br>
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
	 * This method guarantees that the poset is displayed in a reduced form, i.e. rid of its 'non-informative' elements. <br>
	 * 
	 * An element is informative only if it is a dimension, a dimension value, an atom of the (lower semi-lattice) poset 
	 * or the poset root.  <br>
	 * 
	 * Any non-informative element is removed from the poset and kept as an 'encapsulated property' of its antecedent, in  
	 * the dedicated field of the {@link IProperty}. This procedure can be recursive if the antecedent is itself a 
	 * non-informative property. <br>
	 * 
	 * @throws PropertyPosetException 
	 */
	void reducePoset() throws PropertyPosetException ;


}
