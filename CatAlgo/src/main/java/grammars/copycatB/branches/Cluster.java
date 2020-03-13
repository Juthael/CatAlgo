package grammars.copycatB.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycatB.disjunctions.IRule;
import grammars.copycatB.leaves.ClusteR;

public class Cluster extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Cluster";
	private final ClusteR clusteR;
	private final Size size;
	private final IRule rule;
	
	public Cluster(ClusteR clusteR, Size size, IRule rule) {
		this.clusteR = clusteR;
		this.size = size;
		this.rule = rule;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(clusteR);
		components.add(size);
		components.add(rule);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ClusteR clusteRClone = (ClusteR) clusteR.clone();
		Size sizeClone = (Size) size.clone();
		IRule ruleClone = (IRule) rule.clone();
		return new Cluster(clusteRClone, sizeClone, ruleClone);
	}

}
