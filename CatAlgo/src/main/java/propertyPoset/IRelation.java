package propertyPoset;

import java.util.Set;

import utils.IImplication;

public interface IRelation {

	void addImplication(IImplication implication);
	
	Set<String> getGreaterProperties(String propName);
	
	Set<String> getLesserProperties(String propName);
	
	Set<String> getSuccProperties(String propName);
	
	Set<String> getPrecProperties(String propName);
	
	IRelation getSubrelation(IPropertySet subSet);
	
	boolean checkIfDimension(String propName);
	
	boolean checkIfLocalRoot(String propName);
	
	String getLocalRoot(String dimension);
	
	String getPosetRoot();
	
	Set<String> getPosetleaves();
	
	boolean removeProperty(String propertyName);
	
}
