package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IDoWithBurrow;
import grammars.sphex.leaves.ProvideFoodForTheGrubS;
import grammars.sphex.leaves.SupplyWithAPreY;

public class SupplyWithAPrey extends SyntaxBranch implements ISyntaxBranch, IDoWithBurrow {

	private static final String NAME = "SupplyWithAPrey";
	private SupplyWithAPreY supplyWithAPreY;
	private ProvideFoodForTheGrubS provideFood;
	
	public SupplyWithAPrey(SupplyWithAPreY supplyWithAPreY, ProvideFoodForTheGrubS provideFood) {
		this.supplyWithAPreY = supplyWithAPreY;
		this.provideFood = provideFood;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(supplyWithAPreY);
		components.add(provideFood);
		return components;
	}

	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return supplyWithAPreY;
	}

	@Override
	public ISyntacticStructure clone() {
		SupplyWithAPreY supplyWithAPreYClone = (SupplyWithAPreY) supplyWithAPreY.clone();
		ProvideFoodForTheGrubS provideFoodClone = (ProvideFoodForTheGrubS) provideFood.clone();
		return new SupplyWithAPrey(supplyWithAPreYClone, provideFoodClone);
	}

}
