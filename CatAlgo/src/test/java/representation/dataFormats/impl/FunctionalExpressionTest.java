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

public class FunctionalExpressionTest {

	public static IFunctionalExpression blackBurnFunctionalExpression;
	public static IFunctionalExpression sphexFunctionalExpression;
	public static IRelationalDescription blackBurnRelationalDesc;
	public static IRelationalDescription sphexRelationalDesc;
	public static ILanguage blackBurnLanguageA1;
	public static ILanguage sphexLanguage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		//blackburnExample
		ISyntaxGrove blackburnGrove;
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
		IGenericFileReader ccFileReader = new CcFileReaderB();
		try {
			blackburnGrove = ccFileReader.getSyntacticGrove(backburnDozen1);
			blackburnGrove.markRecursion();
			//to see syntactic paths :
			//System.out.println(blackburnGrove.getListOfTrees().get(0).getTreePaths().toString());
			blackBurnRelationalDesc = blackburnGrove.getListOfTrees().get(0).getRelationalDescription();
			blackBurnLanguageA1 = blackBurnRelationalDesc.getLanguage();
			blackBurnFunctionalExpression = blackBurnRelationalDesc.getFunctionalExpression();
		}
		catch (Exception e) {
			System.out.println("LanguageTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
		//sphexExample
		ISyntaxGrove sphexGrove;
		Path usualSphex = Paths.get(".", "src", "test", "java", "filesUsedForTests", "usualSphex.txt");
		IGenericFileReader sphexFileReader = new SphexFileReader();
		try {
			sphexGrove = sphexFileReader.getSyntacticGrove(usualSphex);
			//to see syntactic paths :
			//System.out.println(sphexGrove.getListOfTrees().get(0).getTreePaths().toString());
			sphexRelationalDesc = sphexGrove.getListOfTrees().get(0).getRelationalDescription();
			sphexLanguage = sphexRelationalDesc.getLanguage();
			sphexFunctionalExpression = sphexRelationalDesc.getFunctionalExpression();
		}
		catch (Exception e) {
			System.out.println("LanguageTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}	
	}
	
	@Test
	public void whenRelationalDescriptionRequestedThenExpectedReturned() {
		IRelationalDescription expected = blackBurnRelationalDesc;
		IRelationalDescription returned = null;
		try {
			returned = blackBurnFunctionalExpression.getRelationalDescription();
		} catch (RepresentationException e) {
			System.out.println("FunctionalExpressionTest.whenRelationalDescriptionRequestedThenExpectedReturned()" 
					+ System.lineSeparator() + e.getMessage());
		}
		//To see expected relational description :
		//System.out.println(expected.toString());
		//To see returned relational description : 
		//System.out.println(returned.toString());
		assertTrue(expected.equals(returned));
	}
	
	@Test
	public void whenLanguageRequestedThenExpectedReturned() {
		String expectedLanguageString = buildLanguageString();
		ILanguage languageReturned = blackBurnFunctionalExpression.getLanguage();
		assertTrue(languageReturned.toString().equals(expectedLanguageString));
	}
	
	@Test
	public void whenGrammarRequestedThenExpectedReturned() {
		IGrammar expectedGrammar = buildExpectedGrammar();
		//To see expected grammar : 
		//System.out.println(expectedGrammar.toString());
		IGrammar returnedGrammar = sphexFunctionalExpression.getGrammar();
		//To see returned grammar :
		//System.out.println(returnedGrammar.toString());
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
			returnedExpectedNbForLetter = 
					(blackBurnFunctionalExpression.getNbOfArgumentsFor(new Symbol("letter")) == 4);
		} catch (RepresentationException e) {
			System.out.println("FunctionalExpressionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get number of arguments for 'letter'." + System.lineSeparator() + e.getMessage());
		}
		try {
			returnedExpectedNbFor1 = (blackBurnFunctionalExpression.getNbOfArgumentsFor(new Symbol("1")) == 0);
		} catch (RepresentationException e) {
			System.out.println("FunctionalExpressionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get number of arguments for '1'." + System.lineSeparator() + e.getMessage());
		}
		try {
			returnedExpectedNbForEndPosition = 
					(blackBurnFunctionalExpression.getNbOfArgumentsFor(new Symbol("position")) == 2);
		} catch (RepresentationException e) {
			System.out.println("FunctionalExpressionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get number of arguments for 'position'." + System.lineSeparator() + e.getMessage());
		}
		try {
			returnedExpectedNbForSequence = 
					(blackBurnFunctionalExpression.getNbOfArgumentsFor(new Symbol("endPosition")) == 1);
		} catch (RepresentationException e) {
			System.out.println("FunctionalExpressionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
					+ "cannot get number of arguments for 'endPosition'." + System.lineSeparator() + e.getMessage());
		}
		try {
			returnedExpectedNbForPosition = 
					(blackBurnFunctionalExpression.getNbOfArgumentsFor(new Symbol("sequence")) == 2);
		} catch (RepresentationException e) {
			System.out.println("FunctionalExpressionTest.whenNbOfArgumentsRequestedThenExpectedNumberReturned() : "
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
		String expected = 
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
		//To see expected : 
		//System.out.println(expected);
		String returned = blackBurnFunctionalExpression.toString();
		//To see returned : 
		//System.out.println(returned.toString());
		assertTrue(returned.equals(expected));
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
	
	private IGrammar buildExpectedGrammar() {
		IGrammar expectedGrammar;
		Set<IGrammaticalRule> rules = new HashSet<IGrammaticalRule>();
		rules.add(buildGrammaticalRule("prey", "grab"));
		rules.add(buildGrammaticalRule("prey", "predate"));
		rules.add(buildGrammaticalRule("prey", "provideFoodForTheGrubs"));
		rules.add(buildGrammaticalRule("grab", "predate"));
		rules.add(buildGrammaticalRule("grab", "provideFoodForTheGrubs"));
		rules.add(buildGrammaticalRule("predate", "provideFoodForTheGrubs"));
		rules.add(buildGrammaticalRule("prey", "position"));
		rules.add(buildGrammaticalRule("prey", "randomPlace"));
		rules.add(buildGrammaticalRule("position", "randomPlace"));
		rules.add(buildGrammaticalRule("prey", "timePosition"));
		rules.add(buildGrammaticalRule("prey", "step1"));
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

}
