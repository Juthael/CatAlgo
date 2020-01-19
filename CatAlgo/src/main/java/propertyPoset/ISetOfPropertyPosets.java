package propertyPoset;

import java.util.Set;

import fca.core.context.binary.BinaryContext;

public interface ISetOfPropertyPosets {
	
	Set<IPropertyPoset> getSetOfPropertyPoset();
	
	Set<BinaryContext> getBinaryContext();
	
	void reducePropertyPosets();

}
