package grammarModel.genericTools;

import java.util.Map;

import grammarModel.structure.ISyntacticTree;

public interface ITreeGenerator {
		
	Map<ISyntacticTree, String> getTreeToVerbalDescription(String signal);

}
