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
	 * A property is non-informative if it is neither a dimension nor a dimension root or value. <br>
	 * 
	 * A good example of a 'non-informative property' would be a property B that has a property A as its unique predecessor 
	 * (and A is not the poset root). 
	 * In this case, when any other element than B implies B, then one knows for sure that it also implies A. Since the set of 
	 * non-B elements implying the property B is the same than the set of elements implying the property A, B provides no 
	 * additional information and can therefore be removed. It must however be kept as an 'encapsulated property' of A in the 
	 * dedicated field of the class {@link IProperty}. <br>
	 * 
	 * @throws PropertyPosetException 
	 */
	void reducePoset() throws PropertyPosetException ;


}
