package grammars.seekWhence.utils.impl;

import java.util.List;

import grammarModel.exceptions.FileReaderException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.impl.GenericFileReader;
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
import grammars.seekWhence.branches.Value;
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

/**
 * ISwFileReader is a IGenericFileReader using the 'SeekWhence' context-free grammar.
 * @author Gael Tregouet
 *
 */
public class SwFileReader extends GenericFileReader implements IGenericFileReader {
	
	public SwFileReader() {
		super.ctxtName = "SeekWhenceCtxt";
	}
		
	@Override
	protected void castComponentsAndInstantiateStructure(String nodeName, List<ISyntacticStructure> components, 
			int treeIndex, int pathIndex, int nodeIndex) throws FileReaderException {
		switch(nodeName) {
		case "AlternationRule" : 
			if (components.size() != 3) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'AlternationRule' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'AlternationRule' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(alternationRule, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "AlternationRulE" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'AlternationRulE' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'AlternationRulE' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(alternationRulE, treeIndex, pathIndex, nodeIndex);	
			}
			break;
		case "ArithSeq" : 
			if (components.size() != 3) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'ArithSeq' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ArithSeq' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(arithSeq, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "ArithSeQ" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'ArithSeQ' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ArithSeQ' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(arithSeQ, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "BouncingCycle" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'BouncingCycle' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'BouncingCycle' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(bouncingCycle, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "BouncingCyclE" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'BouncingCyclE' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'BouncingCyclE' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(bouncingCyclE, treeIndex, pathIndex, nodeIndex);
			}
			break;	
		case "Center" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Center' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Center' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(center, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "CenteR" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'CenteR' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'CenteR' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(centeR, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Cycle" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Cycle' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Cycle' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(cycle, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "CyclE" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'CyclE' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'CyclE' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(cyclE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Digit" : 
			if (components.size() != 3) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Digit' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Digit' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(digit, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "DigiT" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'DigiT' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'DigiT' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(digiT, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Enumeration" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Enumeration' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Enumeration' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(enumeration, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "EnumeratioN" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'EnumeratioN' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'EnumeratioN' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(enumeratioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "EveryXElem" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'EveryXElem' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'EveryXElem' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(everyXElem, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "EveryXEleM" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'EveryXEleM' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'EveryXEleM' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(everyXEleM, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "FirstValue" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'FirstValue' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'FirstValue' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(firstValue, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "FirstValuE" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'FirstValuE' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'FirstValuE' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(firstValuE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Increment" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Increment' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Increment' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(increment, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "IncremenT" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'IncremenT' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'IncremenT' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(incremenT, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "NoAlterN" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'NoAlterN' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'NoAlterN' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(noAlterN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Position" : 
			if (components.size() != 3) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Position' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Position' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(position, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "PositioN" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'PositioN' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'PositioN' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(positioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "ReflectedPart" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'ReflectedPart' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ReflectedPart' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(reflectedPart, treeIndex, pathIndex, nodeIndex);
			}
			break;	
		case "ReflectedParT" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'ReflectedParT' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ReflectedParT' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(reflectedParT, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Relation" : 
			if (components.size() != 3) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Relation' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Relation' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(relation, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "RelatioN" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'RelatioN' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'RelatioN' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(relatioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Size" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Size' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'Size' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(size, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SizE" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'SizE' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'SizE' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(sizE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "StartAt" : 
			if (components.size() != 2) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'StartAt' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'StartAt' syntax branch. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(startAt, treeIndex, pathIndex, nodeIndex);
			}
			break;	
		case "StartAT" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'StartAT' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'StartAT' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(startAT, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Symmetry" :
			if (components.size() != 2 && components.size() != 4) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Symmetry' number "
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
						throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
								+ "a 'Symmetry' syntax branch. " + e.getMessage() 
								+ System.lineSeparator()
								+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
								+ " ; Node : " + Integer.toString(nodeIndex));
					}
					putStructureIntoArray(symmetry, treeIndex, pathIndex, nodeIndex);
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
						throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
								+ "a 'SymmetryWithCenter' syntax branch. " + e.getMessage() 
								+ System.lineSeparator()
								+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
								+ " ; Node : " + Integer.toString(nodeIndex));
					}
					putStructureIntoArray(symmWithCenter, treeIndex, pathIndex, nodeIndex);			
				}
			}
			break;
		case "SymmetrY" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'SymmetrY' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'SymmetrY' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(symmetrY, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SymmetryWithCenteR" : 
			if (!components.isEmpty()) {
				throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'SymmetryWithCenteR' number "
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
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'SymmetryWithCenteR' syntax leaf. " + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(symmetryWithCenteR, treeIndex, pathIndex, nodeIndex);
			}
			break;
		default : 
			if(nodeIndex+1 < treeDescriptions[treeIndex][pathIndex].length) {
				if (isInteger(nodeName)) {
					if (components.size() != 2) {
						throw new FileReaderException("SwFileReader.buildSyntacticStructure() : 'Value' number "
								+ "of components should be 2 instead of " + Integer.toString(components.size()) + "." 
								+ System.lineSeparator()
								+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
								+ " ; Node : " + Integer.toString(nodeIndex));
					}
					else {
						Value value;
						try {
							ValuE valuE = (ValuE) components.get(0);
							IValueOrValuE whichValue = (IValueOrValuE) components.get(1);
							value = new Value(valuE, whichValue);
						}
						catch (Exception e) {
							throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
									+ "a 'Value' syntax branch. " + e.getMessage() 
									+ System.lineSeparator()
									+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
									+ " ; Node : " + Integer.toString(nodeIndex));
						}
						putStructureIntoArray(value, treeIndex, pathIndex, nodeIndex);
					}
				}
				else {
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() :  unrecognized "
							+ "non-terminal node name '" + nodeName + "'." 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));					
				}
			}
			else {
				ValuE valuE;
				try {
					valuE = new ValuE(nodeName);
				}
				catch (Exception e) {
					throw new FileReaderException("SwFileReader.buildSyntacticStructure() : failed to build "
							+ "a 'ValuE' syntax leaf with param '" + nodeName + "'." + e.getMessage() 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex));
				}
				putStructureIntoArray(valuE, treeIndex, pathIndex, nodeIndex);
			}
			break;					
		}
	}
	
	private static boolean isInteger(String s) {
		boolean integer = true;
		if(s.isEmpty())
			integer = false;
		else {
			int i = 0;
			while (integer == true && i < s.length()) {
				char current = s.charAt(i);
				if(current == '-') {
					if (i != 0 || s.length() == 1)
						integer = false;
				}
				else integer = Character.isDigit(current);
				i++;
			}
		}
		return integer;
	}
}
