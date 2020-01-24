package propertyPoset.impl;

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
		String posetName = relation.getPosetRoot();
		Vector<String> properties = new Vector<String>(set.getSetOfPropertyNames());
		Vector<Vector<String>> values = initializeValues(properties);
		return null;
	}

	@Override
	public boolean hasBeenreduced() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reducePoset() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Vector<Vector<String>> initializeValues(Vector<String> properties){
		Vector<Vector<String>> values = new Vector<Vector<String>>();
		for (int i=0 ; i < properties.size() ; i++) {
			Vector<String> valueRow = new Vector<String>();
			for (int j=0 ; j < properties.size() ; j++) {
				valueRow.add("");
			}
			values.add(valueRow);
		}
		return values;
	}

}
