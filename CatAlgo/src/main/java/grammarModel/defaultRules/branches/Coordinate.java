package grammarModel.defaultRules.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.defaultRules.disjunctions.IValuEOrCoordSubValue;
import grammarModel.defaultRules.leaves.CoordinatE;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;

public class Coordinate extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Coordinate";
	private CoordinatE coordinatE;
	private IValuEOrCoordSubValue value;
	
	public Coordinate(CoordinatE coordinatE, IValuEOrCoordSubValue value) {
		this.value = value;
		this.coordinatE = coordinatE;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public ISyntaxLeaf getEponymLeaf() {
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
	public ISyntacticStructure clone() {
		CoordinatE coordinatEClone = (CoordinatE) coordinatE.clone();
		IValuEOrCoordSubValue valueClone = (IValuEOrCoordSubValue) value.clone();
		return new Coordinate(coordinatEClone, valueClone);
	}

}
