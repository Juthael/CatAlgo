package propertyPoset.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import fca.core.context.binary.BinaryContext;
import grammarModel.structure.ISyntaxGrove;
import propertyPoset.IProperty;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;

/**
 * A PropertyPoset is an implementation of a partially ordered set whose elements are properties. <br>
 * 
 * As such, it is composed of a {@link IPropertySet} object (unordered set of properties), and of a 
 * {@link IRelation} object (partial order relation on the set of properties). <br>
 * 
 * @author Gael Tregouet
 *
 */
public class PropertyPoset implements IPropertyPoset {

	private IPropertySet set;
	private IRelation relation;
	private boolean dimensionValuesAreIndependent = false;
	private boolean posetReduced = false;
	
	/**
	 * @param setOfPropNames names of a set of properties
	 * @param relation partial order relation on the set of properties
	 * @throws PropertyPosetException 
	 */
	public PropertyPoset(Set<String> setOfPropNames, IRelation relation) throws PropertyPosetException {
		set = new PropertySet(setOfPropNames, true);
		this.relation = relation;
	}
	
	/**
	 * @param maxChains spanning chains of the poset to be built ; in principle, provided by a 
	 * {@link ISyntaxGrove} object. 
	 * @throws PropertyPosetException
	 */
	public PropertyPoset(IPosetMaxChains maxChains) throws PropertyPosetException {
		set = new PropertySet(maxChains.getProperties(), true);
		relation = new Relation(set);
		try {
			for (IImplication implication : maxChains.getImplications())
				relation.addImplication(implication);
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset() : error while adding an implication");
		}
	}

	@Override
	public IPropertySet getProperties() {
		return set;
	}

	@Override
	public IRelation getRelation() {
		return relation;
	}

	@Override
	public BinaryContext getBinaryContext() throws PropertyPosetException {
		BinaryContext context;
		if (!posetReduced) {
			try {
				reducePoset();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.getBinaryContext() : error during poset reduction."
						+ System.lineSeparator() + "Cannot proceed if poset hasn't been reduced." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		String posetName = relation.getPosetRoot();
		Vector<String> properties = new Vector<String>(set.getSetOfPropertyNames());
		Vector<Vector<String>> values = new Vector<Vector<String>>();
		for (String antecedent : properties) {
			Vector<String> valuesForThisAntcdt = new Vector<String>();
			for (String potentialConsequent : properties) {
				if (relation.getConsequents(antecedent).contains(potentialConsequent))
					valuesForThisAntcdt.add(BinaryContext.TRUE);
				else valuesForThisAntcdt.add(BinaryContext.FALSE);
			}
			values.add(valuesForThisAntcdt);
		}
		context = new BinaryContext(posetName, properties, properties, values);
		return context;
	}
	
	@Override
	public void makeDimensionValuesIndependent() throws PropertyPosetException {
		HashMap<String, String> encapsulatorToEncapsulated;
		try {
			encapsulatorToEncapsulated = relation.setDimensionsAndMakeValuesIndependent();
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset.makeDimensionValuesIndependent() : relation modification "
					+ "has failed." + System.lineSeparator() + e.getMessage());
		}
		for (String encapsulator : encapsulatorToEncapsulated.keySet()) {
			IProperty encapsulated = set.getProperty(encapsulatorToEncapsulated.get(encapsulator));
			set.getProperty(encapsulator).addEncapsulatedProp(encapsulated);
		}
		dimensionValuesAreIndependent = true;
	}
	
	@Override
	public void reducePoset() throws PropertyPosetException {
		if (!dimensionValuesAreIndependent)
			makeDimensionValuesIndependent();
		boolean aRemovalHasOccured;
		do {
			aRemovalHasOccured = false;
			Set<String> leaves = relation.getPosetleaves();
			Map<String, String> removableToAntecedent = new HashMap<String, String>();
			for (String leaf : leaves) {
				Set<String> leafAntcdts = relation.getPredecessors(leaf);
				if (leafAntcdts.size() == 1)
					removableToAntecedent.put(leaf, leafAntcdts.iterator().next());
			}
			if (!removableToAntecedent.isEmpty()) {
				for (String removableLeaf : removableToAntecedent.keySet()) {
					relation.removeProperty(set.removeProperty(removableLeaf, removableToAntecedent.get(removableLeaf)));
				}
				aRemovalHasOccured = true;
			}
		}
		while (aRemovalHasOccured == true);
		posetReduced = true;
	}

}
