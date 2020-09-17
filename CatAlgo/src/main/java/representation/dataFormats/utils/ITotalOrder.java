package representation.dataFormats.utils;

import java.util.List;
import java.util.Set;

import representation.dataFormats.IPair;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

/**
 * <p>
 * A <b> total order </b> is a binary relation which is antisymmetric, transitive and connex. <br>
 * </p>
 * 
 * <p>
 * The set paired with a total ordered forms a chain of symbols. If these symbols are attributes, 
 * this chain is an expression that denotes a property. //HERE
 * @author Gael Tregouet
 *
 */
public interface ITotalOrder extends Cloneable {

	//getters
	
	/**
	 * Returns a deep copy of this object. 
	 * @return a deep copy of this object. 
	 */
	ITotalOrder clone();
	
	@Override
	boolean equals(Object o);
	
	/**
	 * <p>
	 * Returns the set of pairs that constitutes this totally ordered binary relation. <br>
	 * </p> 
	 * @return the set of pairs of this order
	 */
	Set<IPair> getPairs();
	
	/**
	 * <p>
	 * Returns the chain of symbols given by the successor relation of this total order. This chain forms 
	 * an expression that denotes a <i> property </i>. 
	 * </p> 
	 * @return the property associated with this total order
	 */
	List<ISymbol> getProperty();
	
	/**
	 * <p>
	 * Returns the set of sub-orders of this order. <br>
	 * A sub-order is a totally ordered binary relation that is a sub-relation of this order (and not necessarily a 
	 * <i> proper </i> sub-relation ; so the returned set includes this order). 
	 * </p> 
	 * @return
	 */
	Set<ITotalOrder> getSetOfSubOrders();
	
	@Override
	int hashCode();
	
	boolean isSuperSetOf(ITotalOrder property);
	
	boolean isSubSetOf(ITotalOrder property);
	
	//setters
	
	void extendWithMinimum(ISymbol symbol);
	
	void restrictTo(Set<IPair> pairs) throws RepresentationException;
	
}
