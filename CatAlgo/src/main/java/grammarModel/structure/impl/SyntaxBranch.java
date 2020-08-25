package grammarModel.structure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import grammarModel.GrammarModelConstants;
import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.utils.ITreePaths;
import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.impl.BinaryRelation;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;

public abstract class SyntaxBranch extends SyntacticStructure implements ISyntaxBranch {
	
	protected boolean tree = false;
	
	public SyntaxBranch() {
	}

	//getters
	
	@Override
	public List<ISyntacticStructure> getArguments() {
		List<ISyntacticStructure> arguments = getListOfComponents();
		ISyntaxLeaf function = getFunction();
		arguments.remove(function);
		return arguments;
	}
	
	@Override
	public abstract SyntaxLeaf getFunction();
	
	@Override
	public abstract ISyntacticStructure clone() throws CloneNotSupportedException;
	
	@Override
	public IBinaryRelation getBinaryRelation() {
		IBinaryRelation relation;
		ISyntaxLeaf function = getFunction();
		ISymbol functionSymbol = new Symbol(function.getName());
		List<ISyntacticStructure> arguments = getArguments();
		Map<ISymbol, ISymbol> symbolMap = new HashMap<ISymbol, ISymbol>();
		for (ISyntacticStructure argument : arguments) {
			IBinaryRelation argRelation = argument.getBinaryRelation();
			if (argRelation.getRelationMap().isEmpty()) {
				//then argument is a syntax leaf
				ISymbol argSymbol = new Symbol(argument.getName());
				symbolMap.put(functionSymbol, argSymbol);
			}
			else {
				//then argument is a syntax branch
				Map<ISymbol, ISymbol> argSymbolMap = argRelation.getRelationMap();
				for (ISymbol antSymbol : argSymbolMap.keySet()) {
					symbolMap.put(functionSymbol, antSymbol);
					symbolMap.put(functionSymbol, argSymbolMap.get(antSymbol));
				}
				symbolMap.putAll(argSymbolMap);
			}
		}
		relation = new BinaryRelation(symbolMap);
		return relation;
	}
	
	@Override
	public IFunctionalExpression getFunctionalExpression() {
		ISymbol functionSymbol = new Symbol(getFunction().getName());
		ISymbol[][] applicationArray = new ISymbol[1][getArguments().size() + 1];
		applicationArray[0][0] = functionSymbol;
		for (ISyntacticStructure argument : getArguments()) {
			
		}
	}
	
	@Override
	public abstract List<ISyntacticStructure> getListOfComponents();	
	
	@Override
	public List<Long> getListOfLeafIDs() {
		//VERIFIER
		List<Long> leafIDs = new ArrayList<Long>();
		for (ISyntacticStructure component : getListOfComponents()) {
			leafIDs.addAll(component.getListOfLeafIDs());
		}
		return leafIDs;
	}
	
	@Override
	public List<List<String>> getPathsAsListsOfStrings() {
		List<List<String>> synChains = new ArrayList<List<String>>();
		for (ISyntacticStructure component : getListOfComponents()) {
			List<List<String>> compSynChains = component.getListOfTreeStringPaths();
			for (List<String> chain : compSynChains) {
				chain.add(0, getName());
				synChains.add(chain);
			}
		}
		return synChains;
	}
	
	@Override
	public boolean isATree() {
		return tree;
	}	
	
	//setters
	
	@Override
	public void markRecursion() throws GrammarModelException {
		if (recursionIndexHasBeenSet) {
			ISyntaxLeaf functionLeaf = getFunction();
			functionLeaf.setRecursionMark(recursionIndex);
		}
		else throw new GrammarModelException("SyntaxBranch.markRecursion() : recursion cannot be marked if "
				+ "the recursion index hasn't been set beforehand.");
		for (ISyntacticStructure component : getListOfComponents())
			component.markRecursion();
	}
	
	@Override
	public boolean replaceComponents(ISyntacticStructure newComp, List<Long> compIDs) {
		boolean compReplaced = false;
		ListIterator<ISyntacticStructure> compIt = getListOfComponents().listIterator();
		while (compIt.hasNext() && compReplaced == false) {
			ISyntacticStructure comp = compIt.next();
			List<Long> compLeafIDs = comp.getListOfLeafIDs();
			if (compLeafIDs.removeAll(compIDs) == true) {
				if (compLeafIDs.size() == 1) {
					compIt.set(newComp);
					compReplaced = true;
				}
				else {
					compReplaced = comp.replaceComponents(newComp, compIDs);
				}
			}
		}
		return compReplaced;
	}
	
	@Override
	public Map<String, Integer> setRecursionIndex() throws GrammarModelException {
		//VERIFIER
		Map<String, Integer> propNameToRecursionIdx = new HashMap<String, Integer>();
		if (!recursionIndexHasBeenSet) {
			for (ISyntacticStructure component : getListOfComponents()) {
				Map<String, Integer> compPropNameToRecursIdx = component.setRecursionIndex();
				for (String propName : compPropNameToRecursIdx.keySet()) {
					if (!propNameToRecursionIdx.containsKey(propName) 
							|| (propNameToRecursionIdx.get(propName) < compPropNameToRecursIdx.get(propName))) {
						propNameToRecursionIdx.put(propName, compPropNameToRecursIdx.get(propName));
					}
				}
			}
			if (propNameToRecursionIdx.containsKey(this.getName())) {
				recursionIndex = propNameToRecursionIdx.get(this.getName()) + 1;
				propNameToRecursionIdx.put(this.getName(), recursionIndex);
			}
			else propNameToRecursionIdx.put(this.getName(), recursionIndex);
		}
		else throw new GrammarModelException("SyntacticStructure.setRecursionIndex() : this method has already "
				+ "been called.");
		recursionIndexHasBeenSet = true;
		return propNameToRecursionIdx;
	}		

}
