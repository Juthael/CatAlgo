package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.leaves.SizE;

/**
 * Size, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Size extends SyntaxBranch implements ISyntaxBranch {
	
	private static final String NAME = "Size";
	private final SizE sizE;
	private IValueOrRelation valOrRel;

	public Size(SizE sizE, IValueOrRelation valOrRel) {
		this.sizE = sizE;
		this.valOrRel = valOrRel;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(sizE);
		components.add(valOrRel);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		SizE sizEClone = (SizE) sizE.clone();
		IValueOrRelation valOrRelClone = (IValueOrRelation) valOrRel.clone();
		return new Size(sizEClone, valOrRelClone);
	}

}
