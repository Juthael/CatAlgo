package grammarModel.genericRules.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.genericRules.disjunctions.IValuEOrCoordSubValue;
import grammarModel.genericRules.leaves.CoordinatE;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;

public class Coordinate extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Coordinate";
	private CoordinatE coordinatE;
	private IValuEOrCoordSubValue value;
	
	/**
	 * <p>
	 * This class defines a generic and domain-agnostic rule, that can integrate context-free grammars associated with various 
	 * microworlds. <br>
	 * </p>
	 * 
	 * <p>
	 * <b>Coordinate</b> is used to denote that an object can be characterized by its positon in a given context. 
	 * </p>
	 * @see grammarModel.structure.ISyntacticStructure
	 * @author Gael Tregouet
	 *
	 */
	public Coordinate(CoordinatE coordinatE, IValuEOrCoordSubValue value) {
		this.value = value;
		this.coordinatE = coordinatE;
	}
	
	//getters
	
	@Override
	public ISyntacticStructure clone() {
		CoordinatE coordinatEClone = (CoordinatE) coordinatE.clone();
		IValuEOrCoordSubValue valueClone = (IValuEOrCoordSubValue) value.clone();
		return new Coordinate(coordinatEClone, valueClone);
	}	
	
	@Override
	public ISyntaxLeaf getFunction() {
		return coordinatE;
	}	
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(coordinatE);
		components.add(value);
		return components;
	}	
	
	@Override
	public String getName() {
		return NAME;
	}	

}
