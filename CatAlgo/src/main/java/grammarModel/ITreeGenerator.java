package grammarModel;

import java.util.Map;

public interface ITreeGenerator {
		
	Map<ISyntacticTree, String> getTreeToVerbalDescription(IGrafts grafts);

}
