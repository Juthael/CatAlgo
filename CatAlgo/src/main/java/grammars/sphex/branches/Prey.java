package grammars.sphex.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.sphex.disjunctions.IDoWithPrey;
import grammars.sphex.leaves.PreY;

public class Prey extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Prey";
	private PreY preY;
	private Position position;
	private IDoWithPrey doWithPrey;
	private Procedure procedure;
	
	public Prey(PreY preY, Position position, IDoWithPrey doWithPrey, Procedure procedure) {
		this.preY = preY;
		this.position = position;
		this.doWithPrey = doWithPrey;
		this.procedure = procedure;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(preY);
		components.add(position);
		components.add(doWithPrey);
		components.add(procedure);
		return components;
	}

	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return preY;
	}

	@Override
	public ISyntacticStructure clone() {
		PreY preYClone = (PreY) preY.clone();
		Position positionClone = (Position) position.clone();
		IDoWithPrey doWithPreyClone = (IDoWithPrey) doWithPrey.clone();
		Procedure procedureClone = (Procedure) procedure.clone();
		return new Prey(preYClone, positionClone, doWithPreyClone, procedureClone);
	}

}
