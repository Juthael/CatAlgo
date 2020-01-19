package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.CenteR;

/**
 * Center represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Center extends SyntaxBranch implements ISyntaxBranch {
	
	private static final String NAME = "Center";
	private final CenteR centeR;
	private IValueOrRelation valOrRel;

	public Center(CenteR centeR, IValueOrRelation valOrRel) {
		this.centeR = centeR;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(centeR);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		CenteR centeRClone = (CenteR) centeR.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new Center(centeRClone, valOrRelClone);
	}

}
