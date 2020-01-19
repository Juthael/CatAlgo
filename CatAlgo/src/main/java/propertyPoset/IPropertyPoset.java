package propertyPoset;

import fca.core.context.binary.BinaryContext;

public interface IPropertyPoset {
	
	IPropertySet getProperties();
	
	IRelation getRelation();
	
	BinaryContext getBinaryContext();
	
	boolean hasBeenreduced();
	
	boolean reducePoset();

}
