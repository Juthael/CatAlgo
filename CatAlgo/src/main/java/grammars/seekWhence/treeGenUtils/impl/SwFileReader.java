package grammars.seekWhence.treeGenUtils.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticGrove;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.impl.SyntacticGrove;
import grammars.seekWhence.branches.AlternationRule;
import grammars.seekWhence.branches.ArithSeq;
import grammars.seekWhence.branches.BouncingCycle;
import grammars.seekWhence.branches.Center;
import grammars.seekWhence.branches.Cycle;
import grammars.seekWhence.branches.Digit;
import grammars.seekWhence.branches.Enumeration;
import grammars.seekWhence.branches.EveryXElem;
import grammars.seekWhence.branches.FirstValue;
import grammars.seekWhence.branches.Increment;
import grammars.seekWhence.branches.Position;
import grammars.seekWhence.branches.ReflectedPart;
import grammars.seekWhence.branches.Relation;
import grammars.seekWhence.branches.Size;
import grammars.seekWhence.branches.StartAt;
import grammars.seekWhence.branches.Symmetry;
import grammars.seekWhence.branches.SymmetryWithCenter;
import grammars.seekWhence.disjunctions.IAlternation;
import grammars.seekWhence.disjunctions.IRule;
import grammars.seekWhence.disjunctions.IValueOrRelation;
import grammars.seekWhence.disjunctions.IValueOrValuE;
import grammars.seekWhence.leaves.AlternationRulE;
import grammars.seekWhence.leaves.ArithSeQ;
import grammars.seekWhence.leaves.BouncingCyclE;
import grammars.seekWhence.leaves.CenteR;
import grammars.seekWhence.leaves.CyclE;
import grammars.seekWhence.leaves.DigiT;
import grammars.seekWhence.leaves.EnumeratioN;
import grammars.seekWhence.leaves.EveryXEleM;
import grammars.seekWhence.leaves.FirstValuE;
import grammars.seekWhence.leaves.IncremenT;
import grammars.seekWhence.leaves.NoAlterN;
import grammars.seekWhence.leaves.PositioN;
import grammars.seekWhence.leaves.ReflectedParT;
import grammars.seekWhence.leaves.RelatioN;
import grammars.seekWhence.leaves.SizE;
import grammars.seekWhence.leaves.StartAT;
import grammars.seekWhence.leaves.SymmetrY;
import grammars.seekWhence.leaves.SymmetryWithCenteR;
import grammars.seekWhence.leaves.ValuE;
import grammars.seekWhence.treeGenUtils.ISwFileReader;
import grammars.seekWhence.treeGenUtils.exception.SwFileReaderException;
import grammars.seekWhence.treeGenUtils.exception.SwTreeGenException;

public class SwFileReader implements ISwFileReader {

	//to do : genericFileReaderException, genericFileReader, classwithMain
	
	String[][][] treeDescriptions;
	ISyntacticStructure[][][] structures;
	
	public SwFileReader() {
	}

	@Override
	public ISyntacticGrove getSyntacticGrove(Path path) throws SwTreeGenException, SwFileReaderException {
		List<ISyntacticStructure> trees = new ArrayList<ISyntacticStructure>();
		treeDescriptions = setDescriptions(path);
		structures = setStructures(treeDescriptions);
		for (int treeIndex=0 ; treeIndex < treeDescriptions.length ; treeIndex++)
			trees.add(buildTree(treeIndex));
		return new SyntacticGrove("SeekWhenceCtxt", trees);
	}
	
