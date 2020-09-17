package representation.inputOutput.impl;

import java.util.Set;

import representation.dataFormats.IDescription;
import representation.inputOutput.IContextInput;

public class ContextInput implements IContextInput {

	private Set<IDescription> objectDescriptions;
	
	public ContextInput(Set<IDescription> objectDescriptions) {
		this.objectDescriptions = objectDescriptions;
	}

	@Override
	public Set<IDescription> getDescriptionsOfContextObjects() {
		return objectDescriptions;
	}

}
