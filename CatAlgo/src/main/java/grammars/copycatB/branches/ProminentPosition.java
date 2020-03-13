package grammars.copycatB.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycatB.disjunctions.IPositionAttribute;
import grammars.copycatB.disjunctions.ISpecifiedProminentPosition;
import grammars.copycatB.leaves.ProminentPositioN;

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
