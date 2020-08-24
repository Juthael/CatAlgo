package grammarModel.structure;

import java.util.List;

import grammarModel.exceptions.GrammarModelException;
import representation.IContextInput;
import representation.IRepresentation;

/**
 * <p>
 * A syntax grove is a container of syntax trees. Syntax trees are syntax branches ({@link ISyntaxBranch}) whose name is 
 * the start element of the context-free grammar at use. A syntax grove is associated to a context of objects ; each one 
 * of its syntax trees applies to a particular object and yields, as its string of terminals, a functional expression 
 * that is a description of this object. <br>
 * </p> 
 * 
 * <p>
 * A syntax grove generates a <i> context input </i> ({@link IContextInput}), out of which a <i> representation </i> 
 * ({@link IRepresentation}) can be constructed. A representation is an information structure that determines which 
 * categories are being perceived in a given context, and which relations bind them. <br>
 * </p> 
 * 
 * @see grammarModel.structure.ISyntaxBranch
 * @see representation.IFunctionalExpression
 * @see representation.IContextInput
 * @see representation.IRepresentation
 * @author Gael Tregouet
 *
 */
public interface ISyntaxGrove extends ISyntaxBranch {
	
	//getters

	@Override
	ISyntacticStructure clone();
	
	/**
	 * Returns a context input, out of which a representation of the context can be built.
	 * 
	 * @return a context input
	 */
	IContextInput getContextInput();
	
	/**
	 * There is no function leaf applying to the grove's tree components. So this method returns <code> null </code>. 
	 * 
	 * @see grammarModel.structure.ISyntaxLeaf
	 * @return <code> null </code>
	 */
	@Override
	ISyntaxLeaf getFunction();
	
	/**
	 * Returns the list of the grove's syntax trees. 
	 * 
	 * @return the grove's trees
	 */
	@Override
	List<ISyntacticStructure> getListOfComponents();
	
	//setters
	
	/**
	 * 
	 */
	@Override
	void markRecursion();
	
	

}
