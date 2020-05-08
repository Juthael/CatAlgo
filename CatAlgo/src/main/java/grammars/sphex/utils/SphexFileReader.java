package grammars.sphex.utils;

import java.util.List;

import grammarModel.exceptions.FileReaderException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.impl.GenericFileReader;
import grammars.sphex.branches.Burrow;
import grammars.sphex.branches.DragInTheBurrow;
import grammars.sphex.branches.DragPreyIn;
import grammars.sphex.branches.DropOnThreshold;
import grammars.sphex.branches.Grab;
import grammars.sphex.branches.Inspect;
import grammars.sphex.branches.InspectionStatus;
import grammars.sphex.branches.Position;
import grammars.sphex.branches.Predate;
import grammars.sphex.branches.Prey;
import grammars.sphex.branches.SupplyStatus;
import grammars.sphex.branches.SupplyWithAPrey;
import grammars.sphex.branches.TimePosition;
import grammars.sphex.disjunctions.IDoWithBurrow;
import grammars.sphex.disjunctions.IDoWithPrey;
import grammars.sphex.disjunctions.IInspectionStatus;
import grammars.sphex.disjunctions.IPosition;
import grammars.sphex.disjunctions.ISupplyStatus;
import grammars.sphex.leaves.BurroW;
import grammars.sphex.leaves.DragInTheBurroW;
import grammars.sphex.leaves.DragPreyIN;
import grammars.sphex.leaves.DropOnThresholD;
import grammars.sphex.leaves.GraB;
import grammars.sphex.leaves.GrabbeD;
import grammars.sphex.leaves.InBurroW;
import grammars.sphex.leaves.InspecT;
import grammars.sphex.leaves.InspecteD;
import grammars.sphex.leaves.InspectionStatuS;
import grammars.sphex.leaves.NotInspecteD;
import grammars.sphex.leaves.NotSupplieD;
import grammars.sphex.leaves.PositioN;
import grammars.sphex.leaves.PreY;
import grammars.sphex.leaves.PredatE;
import grammars.sphex.leaves.ProvideFoodForTheGrubS;
import grammars.sphex.leaves.RandomPlacE;
import grammars.sphex.leaves.SteP;
import grammars.sphex.leaves.SupplieD;
import grammars.sphex.leaves.SupplyStatuS;
import grammars.sphex.leaves.SupplyWithAPreY;
import grammars.sphex.leaves.ThresholD;
import grammars.sphex.leaves.TimePositioN;

public class SphexFileReader extends GenericFileReader implements IGenericFileReader {

	public SphexFileReader() {
		super.ctxtName = "Sphex";
	}

