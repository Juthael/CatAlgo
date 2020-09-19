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
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.impl.FunctionalExpression;
import representation.exceptions.RepresentationException;
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
	public IRelationalDescription getRelationalDescription() throws GrammarModelException {
		IRelationalDescription branchRelation;
		ISymbol function = new Symbol(getFunction().getName());
		Set<IRelationalDescription> argRelationalDescriptions = new HashSet<IRelationalDescription>();
		for (ISyntacticStructure argument : getArguments()) {
			argRelationalDescriptions.add(argument.getRelationalDescription());
		}
		try {
			branchRelation = IRelationalDescription.applyFunctionToArguments(function, argRelationalDescriptions);
		} catch (RepresentationException e) {
			throw new GrammarModelException("SyntaxBranch.getRelationalDescription() : function application has failed. "
					+ System.lineSeparator() 
					+ e.getMessage());
		}
		return branchRelation;
	}	
	
	@Override
	public IFunctionalExpression getFunctionalExpression() {
		IFunctionalExpression functionalExpression;
		Map<List<Integer>, ISymbol> coordinatesOntoSymbols = new HashMap<List<Integer>, ISymbol>();
		ISymbol thisFunction = new Symbol(getFunction().getName());
		//A function has no coordinate, unless it belongs to an argument of another function ; so the list remains empty
		List<Integer> thisCoordinate = new ArrayList<Integer>();
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
	public boolean replaceComponents(ISyntacticStructure newComp, List<Long> compIDs) throws GrammarModelException {
		boolean compReplaced = false;
		if (recursionIndexHasBeenSet)
			throw new GrammarModelException("SyntaxBranch.replaceArguments() : no replacement is allowed once "
					+ "the recursion index has been set.");
		else {
			ListIterator<ISyntacticStructure> compIt = getListOfComponents().listIterator();
			while (compIt.hasNext() && compReplaced == false) {
				ISyntacticStructure comp = compIt.next();
				List<Long> compLeafIDs = comp.getListOfLeafIDs();
				if (compLeafIDs.removeAll(compIDs) == true) {
					if (compLeafIDs.size() == 1) {
						//then current iterator structure is a syntax leaf that must be replaced
						compIt.set(newComp);
						compReplaced = true;
					}
					else {
						//then current iterator structure is a syntax branch
						ISyntaxBranch branchComp = (ISyntaxBranch) comp;
						compReplaced = branchComp.replaceComponents(newComp, compIDs);
					}
				}
			}
		}
		return compReplaced;
	}
	
	@Override
	public Map<String, Integer> setRecursionIndex() {
		Map<String, Integer> nameOntoRecursionIdx = new HashMap<String, Integer>();
		if (!recursionIndexHasBeenSet) {
			for (ISyntacticStructure arguments : getArguments()) {
				Map<String, Integer> argNameOntoRecursIdx = arguments.setRecursionIndex();
				for (String argName : argNameOntoRecursIdx.keySet()) {
					if (!nameOntoRecursionIdx.containsKey(argName) 
							|| (nameOntoRecursionIdx.get(argName) < argNameOntoRecursIdx.get(argName))) {
						nameOntoRecursionIdx.put(argName, argNameOntoRecursIdx.get(argName));
					}
				}
			}
			if (nameOntoRecursionIdx.containsKey(this.getName())) {
				recursionIndex = nameOntoRecursionIdx.get(this.getName()) + 1;
			}
			nameOntoRecursionIdx.put(this.getName(), recursionIndex);
			recursionIndexHasBeenSet = true;
		}
		return nameOntoRecursionIdx;
	}		

}
