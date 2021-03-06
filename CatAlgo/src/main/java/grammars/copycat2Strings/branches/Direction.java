package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.disjunctions.IDirectionValue;
import grammars.copycat2Strings.leaves.DirectioN;

public class Direction extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Direction";
	private final DirectioN directioN;
	private final IDirectionValue directionValue;
	
	public Direction(DirectioN directioN, IDirectionValue directionValue) {
		this.directioN = directioN;
		this.directionValue = directionValue;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return directioN;
	}	

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(directioN);
		components.add(directionValue);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		DirectioN directionClone = (DirectioN) directioN.clone();
		IDirectionValue directionValueClone = (IDirectionValue) directionValue.clone();
		return new Direction(directionClone, directionValueClone);
	}

}
