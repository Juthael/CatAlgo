package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.leaves.CoordinatE;
import grammars.copycat2Strings.leaves.ValuE;

public class Coordinate extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Coordinate";
	private CoordinatE coordinatE;
	private ValuE valuE;
	
	public Coordinate(CoordinatE coordinatE, ValuE valuE) {
		this.valuE = valuE;
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
	
	public ValuE getValuE() {
		return valuE;
	}	

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(coordinatE);
		components.add(valuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		CoordinatE coordinatEClone = (CoordinatE) coordinatE.clone();
		ValuE valuEClone = (ValuE) valuE.clone();
		return new Coordinate(coordinatEClone, valuEClone);
	}

}
