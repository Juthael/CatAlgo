package propertyPoset;

import java.util.Set;

import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IProperty is characterized by its name and by its potential 'encapsulated properties'. <br>
 * 
 * @author Gael Tregouet
 *
 */
public interface IProperty {
	
	/**
	 * Encapsulated properties of a property P are elements removed from the {@link IPropertyPoset} during 
	 * the 'poset reduction procedure', because they do not not provide any additional information : any other 
	 * property that implied an encapsulated property of P also implies P. 
	 * 
	 * @param prop : a 'non-informative' property, removed from the {@link IPropertyPoset} because it is only implied by 
	 * the property on which this method is called .
	 */
	void addEncapsulatedProp(IProperty prop);
	
	/**
	 * Some properties must be protected from removal because they could be spotted by a {@link IPropertyPoset} as 
	 * 'non-informative' during the set reduction procedure (IPropertySet.reduce()), although being the root of an 
	 * extracted subset. 
	 */
	void setAsNotRemovable();
	
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
	 * Encapsulated properties of a property P are elements removed from the {@link IPropertyPoset}  during 
	 * the 'poset reduction procedure', because they do not not provide any additional information : any other 
	 * property that implies an encapsulated property of P also implied P. 
	 * 
	 * @return encapsulated properties.
	 */
	Set<IProperty> getEncapsulatedProperties();
	
	/**
	 * The rank of a property is the maximal length of a spanning chain bounded by the root of the (lower 
	 * semi-lattice) poset and this property.  
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
	 * The term 'dimension root' refer to the infimum of the immediate predecessors of (at least) one 
	 * dimension (the 'dimension root' is also called the 'contextual basis' of this dimension). 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.
	 * @return true if this property is a dimension root according to the relation given in parameter.  
	 * @throws PropertyPosetException 
	 */
	boolean isADimensionRoot(IRelation rel) throws PropertyPosetException;
	
	/**
	 * The term 'dimension atom' refer to the immediate successors of a dimension root.
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.
	 * @return true if this property is a dimension atom according to the relation given in parameter.  
	 * @throws PropertyPosetException 
	 */
	boolean isADimensionAtom(IRelation rel) throws PropertyPosetException;
	
	/** 
	 * Some properties must be protected from removal because they could be identified by a {@link IPropertyPoset} as 
	 * 'non-informative' during the set reduction procedure (IPropertySet.reduce()), although being the root of an 
	 * extracted subset. 
	 * @return 'true' if removable, false otherwise
	 */
	boolean isRemovable();
	
}
