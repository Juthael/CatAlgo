package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.disjunctions.IRule;
import grammars.copycat2Strings.leaves.ClusteR;

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
	public ISyntaxLeaf getEponymLeaf() {
		return clusteR;
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
