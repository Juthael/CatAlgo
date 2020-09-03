package grammarModel.structure.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.ITreePaths;
import grammars.copycat2Strings.utils.CcFileReaderB;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;

@SuppressWarnings("unused")
public class SyntacticStructureTest {

	public ISyntaxGrove grove;
	public static Path e2;
	public static IGenericFileReader fileReader;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		e2 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
		fileReader = new CcFileReaderB();
	}
	
	@Before
	public void setUpBeforEach() throws Exception {
		try {
			grove = fileReader.getSyntacticGrove(e2);
			// printChains(grove.getListOfSyntacticStringChains());
		}
		catch (Exception e) {
			System.out.println("SyntacticStructureTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}	
	
	@Test
	public void whenClonedThenASyntacticStructureIsReturned() {
		boolean structureReturned;
		ISyntacticStructure clone;
		try {
			clone = grove.clone();
			structureReturned = true;
		}
		catch (Exception e) {
			structureReturned = false;
			System.out.println("SyntacticStructureTest."
					+ "whenClonedThenASyntacticStructureIsReturned() : " + System.lineSeparator()
					+ e.getMessage());
		}
		assertTrue(structureReturned);
	}	
	
	@Test
	public void whenRecursionIndexCalledThenExpectedIndexReturned() throws GrammarModelException {
		/*
		ValuE valuE = new ValuE("1");
		CoordinatE coordinatE = new CoordinatE(); 
		Coordinate coordinate = new Coordinate(coordinatE, valuE);
		ValuE superValuE = new ValuE("2");
		CoordinatE superCoordinatE = new CoordinatE();
		Coordinate superCoordinate = new Coordinate(superCoordinatE, superValuE);
		ValuE superSuperValuE = new ValuE("3");
		CoordinatE superSuperCoordinatE = new CoordinatE();
		Coordinate superSuperCoordinate = new Coordinate(superSuperCoordinatE, superSuperValuE);
		superCoordinate = new SubCoordinate(superCoordinate, superSuperCoordinate);
		coordinate = new SubCoordinate(coordinate, superCoordinate);
		
		System.out.println(coordinate.getTreePaths().getChainsInASingleString());
		
		coordinate.setRecursionIndex();
		
		assertTrue(coordinate.getRecursionIndex() == 2);
		*/
		assertTrue(false);
	}
	
	@Test
	public void whenRecursionMarkingCalledThenExpectedMarksAppended() throws GrammarModelException {
		/*
		ValuE valuE = new ValuE("1");
		CoordinatE coordinatE = new CoordinatE(); 
		Coordinate coordinate = new Coordinate(coordinatE, valuE);
		ValuE superValuE = new ValuE("2");
		CoordinatE superCoordinatE = new CoordinatE();
		Coordinate superCoordinate = new Coordinate(superCoordinatE, superValuE);
		ValuE superSuperValuE = new ValuE("3");
		CoordinatE superSuperCoordinatE = new CoordinatE();
		Coordinate superSuperCoordinate = new Coordinate(superSuperCoordinatE, superSuperValuE);
		superCoordinate = new SubCoordinate(superCoordinate, superSuperCoordinate);
		coordinate = new SubCoordinate(coordinate, superCoordinate);
		/*
		System.out.println(coordinate.getTreePaths().getChainsInASingleString() + System.lineSeparator());
		*/
		/*
		List<ISyntacticStructure> listOfComponents = new ArrayList<ISyntacticStructure>();
		listOfComponents.add(coordinate);
		String name = "test";
		ISyntaxGrove grove = new SyntaxGrove(name, listOfComponents);
		grove.markRecursion();
		grove.setPosetElementID();
		*/
		/*
		System.out.println(grove.getPosetMaxChains().getChainsInASingleString());
		*/
		/*
		assertTrue(coordinatE.getPosetElementName().equals("coordinate##") 
				&& superCoordinatE.getPosetElementName().equals("coordinate#"));
		*/
		assertTrue(false);
	}	
	
	private static void printChains(ITreePaths chains) {
		printChains(chains.getChains());
	}
	
	private static void printChains(List<List<String>> paths) {
		StringBuilder sB = new StringBuilder();
		for (List<String> path : paths) {
			for (int i = 0 ; i < path.size() ; i++) {
				sB.append(path.get(i));
				if (i < path.size() - 1)
					sB.append("/");
			}
			sB.append(System.lineSeparator());
		}
		System.out.println(sB.toString());
	}	
	
	private static List<ISyntacticStructure> getAllStructures(ISyntacticStructure currentStructure) {
		List<ISyntacticStructure> allStructures = new ArrayList<ISyntacticStructure>();
		allStructures.add(currentStructure);
		for (ISyntacticStructure component : currentStructure.getListOfComponents()) {
			allStructures.add(component);
			allStructures.addAll(getAllStructures(component));
		}
		return allStructures;
	}	

}
