package grammarModel;

import java.util.Set;

import propertyPoset.IImplication;

public interface IPosetMaxChains extends IChains {

	Set<String> getProperties();
	
	Set<IImplication> getImplications();
	
}
