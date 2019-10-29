package grammarModel;

import exceptions.grammarModelException;

public interface ISyntacticTree extends ISyntacticBranch {
	
	void setPosetID() throws grammarModelException;

}
