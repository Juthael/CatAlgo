package propertyPoset;

import java.util.Set;

import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IProperty is characterized by its name and by its potential 'encapsulated properties'. <br>
 * 
 * Encapsulated properties of a property P are elements removed from the property poset because they 
 * do not not provide any additional information. 
 * 
 * The reason is that, in the context of this poset, they are implied by P and only P (and P is not 
 * the root of the lower semi-lattice) ; therefore, any distinction they allow among the context elements 
 * can also be made using P.
 * 
 * @see IPropertyPoset
 * 
 * @author Gael Tregouet
 *
 */
public interface IProperty {
	
	/**
	 * Encapsulated properties of a property P are elements removed from the property poset because they 
	 * do not not provide any additional information. 
	 * 
	 * The reason is that, in the context of this poset, they are implied by P and only P (and P is not 
	 * the root of the lower semi-lattice) ; therefore, any distinction they allow among the context elements 
	 * can also be made using P.
	 * @param prop : a 'superfluous' property, removed from the {@link IPropertyPoset} because it is only implied by 
	 * the property on which this method is called .
	 */
	void addEncapsulatedProp(IProperty prop);
	
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
	 * @return names of all the properties that preceeds this one according to the relation given in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getPrecProperties(IRelation rel) throws PropertyPosetException;
	
	
	/**
	 * Encapsulated properties of a property P are elements removed from the property poset because they 
	 * do not not provide any additional information. 
	 * 
	 * The reason is that, in the context of this poset, they are implied by P and only P (and P is not 
	 * the root of the lower semi-lattice) ; therefore, any distinction they allow among the context elements 
	 * can also be made using P.
	 * @return encapsulated properties.
	 */
	Set<IProperty> getEncapsulatedProperties();
	
	/**
	 * The rank of a property is the maximal length of a spanning chain bounded by the root of the (lower semi-lattice) 
	 * poset and this property.  
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}. 
	 * @return the rank of this property. 
	 * @throws PropertyPosetException 
	 */
	int getRank(IRelation rel) throws PropertyPosetException;
	
	/**
	 * The term 'dimension' refer to a sup-reducible element of a property (lower semi-lattice) poset. 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.
	 * @return true if this property is a dimension according to the relation given in parameter.  
	 * @throws PropertyPosetException 
	 */
	boolean isADimension(IRelation rel) throws PropertyPosetException;
	
	/**
	 * The term 'local root' refer to the infimum of the immediate predecessors of (at least) one 
	 * dimension (the 'local root' is also called the 'contextual basis' of this dimension). 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.
	 * @return true if this property is a local root according to the relation given in parameter.  
	 * @throws PropertyPosetException 
	 */
	boolean isALocalRoot(IRelation rel) throws PropertyPosetException;
	
	/**
	 * The term 'local atom' refer to the immediate successors of a local root.
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.
	 * @return true if this property is a local atom according to the relation given in parameter.  
	 * @throws PropertyPosetException 
	 */
	boolean isALocalAtom(IRelation rel) throws PropertyPosetException;
	
}
