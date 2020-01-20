package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.CenteR;

/**
 * Center, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Center extends SyntaxBranch implements ISyntaxBranch {
	
	private static final String NAME = "Center";
	private final CenteR centeR;
	private IValueOrRelation valOrRel;

	/**
	 * As any {@link ISyntaxBranch}, Center is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
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