	private String[][][] setDescriptions(Path path) throws SwFileReaderException {
		String[][][] treeDescripts = new String[getNumberOfTrees(path)][][];
		int treeDescriptsIndex = 0;
		BufferedReader reader;
		try {
			reader = Files.newBufferedReader(path);
		}
		catch (Exception e) {
			throw new SwFileReaderException("SwFileReader.setDescriptions() : BufferedReader couldn't be instantiated."
					+ System.lineSeparator() + e.getMessage());
		}
		String line;
		String[][] treeDescript;
		List<String> currentTreePaths = new ArrayList<String>(); 
		do {
			try {
				line = reader.readLine();
			}
			catch (IOException e) {
				throw new SwFileReaderException("SwFileReader.setDescriptions() : IOException thrown."
						+ System.lineSeparator() + e.getMessage());
			}
			if (!line.equals("/")) {
				currentTreePaths.add(line);
			}
			else {
				if (!currentTreePaths.isEmpty()) {
					treeDescript = new String[currentTreePaths.size()][];
					for (int i=0 ; i < currentTreePaths.size() ; i++) {
						treeDescript[i] = currentTreePaths.get(i).split("/");
					}
					treeDescripts[treeDescriptsIndex] = treeDescript;
					treeDescriptsIndex++;
					currentTreePaths = new ArrayList<String>();
				}
			}
		}
		while (line != null);
		return treeDescripts;
	}
	
	private int getNumberOfTrees(Path path) throws SwFileReaderException {
		int numberOfTrees = 0;
		BufferedReader reader;
		try {
			reader = Files.newBufferedReader(path);
		}
		catch (Exception e) {
			throw new SwFileReaderException("SwFileReader.getNumberOfTrees() : reader couldn't be instantiated."
					+ System.lineSeparator() + e.getMessage());
		}
		String line;
		List<String> currentTreePaths = new ArrayList<String>(); 
		do {
			try {
				line = reader.readLine();
			}
			catch (IOException e) {
				throw new SwFileReaderException("SwFileReader.getNumberOfTrees() : IOException thrown."
						+ System.lineSeparator() + e.getMessage());
			}
			if (line != null) {
				if (line.equals("/"))
					numberOfTrees++;
			}
		}
		while (line != null);
		return numberOfTrees;
	}
	
	private String[][] getSingleTreeDescription(List<String> paths){
		//to implement
		return null;
	}
	
	private ISyntacticStructure[][][] setStructures(String[][][] treeDescriptions) {
		ISyntacticStructure[][][] structures = new ISyntacticStructure[treeDescriptions.length][][];
		for (int treeIndex=0 ; treeIndex < treeDescriptions.length ; treeIndex++) {
			structures[treeIndex] = new ISyntacticStructure[treeDescriptions[treeIndex].length][];
			for (int pathIndex=0 ; pathIndex < treeDescriptions[treeIndex].length ; pathIndex++) {
				structures[treeIndex][pathIndex] = new ISyntacticStructure[treeDescriptions[treeIndex][pathIndex].length];
			}
		}
		return structures;
	}
	
	private Digit buildTree(int treeIndex) throws SwFileReaderException {
		int pathMaxLength = setPathMaxLength(treeIndex);
		for (int nodeIndex=pathMaxLength-1 ; nodeIndex >= 0 ; nodeIndex--) {
			int pathIndex = 0;
			while (pathIndex < treeDescriptions[treeIndex].length) {
				if (nodeIndex < treeDescriptions[treeIndex][pathIndex].length) {
					pathIndex = buildSyntacticStructure(treeIndex, pathIndex, nodeIndex);
				}
				else pathIndex++;
			}
		}
		return (Digit) structures[treeIndex][0][0];
	}
	
	private int buildSyntacticStructure(int treeIndex, int pathIndex, int nodeIndex) throws SwFileReaderException {
		int currentPathIndex = pathIndex;
		String nodeName = treeDescriptions[treeIndex][pathIndex][nodeIndex];
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		if (nodeIndex+1 < treeDescriptions[treeIndex][pathIndex].length) {
			while(currentPathIndex < treeDescriptions[treeIndex].length
					&& nodeIndex+1 < treeDescriptions[treeIndex][currentPathIndex].length
					&& treeDescriptions[treeIndex][currentPathIndex][nodeIndex].equals(nodeName)) {
				if (structures[treeIndex][currentPathIndex][nodeIndex+1] != null) {
					components.add(structures[treeIndex][currentPathIndex][nodeIndex+1]);
				}
				currentPathIndex++;
			}
		}
		else {
			currentPathIndex++;
		}
		castComponentsAndInstantiateStructure(nodeName, components, treeIndex, currentPathIndex, nodeIndex);
		return currentPathIndex;
	}	
	
