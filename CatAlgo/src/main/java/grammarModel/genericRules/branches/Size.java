package grammarModel.genericRules.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.genericRules.disjunctions.IValueOrClusteredValue;
import grammarModel.genericRules.leaves.SizE;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;

public class Size extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Size";
	private final SizE sizE;
	private final IValueOrClusteredValue value;
	
	/**
	 * <p>
	 * This class defines a generic and domain-agnostic rule, that can integrate context-free grammars associated with various 
	 * microworlds. <br>
	 * </p>
	 * 
	 * <p>
	 * <b>Size</b> is used to indicate a size value (e.g., the size of a group). <br>
	 * </p>
	 * @see grammarModel.structure.ISyntacticStructure
	 * @author Gael Tregouet
	 *
	 */
	public Size(SizE sizE, IValueOrClusteredValue value) {
		this.sizE = sizE;
		this.value = value;
	}
	
	//getters
	
	@Override
	public ISyntacticStructure clone() {
		SizE sizEClone = (SizE) sizE.clone();
		IValueOrClusteredValue valueClone = (IValueOrClusteredValue) value.clone();
		return new Size(sizEClone, valueClone);
	}
	
	@Override
	public ISyntaxLeaf getFunction() {
		return sizE;
	}
	
	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(sizE);
		components.add(value);
		return components;
	}		

	@Override
	public String getName() {
		return NAME;
	}

}
