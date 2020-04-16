package grammars.copycatB.branches;

import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammars.copycatB.leaves.CoordinatE;

public class SubCoordinate extends Coordinate implements ISyntaxBranch {

	private Coordinate superCoordinate;
	
	public SubCoordinate(Coordinate coordinate, Coordinate superCoordinate) {
		super((CoordinatE) coordinate.getEponymLeaf(), coordinate.getValuE());
		this.superCoordinate = superCoordinate;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = super.getListOfComponents();
		components.add(superCoordinate);
		return components;
	}
	
	@Override
	public ISyntacticStructure clone() {
		Coordinate coordClone = (Coordinate) super.clone();
		Coordinate superCoordClone = (Coordinate) superCoordinate.clone();
		return new SubCoordinate(coordClone, superCoordClone);
	}	

}
