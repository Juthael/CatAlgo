package representation.dataFormats;

import java.util.HashSet;
import java.util.Set;

import grammarModel.structure.ISyntaxGrove;
import representation.dataFormats.impl.RelationalDescription;
import representation.dataFormats.utils.ITotalOrder;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

/**
 * <p>
 * A <b>relational description</b> is one of the three equivalent description format that can be used to describe an object 
 * or a category ; the two others being a regular language (i.e. a set of words accepted by a finite state machine) and 
 * a functional expression. Each one of this format can be translated in one of the others, in order to proceed some 
 * format-specific operations (e.g., set operations with a relational description).<br>
 * </p>
 * 
 * <p>
 * A relational description is composed of : <br> 
 * <ul> 
 * <li> A set of total orders over sets of symbols. The successor relation in each order ({@link ITotalOrder}) yields a string 
 * of symbols. If the description is about an object or a category, then this string denotes an observable property on this 
 * object or category. 
 * <li> A binary relation formed by the union of all ordered sets. This relation is usually not an order itself.    
 * </ul>
 * </p>
 * 
 * <P>
 * The relational description format is mainly used to determine the common features in a set of objects or categories. 
 * It allows to proceed this calculation easily and with great flexibility, by simple intersection operations on binary 
 * relations. <br>
 * This "abstraction" function ( {@link IDescription#doAbstract(Set)} ) applies to a set of descriptions, and returns a 
 * single description. Although any description format is bound to implement this method, it actually does so by converting 
 * the descriptions given as arguments in a <i> relational description </i> format. Once the abstraction operation is 
 * executed, the result can be converted back into any other description format (regular language or functional expression).   
 * </p>
 * 
 * <p>
 * Warning : the {@link IRelationalDescription} type can (and should) only deal with well-founded binary relations. Otherwise, 
 * exceptions will be thrown and/or errors will occur. <br>
 * The {@link ISyntaxGrove#markRecursion()} method provides a recursion index mechanism, which ensures that the 
 * descriptions in the generated context input ({@link ISyntaxGrove#getContextInput()}) will only yield well-founded relations. 
 * </p>
 * 
 * @see representation.dataFormats.IDescription
 * @see representation.dataFormats.ILanguage
 * @see representation.dataFormats.utils.ITotalOrder
 * @see representation.dataFormats.IFunctionalExpression
 * @see grammarModel.structure.ISyntaxGrove
 * 
 * @author Gael Tregouet
 *
 */
public interface IRelationalDescription extends IDescription, Cloneable {
	
	//static
	
	/**
	 * <p>
	 * Returns a description resulting of the application of the function passed by the first parameter, to a set of arguments 
	 * passed by the second parameter. <br>
	 * </p>  
	 * 
	 * <p>
	 * Example : <br>
	 * -function : <i> airbusA320  </i> <br>
	 * -arguments : {(<i>latitude 48.240</i>) ; (<i>longitude -3,9</i>), (<i>altitude 39025ft</i>), 
	 * (<i>time 20-09-17_09:25</i>)} <br>
	 * -result : <i> airbusA320((latitude 48.240) Λ (longitude -3,9) Λ (altitude 39025ft) Λ (time 20-09-17_09:25))</i>
	 * </p>
	 * 
	 * <p>
	 * How to proceed this application on a set of relational descriptions : <br>
	 * 1-build the set <i> S </i> of strictly ordered sets of symbols, formed by the union of the sets of ordered sets 
	 * ({@link ITotalOrder}) in every relational description argument. <br>
	 * 2-build the new relational descriptor <i>newDesc(S)</i> <br>
	 * 3-extend every total order in <i>newDesc</i> ( {@link IRelationalDescription#applyFunction(ISymbol)} ), so that 
	 * their associated strictly ordered sets all have the specified function as their new minimum. <br>
	 * 4-return <i>newDesc</i>.
	 * </p>
	 * 
	 * @see representation.dataFormats.utils.ITotalOrder
	 * @param function a symbol denoting the function to be applied to arguments
	 * @param arguments a set of relational descriptions to which the function is to be applied
	 * @return the result of this application 
	 * @throws RepresentationException
	 */
	public static IRelationalDescription applyFunctionToArguments(ISymbol function, Set<IRelationalDescription> arguments) 
			throws RepresentationException {
		IRelationalDescription output;
		Set<ITotalOrder> orders = new HashSet<ITotalOrder>();
		for (IRelationalDescription argument : arguments) {
			IRelationalDescription newArgument;
			try {
				newArgument = argument.clone();
			}
			catch (CloneNotSupportedException e) {
				throw new RepresentationException("IRelationalDescription.applyFunctionToArgument(ISymbol, "
						+ "Set<IRelationalDescription>) : error during cloning operation.");
			}
			newArgument.applyFunction(function);
			orders.addAll(newArgument.getPropertiesAsTotalOrders());
		}
		output = new RelationalDescription(orders);
		return output;
	}	
	
	//getters
	
	/**
	 * Returns a deep copy of this relational description. 
	 * @return a clone relational description. 
	 * @throws CloneNotSupportedException
	 */
	IRelationalDescription clone() throws CloneNotSupportedException;
	
	@Override
	boolean equals(Object other);
	
