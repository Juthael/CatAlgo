package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IDoWithBurrow;
import grammars.sphex.leaves.BurroW;

public class Burrow extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Burrow";
	private BurroW burroW;
	private InspectionStatus inspectionStatus;
	private SupplyStatus supplyStatus;
	private IDoWithBurrow doWithBurrow;
	private TimePosition timePosition;
	
	
	public Burrow(BurroW burroW, InspectionStatus inspectionStatus, SupplyStatus supplyStatus, IDoWithBurrow doWithBurrow, 
			TimePosition timePosition) {
		this.burroW = burroW;
		this.inspectionStatus = inspectionStatus;
		this.supplyStatus = supplyStatus;
		this.doWithBurrow = doWithBurrow;
		this.timePosition = timePosition;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(burroW);
		components.add(inspectionStatus);
		components.add(supplyStatus);
		components.add(doWithBurrow);
		components.add(timePosition);
		return components;
	}

	@Override
	public ISyntaxLeaf getFunction() {
		return burroW;
	}

	@Override
	public ISyntacticStructure clone() {
		BurroW burroWClone = (BurroW) burroW.clone();
		InspectionStatus inspectionStatusClone = (InspectionStatus) inspectionStatus.clone();
		SupplyStatus supplyStatusClone = (SupplyStatus) supplyStatus.clone();
		IDoWithBurrow doWithBurrowClone = (IDoWithBurrow) doWithBurrow.clone();
		TimePosition timePositionClone = (TimePosition) timePosition.clone();
		return new Burrow(burroWClone, inspectionStatusClone, supplyStatusClone, doWithBurrowClone, timePositionClone);
	}

}
