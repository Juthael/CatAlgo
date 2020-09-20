package representation.dataFormats;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import representation.dataFormats.impl.RelationalDescription;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

/**
 * <p>
 * A <b>description</b> can apply to any category, including objects (which are regarded as categories whose extent includes 
 * only themselves). It can be displayed as a relational description, as a regular language or as a functional expression. <br>
 * </p>
 * <p> 
 * All of these formats are equivalent and interchangeable, although some operations are format-specific (like set 
 * operations on a relational description, or the evaluation of words of a regular language by a state machine). If a relational 
 * description <i>A</i> is converted into a language <i>A'</i> and a functional expression <i>A''</i>, then <code> A.equals(A') 
 * </code>, <code>A'.equals(A'')</code> and <code>A.equals(A'')</code> will always return <code> true </code> : only the 
 * <i> meaning </i> matters, and not the format or implementation. <br>
 * </p>
 * 
 * @see representation.stateMachine.ICategory
 * @see representation.stateMachine.IContextObject
 * @see representation.dataFormats.IRelationalDescription
 * @see representation.dataFormats.ILanguage
 * @see representation.dataFormats.IFunctionalExpression
 * 
 * @author Gael Tregouet
 *
 */
public interface IDescription {
	
	/**
	 * <p>
	 * Returns a description of what the descriptions given as argument have in common. <br>
	 * Does so by converting the descriptions given as arguments in a relational description format. Then the intersection 
	 * of the set of orders returned by each relational description is calculated ; its set of maximal elements yields the 
	 * properties of a new description, that defines what is true about any argument. <br>  
	 * </p>
	 * 
	 * <p>
	 * Once the abstraction operation is executed, the result can be converted back into any other description format 
	 * (regular language or functional expression). <br>
	 * </p>
	 * 
	 * @see representation.dataFormats.IRelationalDescription
	 * @param descriptions a set of descriptions
	 * @return the description of what arguments have in common. 
	 * @throws RepresentationException if the set given as argument is null or empty
	 */
	static IDescription doAbstract(Set<IDescription> descriptions) throws RepresentationException {
		IRelationalDescription description;
		if (!descriptions.isEmpty() || descriptions == null) {
			Set<ITotalOrder> orders = new HashSet<ITotalOrder>();
			Iterator<IDescription> descIte = descriptions.iterator();
			IDescription firstDescription = descIte.next();
			if (firstDescription != null) {
				Set<ITotalOrder> currentDescOrders = firstDescription.getRelationalDescription().getTotalOrders();
				orders.addAll(currentDescOrders);
			}
			else throw new RepresentationException("IDescription.doAbstract() : first description is null.");
			while (descIte.hasNext()) {
				IDescription currentDescription = descIte.next();
				if (currentDescription != null) {
					orders.retainAll(currentDescription.getRelationalDescription().getTotalOrders());
				}
				else throw new RepresentationException("IDescription.doAbstract() : current description is null.");
			}
			description = new RelationalDescription(orders, RelationalDescription.ORDERS);
		}
		else throw new RepresentationException("IDescription.doAbstract(Set<IDescription>) : cannot abstract "
				+ "an empty/null set.");
		return description;
	}
	
	@Override
	boolean equals(Object o);
	
	/**
	 * <p>
	 * Returns the description as a functional expression. <br>
	 * </p>
	 * 
	 * <p>
	 * A functional expression is a statement that uses the function/argument relation to describe an object or a 
	 * category. <br>
	 * </p>
	 * <p> 
	 * As the word of a language, a functional expression is made up of a string of symbols ; but where a word is used to 
	 * denote a single property of an object, a functional expression is an exhaustive description of this object in a single 
	 * string. This description relies on the use of the conjunction symbol " ∧  ", by which a function can receive many 
	 * parameters. <br>
	 * </p>
	 * <p> 
	 * Ex : <i> ball (big ∧  blue ∧  deflated)</i> <br>
	 * </p>  
	 * @return the description as a functional expression
	 * @throws RepresentationException 
	 */
	IFunctionalExpression getFunctionalExpression() throws RepresentationException;	
	
