package grammars.seekWhence.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticBranch;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticBranch;
import grammars.seekWhence.disjunctions.IAlternation;
import grammars.seekWhence.leaves.AlternationRulE;

/**
 * AlternationRule represents a variable symbol of the context-free grammar associated with the microworld 'SeekWhence'. 
 * Any instance of this class represents a 'syntactic branch', i.e. the whole derivation of a non-terminal node in a
 * syntactic tree.
 * @author Gael Tregouet
 *
 */
public final class AlternationRule extends SyntacticBranch implements ISyntacticBranch, IAlternation {
	
	private static final String NAME = "AlternationRule";
	private final AlternationRulE alternationRulE;
	private final EveryXElem everyXElem;
	private final StartAt startAt;

	public AlternationRule(AlternationRulE alternationRulE, EveryXElem everyXElem, StartAt startAt) {
		this.alternationRulE = alternationRulE;
		this.everyXElem = everyXElem;
		this.startAt = startAt;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(alternationRulE);
		components.add(everyXElem);
		components.add(startAt);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		AlternationRulE alternationRulEClone = (AlternationRulE) alternationRulE.clone();
		EveryXElem everyXElemClone = (EveryXElem) everyXElem.clone();
		StartAt startAtClone = (StartAt) startAt.clone();
		return new AlternationRule(alternationRulEClone, everyXElemClone, startAtClone);
	}

}
