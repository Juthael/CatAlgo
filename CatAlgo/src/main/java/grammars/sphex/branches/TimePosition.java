package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.leaves.TimePositioN;
import grammars.sphex.leaves.SteP;

public class TimePosition extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "TimePosition";
	private TimePositioN timePositioN;
	private SteP steP;
	
	public TimePosition(TimePositioN timePositioN, SteP steP) {
		this.timePositioN = timePositioN;
		this.steP = steP;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(timePositioN);
		components.add(steP);
		return components;
	}

	@Override
	public ISyntaxLeaf getFunction() {
		return timePositioN;
	}

	@Override
	public ISyntacticStructure clone() {
		TimePositioN procedurEClone = (TimePositioN) timePositioN.clone();
		SteP stePClone = (SteP) steP.clone();
		return new TimePosition(procedurEClone, stePClone);
	}

}
