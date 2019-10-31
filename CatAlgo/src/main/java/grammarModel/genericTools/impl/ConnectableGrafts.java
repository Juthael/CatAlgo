package grammarModel.genericTools.impl;

import java.util.List;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IConnectableGrafts;
import grammarModel.genericTools.IGraftsConnector;
import grammarModel.structure.ISyntacticBranch;

public class ConnectableGrafts extends Grafts implements IConnectableGrafts {

	private int age;
	private int nBOfGrafts;
	private boolean connected;
	
	public ConnectableGrafts(List<ISyntacticBranch> branches, int age) {
		super(branches);
		this.age = age;
		nBOfGrafts = branches.size();
	}

	public int getGraftsAge() {
		return age;
	}

	public int getNbOfGrafts() {
		return nBOfGrafts;
	}
	
	public boolean areConnected() {
		return connected;
	}

	public boolean attemptConnexion(IGraftsConnector connector) throws GrammarModelException {
		if (connected != true) {
			if (connector.connexionComplete(super.branches)) {
				super.branches = connector.getConnectedBranches();
				connected = true;
			}
			return connected;
		}
		else throw new GrammarModelException("ConnectableGrafts.attemptConnexion() : branches are already connected");
	}

}
