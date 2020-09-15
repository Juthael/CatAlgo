package grammarModel.genericRules.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.genericRules.disjunctions.IValuEOrCoordSubValue;
import grammarModel.genericRules.leaves.ValuE;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;

public class CoordSubValue extends SyntaxBranch implements ISyntaxBranch, IValuEOrCoordSubValue {

	private final String name;
	private final ValuE valuE;
	private final Coordinate coordinate;
	
	/**
	 * <p>
	 * This class defines a generic and domain-agnostic rule, that can integrate context-free grammars associated with various 
	 * microworlds. <br>
	 * </p>
	 * 
	 * <p>
	 * <b>CoordSubValue</b> is used to indicate the position of an object in a group of objects that is characterized itself 
	 * by a position in the context (or in the broader group) it belongs to. <br>
	 * </p>
	 * @see grammarModel.structure.ISyntacticStructure
	 * @author Gael Tregouet
	 *
	 */
	public CoordSubValue(ValuE valuE, Coordinate coordinate) {
		name = valuE.getName();
		this.valuE = valuE;
		this.coordinate = coordinate;
	}		

	//getters
	
	@Override
	public ISyntacticStructure clone() {
		ValuE valuEClone = (ValuE) valuE.clone();
		Coordinate coordinateClone = (Coordinate) coordinate.clone();
		return new CoordSubValue(valuEClone, coordinateClone);
	}	
	
	@Override
	public ISyntaxLeaf getFunction() {
		return valuE;
	}	
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(valuE);
		components.add(coordinate);
		return components;
	}	
	
	@Override
	public String getName() {	
		return name;
	}

}
