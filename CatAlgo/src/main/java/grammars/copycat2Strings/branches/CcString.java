package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.disjunctions.IStringName;
import grammars.copycat2Strings.leaves.CcStrinG;

public class CcString extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "CcString";
	private final CcStrinG ccStrinG;
	private final IStringName stringName;
	
	public CcString(CcStrinG ccString, IStringName stringName) {
		this.ccStrinG = ccString;
		this.stringName = stringName;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return ccStrinG;
	}	

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(ccStrinG);
		components.add(stringName);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		CcStrinG ccStringClone = (CcStrinG) ccStrinG.clone();
		IStringName stringNameClone = (IStringName) stringName.clone();
		return new CcString(ccStringClone, stringNameClone);
	}

}
