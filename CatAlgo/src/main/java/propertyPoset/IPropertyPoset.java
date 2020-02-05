package propertyPoset;

import java.util.Set;

import fca.LatticeMiner;
import fca.core.context.binary.BinaryContext;
import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IPropertyPoset is a partially ordered set whose elements are properties. <br>
 * 
 * As such, it is composed of a {@link IPropertySet} object (unordered set of properties), and of a 
 * {@link IRelation} object (partial order relation on the set of properties). <br>
 * 
 * It is also endowed with a (possibly empty) set of IPropertyPoset objects called 'sub-contexts'. 
 * A 'sub-context' is a IPropertyPoset object built with the set of properties implied by a 'sub-context root'. <br> 
 * A 'sub-context root' is a minimal element in the set of all 'dimension roots' minus the poset root. <br> 
 * A 'dimension root' is the infimum of the set of properties that precede a dimension. <br>
 * A 'dimension' is a sup-reducible element of the poset. <br>  
 * 
 * Any constructor of a IPropertyPoset object must ensure that all sub-contexts have been extracted and that there 
 * exists no more sub-context root in the original poset. This can be achieved by turning all sub-context roots into leaves 
 * after sub-contexts have been extracted ; all elements that are connected no more to the (ex-) poset root can 
 * subsequently be removed. <br> 
 * 
 * Any constructor of a IPropertyPoset object must also ensure that the poset is displayed in a reduced form, 
 * i.e. rid of its 'non-informative' elements. <br>
 * 
 * A 'non-informative element' is a property B that has a property A as its unique predecessor (and A is not the poset root). 
 * In this case, when any other element than B implies B, then one knows for sure that it also implies A (in this case, 
 * people don't even notice B most of the time). Since the set of non-B elements implying the property B is the same than 
 * the set of elements implying the property A, B provides no additional information and can therefore be removed. It must 
 * however be kept as an 'encapsulated property' of A in the dedicated field of the class {@link IProperty}. <br>
 * 
 * @author Gael Tregouet
 *
 */
public interface IPropertyPoset {
	
	/**
	 * 
	 * @return the set of properties.
	 */
	IPropertySet getProperties();
	
	/**
	 * 
	 * @return the relation on the set of properties, implemented as an object mapping every property with the set of its 
	 * consequents and offering a few more functionalities to identify special elements. 
	 */
	IRelation getRelation();
	
	/**
	 * 
	 * The 'binary context' is a binary relation over the set of properties such that (x,y) means 'x implies y'.  
	 * @return a binary context that can be used as an argument for the construction of a lattice with {@link LatticeMiner}. 
	 * @throws PropertyPosetException 
	 */
	BinaryContext getBinaryContext() throws PropertyPosetException;
	
	/**
	 * A 'sub-context' is the set of consequents of a sub-context root. <br>
	 * A 'sub-context root' is a minimal element in the set of 'all dimension roots minus the poset root'. If this 
	 * set is empty, then there is no sub-context root and no sub-context. <br>
	 * A 'dimension root' is the infimum of the set of elements that precede a dimension. <br> 
	 * A 'dimension' is a sup-reducible element of the poset. <br>
	 * @return a set of posets, each one being a 'sub-context' of this poset.
	 * @throws PropertyPosetException 
	 */
	Set<IPropertyPoset> getSubContexts() throws PropertyPosetException;


}
