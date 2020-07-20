package representation.stateMachine;

/**
 * 
 * A IDimension is a chain of attributes. One of theses attributes is defined as a <i>free predecessor</i>, and it may also 
 * contain a <i> free successor </i> element. A {@link IValue} instance can be associated with a IDimension instance in 
 * a {@link IValueAttribution} if the minimum of the {@link IValue} is the free predecessor of the IDimension and, in the case 
 * where there is a free successor, if it belongs to the maximal elements of the {@link IValue}. 
 * @author Gael Tregouet
 *
 */
public interface IDimension {
	
	/**
	 * A {@link IValue} instance is a value of this Dimension if the minimum of the {@link IValue} is the free predecessor of 
	 * the IDimension and, in the case where there is a free successor, if it belongs to the maximal elements of the 
	 * {@link IValue}. 
	 * @param value - the value to be tested
	 * @return true if the specified value is a value of this dimension, false otherwise
	 */
	boolean isAValueOfThisDimension(IValue value);

}
