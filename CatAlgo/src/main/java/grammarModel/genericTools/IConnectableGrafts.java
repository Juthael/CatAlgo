package grammarModel.genericTools;

import exceptions.GrammarModelException;

public interface IConnectableGrafts extends IGrafts {
	
	int getGraftsAge();
	
	int getNbOfGrafts();
	
	boolean attemptConnexion(IGraftsConnector connector) throws GrammarModelException;
	
	boolean areConnected();

}
