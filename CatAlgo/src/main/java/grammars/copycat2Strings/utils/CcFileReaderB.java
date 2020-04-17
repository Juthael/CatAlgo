package grammars.copycat2Strings.utils;

import java.util.List;

import grammarModel.defaultRules.branches.Cluster;
import grammarModel.defaultRules.branches.ClusteredValue;
import grammarModel.defaultRules.branches.CoordSubValue;
import grammarModel.defaultRules.branches.Coordinate;
import grammarModel.defaultRules.branches.Size;
import grammarModel.defaultRules.disjunctions.IRule;
import grammarModel.defaultRules.disjunctions.IValuEOrCoordSubValue;
import grammarModel.defaultRules.disjunctions.IValueOrClusteredValue;
import grammarModel.defaultRules.leaves.ClusteR;
import grammarModel.defaultRules.leaves.CoordinatE;
import grammarModel.defaultRules.leaves.SizE;
import grammarModel.defaultRules.leaves.ValuE;
import grammarModel.exceptions.FileReaderException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.impl.GenericFileReader;
import grammars.copycat2Strings.branches.CcString;
import grammars.copycat2Strings.branches.Direction;
import grammars.copycat2Strings.branches.EndPosition;
import grammars.copycat2Strings.branches.FirstValue;
import grammars.copycat2Strings.branches.Increment;
import grammars.copycat2Strings.branches.Letter;
import grammars.copycat2Strings.branches.LetterValue;
import grammars.copycat2Strings.branches.Pattern;
import grammars.copycat2Strings.branches.Position;
import grammars.copycat2Strings.branches.ProminentPosition;
import grammars.copycat2Strings.branches.Sequence;
import grammars.copycat2Strings.disjunctions.IDirectionValue;
import grammars.copycat2Strings.disjunctions.IEndPositionValue;
import grammars.copycat2Strings.disjunctions.IPositionAttribute;
import grammars.copycat2Strings.disjunctions.ISpecifiedProminentPosition;
import grammars.copycat2Strings.disjunctions.IStringName;
import grammars.copycat2Strings.leaves.CcStrinG;
import grammars.copycat2Strings.leaves.CentralPositioN;
import grammars.copycat2Strings.leaves.DirectioN;
import grammars.copycat2Strings.leaves.EndPositioN;
import grammars.copycat2Strings.leaves.FirstPositioN;
import grammars.copycat2Strings.leaves.FirstStrinG;
import grammars.copycat2Strings.leaves.FirstValuE;
import grammars.copycat2Strings.leaves.FromLeftToRighT;
import grammars.copycat2Strings.leaves.FromRightToLefT;
import grammars.copycat2Strings.leaves.IncremenT;
import grammars.copycat2Strings.leaves.LastPositioN;
import grammars.copycat2Strings.leaves.LetteR;
import grammars.copycat2Strings.leaves.LetterValuE;
import grammars.copycat2Strings.leaves.NonProminentPositioN;
import grammars.copycat2Strings.leaves.PatterN;
import grammars.copycat2Strings.leaves.PositioN;
import grammars.copycat2Strings.leaves.ProminentPositioN;
import grammars.copycat2Strings.leaves.SecondStrinG;
import grammars.copycat2Strings.leaves.SequencE;

public class CcFileReaderB extends GenericFileReader implements IGenericFileReader {

	public CcFileReaderB() {
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
		case "ccString" :
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
		case "centralPosition" :
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
		case "Cluster" :
			if (components.size() != 3) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				Cluster cluster;
				try {
					ClusteR clusteR = (ClusteR) components.get(0);
					Size size = (Size) components.get(1);
					IRule rule = (IRule) components.get(2);
					cluster = new Cluster(clusteR, size, rule);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(cluster, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "cluster" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				ClusteR clusteR;
				try {
					clusteR = new ClusteR();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(clusteR, treeIndex, pathIndex, nodeIndex);
			}
			break;	
		case "Coordinate" :
			if (components.size() == 2) {
				Coordinate coordinate;
				try {
					CoordinatE coordinatE = (CoordinatE) components.get(0);
					IValuEOrCoordSubValue valuE = (IValuEOrCoordSubValue) components.get(1);
					coordinate = new Coordinate(coordinatE, valuE);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(coordinate, treeIndex, pathIndex, nodeIndex);
			}
			else {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			break;	
		case "coordinate" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				CoordinatE coordinatE;
				try {
					coordinatE = new CoordinatE();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(coordinatE, treeIndex, pathIndex, nodeIndex);
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
		case "direction" :
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
		case "endPosition" :
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
		case "firstPosition" :
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
		case "firstString" :
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
					IValueOrClusteredValue value = (IValueOrClusteredValue) components.get(1);
					firstValue = new FirstValue(firstValuE, value);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(firstValue, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "firstValue" :
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
		case "fromLeftToRight" :
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
		case "fromRightToLeft" :
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
					IValueOrClusteredValue value = (IValueOrClusteredValue) components.get(1);
					increment = new Increment(incremenT, value);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(increment, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "increment" :
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
		case "lastPosition" :
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
		case "letter" :
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
					IValueOrClusteredValue value = (IValueOrClusteredValue) components.get(1);
					letterValue = new LetterValue(letterValuE, value);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(letterValue, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "letterValue" :
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
		case "nonProminentPosition" :
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
					IValueOrClusteredValue value = (IValueOrClusteredValue) components.get(1);
					pattern = new Pattern(patterN, value);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(pattern, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "pattern" :
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
					Coordinate coordinate = (Coordinate) components.get(2);
					position = new Position(positioN, positionAttribute, coordinate);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(position, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "position" :
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
		case "prominentPosition" :
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
		case "secondString" :
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
		case "sequence" :
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
		case "Size" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Size size;
				try {
					SizE sizE = (SizE) components.get(0);
					IValueOrClusteredValue value = (IValueOrClusteredValue) components.get(1);
					size = new Size(sizE, value);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(size, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "size" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				SizE sizE;
				try {
					sizE = new SizE();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(sizE, treeIndex, pathIndex, nodeIndex);
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
						try {
							ValuE valuE = (ValuE) components.get(0);
							Object obj = (Object) components.get(1);
							if (obj instanceof grammarModel.defaultRules.branches.Cluster) {
								ClusteredValue clusteredValue;
								Cluster cluster = (Cluster) obj;
								clusteredValue = new ClusteredValue(valuE, cluster);
								putStructureIntoArray(clusteredValue, treeIndex, pathIndex, nodeIndex);
							}
							else if (obj instanceof grammarModel.defaultRules.branches.Coordinate) {
								CoordSubValue coordSubValue;
								Coordinate coordinate = (Coordinate) obj;
								coordSubValue = new CoordSubValue(valuE, coordinate);
								putStructureIntoArray(coordSubValue, treeIndex, pathIndex, nodeIndex);
							}
							else throw new FileReaderException("Unknown object " + obj.toString() + ". " 
									+ System.lineSeparator());
						}
						catch (Exception e) {
							throw new FileReaderException(
									getExceptionMessage2(
											nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
						}
						
					}
				}
				else {
					throw new FileReaderException("CcFileReader.buildSyntacticStructure() :  unrecognized "
							+ "non-terminal node named '" + nodeName + "'." 
							+ System.lineSeparator()
							+ "Tree : " + Integer.toString(treeIndex) + " ; Path : " + Integer.toString(pathIndex) 
							+ " ; Node : " + Integer.toString(nodeIndex) + System.lineSeparator());					
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
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),10) < 0) return false;
	    }
	    return true;
	}	

}
