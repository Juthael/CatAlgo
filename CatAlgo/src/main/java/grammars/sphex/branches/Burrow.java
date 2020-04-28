package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.leaves.BurroW;

public class Burrow extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Burrow";
	private BurroW burroW;
	private InspectionStatus inspectionStatus;
	private SupplyStatus supplyStatus;
	private Procedure procedure;
	
	
	public Burrow(BurroW burroW, InspectionStatus inspectionStatus, SupplyStatus supplyStatus, Procedure procedure) {
		this.burroW = burroW;
		this.inspectionStatus = inspectionStatus;
		this.supplyStatus = supplyStatus;
		this.procedure = procedure;
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
		components.add(procedure);
		return components;
	}

	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return burroW;
	}

	@Override
	public ISyntacticStructure clone() {
		BurroW burroWClone = (BurroW) burroW.clone();
		InspectionStatus inspectionStatusClone = (InspectionStatus) inspectionStatus.clone();
		SupplyStatus supplyStatusClone = (SupplyStatus) supplyStatus.clone();
		Procedure procedureClone = (Procedure) procedure.clone();
		return new Burrow(burroWClone, inspectionStatusClone, supplyStatusClone, procedureClone);
	}

}
