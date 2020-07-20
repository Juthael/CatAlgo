package propertyPoset.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import fca.core.context.binary.BinaryContext;
import grammarModel.structure.ISyntaxGrove;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IDimensionAnalysis;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;
import propertyPoset.utils.impl.PosetMaxChains;

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
		try {
			ensurePosetConsistency();
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset.getBinaryContext() : cannot ensure poset consistency." 
					+ System.lineSeparator() + e.getMessage());
		}
		String posetName = relation.getPosetRoot();
		Vector<String> explicitProperties = new Vector<String>();
		List<String> properties = new ArrayList<String>(set.getSetOfPropertyNames());
		for (String property : properties) {
			explicitProperties.add(set.getProperty(property).getPropertyExplicitName());
		}
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
		context = new BinaryContext(posetName, explicitProperties, explicitProperties, values);
		return context;
	}
	
	@Override
	public IPosetMaxChains getChains() throws PropertyPosetException {
		IPosetMaxChains chains;
		try {
			ensurePosetConsistency();
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset.getBinaryContext() : cannot ensure poset consistency." 
					+ System.lineSeparator() + e.getMessage());
		}
		List<List<String>> stringChains = relation.getChains();
		List<List<String>> stringChainsWithExplicitNames = new ArrayList<List<String>>();
		for (List<String> singlechain : stringChains) {
			List<String> singleChainWithExplicitNames = new ArrayList<String>();
			for (String property : singlechain) {
				singleChainWithExplicitNames.add(set.getProperty(property).getPropertyExplicitName());
			}
			stringChainsWithExplicitNames.add(singleChainWithExplicitNames);
		}
		try {
			chains = new PosetMaxChains(stringChainsWithExplicitNames);
		} catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset.getChains() : PosetMaxChains() instantiation failed." 
					+ System.lineSeparator() + e.getMessage());
		}
		return chains;
	}
	
	@Override
	public void ensureDimensionsHaveIndependentValues() throws PropertyPosetException {
		Set<IDimensionAnalysis> dimAnalyzes = relation.getDimensionAnalyzes();
		for (IDimensionAnalysis dimAnalysis : dimAnalyzes) {
			if (dimAnalysis.checkIfPosetModificationRequired() == true) {
				String dimensionName = dimAnalysis.getDimensionName();
				List<String> instancesForThisDim = dimAnalysis.getAllInstancesOfThisDimension();
				Set<String> greaterProps = relation.getGreaterProperties(dimensionName);
				for (int i=0 ; i<instancesForThisDim.size() ; i++) {
					String instanceName = instancesForThisDim.get(i);
					Set<String> instancePredecessors = 
							dimAnalysis.getPredecessorsForThisDimensionInstance(instanceName);
					if (i==0) {
						relation.modifyRelation(instanceName, instancePredecessors);
					}
					else {
						Set<String> instanceConsequents = new HashSet<String>(greaterProps);
						instanceConsequents.add(instanceName);
						set.addNewProperty(
								relation.addNewProperty(instanceName, instancePredecessors, instanceConsequents));
					}
				}
			}
		}
	}
	
	@Override
	public void reducePoset() throws PropertyPosetException {
		if (!dimensionValuesAreIndependent)
			ensureDimensionsHaveIndependentValues();
		boolean aRemovalHasOccured;
		do {
			aRemovalHasOccured = false;
			Set<String> leaves = relation.getPosetleaves();
			Map<String, String> removableToAntecedent = new HashMap<String, String>();
			for (String property : set.getSetOfPropertyNames()) {
				Set<String> greaterProps = relation.getGreaterProperties(property);
				if (!greaterProps.isEmpty() && leaves.containsAll(greaterProps)) {
					Iterator<String> greaterPropsIte = greaterProps.iterator();
					boolean allGreaterPropsHaveOnly1Pred = true;
					while (greaterPropsIte.hasNext() && allGreaterPropsHaveOnly1Pred == true) {
						String greaterProp = greaterPropsIte.next();
						allGreaterPropsHaveOnly1Pred = (relation.getPredecessors(greaterProp).size() == 1);
					}
					if (allGreaterPropsHaveOnly1Pred) {
						greaterPropsIte = greaterProps.iterator();
						while (greaterPropsIte.hasNext()) {
							removableToAntecedent.put(greaterPropsIte.next(), property);
						}
					}
				}
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
	
	private void ensurePosetConsistency() throws PropertyPosetException {
		if (!dimensionValuesAreIndependent) {
			try {
				//HERE
				//ensureDimensionsHaveIndependentValues();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.ensurePosetConsistency() : "
						+ "error while trying to make values independent." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		if (!posetReduced) {
			try {
				//HERE
				// reducePoset();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.ensurePosetConsistency() : error during poset reduction."
						+ System.lineSeparator() + "Cannot proceed if poset hasn't been reduced." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
	}

}
