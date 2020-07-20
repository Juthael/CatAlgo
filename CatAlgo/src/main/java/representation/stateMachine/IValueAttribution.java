package representation.stateMachine;

/**
 * A IValueAttribution is composed of a {@link IDimension} and of a {@link IValue} accepted by the {@link IDimension}.
 * @author Gael Tregouet
 *
 */
public interface IValueAttribution {
	
	/**
	 * 
	 * @return the dimension to which a {@link IValue} is attributed
	 */
	IDimension getDimension();
	
	/**
	 * 
	 * @return the value attributed to a {@link IDimension}
	 */
	IValue getValue();
	
	/**
	 * 
	 * @return the value attribution as a String
	 */
	String toString();

}
