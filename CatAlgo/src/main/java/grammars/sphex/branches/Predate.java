package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.leaves.PredatE;
import grammars.sphex.leaves.ProvideFoodForTheGrubS;

public class Predate extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Predate";
	private PredatE predatE;
	private ProvideFoodForTheGrubS provideFooD;
	
	public Predate(PredatE predatE, ProvideFoodForTheGrubS provideFooD) {
		this.predatE = predatE;
		this.provideFooD = provideFooD;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(predatE);
		components.add(provideFooD);
		return components;
	}

	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return predatE;
	}

	@Override
	public ISyntacticStructure clone() {
		PredatE predatEClone = (PredatE) predatE.clone();
		ProvideFoodForTheGrubS provideFooDClone = (ProvideFoodForTheGrubS) provideFooD.clone();
		return new Predate(predatEClone, provideFooDClone);
	}


}
