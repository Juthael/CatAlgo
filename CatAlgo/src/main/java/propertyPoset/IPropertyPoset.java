package propertyPoset;

import java.util.Set;

import fca.LatticeMiner;
import fca.core.context.binary.BinaryContext;
import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IPropertyPoset is a partially ordered set whose elements are properties. <br>
 * 
 * As such, it is composed of a {@link IPropertySet} object (unordered set of properties), and of a 
 * {@link IRelation} object (partial order relation on the set of properties). 
 * @author Gael Tregouet
 *
 */
public interface IPropertyPoset {
	
	/**
	 * 
	 * @return the set of properties.
	 */
	Set<IProperty> getProperties();
	
	/**
	 * 
	 * @return the relation on the set of properties.
	 */
	IRelation getRelation();
	
	/**
	 * The 'binary context' returned here is actually a binary relation over the set of properties such that 
	 * (x,y) means 'x implies y'.  
	 * @return a binary context that can be used as an argument for the construction of a lattice with {@link LatticeMiner}. 
	 * @throws PropertyPosetException 
	 */
	BinaryContext getBinaryContext() throws PropertyPosetException;
	
	/**
	 * 
	 * @return true if the set reduction has occurred, i.e. if see reducePoset() has been called.
	 */
	boolean hasBeenreduced();
	
	/**
	 * A property poset is reduced by removing the contextually superfluous properties. 
	 * 
	 * These are properties that are directly implied, in the context, by one and only one other property (if this 
	 * other property is not the root of the lower semi-lattice) : <br> 
	 * For example, a property B implied by a property A is superfluous if, when an element of the context has B, then 
	 * one knows for sure that it also has A (in this case, people don't even notice B most of the time). Since the 
	 * set of elements having the property B is the same than the set of elements having the property A, B provides no 
	 * additional information and can therefore be removed. It will however be kept as an 'encapsulated property' 
	 * of A in the dedicated field of the class {@link IProperty}. <br>
	 * 
	 * This method operates by removing any property P that does not fulfill one of the following conditions : <br>
	 * 1/ P is sup-reducible (and is then called a 'dimension'). <br>
	 * 2/ P is the infimum of the immediate predecessors of (at least) one dimension (P is then called the 
	 * 'contextual basis' of this dimension). <br>
	 * 3/ P is an immediate successor of a contextual basis. <br>
	 * 
	 * This method should only be called on a component of the {@link ISetOfPropertyPosets} instance 
	 * returned by the 'getExtractedContextPosets()' method of {@link IOriginalPropertyPoset}. In this case, the
	 * only element fulfilling the second condition is the minimum element of the poset.   
	 *  
	 * @return true if the poset cardinal has changed, false otherwise.
	 * @throws PropertyPosetException 
	 */
	boolean reducePoset() throws PropertyPosetException;

}