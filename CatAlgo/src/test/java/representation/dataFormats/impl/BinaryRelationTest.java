package representation.dataFormats.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammars.copycat2Strings.utils.CcFileReaderB;
import grammars.sphex.utils.SphexFileReader;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.ILanguage;
import representation.exceptions.RepresentationException;

public class BinaryRelationTest {

	public static IRelationalDescription blackburnRelationA1;
	public static IRelationalDescription sphexRelationPrey1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ISyntaxGrove blackburnGrove;
		ISyntaxGrove sphexGrove;
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
		Path usualSphex = Paths.get(".", "src", "test", "java", "filesUsedForTests", "usualSphex.txt");
		IGenericFileReader ccFileReader = new CcFileReaderB();
		IGenericFileReader sphexFileReader = new SphexFileReader();
		try {
			blackburnGrove = ccFileReader.getSyntacticGrove(backburnDozen1);
			blackburnGrove.markRecursion();
			//to see syntactic paths
			System.out.println(blackburnGrove.getListOfTrees().get(0).getTreePaths().toString());
			blackburnRelationA1 = blackburnGrove.getListOfTrees().get(0).getBinaryRelation();
		}
		catch (Exception e) {
			System.out.println("SyntaxBranchTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
		try {
			sphexGrove = sphexFileReader.getSyntacticGrove(usualSphex);
			sphexRelationPrey1 = sphexGrove.getListOfTrees().get(0).getBinaryRelation();
		}
		catch (Exception e) {
			System.out.println("SyntaxBranchTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}
	
	@Test
	public void whenLanguageRequestedThenReturned() {
		boolean languageReturned = false;
		ILanguage language;
		try {
			language = blackburnRelationA1.getLanguage();
			languageReturned = (language != null && !language.getWords().isEmpty());
			//to see language : 
			System.out.println(language.toString());
		} catch (RepresentationException e) {
			System.out.println("BinaryRelationTest.whenLanguageRequestedThenReturned() :" + System.lineSeparator() 
				+e.getMessage());
		}
		assertTrue(languageReturned);
		//HERE ensure first that recursion can be marked. 
	}
	
	@Test
	public void whenFunctionalExpressionRequestedThenReturned() {
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
