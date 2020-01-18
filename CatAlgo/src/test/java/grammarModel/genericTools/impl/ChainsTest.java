package grammarModel.genericTools.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.genericTools.IChains;
import grammarModel.structure.ISyntacticGrove;
import grammars.seekWhence.utils.impl.SwFileReader;

public class ChainsTest {

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
			System.out.println("ChainsTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}

	@Test
	public void whenChainsInstantiatedThenAllElementsAreAttainableViaNext() {
		boolean allElementsInstantiable;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		IChains chains = null;
		try {
			chains =  grove.getSyntacticChains();
		}
		catch (Exception e) {
			allElementsInstantiable = false;
			System.out.println("ChainsTest. whenChainsInstantiatedThenAllElementsAreAttainableViaNext() : "
					+ System.lineSeparator() + "chains cannot be instantiated." + System.lineSeparator() 
					+ e.getMessage());
		}
		while(chains.hasNext()) {
			try {
				list1.add(chains.next());
			}
			catch (Exception e) {
				allElementsInstantiable = false;
				System.out.println("ChainsTest. whenChainsInstantiatedThenAllElementsAreAttainableViaNext() : "
						+ System.lineSeparator() + "next element cannot be returned." + System.lineSeparator() 
						+ e.getMessage());
			}
		}
		List<List<String>> listOfStringChains = grove.getListOfSyntacticStringChains();
		for (List<String> stringChain : listOfStringChains) {
			for (String element : stringChain) {
				list2.add(element);
			}
		}
		if (list1.isEmpty() || list2.isEmpty()) {
			allElementsInstantiable = false;
		}
		else allElementsInstantiable = list1.equals(list2);
		assertTrue(allElementsInstantiable);
	}

}
