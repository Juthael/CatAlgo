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
	 * property that implies an encapsulated property of P also implies P. 
	 * 
	 * @param prop : a 'non-informative' property, removed from the {@link IPropertyPoset} because it is only implied by 
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
	 * @return names of all the properties that precedes this one according to the relation given in parameter. 
	 * @throws PropertyPosetException 
	 */
	Set<String> getPrecProperties(IRelation rel) throws PropertyPosetException;
	
	
	/**
	 * Encapsulated properties of a property P are elements removed from the {@link IPropertyPoset} because 
	 * they do not not provide any additional information : any other property that implies an encapsulated 
	 * property of P also implies P. 
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
	 * A dimension is a sup-reducible element of a poset (provided that it has independent values, which is 
	 * ensured by a dedicated method of {@link IPropertyPoset}.
	 * 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.
	 * @return true if this property is a dimension according to the relation given in parameter.  
	 * @throws PropertyPosetException 
	 */
	boolean isADimension(IRelation rel) throws PropertyPosetException;
	
}
