package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IInspectionStatus;
import grammars.sphex.leaves.InspectionStatuS;

public class InspectionStatus extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "InspectionStatus";
	private InspectionStatuS inspectionStatuS;
	private IInspectionStatus inspectionStatus;
	
	public InspectionStatus(InspectionStatuS inspectionStatuS, IInspectionStatus inspectionStatus) {
		this.inspectionStatuS = inspectionStatuS;
		this.inspectionStatus = inspectionStatus;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(inspectionStatuS);
		components.add(inspectionStatus);
		return components;
	}

	@Override
	public ISyntaxLeaf getFunction() {
		return inspectionStatuS;
	}

	@Override
	public ISyntacticStructure clone() {
		InspectionStatuS inspectionStatuSClone = (InspectionStatuS) inspectionStatuS.clone();
		IInspectionStatus inspectionStatusClone = (IInspectionStatus) inspectionStatus.clone();
		return new InspectionStatus(inspectionStatuSClone, inspectionStatusClone);
	}

}
