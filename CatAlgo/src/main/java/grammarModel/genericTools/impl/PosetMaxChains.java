package grammarModel.genericTools.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.GrammarModelException;
import grammarModel.genericTools.IPosetMaxChains;
import propertyPoset.IImplication;

public class PosetMaxChains extends Chains implements IPosetMaxChains {
	
	private Set<IImplication> implications;
	
	public PosetMaxChains(List<List<String>> listOfChains, Set<IImplication> implications) throws GrammarModelException {
		super(listOfChains);
		this.implications = implications;
	}

	public Set<String> getProperties() {
		Set<String> properties = new HashSet<String>();
		for (List<String> chain : listOfChains) {
			for (String prop : chain) {
				properties.add(prop);
			}
		}
		return properties;
	}

	public Set<IImplication> getImplications() {
		return implications;
	}

}