	private int setPathMaxLength(int treeIndex) {
		int pathMaxLength = 0;
		for (String[] path : treeDescriptions[treeIndex]) {
			if (pathMaxLength < path.length)
				pathMaxLength = path.length;
		}
		return pathMaxLength;
	}
	
	public void castComponentsAndInstantiateStructure(String nodeName, List<ISyntacticStructure> components, 
			int treeIndex, int pathIndex, int nodeIndex) throws SwFileReaderException {
		switch(nodeName) {
		case "AlternationRule" : 
			if (components.size() != 3) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'AlternationRule' number "
						+ "of components should be 3 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				AlternationRule alternationRule;
				try {
					AlternationRulE alternationRulE = (AlternationRulE) components.get(0);
					EveryXElem everyXElem = (EveryXElem) components.get(1);
					StartAt startAt = (StartAt) components.get(2);
					alternationRule = new AlternationRule(alternationRulE, everyXElem, startAt);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'AlternationRule' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = alternationRule;
			}
			break;
		case "AlternationRulE" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'AlternationRulE' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				AlternationRulE alternationRulE;
				try {
					alternationRulE = new AlternationRulE();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'AlternationRulE' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = alternationRulE;
			}
			break;
		case "ArithSeq" : 
			if (components.size() != 3) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'ArithSeq' number "
						+ "of components should be 3 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				ArithSeq arithSeq;
				try {
					ArithSeQ arithSeQ = (ArithSeQ) components.get(0);
					FirstValue firstValue = (FirstValue) components.get(1);
					Increment increment = (Increment) components.get(2);
					arithSeq = new ArithSeq(arithSeQ, firstValue, increment);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ArithSeq' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = arithSeq;
			}
			break;
		case "ArithSeQ" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'ArithSeQ' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				ArithSeQ arithSeQ;
				try {
					arithSeQ = new ArithSeQ();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ArithSeQ' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = arithSeQ;
			}
			break;
		case "BouncingCycle" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'BouncingCycle' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				BouncingCycle bouncingCycle;
				try {
					BouncingCyclE bouncingCyclE = (BouncingCyclE) components.get(0);
					ValuE valuE = (ValuE) components.get(1);
					bouncingCycle = new BouncingCycle(bouncingCyclE, valuE);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'BouncingCycle' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = bouncingCycle;
			}
			break;
		case "BouncingCyclE" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'BouncingCyclE' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				BouncingCyclE bouncingCyclE;
				try {
					bouncingCyclE = new BouncingCyclE();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'BouncingCyclE' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = bouncingCyclE;
			}
			break;	
		case "Center" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'Center' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				Center center;
				try {
					CenteR centeR = (CenteR) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					center = new Center(centeR, valOrRel);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Center' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = center;
			}
			break;
		case "CenteR" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'CenteR' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				CenteR centeR;
				try {
					centeR = new CenteR();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'CenteR' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = centeR;
			}
			break;
		case "Cycle" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'Cycle' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				Cycle cycle;
				try {
					CyclE cyclE = (CyclE) components.get(0);
					ValuE valuE = (ValuE) components.get(1);
					cycle = new Cycle(cyclE, valuE);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Cycle' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = cycle;
			}
			break;
		case "CyclE" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'CyclE' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				CyclE cyclE;
				try {
					cyclE = new CyclE();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'CyclE' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = cyclE;
			}
			break;
		case "Digit" : 
			if (components.size() != 3) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'Digit' number "
						+ "of components should be 3 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				Digit digit;
				try {
					DigiT digiT = (DigiT) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					Position position = (Position) components.get(2);
					digit = new Digit(digiT, valOrRel, position);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Digit' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = digit;
			}
			break;
		case "DigiT" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'DigiT' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				DigiT digiT;
				try {
					digiT = new DigiT();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'DigiT' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = digiT;
			}
			break;
		case "Enumeration" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'Enumeration' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				Enumeration enumeration;
				try {
					EnumeratioN enumeratioN = (EnumeratioN) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					enumeration = new Enumeration(enumeratioN, valOrRel);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Enumeration' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = enumeration;
			}
			break;
		case "EnumeratioN" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'EnumeratioN' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				EnumeratioN enumeratioN;
				try {
					enumeratioN = new EnumeratioN();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'EnumeratioN' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = enumeratioN;
			}
			break;
		case "EveryXElem" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'EveryXElem' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				EveryXElem everyXElem;
				try {
					EveryXEleM everyXEleM = (EveryXEleM) components.get(0);
					ValuE valuE = (ValuE) components.get(1);
					everyXElem = new EveryXElem(everyXEleM, valuE);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'EveryXElem' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = everyXElem;
			}
			break;
		case "EveryXEleM" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'EveryXEleM' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				EveryXEleM everyXEleM;
				try {
					everyXEleM = new EveryXEleM();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'EveryXEleM' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = everyXEleM;
			}
			break;
		case "FirstValue" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'FirstValue' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				FirstValue firstValue;
				try {
					FirstValuE firstValuE = (FirstValuE) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					firstValue = new FirstValue(firstValuE, valOrRel);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'FirstValue' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = firstValue;
			}
			break;
		case "FirstValuE" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'FirstValuE' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				FirstValuE firstValuE;
				try {
					firstValuE = new FirstValuE();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'FirstValuE' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = firstValuE;
			}
			break;
		case "Increment" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'Increment' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				Increment increment;
				try {
					IncremenT incremenT = (IncremenT) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					increment = new Increment(incremenT, valOrRel);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Increment' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = increment;
			}
			break;
		case "IncremenT" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'IncremenT' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				IncremenT incremenT;
				try {
					incremenT = new IncremenT();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'IncremenT' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = incremenT;
			}
			break;
		case "NoAlterN" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'NoAlterN' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				NoAlterN noAlterN;
				try {
					noAlterN = new NoAlterN();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'NoAlterN' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = noAlterN;
			}
			break;
		case "Position" : 
			if (components.size() != 3) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'Position' number "
						+ "of components should be 3 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				Position position;
				try {
					PositioN positioN = (PositioN) components.get(0);
					IAlternation iAlternation = (IAlternation) components.get(1);
					IValueOrValuE valueOrValuE = (IValueOrValuE) components.get(2);
					position = new Position(positioN, iAlternation, valueOrValuE);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Position' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = position;
			}
			break;
		case "PositioN" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'PositioN' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				PositioN positioN;
				try {
					positioN = new PositioN();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'PositioN' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = positioN;
			}
			break;
		case "ReflectedPart" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'ReflectedPart' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				ReflectedPart reflectedPart;
				try {
					ReflectedParT reflectedParT = (ReflectedParT) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					reflectedPart = new ReflectedPart(reflectedParT, valOrRel);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ReflectedPart' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = reflectedPart;
			}
			break;	
		case "ReflectedParT" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'ReflectedParT' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				ReflectedParT reflectedParT;
				try {
					reflectedParT = new ReflectedParT();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ReflectedParT' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = reflectedParT;
			}
			break;
		case "Relation" : 
			if (components.size() != 3) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'Relation' number "
						+ "of components should be 3 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				Relation relation;
				try {
					RelatioN relatioN = (RelatioN) components.get(0);
					Size size = (Size) components.get(1);
					IRule iRule = (IRule) components.get(2);
					relation = new Relation(relatioN, size, iRule);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Relation' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = relation;
			}
			break;
		case "RelatioN" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'RelatioN' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				RelatioN relatioN;
				try {
					relatioN = new RelatioN();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'RelatioN' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = relatioN;
			}
			break;
		case "Size" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'Size' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				Size size;
				try {
					SizE sizE = (SizE) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					size = new Size(sizE, valOrRel);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Size' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = size;
			}
			break;
		case "SizE" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'SizE' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				SizE sizE;
				try {
					sizE = new SizE();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'SizE' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = sizE;
			}
			break;
		case "StartAt" : 
			if (components.size() != 2) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'StartAt' number "
						+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				StartAt startAt;
				try {
					StartAT startAT = (StartAT) components.get(0);
					ValuE valuE = (ValuE) components.get(1);
					startAt = new StartAt(startAT, valuE);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'StartAt' syntactic branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = startAt;
			}
			break;	
		case "StartAT" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'StartAT' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				StartAT startAT;
				try {
					startAT = new StartAT();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'StartAT' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = startAT;
			}
			break;
		case "Symmetry" :
			if (components.size() != 2 && components.size() != 4) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'Symmetry' number "
						+ "of components can't be " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				if (components.size() == 2) {
					Symmetry symmetry;
					try {
						SymmetrY symmetrY = (SymmetrY) components.get(0);
						ReflectedPart reflectedPart = (ReflectedPart) components.get(1);
						symmetry = new Symmetry(symmetrY, reflectedPart);
					}
					catch (Exception e) {
						throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
								+ "a 'Symmetry' syntactic branch. " + e.getMessage() 
								+ System.lineSeparator()
								+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
								+ " ; Node : " + Integer.toString(nodeIndex));
					}
					structures[treeIndex][pathIndex][nodeIndex] = symmetry;	
				}
				else if (components.size() == 4) {
					SymmetryWithCenter symmWithCenter;
					try {
						SymmetrY symmetrY = (SymmetrY) components.get(0);
						ReflectedPart reflectedPart = (ReflectedPart) components.get(1);
						SymmetryWithCenteR symmWithCenteR = (SymmetryWithCenteR) components.get(2);
						Center center = (Center) components.get(3);
						symmWithCenter = new SymmetryWithCenter(symmetrY, reflectedPart, symmWithCenteR, center);
					}
					catch (Exception e) {
						throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
								+ "a 'SymmetryWithCenter' syntactic branch. " + e.getMessage() 
								+ System.lineSeparator()
								+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
								+ " ; Node : " + Integer.toString(nodeIndex));
					}
					structures[treeIndex][pathIndex][nodeIndex] = symmWithCenter;						
				}
			}
			break;
		case "SymmetrY" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'SymmetrY' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				SymmetrY symmetrY;
				try {
					symmetrY = new SymmetrY();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'SymmetrY' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = symmetrY;
			}
			break;
		case "SymmetryWithCenteR" : 
			if (!components.isEmpty()) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : 'SymmetryWithCenteR' number "
						+ "of components should be 0 instead of " + Integer.toString(components.size()) + "." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				SymmetryWithCenteR symmetryWithCenteR;
				try {
					symmetryWithCenteR = new SymmetryWithCenteR();
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'SymmetryWithCenteR' syntactic leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = symmetryWithCenteR;
			}
			break;
		default : 
			if(nodeIndex+1 < treeDescriptions[treeIndex][pathIndex].length) {
				throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() :  unrecognized "
						+ "non-terminal node name '" + nodeName + "'." 
						+ System.lineSeparator()
						+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
						+ " ; Node : " + Integer.toString(nodeIndex));
			}
			else {
				ValuE valuE;
				try {
					valuE = new ValuE(nodeName);
				}
				catch (Exception e) {
					throw new SwFileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ValuE' syntactic leaf with param '" + nodeName + "'." + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				structures[treeIndex][pathIndex][nodeIndex] = valuE;
			}
			break;					
		}
	}

}
