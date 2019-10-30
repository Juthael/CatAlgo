package grammarModel.impl;

import java.util.List;

import grammarModel.IConnectedGrafts;
import grammarModel.ISyntacticBranch;

public class ConnectedGrafts extends Grafts implements IConnectedGrafts {

	private int age;
	private int nBOfGrafts;
	
	public ConnectedGrafts(List<ISyntacticBranch> branches, int age) {
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

}
