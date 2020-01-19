package propertyPoset;

import java.util.Set;

public interface IProperty {
	
	void addEncapsulatedProp(String propName);
	
	String getPropertyName();
	
	Set<String> getGreaterProperties(IRelation rel);
	
	Set<String> getLesserProperties(IRelation rel);
	
	Set<String> getSuccProperties(IRelation rel);
	
	Set<String> getPrecProperties(IRelation rel);
	
	Set<IProperty> getEncapsulatedProperties();
	
	int getRank(IRelation rel);
	
	boolean isADimension(IRelation rel);
	
	boolean isALocalRoot(IRelation rel);
	
	boolean isALocalAtom(IRelation rel);
	
}