	@Override
	protected void castComponentsAndInstantiateStructure(String nodeName, List<ISyntacticStructure> components,
			int treeIndex, int pathIndex, int nodeIndex) throws FileReaderException {
		switch (nodeName) {
		case "Burrow" :
			if (components.size() != 5) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Burrow burrow;
				try {
					BurroW burroW = (BurroW) components.get(0);
					InspectionStatus inspectionStatus = (InspectionStatus) components.get(1);
					SupplyStatus supplyStatus = (SupplyStatus) components.get(2);
					IDoWithBurrow doWithBurrow = (IDoWithBurrow) components.get(3);
					TimePosition timePosition = (TimePosition) components.get(4);
					burrow = new Burrow(burroW, inspectionStatus, supplyStatus, doWithBurrow, timePosition);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(burrow, treeIndex, pathIndex, nodeIndex);
			}			
			break;
		case "DragInTheBurrow" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				DragInTheBurrow dragInTheBurrow;
				try {
					DragInTheBurroW dragInTheBurroW = (DragInTheBurroW) components.get(0);
					Predate predate = (Predate) components.get(1);
					dragInTheBurrow = new DragInTheBurrow(dragInTheBurroW, predate);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(dragInTheBurrow, treeIndex, pathIndex, nodeIndex);
			}	
			break;
		case "DragPreyIn" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				DragPreyIn dragPreyIn;
				try {
					DragPreyIN dragPreyIN = (DragPreyIN) components.get(0);
					SupplyWithAPrey supplyWithAPrey = (SupplyWithAPrey) components.get(1);
					dragPreyIn = new DragPreyIn(dragPreyIN, supplyWithAPrey);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(dragPreyIn, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "DropOnThreshold" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				DropOnThreshold dropOnThreshold;
				try {
					DropOnThresholD dropOnThresholD = (DropOnThresholD) components.get(0);
					Predate predate = (Predate) components.get(1);
					dropOnThreshold = new DropOnThreshold(dropOnThresholD, predate);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(dropOnThreshold, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Grab" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Grab grab;
				try {
					GraB graB = (GraB) components.get(0);
					Predate predate = (Predate) components.get(1);
					grab = new Grab(graB, predate);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(grab, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Inspect" : 
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Inspect inspect;
				try {
					InspecT inspecT = (InspecT) components.get(0);
					SupplyWithAPrey supplyWithAPrey = (SupplyWithAPrey) components.get(1);
					inspect = new Inspect(inspecT, supplyWithAPrey);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(inspect, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "InspectionStatus" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				InspectionStatus inspectionStatus;
				try {
					InspectionStatuS inspectionStatuS = (InspectionStatuS) components.get(0);
					IInspectionStatus iInspectionStatus = (IInspectionStatus) components.get(1);
					inspectionStatus = new InspectionStatus(inspectionStatuS, iInspectionStatus);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(inspectionStatus, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Predate" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Predate predate;
				try {
					PredatE predatE = (PredatE) components.get(0);
					ProvideFoodForTheGrubS provideFooD = (ProvideFoodForTheGrubS) components.get(1);
					predate = new Predate(predatE, provideFooD);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(predate, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Prey" :
			if (components.size() != 4) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Prey prey;
				try {
					PreY preY = (PreY) components.get(0);
					Position position = (Position) components.get(1);
					IDoWithPrey doWithPrey = (IDoWithPrey) components.get(2);
					TimePosition timePosition = (TimePosition) components.get(3);
					prey = new Prey(preY, position, doWithPrey, timePosition);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(prey, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "Position" : 
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				Position position;
				try {
					PositioN positioN = (PositioN) components.get(0);
					IPosition iPosition = (IPosition) components.get(1);
					position = new Position(positioN, iPosition);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(position, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SupplyStatus" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				SupplyStatus supplyStatus;
				try {
					SupplyStatuS supplyStatuS = (SupplyStatuS) components.get(0);
					ISupplyStatus iSupplyStatus = (ISupplyStatus) components.get(1);
					supplyStatus = new SupplyStatus(supplyStatuS, iSupplyStatus);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(supplyStatus, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "SupplyWithAPrey" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				SupplyWithAPrey supplyWithAPrey;
				try {
					SupplyWithAPreY supplyWithAPreY = (SupplyWithAPreY) components.get(0);
					ProvideFoodForTheGrubS provideFood = (ProvideFoodForTheGrubS) components.get(1);
					supplyWithAPrey = new SupplyWithAPrey(supplyWithAPreY, provideFood);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(supplyWithAPrey, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "TimePosition" :
			if (components.size() != 2) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 2));
			}
			else {
				TimePosition timePosition;
				try {
					TimePositioN timePositioN = (TimePositioN) components.get(0);
					SteP steP = (SteP) components.get(1);
					timePosition = new TimePosition(timePositioN, steP);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(timePosition, treeIndex, pathIndex, nodeIndex);
			}
			break;			
		case "burrow" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				BurroW burroW;
				try {
					burroW = new BurroW();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(burroW, treeIndex, pathIndex, nodeIndex);
			}			
			break;
		case "dragInTheBurrow" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				DragInTheBurroW dragInTheBurroW;
				try {
					dragInTheBurroW = new DragInTheBurroW();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(dragInTheBurroW, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "dragPreyIn" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				DragPreyIN dragPreyIN;;
				try {
					dragPreyIN = new DragPreyIN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(dragPreyIN, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "dropOnThreshold" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				DropOnThresholD dropOnThresholD;
				try {
					dropOnThresholD = new DropOnThresholD();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(dropOnThresholD, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "grab" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				GraB graB;
				try {
					graB = new GraB();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(graB, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "grabbed" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				GrabbeD grabbeD;
				try {
					grabbeD = new GrabbeD();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(grabbeD, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "inBurrow" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				InBurroW inBurroW;
				try {
					inBurroW = new InBurroW();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(inBurroW, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "inspect" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				InspecT inspecT;
				try {
					inspecT = new InspecT();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(inspecT, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "inspected" : 
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				InspecteD inspecteD;
				try {
					inspecteD = new InspecteD();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(inspecteD, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "inspectionStatus" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				InspectionStatuS inspectionStatuS;
				try {
					inspectionStatuS = new InspectionStatuS();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(inspectionStatuS, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "notInspected" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				NotInspecteD notInspecteD;
				try {
					notInspecteD = new NotInspecteD();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(notInspecteD, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "notSupplied" : 
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				NotSupplieD notSupplieD;
				try {
					notSupplieD = new NotSupplieD();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(notSupplieD, treeIndex, pathIndex, nodeIndex);
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
		case "predate" : 
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				PredatE predatE;
				try {
					predatE = new PredatE();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(predatE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "prey" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				PreY preY;
				try {
					preY = new PreY();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(preY, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "provideFoodForTheGrubs" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				ProvideFoodForTheGrubS provideFood;
				try {
					provideFood = new ProvideFoodForTheGrubS();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(provideFood, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "randomPlace" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				RandomPlacE randomPlacE;
				try {
					randomPlacE = new RandomPlacE();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(randomPlacE, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "supplied" : 
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				SupplieD supplieD;
				try {
					supplieD = new SupplieD();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(supplieD, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "supplyStatus" : 
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				SupplyStatuS supplyStatuS;
				try {
					supplyStatuS = new SupplyStatuS();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(supplyStatuS, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "supplyWithAPrey" : 
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				SupplyWithAPreY supplyWithAPreY;
				try {
					supplyWithAPreY = new SupplyWithAPreY();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(supplyWithAPreY, treeIndex, pathIndex, nodeIndex);
			}
			break;
		case "timePosition" :
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				TimePositioN timePositioN;
				try {
					timePositioN = new TimePositioN();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(timePositioN, treeIndex, pathIndex, nodeIndex);
			}
			break;			
		case "threshold" : 
			if (!components.isEmpty()) {
				throw new FileReaderException(
						getExceptionMessage1(nodeName, components, treeIndex, pathIndex, nodeIndex, 0));
			}
			else {
				ThresholD thresholD;
				try {
					thresholD = new ThresholD();
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(thresholD, treeIndex, pathIndex, nodeIndex);
			}
			break;
		default : 
			if (nodeName.contains("step") && components.isEmpty()) {
				SteP steP;
				try {
					steP = new SteP(nodeName);
				}
				catch (Exception e) {
					throw new FileReaderException(
							getExceptionMessage2(nodeName, treeIndex, pathIndex, nodeIndex, e.getMessage()));
				}
				putStructureIntoArray(steP, treeIndex, pathIndex, nodeIndex);
			}
			else throw new FileReaderException("Unknown object " + nodeName + ". " 
					+ System.lineSeparator());
			break;
		}
	}
	
	private String getExceptionMessage1(String nodeName, List<ISyntacticStructure> components, 
			int treeIndex, int pathIndex, int nodeIndex, int nbOfRequiredComp) {
		StringBuilder sB = new StringBuilder();
		sB.append("SphexFileReader.buildSyntacticStructure() : '");
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
		sB.append("SphexFileReader.buildSyntacticStructure() : failed to build a '");
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

}
