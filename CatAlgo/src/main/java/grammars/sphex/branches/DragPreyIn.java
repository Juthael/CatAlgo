package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IDoWithBurrow;
import grammars.sphex.leaves.DragPreyIN;

public class DragPreyIn extends SyntaxBranch implements ISyntaxBranch, IDoWithBurrow {

	private static final String NAME = "DragPreyIn";
	private DragPreyIN dragPreyIN;
	private SupplyWithAPrey supplyWithAPrey;
	
	public DragPreyIn(DragPreyIN dragPreyIN, SupplyWithAPrey supplyWithAPrey) {
		this.dragPreyIN = dragPreyIN;
		this.supplyWithAPrey = supplyWithAPrey;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(dragPreyIN);
		components.add(supplyWithAPrey);
		return components;
	}

	@Override
	public ISyntaxLeaf getFunction() {
		return dragPreyIN;
	}

	@Override
	public ISyntacticStructure clone() {
		DragPreyIN dragPreyINClone = (DragPreyIN) dragPreyIN.clone();
		SupplyWithAPrey supplyWithAPreyClone = (SupplyWithAPrey) supplyWithAPrey.clone();
		return new DragPreyIn(dragPreyINClone, supplyWithAPreyClone);
	}

}
