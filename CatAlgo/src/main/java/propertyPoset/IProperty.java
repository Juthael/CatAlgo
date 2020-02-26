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
	 * Encapsulated properties of a property P are elements removed from the {@link IPropertyPoset}  during 
	 * the 'poset reduction procedure', because they do not not provide any additional information : any other 
	 * property that implies an encapsulated property of P also implies P. 
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
	 * A dimension is a property that some elements of a context have in common, and which is also 
	 * used to express their differences, since there are many ways to have this property. 
	 * 
	 * A property d is a 'dimension' if : <br> 
	 * 1/ it has more than one predecessor (i.e., is sup-reducible). <br>
	 * 2/ if 'P' is the set of predecessors, 'r' its infimum ; for any property 'v' less than 'd' and 
	 * greater than 'r', there is no property 'p' that verifies (('p' < 'v') && ('p' not comparable 
	 * to 'r')). <br> 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}.
	 * @return true if this property is a dimension according to the relation given in parameter.  
	 * @throws PropertyPosetException 
	 */
	boolean isADimension(IRelation rel) throws PropertyPosetException;
	
	/**
	 * Informative properties are dimensions, dimension roots, and dimension values. <br>
	 * 
	 * A property d is a 'dimension' if :  <br>
	 * 1/ it has more than one predecessor (i.e., is sup-reducible).  <br>
	 * 2/ if 'P' is the set of predecessors, 'r' its infimum ; for any property 'q' less than 'd' and 
	 * greater than 'r', there is no property 'p' that verifies (('p' < 'q') && ('p' not comparable 
	 * to 'r')).  <br>
	 * 
	 * If 'd' is a dimension, then 'r' is its root. Let 'A' be the set of properties succeeding 'r' 
	 * and less than 'd' ; then a property 'v' is a value of 'd' iff there exists a subset 'X' of 'A' 
	 * such that 'v' is the supremum of 'A'. <br> 
	 * @param rel a relation that links this property with other properties of a {@link IPropertyPoset}. 
	 * @return 'true' if this property is informative, 'false' otherwise
	 * @throws PropertyPosetException
	 */
	boolean isInformative(IRelation rel) throws PropertyPosetException;
	
}
