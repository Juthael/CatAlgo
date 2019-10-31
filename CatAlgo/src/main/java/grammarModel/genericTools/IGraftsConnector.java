package grammarModel.genericTools;

import java.util.List;

import exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticBranch;

public interface IGraftsConnector {
	
	boolean connexionComplete(List<ISyntacticBranch> branches) ;
	
	List<ISyntacticBranch> getConnectedBranches() throws GrammarModelException;

}
