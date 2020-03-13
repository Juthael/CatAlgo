package grammars.copycatB.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycatB.disjunctions.ICoordinateOrCoordinatE;
import grammars.copycatB.leaves.ValuE;

public class Coordinate extends SyntaxBranch implements ISyntaxBranch, ICoordinateOrCoordinatE {

	private final String name; 
	private ValuE valuE;
	private ICoordinateOrCoordinatE coordinatE;
	
	public Coordinate(ValuE valuE, ICoordinateOrCoordinatE coordinatE) {
		name = valuE.getName();
		this.valuE = valuE;
		this.coordinatE = coordinatE;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getPosetElementName() throws GrammarModelException {
		String namePlusSeparator = getName().concat("_");
		return namePlusSeparator.concat(coordinatE.getPosetElementName());
	}	

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(valuE);
		components.add(coordinatE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ValuE valuEClone = (ValuE) valuE.clone();
		ICoordinateOrCoordinatE coordinateClone = (ICoordinateOrCoordinatE) coordinatE.clone();
		return new Coordinate(valuEClone, coordinateClone);
	}

}
