package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IDoWithPrey;
import grammars.sphex.leaves.GraB;

public class Grab extends SyntaxBranch implements ISyntaxBranch, IDoWithPrey {

	private static final String NAME = "Grab";
	private GraB graB;
	private Predate predate;
	
	public Grab(GraB graB, Predate predate) {
		this.graB = graB;
		this.predate = predate;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(graB);
		components.add(predate);
		return components;
	}

	@Override
	public ISyntaxLeaf getFunction() {
		return graB;
	}

	@Override
	public ISyntacticStructure clone() {
		GraB graBClone = (GraB) graB.clone();
		Predate predateClone = (Predate) predate.clone();
		return new Grab(graBClone, predateClone);
	}

}
