package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.leaves.ProcedurE;
import grammars.sphex.leaves.SteP;

public class Procedure extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Procedure";
	private ProcedurE procedurE;
	private SteP steP;
	
	public Procedure(ProcedurE procedurE, SteP steP) {
		this.procedurE = procedurE;
		this.steP = steP;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(procedurE);
		components.add(steP);
		return components;
	}

	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return procedurE;
	}

	@Override
	public ISyntacticStructure clone() {
		ProcedurE procedurEClone = (ProcedurE) procedurE.clone();
		SteP stePClone = (SteP) steP.clone();
		return new Procedure(procedurEClone, stePClone);
	}

}
