package propertyPoset.impl;

import java.util.HashSet;
import java.util.Set;

import fca.core.context.binary.BinaryContext;
import propertyPoset.IPropertyPoset;
import propertyPoset.ISetOfPropertyPosets;
import propertyPoset.exceptions.PropertyPosetException;

public class SetOfPropertyPosets implements ISetOfPropertyPosets {

	Set<IPropertyPoset> posets;
	boolean reduced;
	
	public SetOfPropertyPosets(Set<IPropertyPoset> posets) {
		this.posets = posets;
		reduced = false;
	}

	@Override
	public Set<IPropertyPoset> getSetOfPropertyPosets() {
		return posets;
	}

	@Override
	public Set<BinaryContext> getBinaryContexts() throws PropertyPosetException {
		Set<BinaryContext> contexts = new HashSet<BinaryContext>();
		for (IPropertyPoset poset : posets)
			try {
				contexts.add(poset.getBinaryContext());
			}
			catch (Exception e) {
				throw new PropertyPosetException("SetOfPropertyPosets.getBinaryContexts : cannot get a binary "
						+ "context from the poset whose root's name is '" + poset.getRelation().getPosetRoot() 
						+ "'." + System.lineSeparator() + e.getMessage());
			}
		return contexts;
	}

	@Override
	public void reducePropertyPosets() throws PropertyPosetException {
		for (IPropertyPoset poset : posets) {
			try {
				poset.reducePoset();
			}
			catch (Exception e) {
				throw new PropertyPosetException("SetOfPropertyPosets.reducePosets() : reduction failed "
						+ "for the poset whose root's name is '" + poset.getRelation().getPosetRoot() 
						+ "'." + System.lineSeparator() + e.getMessage());
			}
		}
	}

}
