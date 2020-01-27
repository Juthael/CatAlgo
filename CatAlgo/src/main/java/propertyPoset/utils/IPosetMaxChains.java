package propertyPoset.utils;

import java.util.List;
import java.util.Set;

import grammarModel.utils.IChains;

/**
 * PosetMaxChains represents the spanning chains of a lower semilattice. It provides navigation functionalities and an access to 
 * the set of implications associated with the semilattice.  
 * 
 * @author Gael Tregouet
 *
 */
public interface IPosetMaxChains extends IChains {

	/**
	 * 
	 * @return a list of 'chains', i.e. a list of lists of String.
	 */
	List<List<String>> getMaximalChains();
	
	/**
	 * 
	 * @return the set of all the elements contained in at least one of the strings.
	 */
	Set<String> getProperties();
	
	/**
	 * 
	 * @return the set of implications associated with the semilattice.
	 */
	Set<IImplication> getImplications();
	
	/**
	 * In order to reinitialize the navigation and generate the implications associated with the semilattice, reset 
	 * IChains specific indexes (pointing the antecedent of an implication) and IPosetMaxChains index 
	 * (pointing the consequent).
	 */
	void resetIndexes();
	
}
