package grammarModel.structure.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.ITreePaths;
import grammars.copycat2Strings.utils.CcFileReaderB;
import representation.dataFormats.IPair;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.ITotalOrder;
import representation.inputOutput.IContextInput;

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
		ISyntaxGrove clone = grove.clone();
		assertTrue(clone != grove);
	}
	
	@Test
	public void whenContextInputRequiredThenReturned() {
		boolean contextInputReturned = true;
		@SuppressWarnings("unused")
		IContextInput contextInput = null;
		try {
			contextInput = grove.getContextInput();
		} catch (GrammarModelException e) {
			contextInputReturned = false;
			System.out.println("SyntaxGroveTest.whenContextInputRequiredThenReturned() : " + System.lineSeparator()
					+ e.getMessage());
		}
		assertTrue(contextInputReturned);
	}
	
	@Test
	public void whenRecursionIndexMarkingRequestedThenEffective() {
		boolean markingEffective = true;
		/*
		 * If recursion marking is effective, then no pair in the binary relation associates a symbol to itself. 
		 */
		ISyntaxBranch anyTree = grove.getListOfTrees().get(0);
		
		try {
			@SuppressWarnings("unused")
			ITreePaths treePaths = anyTree.getTreePaths();
			//to see tree paths
			//System.out.println(treePaths.toString());
		} catch (GrammarModelException e) {
			System.out.println("SyntaxGroveTest.whenRecursionIndexMarkingRequestedThenEffective() :" + System.lineSeparator()
					+ e.getMessage());
		}
		IRelationalDescription relationalDescription = null;
		try {
			relationalDescription = anyTree.getRelationalDescription();
		} catch (GrammarModelException e) {
			System.out.println("SyntaxGroveTest.whenRecursionIndexMarkingRequestedThenEffective() :" + System.lineSeparator()
			+ e.getMessage());
			markingEffective = false;
		}
		Set<IPair> binaryRelation = new HashSet<IPair>();
		for (ITotalOrder maxOrder : relationalDescription.getMaxTotalOrders())
			binaryRelation.addAll(maxOrder.getPairs());			
		for (IPair pair : binaryRelation) {
			if (pair.getAntecedent().equals(pair.getConsequent()))
				markingEffective = false;
		}
		assertTrue(markingEffective);
	}

}
