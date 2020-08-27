package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.ISupplyStatus;
import grammars.sphex.leaves.SupplyStatuS;

public class SupplyStatus extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "SupplyStatus";
	private SupplyStatuS supplyStatuS;
	private ISupplyStatus supplyStatus;
	
	public SupplyStatus(SupplyStatuS supplyStatuS, ISupplyStatus supplyStatus) {
		this.supplyStatus = supplyStatus;
		this.supplyStatuS = supplyStatuS;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(supplyStatuS);
		components.add(supplyStatus);
		return components;
	}

	@Override
	public ISyntaxLeaf getFunction() {
		return supplyStatuS;
	}

	@Override
	public ISyntacticStructure clone() {
		SupplyStatuS supplyStatuSClone = (SupplyStatuS) supplyStatuS.clone();
		ISupplyStatus supplyStatusClone = (ISupplyStatus) supplyStatus.clone();
		return new SupplyStatus(supplyStatuSClone, supplyStatusClone);
	}

}
