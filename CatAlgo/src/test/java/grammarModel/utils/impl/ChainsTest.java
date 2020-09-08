package grammarModel.utils.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IChains;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.ITreePaths;
import grammars.copycat2Strings.utils.CcFileReaderB;

public class ChainsTest {

	public static ISyntaxGrove grove;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Path e2 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
		IGenericFileReader fileReader = new CcFileReaderB();
		try {
			grove = fileReader.getSyntacticGrove(e2);
			// printChains(grove.getListOfSyntacticStringChains());
		}
		catch (Exception e) {
			System.out.println("ChainsTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}

	@Test
	public void whenChainsInstantiatedThenAllElementsAreAttainableViaNext() {
		boolean allElementsAttainable;
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		IChains chains = null;
		try {
			chains =  grove.getListOfTrees().get(0).getTreePaths();
		}
		catch (Exception e) {
			allElementsAttainable = false;
			System.out.println("ChainsTest.whenChainsInstantiatedThenAllElementsAreAttainableViaNext() : "
					+ System.lineSeparator() + "chains cannot be instantiated." + System.lineSeparator() 
					+ e.getMessage());
		}
		while(chains.hasNext()) {
			try {
				list1.add(chains.next());
			}
			catch (Exception e) {
				allElementsAttainable = false;
				System.out.println("ChainsTest.whenChainsInstantiatedThenAllElementsAreAttainableViaNext() : "
						+ System.lineSeparator() + "next element cannot be returned." + System.lineSeparator() 
						+ e.getMessage());
			}
		}
		List<List<String>> listOfStringChains = grove.getListOfTrees().get(0).getPathsAsListsOfStrings();
		for (List<String> stringChain : listOfStringChains) {
			for (String element : stringChain) {
				list2.add(element);
			}
		}
		if (list1.isEmpty() || list2.isEmpty()) {
			allElementsAttainable = false;
		}
		else allElementsAttainable = list1.equals(list2);
		assertTrue(allElementsAttainable);
	}
	
	@Test
	public void whenIdenticalChainsAreComparedThenEqual() {
		ISyntaxBranch branch = grove.getListOfTrees().get(0);
		ISyntaxBranch branchClone = (ISyntaxBranch) branch.clone();
		boolean equals = true;
		ITreePaths chains1 = null;
		ITreePaths chains2 = null;
		try {
			chains1 = branch.getTreePaths();
			chains2 = branchClone.getTreePaths();
			if (chains1 == null || chains2 == null)
				throw new Exception("one or both chains variable is null");
		}
		catch (Exception e){
			equals = false;
			System.out.println("ChainsTest.whenIdenticalChainsAreComparedThenEqualsReturnsTrue() : "
					+ System.lineSeparator() + "chains cannot be instantiated." + System.lineSeparator() 
					+ e.getMessage());
		}
		if (equals) {
			equals = chains1.equals(chains2);
		}
	}	

}
