package grammarModel.genericTools;

import exceptions.GrammarModelException;

public interface IConnectableGrafts extends IGrafts {
	
	boolean attemptConnexion(IGraftsConnector connector) throws GrammarModelException;
	
	boolean areConnected();

}
