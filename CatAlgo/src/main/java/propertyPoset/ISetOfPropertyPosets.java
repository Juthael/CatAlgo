package propertyPoset;

import java.util.Set;

import fca.core.context.binary.BinaryContext;

/**
 * An ISetOfPropertyPosets is a set of {@link IPropertyPoset}, with some additional functionalities. It is obtained 
 * by calling the 'getExtractedContexts()' method on a {@link IOriginalPropertyPoset}.
 * @author Gael Tregouet
 *
 */
public interface ISetOfPropertyPosets {
	
	/**
	 * 
	 * @return a set of property posets.
	 */
	Set<IPropertyPoset> getSetOfPropertyPoset();
	
	/**
	 * A 'binary context' is a binary relation over a set of properties such that (x,y) means 'x implies y'.  
	 * @return a set of binary context, each one being associated with (and generated from) a property poset. 
	 */
	Set<BinaryContext> getBinaryContext();
	
	/**
	 * Calls the method 'reducePoset()' of every property poset in the set.
	 * @see IPropertyPoset
	 */
	void reducePropertyPosets();

}
