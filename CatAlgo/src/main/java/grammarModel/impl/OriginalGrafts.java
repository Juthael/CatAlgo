package grammarModel.impl;

import java.util.List;

import grammarModel.IOriginalGrafts;
import grammarModel.ISyntacticBranch;

public abstract class OriginalGrafts implements IOriginalGrafts {

	private List<ISyntacticBranch> signalElements;
	
	public OriginalGrafts(List<ISyntacticBranch> signalElements) {
		this.signalElements = signalElements;
	}

	public String getGraftsDescription() {
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
