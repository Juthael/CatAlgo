package grammarModel.structure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IPair;
import representation.dataFormats.impl.BinaryRelation;
import representation.dataFormats.impl.FunctionalExpression;
import representation.dataFormats.impl.utils.utilsBR.Pair;
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
	public IBinaryRelation getBinaryRelation() {
		IBinaryRelation branchRelation;
		Set<IPair> branchRelationPairs = new HashSet<IPair>();
		ISyntaxLeaf function = getFunction();
		ISymbol functionSymbol = new Symbol(function.getName());
		List<ISyntacticStructure> arguments = getArguments();
		for (ISyntacticStructure argument : arguments) {
			IBinaryRelation argRelation = argument.getBinaryRelation();
			if (argRelation.getPairs().isEmpty()) {
				//then argument is a syntax leaf
				ISymbol argSymbol = new Symbol(argument.getName());
				branchRelationPairs.add(new Pair(functionSymbol, argSymbol));
			}
			else {
				//then argument is a syntax branch
				Set<IPair> argRelationPairs = argRelation.getPairs();
				for (IPair currentArgPair : argRelationPairs) {
					branchRelationPairs.add(new Pair(functionSymbol, currentArgPair.getAntecedent()));
					branchRelationPairs.add(new Pair(functionSymbol, currentArgPair.getConsequent()));
				}
				branchRelationPairs.addAll(argRelationPairs);
			}
		}
		branchRelation = new BinaryRelation(branchRelationPairs);
		return branchRelation;
	}	
	
	@Override
	public IFunctionalExpression getFunctionalExpression() {
		IFunctionalExpression functionalExpression;
		Map<List<Integer>, ISymbol> coordinatesOntoSymbols = new HashMap<List<Integer>, ISymbol>();
		ISymbol thisFunction = new Symbol(getFunction().getName());
		List<Integer> thisCoordinate = new ArrayList<Integer>();
		//A function has no coordinate, unless it belongs to an argument of another function ; so the list remains empty
		coordinatesOntoSymbols.put(thisCoordinate, thisFunction);
		int argIndex = 0;
		for (ISyntacticStructure argument : getArguments()) {
			Map<List<Integer>, ISymbol> argCoordinatesOntoSymbols = 
					argument.getFunctionalExpression().getCoordinatesOntoSymbols();
			for (List<Integer> coordinate : argCoordinatesOntoSymbols.keySet()) {
				ISymbol currentSymbol = argCoordinatesOntoSymbols.get(coordinate);
				List<Integer> currentSymbolNewCoordinate = new ArrayList<Integer>();
				currentSymbolNewCoordinate.add(argIndex);
				currentSymbolNewCoordinate.addAll(coordinate);
				coordinatesOntoSymbols.put(currentSymbolNewCoordinate, currentSymbol);
			}
			argIndex++;
		}
		functionalExpression = new FunctionalExpression(coordinatesOntoSymbols);
		return functionalExpression;
	}
	
	@Override
	public List<Long> getListOfLeafIDs() {
		List<Long> leafIDs = new ArrayList<Long>();
		for (ISyntacticStructure component : getListOfComponents()) {
			leafIDs.addAll(component.getListOfLeafIDs());
		}
		return leafIDs;
	}
	
	@Override
	public List<List<String>> getPathsAsListsOfStrings() {
		List<List<String>> paths = new ArrayList<List<String>>();
		for (ISyntacticStructure component : getListOfComponents()) {
			List<List<String>> compPaths = component.getPathsAsListsOfStrings();
			for (List<String> chain : compPaths) {
				chain.add(0, getName());
				paths.add(chain);
			}
		}
		return paths;
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
	public boolean replaceArguments(ISyntacticStructure newComp, List<Long> compIDs) {
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
					compReplaced = comp.replaceArguments(newComp, compIDs);
				}
			}
		}
		return compReplaced;
	}
	
	@Override
	public Map<String, Integer> setRecursionIndex() {
		Map<String, Integer> nameToRecursionIdx = new HashMap<String, Integer>();
		if (!recursionIndexHasBeenSet) {
			for (ISyntacticStructure arguments : getArguments()) {
				Map<String, Integer> argNameToRecursIdx = arguments.setRecursionIndex();
				for (String argName : argNameToRecursIdx.keySet()) {
					if (!nameToRecursionIdx.containsKey(argName) 
							|| (nameToRecursionIdx.get(argName) < argNameToRecursIdx.get(argName))) {
						nameToRecursionIdx.put(argName, argNameToRecursIdx.get(argName));
					}
				}
			}
			if (nameToRecursionIdx.containsKey(this.getName())) {
				recursionIndex = nameToRecursionIdx.get(this.getName()) + 1;
			}
			nameToRecursionIdx.put(this.getName(), recursionIndex);
			recursionIndexHasBeenSet = true;
		}
		return nameToRecursionIdx;
	}		

}
