package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IDoWithPrey;
import grammars.sphex.leaves.DropOnThresholD;

public class DropOnThreshold extends SyntaxBranch implements ISyntaxBranch, IDoWithPrey {

	private static final String NAME = "DropOnThreshold";
	private DropOnThresholD dropOnThresholD;
	private Predate predate;
	
	public DropOnThreshold(DropOnThresholD dropOnThresholD, Predate predate) {
		this.dropOnThresholD = dropOnThresholD;
		this.predate = predate;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(dropOnThresholD);
		components.add(predate);
		return components;
	}

	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return dropOnThresholD;
	}

	@Override
	public ISyntacticStructure clone() {
		DropOnThresholD dropOnThresholDClone = (DropOnThresholD) dropOnThresholD.clone();
		Predate predateClone = (Predate) predate.clone();
		return new DropOnThreshold(dropOnThresholDClone, predateClone);
	}

}
