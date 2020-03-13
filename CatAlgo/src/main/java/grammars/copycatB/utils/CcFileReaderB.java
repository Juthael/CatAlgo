package grammars.copycatB.utils;

import java.util.List;

import grammarModel.exceptions.FileReaderException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.impl.GenericFileReader;
import grammars.copycatB.branches.CcString;
import grammars.copycatB.branches.Cluster;
import grammars.copycatB.branches.ClusteredValue;
import grammars.copycatB.branches.Coordinate;
import grammars.copycatB.branches.Direction;
import grammars.copycatB.branches.EndPosition;
import grammars.copycatB.branches.FirstValue;
import grammars.copycatB.branches.Increment;
import grammars.copycatB.branches.Letter;
import grammars.copycatB.branches.LetterValue;
import grammars.copycatB.branches.Pattern;
import grammars.copycatB.branches.Position;
import grammars.copycatB.branches.ProminentPosition;
import grammars.copycatB.branches.Sequence;
import grammars.copycatB.branches.Size;
import grammars.copycatB.disjunctions.ICoordinateOrCoordinatE;
import grammars.copycatB.disjunctions.IDirectionValue;
import grammars.copycatB.disjunctions.IEndPositionValue;
import grammars.copycatB.disjunctions.IPositionAttribute;
import grammars.copycatB.disjunctions.IRule;
import grammars.copycatB.disjunctions.ISpecifiedProminentPosition;
import grammars.copycatB.disjunctions.IStringName;
import grammars.copycatB.disjunctions.IValueOrClusteredValue;
import grammars.copycatB.leaves.CcStrinG;
import grammars.copycatB.leaves.CentralPositioN;
import grammars.copycatB.leaves.ClusteR;
import grammars.copycatB.leaves.DirectioN;
import grammars.copycatB.leaves.EndPositioN;
import grammars.copycatB.leaves.FirstPositioN;
import grammars.copycatB.leaves.FirstStrinG;
import grammars.copycatB.leaves.FirstValuE;
import grammars.copycatB.leaves.FromLeftToRighT;
import grammars.copycatB.leaves.FromRightToLefT;
import grammars.copycatB.leaves.IncremenT;
import grammars.copycatB.leaves.LastPositioN;
import grammars.copycatB.leaves.LetteR;
import grammars.copycatB.leaves.LetterValuE;
import grammars.copycatB.leaves.NonProminentPositioN;
import grammars.copycatB.leaves.PatterN;
import grammars.copycatB.leaves.PositioN;
import grammars.copycatB.leaves.ProminentPositioN;
import grammars.copycatB.leaves.SecondStrinG;
import grammars.copycatB.leaves.SequencE;
import grammars.copycatB.leaves.SizE;
import grammars.copycatB.leaves.ValuE;

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
		case "ClusteR" :
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
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Coordinate coordinate;
				try {
					ValuE valuE = (ValuE) components.get(0);
					ICoordinateOrCoordinatE coordinatE = (ICoordinateOrCoordinatE) components.get(1);
					coordinate = new Coordinate(valuE, coordinatE);
					
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(coordinate, treeIndex, pathIndex, nodeIndex);
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
					ICoordinateOrCoordinatE coordinate = (ICoordinateOrCoordinatE) components.get(2);
					position = new Position(positioN, positionAttribute, coordinate);
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
		case "SizE" :
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
						Coordinate coordinate;
						try {
							ValuE valuE = (ValuE) components.get(0);
							ICoordinateOrCoordinatE coordinatE = (ICoordinateOrCoordinatE) components.get(1);
							coordinate = new Coordinate(valuE, coordinatE);
						}
						catch (Exception e) {
							throw new FileReaderException(
									getExceptionMessage2(
											nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
						}
						putStructureIntoArray(coordinate, treeIndex, pathIndex, nodeIndex);
					}
				}
				else if (nodeName.contains("clustered")) {
					if (components.size() != 2) {
						throw new FileReaderException(
								getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
					}
					else {
						ClusteredValue clusteredValue;
						try {
							ValuE valuE = (ValuE) components.get(0);
							Cluster cluster = (Cluster) components.get(1);
							clusteredValue = new ClusteredValue(valuE, cluster);
						}
						catch (Exception e) {
							throw new FileReaderException(
									getExceptionMessage2(
											nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
						}
						putStructureIntoArray(clusteredValue, treeIndex, pathIndex, nodeIndex);
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
