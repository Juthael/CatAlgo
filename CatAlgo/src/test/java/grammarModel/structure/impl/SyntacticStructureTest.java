package grammarModel.structure.impl;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.genericTools.IPosetMaxChains;
import grammarModel.genericTools.ISyntacticChains;
import grammarModel.structure.ISyntacticGrove;
import grammarModel.structure.ISyntacticStructure;
import grammars.seekWhence.utils.impl.SwFileReader;

public class SyntacticStructureTest {

	public static ISyntacticGrove grove;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Path backburnDozen1 = Paths.get("D:","GoogleDrive","0_Doctorat","Modèle","grammars",
				"tests","seekWhence","BlackburnDozen","BlackburnDozen1","BD1_1_12_123.txt");
		SwFileReader fileReader = new SwFileReader();
		try {
			grove = fileReader.getSyntacticGrove(backburnDozen1);
			// printChains(grove.getListOfSyntacticStringChains());
		}
		catch (Exception e) {
			System.out.println("SyntacticStructureTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}

	@Test
	public void whenGetSyntacticChainsIsCalledThenSyntacticChainsAreReturned() {
		boolean syntacticChainsReturned;
		@SuppressWarnings("unused")
		ISyntacticChains chains;
		try {
			chains = grove.getSyntacticChains();
			syntacticChainsReturned = true;
			// printChains(chains);
		}
		catch (Exception e) {
			syntacticChainsReturned = false;
			System.out.println("SyntacticStructureTest.whenGetSyntacticChainsIsCalledThenSyntacticChainsAreReturned() : "
					+ System.lineSeparator() + e.getMessage());
		}
		assertTrue(syntacticChainsReturned);
	}
	
	@Test
	public void whenGetSetOfSyntacticChainsIsCalledThenSetOfSyntacticChainsIsReturned() {
		boolean setOfSyntacticChainsReturned;
		Set<ISyntacticChains> setOfChains;
		try {
			setOfChains = grove.getSetOfSyntacticChains();
			setOfSyntacticChainsReturned = true;
			for (@SuppressWarnings("unused") ISyntacticChains chains : setOfChains) {
				// printChains(chains);
			}
		}
		catch (Exception e) {
			setOfSyntacticChainsReturned = false;
			System.out.println("SyntacticStructureTest."
					+ "whenGetSetOfSyntacticChainsIsCalledThenSetOfSyntacticChainsIsReturned() : " 
					+ System.lineSeparator() + e.getMessage());
		}
		assertTrue(setOfSyntacticChainsReturned);
	}
	
	@Test
	public void whenGetPosetMaxChainsIsCalledThenPosetMaxChainsAreReturned() {
		boolean posetChainsReturned;
		ISyntacticGrove grove1 = (ISyntacticGrove) grove.clone();
		try {
			grove1.setPosetElementID();
		}
		catch (Exception e){
			posetChainsReturned = false;
			System.out.println("SyntacticStructureTest.whenGetPosetMaxChainsIsCalledThenPosetMaxChainsAreReturned() : "
					+ System.lineSeparator() + "error during posetID setting." + System.lineSeparator()
					+ e.getMessage());
		}
		IPosetMaxChains posetChains;
		try {
			posetChains = grove1.getPosetMaxChains();
			posetChainsReturned = (!posetChains.getChains().isEmpty());
			// printChains(posetChains);
		}
		catch (Exception e) {
			posetChainsReturned = false;
			System.out.println("SyntacticStructureTest.whenGetPosetMaxChainsIsCalledThenPosetMaxChainsAreReturned() : "
					+ System.lineSeparator() + "error during posetID retrieval." + System.lineSeparator()
					+ e.getMessage());
		}
		assertTrue(posetChainsReturned);
	}	
	
	@Test
	public void whenGetImplicationsIsCalledThenASetOfImplicationsIsReturned() {
		fail("Not yet implemented");
	}	
	
	@Test
	public void whenHasThisPropertyIsCalledThenTheCorrectBooleanValueIsReturned() {
		boolean correctValueReturned = grove.hasThisProperty("ArithSeq");
		if (correctValueReturned)
			correctValueReturned = !grove.hasThisProperty("Symmetry");
		assertTrue(correctValueReturned);
	}
	
	@Test
	public void whenRedundancyIsMarkedThenRedundantComponentsCanBeRetrieved() {
		boolean redundanciesRetrieved;
		ISyntacticGrove grove1 = (ISyntacticGrove) grove.clone();
		try {
			grove1.markRedundancies();
		}
		catch (Exception e) {
			redundanciesRetrieved = false;
			System.out.println("SyntacticGroveTest.whenRedundancyIsMarkedThenRedundantComponentsCanBeRetrieved() : "
					+ System.lineSeparator() + "error during redundancy marking" + System.lineSeparator() 
					+ e.getMessage());
		}
		List<ISyntacticStructure> redundantStructures = new ArrayList<ISyntacticStructure>();
		List<ISyntacticStructure> allStructures = getAllStructures(grove1);
		for (ISyntacticStructure structure : allStructures) {
			if (structure.isRedundant()) {
				redundantStructures.add(structure);
			}
		}
		for (ISyntacticStructure redStructure : redundantStructures) {
			try {
				printChains(redStructure.getSyntacticChains());
			}
			catch (Exception e) {
				redundanciesRetrieved = false;
				System.out.println("SyntacticGroveTest.whenRedundancyIsMarkedThenRedundantComponentsCanBeRetrieved() : "
						+ System.lineSeparator() + "error during syntactic chains generation" + System.lineSeparator() 
						+ e.getMessage());
			}
		}
		redundanciesRetrieved = !redundantStructures.isEmpty();
		assertTrue(redundanciesRetrieved);
	}		
	
	@Test
	public void whenClonedThenASyntacticStructureIsReturned() {
		boolean structureReturned;
		@SuppressWarnings("unused")
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
	
	private static void printChains(ISyntacticChains chains) {
		// printChains(chains.getChains());
	}
	
	@SuppressWarnings("unused")
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