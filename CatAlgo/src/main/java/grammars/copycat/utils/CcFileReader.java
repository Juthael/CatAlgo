package grammars.copycat.utils;

import java.util.List;

import grammarModel.exceptions.FileReaderException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.impl.GenericFileReader;
import grammars.copycat.branches.CcString;
import grammars.copycat.branches.Direction;
import grammars.copycat.branches.ElementValue;
import grammars.copycat.branches.EndPosition;
import grammars.copycat.branches.FirstValue;
import grammars.copycat.branches.Increment;
import grammars.copycat.branches.Letter;
import grammars.copycat.branches.LetterValue;
import grammars.copycat.branches.Pattern;
import grammars.copycat.branches.Position;
import grammars.copycat.branches.ProminentPosition;
import grammars.copycat.branches.Relation;
import grammars.copycat.branches.Sequence;
import grammars.copycat.branches.SetElement;
import grammars.copycat.branches.SetSize;
import grammars.copycat.branches.Value;
import grammars.copycat.disjunctions.IDirectionValue;
import grammars.copycat.disjunctions.IEndPositionValue;
import grammars.copycat.disjunctions.IPositionAttribute;
import grammars.copycat.disjunctions.IRule;
import grammars.copycat.disjunctions.ISpecifiedProminentPosition;
import grammars.copycat.disjunctions.IStringName;
import grammars.copycat.disjunctions.IValueOrRelation;
import grammars.copycat.disjunctions.IValueOrValuE;
import grammars.copycat.leaves.CcStrinG;
import grammars.copycat.leaves.CentralPositioN;
import grammars.copycat.leaves.DirectioN;
import grammars.copycat.leaves.ElementValuE;
import grammars.copycat.leaves.EndPositioN;
import grammars.copycat.leaves.FirstPositioN;
import grammars.copycat.leaves.FirstStrinG;
import grammars.copycat.leaves.FirstValuE;
import grammars.copycat.leaves.FromLeftToRighT;
import grammars.copycat.leaves.FromRightToLefT;
import grammars.copycat.leaves.IncremenT;
import grammars.copycat.leaves.LastPositioN;
import grammars.copycat.leaves.LetteR;
import grammars.copycat.leaves.LetterValuE;
import grammars.copycat.leaves.NonProminentPositioN;
import grammars.copycat.leaves.PatterN;
import grammars.copycat.leaves.PositioN;
import grammars.copycat.leaves.ProminentPositioN;
import grammars.copycat.leaves.RelatioN;
import grammars.copycat.leaves.SecondStrinG;
import grammars.copycat.leaves.SequencE;
import grammars.copycat.leaves.SetElemenT;
import grammars.copycat.leaves.SetSizE;
import grammars.copycat.leaves.ValuE;

public class CcFileReader extends GenericFileReader implements IGenericFileReader {

	public CcFileReader() {
		super.ctxtName = "CopycatCtxt";
	}

