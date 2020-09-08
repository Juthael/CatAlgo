package grammarModel.structure.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.ITreePaths;
import grammars.copycat2Strings.utils.CcFileReaderB;

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
	public void whenPathsRequestedThenReturned() {
		boolean pathsReturned = true;
		if (grove.getListOfTrees().isEmpty()) {
			pathsReturned = false;
			System.out.println("SyntacticStructureTest.whenPathsRequestedThenReturned() : empty grove.");
		}
		for (ISyntaxBranch tree : grove.getListOfTrees()) {
			try {
				ITreePaths paths = tree.getTreePaths();
				if (paths.hasNext() == false) {
					pathsReturned = false;
					System.out.println("SyntacticStructureTest.whenPathsRequestedThenReturned() : empty path.");
				}
				else {
					//To see paths : 
					//System.out.println(paths.toString());
				}
			} catch (GrammarModelException e) {
				System.out.println("SyntacticStructureTest.whenPathsRequestedThenReturned() : path request has failed.");
				pathsReturned = false;
			}
		}
		assertTrue(pathsReturned);
	}

}
