package propertyPoset;

import java.util.Set;

public interface IPropertySet {
	
	Set<IProperty> getSetOfProperties();
	
	IProperty getProperty(String propertyName);
	
	Set<IProperty> getSubsetOfProperties(Set<String> propertyNames);
	
	boolean removeProperty(String propertyName, String antecedent);

}
