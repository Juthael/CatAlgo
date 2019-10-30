package grammarModel;

import java.util.Map;

public interface ITreeGenerator {
	
	Map<ISyntacticTree, String> getTreeToVerbalDescription(ISeedling seedling);
	
	ISyntacticBranch getBranchFromString(String stringChains);
	
	ISeedling getSignalFromString(String stringChains);

}
