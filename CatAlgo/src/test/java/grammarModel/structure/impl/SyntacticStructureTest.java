package grammarModel.structure.impl;

import static org.junit.Assert.*;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.utils.IChains;
import grammarModel.utils.ITreePaths;
import grammars.seekWhence.utils.SwFileReader;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;

@SuppressWarnings("unused")
public class SyntacticStructureTest {

	public ISyntaxGrove grove;
	public static Path backburnDozen1;
	public static SwFileReader fileReader;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "BD1_1_12_123.txt");
		fileReader = new SwFileReader();
	}
	
	@Before
	public void setUpBeforEach() throws Exception {
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
		ITreePaths chains;
		try {
			chains = grove.getTreePaths();
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
		Set<ITreePaths> setOfChains;
		try {
			setOfChains = grove.getSetOfTreePaths();
			setOfSyntacticChainsReturned = true;
			for (ITreePaths chains : setOfChains) {
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
		ISyntaxGrove grove1 = (ISyntaxGrove) grove.clone();
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
			//printChains(posetChains.getChains());
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
		boolean implicationsReturned = true;
		ISyntaxGrove grove1 = (ISyntaxGrove) grove.clone();
		try {
			grove1.setPosetElementID();
		}
		catch (Exception e){
			implicationsReturned = false;
			System.out.println("SyntacticStructureTest.whenGetImplicationsIsCalledThenASetOfImplicationsIsReturned() : "
					+ System.lineSeparator() + "error during posetID setting." + System.lineSeparator()
					+ e.getMessage());
		}
		Set<IImplication> implications = null;
		try {
			 implications = grove1.getImplications();
		} catch (GrammarModelException e) {
			implicationsReturned = false;
			System.out.println("SyntacticStructureTest.whenGetImplicationsIsCalledThenASetOfImplicationsIsReturned() : "
					+ System.lineSeparator() + "cannot retrieve implications" + System.lineSeparator()
					+ e.getMessage());
		}
		implicationsReturned = (implications != null && !implications.isEmpty());
		assertTrue(implicationsReturned);
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
		ISyntaxGrove grove1 = (ISyntaxGrove) grove.clone();
		try {
			grove1.setPosetElementID();
		} catch (GrammarModelException e) {
			System.out.println("SyntacticStructureTest.whenRedundancyIsMarkedThenRedundantComponentsCanBeRetrieved() : "
					+ System.lineSeparator() + "cannot set Poset Element ID." + System.lineSeparator() 
					+ e.getMessage());
		}
		List<ISyntacticStructure> trees = grove1.getListOfComponents();
		/*
		for (ISyntacticStructure tree : trees) {
			try {
				System.out.println(tree.getPosetMaxChains().getChainsInASingleString());
			}
			catch (Exception e) {
				System.out.println("SyntacticStructureTest.whenRedundancyIsMarkedThenRedundantComponentsCanBeRetrieved() : "
						+ System.lineSeparator() + "cannot retrieve chains." + System.lineSeparator() 
						+ e.getMessage());
			}
		}
		*/
		try {
			grove1.markRedundancies();
		}	
		catch (Exception e) {
			redundanciesRetrieved = false;
			System.out.println("SyntacticStructureTest.whenRedundancyIsMarkedThenRedundantComponentsCanBeRetrieved() : "
					+ System.lineSeparator() + "error during redundancy marking" + System.lineSeparator() 
					+ e.getMessage());
		}
		/*
		for (ISyntacticStructure tree : trees) {
			try {
				System.out.println(tree.getPosetMaxChains().getChainsInASingleString());
			}
			catch (Exception e) {
				System.out.println("SyntacticStructureTest.whenRedundancyIsMarkedThenRedundantComponentsCanBeRetrieved() : "
						+ System.lineSeparator() + "cannot retrieve chains." + System.lineSeparator() 
						+ e.getMessage());
			}
		}
		*/			
		List<ISyntacticStructure> redundantStructures = new ArrayList<ISyntacticStructure>();
		List<ISyntacticStructure> allStructures = getAllStructures(grove1);
		for (ISyntacticStructure structure : allStructures) {
			if (structure.isRedundant()) {
				redundantStructures.add(structure);
			}
		}
		redundanciesRetrieved = !redundantStructures.isEmpty();
		assertTrue(redundanciesRetrieved);
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
