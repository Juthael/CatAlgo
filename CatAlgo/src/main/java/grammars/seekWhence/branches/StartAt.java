package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.leaves.StartAT;
import grammars.seekWhence.leaves.ValuE;

/**
 * StartAt, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class StartAt extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "StartAt";
	private final StartAT startAT = new StartAT();
	private final ValuE valuE;
	
	/**
	 * As any {@link ISyntaxBranch}, StartAt is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
	public StartAt(StartAT startAT, ValuE valuE) {
		this.valuE = valuE;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(startAT);
		components.add(valuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		StartAT startATClone = (StartAT) startAT.clone();
		ValuE valuEClone = (ValuE) valuE.clone();
		return new StartAt(startATClone, valuEClone);		
	}

}
