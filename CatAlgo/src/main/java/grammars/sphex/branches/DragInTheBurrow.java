package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IDoWithPrey;
import grammars.sphex.leaves.DragInTheBurroW;

public class DragInTheBurrow extends SyntaxBranch implements ISyntaxBranch, IDoWithPrey {

	private static final String NAME = "DragInTheBurrow";
	private DragInTheBurroW dragInTheBurroW;
	private Predate predate;
	
	public DragInTheBurrow(DragInTheBurroW dragInTheBurroW, Predate predate) {
		this.dragInTheBurroW = dragInTheBurroW;
		this.predate = predate;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(dragInTheBurroW);
		components.add(predate);
		return components;
	}

	@Override
	public ISyntaxLeaf getFunction() {
		return dragInTheBurroW;
	}

	@Override
	public ISyntacticStructure clone() {
		DragInTheBurroW dragInTheBurroWClone = (DragInTheBurroW) dragInTheBurroW.clone();
		Predate predateClone = (Predate) predate.clone();
		return new DragInTheBurrow(dragInTheBurroWClone, predateClone);
	}

}
