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
	 * It can be used as an argument for the construction of a lattice with {@link LatticeMiner}. Before generating the binary 
	 * context, this method ensures that all dimensions have independent values (ensureDimensionsHaveIndependentValues()), 
	 * and that the poset has been reduced (reducePoset()).  
	 * @return a binary context
	 * @throws PropertyPosetException 
	 */
	BinaryContext getBinaryContext() throws PropertyPosetException;
	
	/**
	 * This method guarantees that values of a dimension are independent, i.e. their intersection is empty. <br>
	 * A dimension is a sup-reducible element of a poset for wich a set of independent values can be found. A value 
	 * 'v' of a dimension is defined as follows : <br> 
	 * Let 'V' be the set of predecessors of a dimension. A value 'v' can be : <br>
	 * 1/ a subset of 'V', such that it is the intersection of V with the set of consequents of (at least) one atom 'a'. <br> 
	 * 2/ the infimum of such a subset. <br>
	 * (and both 1/ and 2/, if the subset has a only one element : then this element is its own infimum) <br>
	 * @throws PropertyPosetException
	 */
	void ensureDimensionsHaveIndependentValues() throws PropertyPosetException;
	

	/**
	 * This method guarantees that the poset is displayed in a reduced form, i.e. rid of its 'non-informative' elements. <br>
	 * 
	 * WARNING : to avoid inconsistent results, this method should only be called after the method 
	 * ensureDimensionsHaveIndependentValues() has proceeded. <br>
	 * 
	 * Non-informative properties are sup-irreducible leaves of the poset (i.e. leaves with only one predecessor, 
	 * since the poset is a lower semi-lattice). Those properties usually remain unnoticed or indistinguishable from 
	 * their antecedent for human subjects, since they do not allow any additional distinction amongst the elements 
	 * of a context. (In a collection of cars, for the 'redness' of a particular car to be salient, it takes the 
	 * 'un-redness' (e.g., blueness) of other elements). <br>
	 * 
	 * This procedure operates recursively on the poset, as long as new sup-irreducible leaves are found.
	 * 
	 * @throws PropertyPosetException 
	 */
	void reducePoset() throws PropertyPosetException ;	

}
