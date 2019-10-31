package grammarModel.genericTools.impl;

import java.util.List;

import grammarModel.genericTools.IGrafts;
import grammarModel.structure.ISyntacticBranch;

public class Grafts implements IGrafts {

	protected List<ISyntacticBranch> branches;
	
	public Grafts(List<ISyntacticBranch> branches) {
		this.branches = branches;
	}

	public String getGraftsDescription() {
		StringBuilder sB = new StringBuilder();
		String newLine = System.lineSeparator();
		for (ISyntacticBranch branch : branches) {
			List<List<String>> chains = branch.getListOfSyntacticStringChains();
			for (List<String> chain : chains) {
				for (int i=0 ; i <= chain.size()-1 ; i++) {
					sB.append(chain.get(i));
					if (i < chain.size()-1)
						sB.append("/");
					else sB.append(newLine);
				}
			}
			sB.append(newLine);
		}
		return sB.toString();
	}

}
