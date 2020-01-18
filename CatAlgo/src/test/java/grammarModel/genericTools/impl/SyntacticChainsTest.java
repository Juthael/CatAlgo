package grammarModel.genericTools.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.genericTools.ISyntacticChains;
import grammarModel.structure.ISyntacticGrove;
import grammars.seekWhence.utils.impl.SwFileReader;

public class SyntacticChainsTest {

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
			System.out.println("SyntacticChainsTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}

	@Test
	public void whenGetPathToLeafIDsCalledThenMapIsReturned() {
		boolean mapReturned = true;
		ISyntacticGrove groveClone = (ISyntacticGrove) grove.clone();
		try {
			Map<List<String>, Set<Long>> pathToLeafIDs = groveClone.getSyntacticChains().getPathToLeafIDs();
			if (pathToLeafIDs.isEmpty())
				mapReturned = false;
		}
		catch (Exception e) {
			mapReturned = false;
			System.out.println("SyntacticChainsTest.whenGetPathToLeafIDsCalledThenMapReturned() : " 
					+ System.lineSeparator() + "an error has occured." + e.getMessage());
		}
		assertTrue(mapReturned);
	}
	
	@Test
	public void whenGetLeafCalledWithIDThenLeafReturned() {
		boolean leafReturned = true;
		ISyntacticChains synChains = null;
		ISyntacticGrove groveClone = (ISyntacticGrove) grove.clone();
		try {
			synChains = groveClone.getSyntacticChains();
		}
		catch (Exception e) {
			System.out.println("SyntacticChainsTest.whenGetLeafCalledWithIDThenLeafReturned() : " 
		+ "cannot instantiate ISyntacticChains. " + System.lineSeparator() + e.getMessage());
		}
		Map<List<String>, Set<Long>> pathToLeafIDs = null;
		try {
			if (synChains != null) {
				pathToLeafIDs = synChains.getPathToLeafIDs();
				if (pathToLeafIDs == null || pathToLeafIDs.isEmpty())
					throw new Exception("pathToLeafIDs map is empty.");
			}
			else throw new Exception("synChains variable is null.");
		}
		catch (Exception e) {
			leafReturned = false;
			System.out.println("SyntacticChainsTest.whenGetLeafCalledWithIDThenLeafReturned() : " 
					+ System.lineSeparator() + "an error has occured" + System.lineSeparator() + e.getMessage());
		}
		if (leafReturned != false) {
			Set<Long> values = new HashSet<Long>();
			Set<Set<Long>> setsOfValues = new HashSet<Set<Long>>();
			setsOfValues.addAll(pathToLeafIDs.values());
			for (Set<Long> setOfValues : setsOfValues) {
				values.addAll(setOfValues);
			}
			for (Long value : values) {
				try {
					String leafName = synChains.getLeaf(value);
					if (leafName.isEmpty()) {
						throw new Exception();
					}
				}
				catch (Exception e) {
					leafReturned = false;
					System.out.println("SyntacticChainsTest.whenGetLeafCalledWithIDThenLeafReturned() : " 
							+ System.lineSeparator() + "cannot get a leaf name with value " + Long.toString(value) 
							+ "." + e.getMessage());
				}
			}
		}
		assertTrue(leafReturned);
	}
	
	@Test
	public void whenHasPropertyCalledThenCorrectBooleanValueReturned() {
		boolean valueReturnedIsCorrect = true;
		ISyntacticChains synChains = null;
		ISyntacticGrove groveClone = (ISyntacticGrove) grove.clone();
		try {
			synChains = groveClone.getSyntacticChains();
			if (synChains == null)
				throw new Exception("synChains variable is null.");
		}
		catch (Exception e) {
			valueReturnedIsCorrect = false;
			System.out.println("SyntacticChainsTest.whenHasPropertyCalledThenCorrectBooleanValueReturned() : " 
					+ System.lineSeparator() + "ISyntacticChains instantiation failed. " 
					+ System.lineSeparator() + e.getMessage());
		}
		if (valueReturnedIsCorrect == true) {
			try {
				valueReturnedIsCorrect = synChains.hasProperty("FirstValue");
				if (valueReturnedIsCorrect)
					valueReturnedIsCorrect = !synChains.hasProperty("false property");	
			}
			catch (Exception e) {
				valueReturnedIsCorrect = false;
				System.out.println("SyntacticChainsTest.whenHasPropertyCalledThenCorrectBooleanValueReturned() : " 
						+ System.lineSeparator() + "error while checking property. " 
						+ System.lineSeparator() + e.getMessage());
			}	
		}
		assertTrue(valueReturnedIsCorrect);
	}	

}
