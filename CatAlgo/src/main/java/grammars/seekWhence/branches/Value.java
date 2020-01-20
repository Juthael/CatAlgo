package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.seekWhence.disjunctions.IValueOrValuE;
import grammars.seekWhence.leaves.ValuE;

/**
 * Value, as any {@link ISyntaxBranch}, implements a rule of a context-free grammar. The one at use here 
 * is associated with the microworld 'SeekWhence'.  
 * Any instance of this class represents a 'syntax branch', i.e. the whole derivation of a non-terminal node in a
 * syntax tree.
 * @author Gael Tregouet
 *
 */
public final class Value extends SyntaxBranch implements ISyntaxBranch, IValueOrValuE {

	public final String value;
	public final ValuE valuE;
	public IValueOrValuE valueOrValuE;
	
	/**
	 * As any {@link ISyntaxBranch}, Value is a derivable element of a context-free grammar, whose 
	 * derivation rule is expressed by its constructor. 
	 * 
	 * The derivation relationship being implemented as a composition relationship, this class represents the 
	 * left-hand side of the rule, and its constructor's list of arguments are the right-hand side.  
	 */
	public Value(ValuE valuE, IValueOrValuE valueOrValuE) {
		this.value = valuE.getName();
		this.valuE = valuE;
		this.valueOrValuE = valueOrValuE;
	}

	@Override
	public String getName() {
		return value;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(valuE);
		components.add(valueOrValuE);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ValuE valuEClone = (ValuE) valuE.clone();
		IValueOrValuE termOrVarValueClone = (IValueOrValuE) valueOrValuE.clone();
		return new Value(valuEClone, termOrVarValueClone);
	}

}
