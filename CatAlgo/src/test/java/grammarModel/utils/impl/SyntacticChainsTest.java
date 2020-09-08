package grammarModel.utils.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.ITreePaths;
import grammars.copycat2Strings.utils.CcFileReaderB;

public class SyntacticChainsTest {

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
			System.out.println("SyntacticChainsTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}

	@Test
	public void whenGetPathToLeafIDsCalledThenMapIsReturned() {
		boolean mapReturned = true;

		try {
			Map<List<String>, List<Long>> pathToLeafIDs = grove.getListOfTrees().get(0).getTreePaths().getPathToLeafIDs();
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
	public void whenLeafRequestedWithIDThenReturned() {
		boolean leafReturned = true;
		ITreePaths treePaths = null;
		try {
			treePaths = grove.getListOfTrees().get(0).getTreePaths();
		}
		catch (Exception e) {
			System.out.println("SyntacticChainsTest.whenLeafRequestedWithIDThenReturned() : " 
		+ "cannot instantiate ITreePaths. " + System.lineSeparator() + e.getMessage());
		}
		Map<List<String>, List<Long>> pathToLeafIDs = null;
		try {
			if (treePaths != null) {
				pathToLeafIDs = treePaths.getPathToLeafIDs();
				if (pathToLeafIDs == null || pathToLeafIDs.isEmpty())
					throw new Exception("pathToLeafIDs map is null or empty.");
			}
			else throw new Exception("treePaths variable is null.");
		}
		catch (Exception e) {
			leafReturned = false;
			System.out.println("SyntacticChainsTest.whenLeafRequestedWithIDThenReturned() : " 
					+ System.lineSeparator() + "an error has occured" + System.lineSeparator() + e.getMessage());
		}
		if (leafReturned != false) {
			Set<Long> iDs = new HashSet<Long>();
			Set<List<Long>> setsOfIDs = new HashSet<List<Long>>();
			setsOfIDs.addAll(pathToLeafIDs.values());
			for (List<Long> setOfIDs : setsOfIDs) {
				iDs.addAll(setOfIDs);
			}
			for (Long iD : iDs) {
				try {
					String leafName = treePaths.getLeaf(iD);
					if (leafName.isEmpty()) {
						throw new Exception();
					}
				}
				catch (Exception e) {
					leafReturned = false;
					System.out.println("SyntacticChainsTest.whenLeafRequestedWithIDThenReturned() : " 
							+ System.lineSeparator() + "cannot get a leaf name with value " + Long.toString(iD) 
							+ "." + e.getMessage());
				}
			}
		}
		assertTrue(leafReturned);
	}

}
