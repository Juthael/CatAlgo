package propertyPoset.impl;

import java.util.HashSet;
import java.util.List;
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
	public BinaryContext getBinaryContextWithIndependentDimensionValues() throws PropertyPosetException {
		BinaryContext context;
		if (!dimensionValuesAreIndependent) {
			try {
				ensureDimensionsHaveIndependentValues();
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.getBinaryContextWithIndependentDimensionValues() : "
						+ "error while trying to make values independent." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		context = getBinaryContext();
		return context;
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

}