	/**
	 * <p>
	 * Returns the context-free grammar associated with this description. <br>
	 * </p>
	 * 
	 * <p>
	 * A description's grammar is the minimal knowledge base required to proceed the description of a 
	 * given object or category (regardless of the format at use). <br>
	 * </p>
	 *  
	 * @return the grammar associated with this description
	 * @throws RepresentationException 
	 */
	IGrammar getGrammar();	
	
	/**
	 * <p>
	 * Returns the description in the form of a regular language. <br>
	 * </p>
	 * 
	 * <p>
	 * A regular language is composed of all the words (i.e., strings of symbols) accepted by a given 
	 * state machine of the 'finite state automaton' (FSA) type. For any regular language, a FSA machine can be found to accept 
	 * it. <br>
	 * </p>
	 * 
	 * <p>
	 * If a language is a description of an object or a category, then each of its word is a functional expression describing 
	 * an accessible property or an attainable goal on this object, or on any object of this category. <br>
	 * </p>  
	 * @return the description as a language
	 * @throws RepresentationException 
	 */
	ILanguage getLanguage() throws RepresentationException;	
	
	/**
	 * <p>
	 * Returns the number of arguments that any (non-conjunctive) functional expression ending with the specified symbol 
	 * will accept. <br>
	 * </p>
	 * 
	 * <p>
	 * Ex : <br> 
	 * - vacuumCleaner electricSpecifications (220V ∧  800A) <br> 
	 * - remoteControlCar electricSpecifications (7.4V ∧  1.5A) <br>
	 * => nbOfArgs(electricSpecifications) = 2 
	 * </p>
	 * 
	 * @param symbol any symbol that is used by the description
	 * @return the number of arguments accepted by any function ending with the specified symbol
	 * @throws RepresentationException if the specified symbol is not actually used in the description
	 */
	int getNbOfArgumentsFor(ISymbol symbol) throws RepresentationException;
	
	/**
	 * <p>
	 * Returns the description in a relational form. <br>
	 * </p>
	 * 
	 * <p>
	 * A relational description is composed of : <br> 
	 * <ul> 
	 * <li> A set of strictly ordered sets of symbols. The successor relation in each order yields a string of symbols. 
	 * If the description is about an object or a category, then this string denotes an observable property on this object 
	 * or category. 
	 * <li> A binary relation formed by the union of all ordered sets. This relation is usually not an order itself.    
	 * </ul>
	 * </p>
	 * @return the description as a binary relation
	 * @throws RepresentationException 
	 */
	IRelationalDescription getRelationalDescription() throws RepresentationException;	
	
	/**
	 * Returns the symbols at use in this description.
	 * @return the symbols at use in this description
	 */
	Set<ISymbol> getSymbols();
	
	@Override
	int hashCode();
	
	/**
	 * <p>
	 * Returns true if this description is an instance of the specified description. <br>
	 * </p>
	 * 
	 * <p>
	 * It means that any statement from the specified description remains true in this one. More formally, 
	 * for any statement about the specified description, a counterpart can be found about this description 
	 * that is either equal or more specific.  <br>
	 * </p>
	 * 
	 * <p>
	 * The most convenient way to find out if a description <i> A </i> meets a description <i> B </i> is 
	 * to adopt a relational format for both, and check that every ordered set in <i> B </i> is a subset of 
	 * an ordered set in <i>A</i> (not necessarily a <i>proper</i> subset). <br>
	 * </p>
	 * 
	 * @see representation.dataFormats.IRelationalDescription
	 * @param description
	 * @return true if this description is an instance of the specified description
	 * @throws RepresentationException 
	 */
	boolean meets(IDescription description) throws RepresentationException;	

}
