package representation;

import java.util.Set;

/**
 * <p>
 * A context input provides a description of each object in the context ; from this set of descriptions, a representation 
 * can be built. <br>
 * </p>
 * 
 * @see representation.IRepresentation
 * @see representation.IContextDescriptor
 * @author Gael Tregouet
 *
 */
public interface IContextInput {
	
	/**
	 * 
	 * @return a set of descriptions, each of which applies to an object in the context
	 */
	Set<IDescription> getDescriptionsOfContextObjects();

}
