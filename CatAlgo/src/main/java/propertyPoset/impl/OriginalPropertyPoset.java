package propertyPoset.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import grammarModel.structure.ISyntacticStructure;
import propertyPoset.IOriginalPropertyPoset;
import propertyPoset.IProperty;
import propertyPoset.IPropertyPoset;
import propertyPoset.IRelation;
import propertyPoset.ISetOfPropertyPosets;
import propertyPoset.exceptions.PropertyPosetException;
import utils.IPosetMaxChains;

/**
 * An OriginalPropertyPoset is the result of the conversion of a syntax tree into a partially ordered 
 * set. <br>
 * 
 * More specifically, it is a property poset ({@link IPropertyPoset}) built from the list of spanning chains in 
 * the diagram of its 'successor' relation ({@link IPosetMaxChains}). These chains are themselves returned by the 
 * 'getPosetMaxChains()' method of a {@link ISyntacticStructure}.
 * @author Gael Tregouet
 *
 */
public class OriginalPropertyPoset extends PropertyPoset implements IOriginalPropertyPoset {

	private List<String> orderedRootNames;
	private Map<String, Set<String>> propToRoots = new HashMap<String, Set<String>>();
	
	/**
	 * 
	 * @param setOfPropNames names of a set of properties
	 * @param relation partial order relation on the set of properties
	 * @throws PropertyPosetException 
	 */
	public OriginalPropertyPoset(Set<String> setOfPropNames, IRelation relation) throws PropertyPosetException {
		super(setOfPropNames, relation);
		try {
			orderedRootNames = getRootNamesOrderedByDecreasingRank();
		}
		catch (Exception e) {
			throw new PropertyPosetException("OriginalPropertyPoset() : "
					+ "poset roots retreiving failed." + System.lineSeparator() + e.getMessage()); 
		}
		try {
			propToRoots = setPropToRootsMap(new HashSet<String>(orderedRootNames));
		}
		catch (Exception e) {
			throw new PropertyPosetException("OriginalPropertyPoset() : "
					+ "propToRoots map cannot be initialized. " + System.lineSeparator() + e.getMessage());
		}		
	}

	@Override
	public ISetOfPropertyPosets getExtractedContextPosets() throws PropertyPosetException {
		ISetOfPropertyPosets setOfPosets;
		Set<IPropertyPoset> posets = new HashSet<IPropertyPoset>();
		for (String rootName : orderedRootNames) {
			IPropertyPoset currentRootPoset;
			Set<String> rootBoundPropertyNames = new HashSet<String>();
			for (String prop : propToRoots.keySet()) {
				if (propToRoots.get(prop).contains(rootName)) {
					rootBoundPropertyNames.add(prop);
				}
			}
			IRelation rootBoundRelation = new Relation(relation, rootBoundPropertyNames);
			currentRootPoset = new PropertyPoset(rootBoundPropertyNames, rootBoundRelation);
			posets.add(currentRootPoset);
			updateOriginalPosetData(rootName, rootBoundPropertyNames);
		}
		setOfPosets = new SetOfPropertyPosets(posets);
		return setOfPosets;
	}
	
	private List<String> getRootNamesOrderedByDecreasingRank() throws PropertyPosetException {
		List<String> orderedRootNames = new ArrayList<String>();
		Set<IProperty> roots = new HashSet<IProperty>();
		try {
			for (IProperty property : set.getSetOfProperties()) {
				if (property.isALocalRoot(relation))
					roots.add(property);
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("OriginalPropertyPoset.getRootsOrderedByDecreasingRank() : "
					+ "cannot retreive roots." + System.lineSeparator() + e.getMessage());
		}
		int currentRank = getMaxRank(roots);
		while (!roots.isEmpty() && currentRank >= 0) {
			Set<IProperty> rootsAtCurrentRank = new HashSet<IProperty>();
			for (IProperty root : roots) {
				if (root.getRank(relation) == currentRank) {
					rootsAtCurrentRank.add(root);
				}
			}
			for (IProperty root : rootsAtCurrentRank) {
				orderedRootNames.add(root.getPropertyName());
			}
			roots.removeAll(rootsAtCurrentRank);
			currentRank--;
		}
		return orderedRootNames;
	}
	
	private int getMaxRank(Set<IProperty> properties) throws PropertyPosetException {
		int maxRank = 0;
		try {
			for (IProperty property : properties) {
				int propertyRank = property.getRank(relation);
				if (maxRank < propertyRank)
					maxRank = propertyRank;
			}
		}
		catch (Exception e) {
			throw new PropertyPosetException("OriginalPropertyPoset.getMaxRank() : error while attempting "
					+ "to access property ranks. " + System.lineSeparator() + e.getMessage());
		}
		return maxRank;
	}
	
	private Map<String, Set<String>> setPropToRootsMap(Set<String> roots) throws PropertyPosetException{
		for (IProperty prop : set.getSetOfProperties()) {
			Set<String> associatedRootNames;
			try {
				Set<String> lesserRootNames = prop.getLesserProperties(relation);
				lesserRootNames.retainAll(roots);
				IRelation assocRootsRel = new Relation(relation, lesserRootNames);	
				associatedRootNames = assocRootsRel.getPosetleaves();
				if (roots.contains(prop.getPropertyName()))
					associatedRootNames.add(prop.getPropertyName());
			}
			catch (Exception e) {
				throw new PropertyPosetException("OriginalPropertySet.setPropToRootsMap() : an error has occured "
						+ "while processing property '" + prop.getPropertyName() + "'" + System.lineSeparator() 
						+ e.getMessage());
			}
			propToRoots.put(prop.getPropertyName(), associatedRootNames);
		}
		return propToRoots;
	}
	
	private void updateOriginalPosetData(String root, Set<String> rootBoundPropertyNames) throws PropertyPosetException {
		for (String propName : rootBoundPropertyNames) {
			try {
				propToRoots.get(propName).remove(root);
				if (propToRoots.get(propName).isEmpty()) {
					set.removeProperty(propName);
					relation.removeProperty(propName);
				}
			}
			catch (Exception e) {
				throw new PropertyPosetException("OriginalPropertyPoset.updateData() : error while processing "
						+ "on root '" + root + "' and property '" + propName + "'." + System.lineSeparator() 
						+ e.getMessage()); 
			}
		}
	}

}
