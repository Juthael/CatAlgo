package propertyPoset;

import java.util.Set;

import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IProperty has a name, can be part of a set on which an order relation can be defined.
 * 
 * @author Gael Tregouet
 *
 */
public interface IProperty {
	
	/**
	 * 
	 * @return the property name.
	 */
	String getPropertyName();
	
	/**
	 * 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.  
	 * @return names of all the properties implying this one according to the relation given in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getAntecedents(IRelation rel) throws PropertyPosetException;
	
	/**
	 * 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.  
	 * @return names of all the properties implied by this one according to the relation given in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getConsequents(IRelation rel) throws PropertyPosetException;
	
	/**
	 * 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.  
	 * @return names of all the properties greater than this one according to the relation given in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getGreaterProperties(IRelation rel) throws PropertyPosetException;
	
	/**
	 * 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.  
	 * @return names of all the properties lesser than this one according to the relation given in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getLesserProperties(IRelation rel) throws PropertyPosetException;
	
	/**
	 * 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.  
	 * @return names of all the properties that succeeds this one according to the relation given in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getSuccProperties(IRelation rel) throws PropertyPosetException;
	
	/**
	 * 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.  
	 * @return names of all the properties that precedes this one according to the relation given in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getPrecProperties(IRelation rel) throws PropertyPosetException;
	
	/**
	 * The rank of a property is the maximal length of a spanning chain bounded by the root of the (lower 
	 * semi-lattice) poset and this property.  
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}. 
	 * @return the rank of this property. 
	 * @throws PropertyPosetException 
	 */
	int getRank(IRelation rel) throws PropertyPosetException;
	
	/**
	 * A dimension is a sup-reducible element of a poset (provided that it has independent values, which is 
	 * ensured by a dedicated method of {@link IPropertyPoset}.
	 * 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.
	 * @return true if this property is a dimension according to the relation given in parameter.  
	 * @throws PropertyPosetException 
	 */
	boolean isADimension(IRelation rel) throws PropertyPosetException;
	
}
