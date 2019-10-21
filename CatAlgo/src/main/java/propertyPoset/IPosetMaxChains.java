package propertyPoset;

import java.util.List;
import java.util.Set;

import grammarModel.IChains;

public interface IPosetMaxChains extends IChains {
	
	List<List<String>> getMaximalChains();
	
	Set<String> getProperties();
	
	boolean hasNextImplication();
	
	IImplication getNextImplication();	
	
	void resetIndexes();

}
