package representation.inputOutput;

import java.util.Set;

import representation.dataFormats.IDescription;

/**
 * <p>
 * A context input provides a description of each object in the context ; out of this set of descriptions, a representation 
 * can be built. <br>
 * </p>
 * 
 * @see representation.stateMachine.IRepresentation
 * @see representation.inputOutput.IContextDescriptor
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
