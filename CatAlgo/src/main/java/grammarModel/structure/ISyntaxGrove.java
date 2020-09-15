package grammarModel.structure;

import java.util.List;

import grammarModel.exceptions.GrammarModelException;
import representation.inputOutput.IContextInput;
import representation.stateMachine.IRepresentation;

/**
 * <p>
 * A syntax grove is a container of syntax trees (i.e., syntax branches that aren't components of another syntax branch). <br>
 * A grove is associated with context of objects ; each one of its syntax trees applies to a particular object and 
 * produces, as its string of terminals, a functional expression that is a description of this object. This description can 
 * also be provided in the equivalent form of a <i> relational description </i>, which uses order relations over a set of 
 * symbols. <br>
 * </p> 
 * 
 * <p>
 * A syntax grove generates a <i> context input </i> ({@link IContextInput}), out of which a <i> representation </i> 
 * ({@link IRepresentation}) can be constructed. A representation is an information structure that determines which 
 * categories are being perceived in a given context, and which relations bind them. <br>
 * </p> 
 * 
 * @see grammarModel.structure.ISyntaxBranch
 * @see representation.dataFormats.IFunctionalExpression
 * @see representation.dataFormats.IRelationalDescription
 * @see representation.inputOutput.IContextInput
 * @see representation.stateMachine.IRepresentation
 * @author Gael Tregouet
 *
 */
public interface ISyntaxGrove extends Cloneable {
	
	//getters

	ISyntaxGrove clone();
	
	/**
	 * Returns a context input, out of which a representation of the context can be built.
	 * 
	 * @return a context input
	 * @throws GrammarModelException 
	 */
	IContextInput getContextInput() throws GrammarModelException;
	
	/**
	 * Returns the list of the grove's syntax trees. 
	 * 
	 * @return the grove's trees
	 */
	List<ISyntaxBranch> getListOfTrees();
	
	/**
	 * Returns the name of the context. <br>
	 * 
	 * @return the name of the context.
	 */
	String getName();
	
	//setters
	
	/**
	 * Sets recursion index on trees ({@link ISyntacticStructure#setRecursionIndex()}), then marks recursion ( {@link ISyntacticStructure#markRecursion()}).
	 * 
	 * @throws GrammarModelException if {@link ISyntacticStructure#setRecursionIndex()} is called on trees and {@link ISyntacticStructure#markRecursion()} hasn't <br> 
	 * been called beforehand.  
	 */
	void markRecursion() throws GrammarModelException;

}
