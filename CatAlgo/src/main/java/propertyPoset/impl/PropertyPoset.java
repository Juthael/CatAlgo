package propertyPoset.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import fca.core.context.binary.BinaryContext;
import propertyPoset.IProperty;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;

/**
 * A IPropertyPoset is an implementation of a partially ordered set whose elements are properties. <br>
 * 
 * As such, it is composed of a {@link IPropertySet} object (unordered set of properties), and of a 
 * {@link IRelation} object (partial order relation on the set of properties). 
 * @author Gael Tregouet
 *
 */
public class PropertyPoset implements IPropertyPoset {

	IPropertySet set;
	IRelation relation;
	boolean reduced = false;
	
	/**
	 * 
	 * @param setOfPropNames names of a set of properties
	 * @param relation partial order relation on the set of properties
	 */
	public PropertyPoset(Set<String> setOfPropNames, IRelation relation) {
		set = new PropertySet(setOfPropNames, true);
		this.relation = relation;
	}

	@Override
	public Set<IProperty> getProperties() {
		return set.getSetOfProperties();
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
			Vector<String> valuesForThisAnt = new Vector<String>();
			for (String potentialConsequent : properties) {
				if (relation.getConsequents(antecedent).contains(potentialConsequent))
					valuesForThisAnt.add(BinaryContext.TRUE);
				else valuesForThisAnt.add(BinaryContext.FALSE);
			}
			values.add(valuesForThisAnt);
		}
		context = new BinaryContext(posetName, properties, properties, values);
		return context;
	}

	@Override
	public boolean hasBeenreduced() {
		return reduced;
	}

	@Override
	public boolean reducePoset() throws PropertyPosetException {
		Set<String> propsToRemove = new HashSet<String>();
		List<String> listOfPropsToRemove;
		for (IProperty property : set.getSetOfProperties()) {
			try {
				if (!property.isADimension(relation) && !property.isALocalRoot(relation) && !property.isALocalAtom(relation))
					propsToRemove.add(property.getPropertyName());
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.reducePoset() : property " + property.getPropertyName() 
					+ "cannot be tested." + System.lineSeparator() + e.getMessage());
			}
		}
		try {
			listOfPropsToRemove = orderPropsByDecreasingRank(propsToRemove);
		}
		catch (Exception e) {
			throw new PropertyPosetException("PropertyPoset.reducePoset() : properties to be removed "
					+ "cannot be ordered." + System.lineSeparator() + e.getMessage());
		}
		for (String property : listOfPropsToRemove) {
			List<String> predecessors = new ArrayList<String>(relation.getPredecessors(property));
			String predecessor;
			if (predecessors.size() != 1) {
				throw new PropertyPosetException("PropertyPoset.reducePoset() error : if property '" + property
						+ "' is removable, then it cannot have " + Integer.toString(predecessors.size()) + " predecessors.");
			}
			else predecessor = predecessors.get(0);
			try {
				relation.removeProperty(property);
				set.removeProperty(property, predecessor);
			}
			catch (Exception e) {
				throw new PropertyPosetException("PropertyPoset.reducePoset() : removal of property '" + property 
						+ "' failed.");
			}
		}
		relation.updateRelationData();
		return true;
	}
	
	/**
	 * @param props set of properties to be ordered
	 * @return list of properties ordered by decreasing rank
	 * @throws PropertyPosetException 
	 */
	private List<String> orderPropsByDecreasingRank(Set<String> props) throws PropertyPosetException{
		Set<String> unorderedProps = new HashSet<String>(props);
		List<String> orderedProps = new ArrayList<String>();
		int maxRank = getMaxRank(props);
		for (int i=maxRank ; i >= 0 ; i--) {
			if (!unorderedProps.isEmpty()) {
				Set<String> foundProps = new HashSet<String>();
				for (String prop : unorderedProps) {
					if (relation.getRank(prop) == i) {
						foundProps.add(prop);
					}
				}
				orderedProps.addAll(foundProps);
				unorderedProps.removeAll(foundProps);	
			}
		}
		return orderedProps;
	}
	
	/**
	 * @return the maximal length of a spanning path from the poset root to one of the properties given in parameter. 
	 * @throws PropertyPosetException 
	 */
	private int getMaxRank(Set<String> propNames) throws PropertyPosetException {
		int maxRank = 0;
		for (String prop : propNames) {
			int thisPropRank = relation.getRank(prop); 
			if (thisPropRank > maxRank)
				maxRank = thisPropRank;
		}
		return maxRank;
	}	

}
