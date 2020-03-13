package grammars.copycatB.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycatB.disjunctions.IStringName;
import grammars.copycatB.leaves.CcStrinG;

public class CcString extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "CcString";
	private final CcStrinG ccString;
	private final IStringName stringName;
	
	public CcString(CcStrinG ccString, IStringName stringName) {
		this.ccString = ccString;
		this.stringName = stringName;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(ccString);
		components.add(stringName);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		CcStrinG ccStringClone = (CcStrinG) ccString.clone();
		IStringName stringNameClone = (IStringName) stringName.clone();
		return new CcString(ccStringClone, stringNameClone);
	}

}