	@Override
	protected void castComponentsAndInstantiateStructure(String nodeName, List<ISyntacticStructure> components,
			int treeIndex, int pathIndex, int nodeIndex) throws FileReaderException {
		switch (nodeName) {
		case "CcString" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				CcString ccString;
				try {
					CcStrinG ccStrinG = (CcStrinG) components.get(0);
					IStringName stringName = (IStringName) components.get(1);
					ccString = new CcString(ccStrinG, stringName);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(ccString, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "CcStrinG" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				CcStrinG ccStrinG;
				try {
					ccStrinG = new CcStrinG();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(ccStrinG, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "CentralPositioN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				CentralPositioN centralPositioN;
				try {
					centralPositioN = new CentralPositioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(centralPositioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Direction" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Direction direction;
				try {
					DirectioN directioN = (DirectioN) components.get(0);
					IDirectionValue directionValue = (IDirectionValue) components.get(1);
					direction = new Direction(directioN, directionValue);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(direction, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "DirectioN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				DirectioN directioN;
				try {
					directioN = new DirectioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(directioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "ElementValue" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				ElementValue elementValue;
				try {
					ElementValuE elementValuE = (ElementValuE) components.get(0);
					ValuE valuE = (ValuE) components.get(1);
					elementValue = new ElementValue(elementValuE, valuE);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(elementValue, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "ElementValuE" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				ElementValuE elementValuE;
				try {
					elementValuE = new ElementValuE();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(elementValuE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "EndPosition" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				EndPosition endPosition;
				try {
					EndPositioN endPositioN = (EndPositioN) components.get(0);
					IEndPositionValue endPositionValue = (IEndPositionValue) components.get(1);
					endPosition = new EndPosition(endPositioN, endPositionValue);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(endPosition, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "EndPositioN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				EndPositioN endPositioN;
				try {
					endPositioN = new EndPositioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(endPositioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "FirstPositioN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				FirstPositioN firstPositioN;
				try {
					firstPositioN = new FirstPositioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(firstPositioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "FirstStrinG" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				FirstStrinG firstStrinG;
				try {
					firstStrinG = new FirstStrinG();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(firstStrinG, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "FirstValue" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				FirstValue firstValue;
				try {
					FirstValuE firstValuE = (FirstValuE) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					firstValue = new FirstValue(firstValuE, valOrRel);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(firstValue, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "FirstValuE" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				FirstValuE firstValuE;
				try {
					firstValuE = new FirstValuE();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(firstValuE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "FromLeftToRighT" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				FromLeftToRighT fromLeftToRighT;
				try {
					fromLeftToRighT = new FromLeftToRighT();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(fromLeftToRighT, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "FromRightToLefT" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				FromRightToLefT fromRightToLefT;
				try {
					fromRightToLefT = new FromRightToLefT();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(fromRightToLefT, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Increment" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Increment increment;
				try {
					IncremenT incremenT = (IncremenT) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					increment = new Increment(incremenT, valOrRel);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(increment, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "IncremenT" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				IncremenT incremenT;
				try {
					incremenT = new IncremenT();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(incremenT, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "LastPositioN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				LastPositioN lastPositioN;
				try {
					lastPositioN = new LastPositioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(lastPositioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Letter" :
			if (components.size() != 5) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 5));
			}
			else {
				Letter letter;
				try {
					LetteR letteR = (LetteR) components.get(0);
					CcString ccString = (CcString) components.get(1);
					Direction direction = (Direction) components.get(2);
					LetterValue letterValue = (LetterValue) components.get(3);
					Position position = (Position) components.get(4);
					letter = new Letter(letteR, ccString, direction, letterValue, position);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(letter, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "LetteR" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				LetteR letteR;
				try {
					letteR = new LetteR();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(letteR, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "LetterValue" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				LetterValue letterValue;
				try {
					LetterValuE letterValuE = (LetterValuE) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					letterValue = new LetterValue(letterValuE, valOrRel);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(letterValue, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "LetterValuE" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				LetterValuE letterValuE;
				try {
					letterValuE = new LetterValuE();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(letterValuE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "NonProminentPositioN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				NonProminentPositioN nonProminentPositioN;
				try {
					nonProminentPositioN = new NonProminentPositioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(nonProminentPositioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Pattern" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Pattern pattern;
				try {
					PatterN patterN = (PatterN) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					pattern = new Pattern(patterN, valOrRel);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(pattern, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "PatterN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				PatterN patterN;
				try {
					patterN = new PatterN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(patterN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Position" :
			if (components.size() != 3) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 3));
			}
			else {
				Position position;
				try {
					PositioN positioN = (PositioN) components.get(0);
					IPositionAttribute positionAttribute = (IPositionAttribute) components.get(1);
					IValueOrValuE valueOrValuE = (IValueOrValuE) components.get(2);
					position = new Position(positioN, positionAttribute, valueOrValuE);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(position, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "PositioN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				PositioN positioN;
				try {
					positioN = new PositioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(positioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "ProminentPosition" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				ProminentPosition prominentPosition;
				try {
					ProminentPositioN prominentPositioN = (ProminentPositioN) components.get(0);
					ISpecifiedProminentPosition specifiedProminentPosition = 
							(ISpecifiedProminentPosition) components.get(1);
					prominentPosition = new ProminentPosition(prominentPositioN, specifiedProminentPosition);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(prominentPosition, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "ProminentPositioN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				ProminentPositioN prominentPositioN;
				try {
					prominentPositioN = new ProminentPositioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(prominentPositioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Relation" :
			if (components.size() != 3) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 3));
			}
			else {
				Relation relation;
				try {
					RelatioN relatioN = (RelatioN) components.get(0);
					SetSize setSize = (SetSize) components.get(1);
					IRule rule = (IRule) components.get(2);
					relation = new Relation(relatioN, setSize, rule);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(relation, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "RelatioN" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				RelatioN relatioN;
				try {
					relatioN = new RelatioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(relatioN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SecondStrinG" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				SecondStrinG secondStrinG;
				try {
					secondStrinG = new SecondStrinG();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(secondStrinG, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Sequence" :
			if (components.size() != 3) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 3));
			}
			else {
				Sequence sequence;
				try {
					SequencE sequencE = (SequencE) components.get(0);
					FirstValue firstValue = (FirstValue) components.get(1);
					Increment increment = (Increment) components.get(2);
					sequence = new Sequence(sequencE, firstValue, increment);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(sequence, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SequencE" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				SequencE sequencE;
				try {
					sequencE = new SequencE();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(sequencE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SetElement" :
			if (components.size() != 3) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 3));
			}
			else {
				SetElement setElement;
				try {
					SetElemenT setElemenT = (SetElemenT) components.get(0);
					SetSize setSize = (SetSize) components.get(1);
					ElementValue elementValue = (ElementValue) components.get(2);
					setElement = new SetElement(setElemenT, setSize, elementValue);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(setElement, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SetElemenT" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				SetElemenT setElemenT;
				try {
					setElemenT = new SetElemenT();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(setElemenT, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SetSize" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				SetSize setSize;
				try {
					SetSizE setSizE = (SetSizE) components.get(0);
					IValueOrRelation valOrRel = (IValueOrRelation) components.get(1);
					setSize = new SetSize(setSizE, valOrRel);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(setSize, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SetSizE" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				SetSizE setSizE;
				try {
					setSizE = new SetSizE();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(setSizE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		default : 
			if(nodeIndex+1 < treeDescriptions[treeIndex][pathIndex].length) {
				if (isInteger(nodeName)) {
					if (components.size() != 2) {
						throw new FileReaderException(
								getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
					}
					else {
						Value value;
						try {
							ValuE valuE = (ValuE) components.get(0);
							IValueOrValuE whichValue = (IValueOrValuE) components.get(1);
							value = new Value(valuE, whichValue);
						}
						catch (Exception e) {
							throw new FileReaderException(
									getExceptionMessage2(
											nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
						}
						putStructureIntoArray(value, treeIndex, pathIndex, nodeIndex);
					}
				}
				else {
					throw new FileReaderException("CcFileReader.buildSyntacticStructure() :  unrecognized "
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
					throw new FileReaderException(
							getExceptionMessage2(
									nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(valuE, treeIndex, pathIndex, nodeIndex);
			}
			break;	
		}
	}
	
	private String getExceptionMessage1(String nodeName, List<ISyntacticStructure> components, 
			int treeIndex, int pathIndex, int nodeIndex, int nbOfRequiredComp) {
		StringBuilder sB = new StringBuilder();
		sB.append("CcFileReader.buildSyntacticStructure() : '");
		sB.append(nodeName);
		sB.append("' number of components should be ");
		sB.append(Integer.toString(nbOfRequiredComp));
		sB.append(" instead of ");
		sB.append(Integer.toString(components.size()));
		sB.append(".");
		sB.append(System.lineSeparator());
		sB.append("Tree : ");
		sB.append(Integer.toString(treeIndex));
		sB.append(" ; Path : ");
		sB.append(Integer.toString(pathIndex));
		sB.append(" ; Node : ");
		sB.append(Integer.toString(nodeIndex));
		return sB.toString();
	}
	
	private String getExceptionMessage2(String nodeName, int treeIndex, int pathIndex, int nodeIndex, 
			String errorMessage) {
		StringBuilder sB = new StringBuilder();
		sB.append("CcFileReader.buildSyntacticStructure() : failed to build a '");
		sB.append(nodeName);
		sB.append("' syntax branch. ");
		sB.append(System.lineSeparator());
		sB.append(errorMessage);
		sB.append(System.lineSeparator());
		sB.append("Tree : ");
		sB.append(Integer.toString(treeIndex));
		sB.append(" ; Path : ");
		sB.append(Integer.toString(pathIndex));
		sB.append(" ; Node : ");
		sB.append(Integer.toString(nodeIndex));
		return sB.toString();
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