	/**
	 * <p>
	 * Returns the set of pairs of symbols that form the binary relation of this relational description. <br>
	 * </p>
	 * 
	 * <p>
	 * This binary relation is formed by the union of all strictly ordered sets contained in a relational description. <br>
	 * </p>
	 * 
	 * @return the pairs of symbols of the binary relation
	 */
	Set<IPair> getBinaryRelation();	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * The most convenient way to get this functional expression is to transform the relational description into a language 
	 * first, and then to call {@link ILanguage#getFunctionalExpression()}.
	 * </p>
	 * 
	 * @see representation.dataFormats.ILanguage
	 * @return the functional expression equivalent to this binary relation
	 * @throws RepresentationException if an error has occured while building the language (used as an intermediate format)
	 */
	@Override
	IFunctionalExpression getFunctionalExpression() throws RepresentationException;	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * A rule <i> x ::= y </i> exists in the returned grammar iff : <br> 
	 * 1-<i> xRy </i>, <i> R </i> being the binary relation of the relational description <br>
	 * 2-In the set of total orders of this relational descriptions, there exist an order <i>O</i> whose successor 
	 * relation is <i>S<sub>O</sub></i>, such as <i>(x, y) ∈  S<sub>O</sub></i> <br> (Note that 2- implies 1-)
	 * </p>
	 * 
	 * <p>
	 * An easy way to get a grammar out of a relation is to transform the relation into a language 
	 * first, and then call {@link ILanguage#getGrammar()}. <br>
	 * </p>
	 * 
	 * @see representation.dataFormats.IDescription
	 * @see representation.dataFormats.ILanguage
	 * @return the grammar associated with this description
	 * @throws RepresentationException
	 */
	@Override
	IGrammar getGrammar() throws RepresentationException;	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Since a language is a set of chains of symbols, and since each total order of a relational description defines 
	 * a strictly ordered set of symbols (i.e., a chain), a relational description is converted into a regular language 
	 * by returning this set of strictly ordered sets. <br>
	 * </p>  
	 * 
	 * @return the language associated with this relation
	 * @throws RepresentationException if an error has occurred while transforming the binary relation into a language
	 */
	@Override
	ILanguage getLanguage() throws RepresentationException;
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * The most convenient way to obtain this number from a binary relation is to build its equivalent language 
	 * or functional expression first, and then to call this same method on them. <br>
	 * </p>  
	 * 
	 * @see representation.dataFormats.ILanguage
	 * @see representation.dataFormats.IRelationalDescription
	 * @throws RepresentationException if the specified symbol is not actually used in the description
	 */
	@Override
	int getNbOfArgumentsFor(ISymbol function) throws RepresentationException;		
	
	/**
	 * <p> 
	 * Returns the set of total orders of this relational description. <br> 
	 * </p>
	 * 
	 * <p>
	 * Each total order defines a strictly ordered set (i.e., a chain) of symbols. This chain denotes a property 
	 * of the object or category associated with this description. <br>
	 * </p>
	 * @return a set of properties, provided in the form of total orders over sets of symbols. 
	 */
	Set<ITotalOrder> getPropertiesAsTotalOrders();
	
	/**
	 * <p>
	 * Returns the set of symbols over which this relational description's binary relation is defined.
	 * </p>
	 * @return the set of symbols used by this relational description
	 */
	Set<ISymbol> getSetOfSymbols();
	
	@Override
	int hashCode();
	
	/**
	 * <p>
	 * Returns the language associated with this relational description, provided in a single string.
	 * </p>
	 * @return a string containing every pair <i>(x, y)</i> such as <i>xRy</i> 
	 */
	@Override
	String toString();
	
	//setters

	/**
	 * <p>
	 * Applies the specified function to the set of arguments constituted by this relational description's set of 
	 * total orders. <br>
	 * </p>  
	 * 
	 * <p>
	 * Example  : <br>
	 * -function : <i> airbusA320  </i> <br>
	 * -arguments : {(<i>latitude 48.240</i>) ; (<i>longitude -3,9</i>), (<i>altitude 39025ft</i>), 
	 * (<i>time 20-09-17_09:25</i>)} <br>
	 * -result : <i> airbusA320((latitude 48.240) Λ (longitude -3,9) Λ (altitude 39025ft) Λ (time 20-09-17_09:25))</i>
	 * </p>
	 * 
	 * <p>
	 * This is done by simply extending the relation's total orders ( {@link ITotalOrder#extendWithMinimum(ISymbol)} ) so 
	 * that their associated strictly ordered sets all have the specified function as their new minimum. <br>
	 * </p>
	 * @param symbol the function to apply
	 * @throws RepresentationException
	 */
	void applyFunction(ISymbol symbol) throws RepresentationException;
	
	/**
	 * <p>
	 * Restricts this relational description's binary relation, and each one of its total orders, to their intersection 
	 * with the specified pairs. <br>
	 * </p>
	 * 
	 * <p>
	 * A consistency check is performed in {@link ITotalOrder#restrictTo(Set)} to ensure that the resulting relation 
	 * remains a total order. <br>
	 * </p>
	 * @see representation.dataFormats.utils.ITotalOrder
	 * 
	 * @param pairs the set of pairs to which this relational description is restricted.  
	 * @throws RepresentationException
	 */
	void restrictTo(Set<IPair> pairs) throws RepresentationException;
}
