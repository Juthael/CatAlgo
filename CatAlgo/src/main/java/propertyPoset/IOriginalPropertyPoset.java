package propertyPoset;

import grammarModel.structure.ISyntacticStructure;
import propertyPoset.exceptions.PropertyPosetException;
import utils.IPosetMaxChains;

/**
 * An IOriginalPropertyPoset results from the conversion of a syntax tree into a partially ordered 
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
	 * sub-posets (one per sub-context) and returned as a set of property posets (even if no division has occurred). <br> 
	 * 
	 * This is to make sure that all dimensions in any poset have the same 'contextual basis', and that this 
	 * contextual basis is the minimum element of the (lower semi-lattice) poset. <br>
	 * 
	 * A 'dimension' of a property poset is a sup-reducible element. The 'contextual basis' (or 'local root') of 
	 * a dimension is the infimum of its immediate predecessors.  
	 * 
	 * @return a set of {@link IPropertyPoset}, with a few additional functionalities. 
	 * @throws PropertyPosetException 
	 */
	ISetOfPropertyPosets getExtractedContextPosets() throws PropertyPosetException;

}
