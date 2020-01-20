package propertyPoset;

import grammarModel.structure.ISyntacticStructure;
import utils.IPosetMaxChains;

/**
 * An IOriginalPropertyPoset is basically the result of the conversion of a syntax tree into a partially ordered set. <br>
 * 
 * To be more specific, it is a property poset ({@link IPropertyPoset}) built from the list of spanning chains in 
 * the diagram of its 'successor' relation ({@link IPosetMaxChains}). Those chains are themselves returned by the 
 * 'getPosetMaxChains()' method of a {@link ISyntacticStructure}.
 * @author Gael Tregouet
 *
 */
public interface IOriginalPropertyPoset extends IPropertyPoset {
	
	/**
	 * Identifies potential 'sub-contexts' in this original poset, which is then divided into multiple 
	 * sub-posets (one per sub-context), returned as a set of property posets (even if no division has occurred). <br> 
	 * 
	 * This is to make sure that all dimensions in every poset have the same 'contextual basis', and that this 
	 * contextual basis is the minimum element of the poset (i.e. its 'root', since a property poset is necessarily 
	 * a lower semi-lattice). <br>
	 * 
	 * A 'dimension' of a property poset is a sup-reducible element. A 'contextual basis' of a dimension is the 
	 * infimum of its immediate predecessors.  
	 * 
	 * 
	 * @return a set of {@link IPropertyPoset}, with a few additional functionalities. 
	 */
	ISetOfPropertyPosets getExtractedContextPosets();

}
