package grammarModel;

import exceptions.GrammarModelException;

public interface ISyntacticTree extends ISyntacticBranch {
	
	void setPosetID() throws GrammarModelException;

}
