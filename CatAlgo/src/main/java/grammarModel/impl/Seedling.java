package grammarModel.impl;

import java.util.List;

import grammarModel.ISeedling;
import grammarModel.ISyntacticBranch;

public abstract class Seedling implements ISeedling {

	private List<ISyntacticBranch> signalElements;
	
	public Seedling(List<ISyntacticBranch> signalElements) {
		this.signalElements = signalElements;
	}

	public String getSignalDescription() {
		StringBuilder sB = new StringBuilder();
		String newLine = System.lineSeparator();
		for (ISyntacticBranch signalElement : signalElements) {
			List<List<String>> chains = signalElement.getListOfSyntacticStringChains();
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
