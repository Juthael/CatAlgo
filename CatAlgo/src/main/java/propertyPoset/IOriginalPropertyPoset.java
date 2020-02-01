package propertyPoset;

import grammarModel.structure.ISyntacticStructure;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IPosetMaxChains;

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
	

	ISetOfPropertyPosets getExtractedContextPosets() throws PropertyPosetException;

}
