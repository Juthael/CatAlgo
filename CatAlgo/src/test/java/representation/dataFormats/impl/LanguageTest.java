package representation.dataFormats.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammars.copycat2Strings.utils.CcFileReaderB;
import grammars.sphex.utils.SphexFileReader;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IRelationalDescription;

public class LanguageTest {

	public static ILanguage blackBurnLanguageA1;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		ISyntaxGrove blackburnGrove;
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
		IGenericFileReader ccFileReader = new CcFileReaderB();
		IRelationalDescription blackburnRelationA1 = null;
		try {
			blackburnGrove = ccFileReader.getSyntacticGrove(backburnDozen1);
			blackburnGrove.markRecursion();
			//to see syntactic paths
			System.out.println(blackburnGrove.getListOfTrees().get(0).getTreePaths().toString());
			blackburnRelationA1 = blackburnGrove.getListOfTrees().get(0).getRelationalDescription();
		}
		catch (Exception e) {
			System.out.println("SyntaxBranchTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
		try {
			blackBurnLanguageA1 = blackburnRelationA1.getLanguage();
		}
		catch (Exception e) {
			System.out.println("SyntaxBranchTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}		
	}
	
	@Test
	public void whenBinaryRelationRequestedThenReturned() {
		assertTrue(false);
	}
	
	@Test
	public void whenFunctionalExpressionRequestedThenReturned() {
		//to see language
		System.out.println(blackBurnLanguageA1.toString());
		System.out.println(blackBurnLanguageA1.getFunctionalExpression().toString());
		assertTrue(false);
	}
	
	@Test
	public void whenStateMachineRequestedThenReturned() {
		assertTrue(false);
	}
	
	@Test
	public void whenGrammarRequestedThenReturned() {
		assertTrue(false);
	}
	
	@Test
	public void whenNbOfArgumentsRequestedThenExpectedNumberReturned() {
		assertTrue(false);
	}
	
	@Test
	public void whenToStringRequestedThenExpectedStringReturned() {
		assertTrue(false);
	}
	
	

}
