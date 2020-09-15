package grammarModel.genericRules.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.genericRules.disjunctions.IRule;
import grammarModel.genericRules.leaves.ClusteR;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;

/**
 * <p>
 * This class defines a generic and domain-agnostic rule, that can integrate context-free grammars associated with various 
 * microworlds. <br>
 * </p>
 * 
 * <p>
 * <b>Cluster</b> is used to denote a to-be-specified structure organizing a group of values. 
 * </p>
 * @see grammarModel.structure.ISyntacticStructure
 * @author Gael Tregouet
 *
 */
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
	
	//getters

	@Override
	public ISyntacticStructure clone() {
		ClusteR clusteRClone = (ClusteR) clusteR.clone();
		Size sizeClone = (Size) size.clone();
		IRule ruleClone = (IRule) rule.clone();
		return new Cluster(clusteRClone, sizeClone, ruleClone);
	}	
	
	@Override
	public ISyntaxLeaf getFunction() {
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
	public String getName() {
		return NAME;
	}





}
