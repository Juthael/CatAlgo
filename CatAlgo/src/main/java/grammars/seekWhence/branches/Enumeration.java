package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IRule;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.EnumeratioN;

/**
 * Enumeration, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Enumeration extends SyntaxBranch implements ISyntaxBranch, IRule {
	
	private static final String NAME = "Enumeration";
	private final EnumeratioN enumeratioN;
	private IValueOrRelation valOrRel;

	public Enumeration(EnumeratioN enumeratioN, IValueOrRelation valOrRel) {
		this.enumeratioN = enumeratioN;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(enumeratioN);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		EnumeratioN enumeratioNClone = (EnumeratioN) enumeratioN.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new Enumeration(enumeratioNClone, valOrRelClone);
	}

}
