package grammarModel.genericTools.impl;

import java.util.List;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IGraftsConnector;
import grammarModel.structure.ISyntacticBranch;

public abstract class GraftsConnector implements IGraftsConnector {

	private List<ISyntacticBranch> branches;
	private boolean connexionComplete = false;
	
	public GraftsConnector() {
	}

	public abstract boolean connexionComplete(List<ISyntacticBranch> branches);

	public List<ISyntacticBranch> getConnectedBranches() throws GrammarModelException {
		if (connexionComplete == true) {
			return branches;
		}
		else throw new GrammarModelException("GraftsConnector.getConnectedBranches() : branches aren't connected");
	}

}
