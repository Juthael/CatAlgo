package grammarModel.genericTools.impl;

import java.util.List;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IConnectableGrafts;
import grammarModel.genericTools.IGraftsConnector;
import grammarModel.structure.ISyntacticBranch;

public class ConnectableGrafts extends Grafts implements IConnectableGrafts {

	private boolean connected = false;
	private boolean readyToMakeATree = false;
	
	public ConnectableGrafts(List<ISyntacticBranch> branches) {
		super(branches);
	}
	
	public boolean areConnected() {
		return connected;
	}
	
	public boolean areReadyToMakeATree() {
		return readyToMakeATree;
	}

	public boolean attemptConnexion(IGraftsConnector connector) throws GrammarModelException {
		if (connected == false) {
			if (connector.connexionComplete(super.branches)) {
				super.branches = connector.getConnectedBranches();
				connected = true;
				readyToMakeATree = connector.graftsAreReadyToMakeATree();
			}
			return connected;
		}
		else throw new GrammarModelException("ConnectableGrafts.attemptConnexion() : branches are already connected");
	}

}
