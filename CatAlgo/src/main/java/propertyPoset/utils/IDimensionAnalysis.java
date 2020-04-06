package propertyPoset.utils;

import java.util.List;
import java.util.Set;

import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.impl.DimensionAnalysis;

/**
 * A IDimensionAnalysis is used to make sure that any dimension has independent values. <br>
 * A dimension is a sup-reducible element. The values of a dimension are the sets obtained by calculating the 
 * intersections of the set of properties that have this dimension as a successor, with the upper sets of every 
 * atom of the (lower semi-lattice) poset of properties. <br>
 * The values must be independent : the intersection of two values must always be an empty set. If this is not the 
 * cas, the {@link DimensionAnalysis} tells how to divide a unique dimension into many instances of it, with their own 
 * respective independent values.
 * @author Gael Tregouet
 *
 */
public interface IDimensionAnalysis {
	
	String getDimensionName();
	
	boolean checkIfPosetModificationRequired();
	
	List<String> getAllInstancesOfThisDimension();
	
	Set<String> getNewInstancesOfThisDimension();
	
	Set<String> getValuesForThisDimensionInstance(String dimName);
	
	boolean addNewDimensionInstance(String dimName);
	
	boolean addNewPredecessorToSpecifiedDimensionInstance(String dimName, String predecessor) throws PropertyPosetException;
	
	boolean addNewPredecessorsToSpecifiedDimensionInstance(String dimName, Set<String> predecessor) 
			throws PropertyPosetException;

}
