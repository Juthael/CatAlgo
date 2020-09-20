package representation.dataFormats.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammars.copycat2Strings.utils.CcFileReaderB;
import grammars.sphex.utils.SphexFileReader;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.IGrammaticalRule;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IRelationalDescription;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;

public class RelationalDescriptionTest {

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
			//System.out.println(blackburnGrove.getListOfTrees().get(0).getTreePaths().toString());
			blackburnRelationA1 = blackburnGrove.getListOfTrees().get(0).getRelationalDescription();
		}
		catch (Exception e) {
			System.out.println("RelationalDescriptionTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
		try {
			sphexGrove = sphexFileReader.getSyntacticGrove(usualSphex);
			sphexRelationPrey1 = sphexGrove.getListOfTrees().get(0).getRelationalDescription();
		}
		catch (Exception e) {
			System.out.println("RelationalDescriptionTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}
	
	@Test
	public void whenLanguageRequestedThenExpectedReturned() {
		String stringLanguageExpected = buildLanguageString();
		//to see stringLanguageExpected : 
		//System.out.println(stringLanguageExpected.toString());
		ILanguage languageReturned = null;
		try {
			languageReturned = blackburnRelationA1.getLanguage();
			//to see languageReturned : 
			//System.out.println(languageReturned.toString());
		} catch (RepresentationException e) {
			System.out.println("RelationalDescriptionTest.whenLanguageRequestedThenExpectedReturned() :" 
					+ System.lineSeparator() +e.getMessage());
		}
		assertTrue(languageReturned.toString().equals(stringLanguageExpected));
	}
	
	@Test
	public void whenFunctionalExpressionRequestedThenExpectedFEReturned() {
		String expectedExpression = 
				"letter ("
					+ "(ccString firstString) "
					+ "Λ (direction fromLeftToRight) "
					+ "Λ (letterValue 1'' cluster' ("
						+ "(size' 1 cluster ((pattern 1,2,1) Λ (size 3))) "
						+ "Λ (sequence' ("
							+ "(firstValue' 1' cluster ("
								+ "(size 3) "
								+ "Λ (sequence ((increment 1) Λ (firstValue 1))))) "
							+ "Λ (increment 0))))) "
					+ "Λ (position ("
						+ "(coordinate' 1' coordinate 1) "
						+ "Λ (prominentPosition endPosition firstPosition))))";
		IFunctionalExpression returnedExpression = null;
		try {
			returnedExpression = blackburnRelationA1.getFunctionalExpression();
		} catch (RepresentationException e) {
			System.out.println("RelationalDescription.whenFunctionalExpressionRequestedThenExpectedFEReturned() : "
					+ System.lineSeparator() + e.getMessage());
		}
			//To see language : 
			//System.out.println(blackBurnLanguageA1.toString());
			//To see expected expression
			//System.out.println(expectedExpression);
			//To see returned expression : 
			//System.out.println(returnedExpression.toString());
			assertTrue(returnedExpression.toString().equals(expectedExpression));
	}
	
	@Test
	public void whenGrammarRequestedThenExpectedReturned() {
		IGrammar expectedGrammar = buildExpectedGrammar();
		//To see language : 
		/*
		try {
			System.out.println(sphexRelationPrey1.getLanguage().toString());
		} catch (RepresentationException e1) {
			System.out.println("RelationalDescriptionTest.whenGrammarRequestedThenReturned() : cannot get language."
					+ System.lineSeparator() + e1.getMessage());
		}
		*/
		IGrammar returnedGrammar = sphexRelationPrey1.getGrammar();
		//To see grammar : 
		//System.out.println(returnedGrammar);
		assertTrue(expectedGrammar.equals(returnedGrammar));
	}
	
	@Test
	public void whenNbOfArgumentsRequestedThenExpectedNumberReturned() {
		boolean returnedExpectedNbForLetter = false;
		boolean returnedExpectedNbFor1 = false;
		boolean returnedExpectedNbForPosition = false;
		boolean returnedExpectedNbForEndPosition = false;
		boolean returnedExpectedNbForSequence = false;
		//To see language :
		/*
		try {
			System.out.println(blackburnRelationA1.getLanguage().toString());
		} catch (RepresentationException e1) {
			System.out.println("RelationalDescriptionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get language." + System.lineSeparator() + e1.getMessage());
		}
		*/
		try {
			returnedExpectedNbForLetter = (blackburnRelationA1.getNbOfArgumentsFor(new Symbol("letter")) == 4);
		} catch (RepresentationException e) {
			System.out.println("RelationalDescriptionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get number of arguments for 'letter'." + System.lineSeparator() + e.getMessage());
		}
		try {
			returnedExpectedNbFor1 = (blackburnRelationA1.getNbOfArgumentsFor(new Symbol("1")) == 0);
		} catch (RepresentationException e) {
			System.out.println("RelationalDescriptionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get number of arguments for '1'." + System.lineSeparator() + e.getMessage());
		}
		try {
			returnedExpectedNbForEndPosition = (blackburnRelationA1.getNbOfArgumentsFor(new Symbol("position")) == 2);
		} catch (RepresentationException e) {
			System.out.println("RelationalDescriptionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get number of arguments for 'position'." + System.lineSeparator() + e.getMessage());
		}
		try {
			returnedExpectedNbForSequence = (blackburnRelationA1.getNbOfArgumentsFor(new Symbol("endPosition")) == 1);
		} catch (RepresentationException e) {
			System.out.println("RelationalDescriptionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get number of arguments for 'endPosition'." + System.lineSeparator() + e.getMessage());
		}
		try {
			returnedExpectedNbForPosition = (blackburnRelationA1.getNbOfArgumentsFor(new Symbol("sequence")) == 2);
		} catch (RepresentationException e) {
			System.out.println("RelationalDescriptionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get number of arguments for 'sequence'." + System.lineSeparator() + e.getMessage());
		}
		assertTrue(returnedExpectedNbForLetter 
				&& returnedExpectedNbFor1 
				&& returnedExpectedNbForPosition
				&& returnedExpectedNbForEndPosition
				&& returnedExpectedNbForSequence);
	}
	
	@Test
	public void whenToStringRequestedThenExpectedStringReturned() {
		String expected;
		StringBuilder sB = new StringBuilder();
		sB.append("prey/grab/predate/provideFoodForTheGrubs" + System.lineSeparator());
		sB.append("prey/position/randomPlace" + System.lineSeparator());
		sB.append("prey/timePosition/step1" + System.lineSeparator());
		expected = sB.toString();
		assertTrue(sphexRelationPrey1.toString().equals(expected));
	}
	
	private IGrammar buildExpectedGrammar() {
		IGrammar expectedGrammar;
		Set<IGrammaticalRule> rules = new HashSet<IGrammaticalRule>();
		rules.add(buildGrammaticalRule("prey", "grab"));
		rules.add(buildGrammaticalRule("grab", "predate"));
		rules.add(buildGrammaticalRule("predate", "provideFoodForTheGrubs"));
		rules.add(buildGrammaticalRule("prey", "position"));
		rules.add(buildGrammaticalRule("position", "randomPlace"));
		rules.add(buildGrammaticalRule("prey", "timePosition"));
		rules.add(buildGrammaticalRule("timePosition", "step1"));
		expectedGrammar = new Grammar(rules);
		return expectedGrammar;
	}
	
	private IGrammaticalRule buildGrammaticalRule(String antecedent, String consequent) {
		IGrammaticalRule rule;
		ISymbol antSymbol = new Symbol(antecedent);
		ISymbol csqSymbol = new Symbol(consequent);
		rule = new GrammaticalRule(antSymbol, csqSymbol);
		return rule;
	}
	
	private String buildLanguageString() {
		String stringLanguage;
		StringBuilder sB = new StringBuilder();
		sB.append("letter/ccString/firstString" + System.lineSeparator());
		sB.append("letter/direction/fromLeftToRight" + System.lineSeparator());
		sB.append("letter/letterValue/1''/cluster'/sequence'/firstValue'/1'/cluster/sequence/firstValue/1" 
				+ System.lineSeparator());
		sB.append("letter/letterValue/1''/cluster'/sequence'/firstValue'/1'/cluster/sequence/increment/1" 
				+ System.lineSeparator());
		sB.append("letter/letterValue/1''/cluster'/sequence'/firstValue'/1'/cluster/size/3" + System.lineSeparator());
		sB.append("letter/letterValue/1''/cluster'/sequence'/increment/0" + System.lineSeparator());
		sB.append("letter/letterValue/1''/cluster'/size'/1/cluster/pattern/1,2,1" + System.lineSeparator());
		sB.append("letter/letterValue/1''/cluster'/size'/1/cluster/size/3" + System.lineSeparator());
		sB.append("letter/position/coordinate'/1'/coordinate/1" + System.lineSeparator());
		sB.append("letter/position/prominentPosition/endPosition/firstPosition" + System.lineSeparator());
		stringLanguage = sB.toString();
		return stringLanguage;
	}
	

}
