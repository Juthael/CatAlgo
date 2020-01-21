package propertyPoset;

import grammarModel.structure.ISyntacticStructure;
import utils.IPosetMaxChains;

/**
 * An IOriginalPropertyPoset is basically the result of the conversion of a syntax tree into a partially ordered 
 * set. <br>
 * 
 * More specifically, it is a property poset ({@link IPropertyPoset}) built from the list of spanning chains in 
 * the diagram of its 'successor' relation ({@link IPosetMaxChains}). These chains are themselves returned by the 
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
	 * contextual basis is the minimum element of the poset (which is necessarily a lower semi-lattice). <br>
	 * 
	 * A 'dimension' of a property poset is a sup-reducible element of this poset. The 'contextual basis' (or 
	 * 'local root') of a dimension is the infimum of its immediate predecessors.  
	 * 
	 * 
	 * @return a set of {@link IPropertyPoset}, with a few additional functionalities. 
	 */
	ISetOfPropertyPosets getExtractedContextPosets();

}
