package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IDoWithBurrow;
import grammars.sphex.leaves.InspecT;

public class Inspect extends SyntaxBranch implements ISyntaxBranch, IDoWithBurrow {

	private static final String NAME = "Inspect";
	private InspecT inspecT;
	private SupplyWithAPrey supplyWithAPrey;
	
	public Inspect(InspecT inspecT, SupplyWithAPrey supplyWithAPrey) {
		this.inspecT = inspecT;
		this.supplyWithAPrey = supplyWithAPrey;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(inspecT);
		components.add(supplyWithAPrey);
		return components;
	}

	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return inspecT;
	}

	@Override
	public ISyntacticStructure clone() {
		InspecT inspecTClone = (InspecT) inspecT.clone();
		SupplyWithAPrey supplyWithAPreyClone = (SupplyWithAPrey) supplyWithAPrey.clone();
		return new Inspect(inspecTClone, supplyWithAPreyClone);
	}

}
