package grammarModel.structure.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.ITreePaths;
import grammars.copycat2Strings.utils.CcFileReaderB;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.IPair;

public class SyntaxGroveTest {

	public static ISyntaxGrove grove;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Path e2 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");		
		IGenericFileReader fileReader = new CcFileReaderB();
		try {
			grove = fileReader.getSyntacticGrove(e2);
			grove.markRecursion();
		}
		catch (Exception e) {
			System.out.println("SyntaxGroveTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}
	
	@Test
	public void whenCloneRequestedThenReturned() {
		assertTrue(false);
	}
	
	@Test
	public void whenContextInputRequiredThenReturned() {
		assertTrue(false);
	}
	
	@Test
	public void whenRecursionIndexMarkingRequestedThenEffective() {
		boolean markingEffective = true;
		/*
		 * If recursion marking is effective, then no pair in the binary relation associates a symbol to itself. 
		 */
		ISyntaxBranch anyTree = grove.getListOfTrees().get(0);
		
		try {
			ITreePaths treePaths = anyTree.getTreePaths();
			//to see tree paths
			System.out.println(treePaths.toString());
		} catch (GrammarModelException e) {
			System.out.println("SyntaxGroveTest.whenRecursionIndexMarkingRequestedThenEffective() :" + System.lineSeparator()
					+ e.getMessage());
		}
		IRelationalDescription relationalDescription = anyTree.getBinaryRelation();
		for (IPair pair : relationalDescription.getBinaryRelation()) {
			if (pair.getAntecedent().equals(pair.getConsequent()))
				markingEffective = false;
		}
		assertTrue(markingEffective);
		//HERE Ensure that recursion index is done correctly.
	}

}
