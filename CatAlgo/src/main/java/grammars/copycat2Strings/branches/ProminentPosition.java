package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.disjunctions.IPositionAttribute;
import grammars.copycat2Strings.disjunctions.ISpecifiedProminentPosition;
import grammars.copycat2Strings.leaves.ProminentPositioN;

public class ProminentPosition extends SyntaxBranch implements ISyntaxBranch, IPositionAttribute {

	private static final String NAME = "ProminentPosition";
	private final ProminentPositioN prominentPositioN;
	private final ISpecifiedProminentPosition specProminentPosition;
	
	public ProminentPosition(ProminentPositioN prominentPositioN, ISpecifiedProminentPosition specProminentPosition) {
		this.prominentPositioN = prominentPositioN;
		this.specProminentPosition = specProminentPosition;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public ISyntaxLeaf getFunction() {
		return prominentPositioN;
	}		

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(prominentPositioN);
		components.add(specProminentPosition);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		ProminentPositioN prominentPositioNClone = (ProminentPositioN) prominentPositioN.clone();
		ISpecifiedProminentPosition specProminentPositionClone 
			= (ISpecifiedProminentPosition) specProminentPosition.clone();
		return new ProminentPosition(prominentPositioNClone, specProminentPositionClone);
	}

}
