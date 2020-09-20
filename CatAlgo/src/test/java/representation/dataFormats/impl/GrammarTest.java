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
import grammars.sphex.utils.SphexFileReader;
import representation.dataFormats.IGrammar;
import representation.dataFormats.IGrammaticalRule;
import representation.dataFormats.IRelationalDescription;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;

public class GrammarTest {
	
	public static IGrammar sphexGrammar;

	@BeforeClass
	public static void setUpBeforeClass() {
		IRelationalDescription sphexRelationPrey1;
		ISyntaxGrove sphexGrove;
		Path usualSphex = Paths.get(".", "src", "test", "java", "filesUsedForTests", "usualSphex.txt");
		IGenericFileReader sphexFileReader = new SphexFileReader();
		try {
			sphexGrove = sphexFileReader.getSyntacticGrove(usualSphex);
			sphexRelationPrey1 = sphexGrove.getListOfTrees().get(0).getRelationalDescription();
			sphexGrammar = sphexRelationPrey1.getGrammar();
		}
		catch (Exception e) {
			System.out.println("RelationalDescriptionTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}	
		//To see grammar :
		//System.out.println(sphexGrammar.toString());
	}
	
	@Test
	public void whenAskedIfContainsSpecifiedRuleThenReturnsExpectedValue() {
		ISymbol preySymbol = new Symbol("prey");
		ISymbol predateSymbol = new Symbol("grab");
		IGrammaticalRule rule = new GrammaticalRule(preySymbol, predateSymbol);
		assertTrue(sphexGrammar.contains(rule));
	}
	
	@Test
	public void whenAskedIfContainsSpecifiedGrammarThenReturnsExpectedValue() {
		IGrammar subGrammar;
		Set<IGrammaticalRule> subsetOfRules = new HashSet<IGrammaticalRule>();
		ISymbol preySymbol = new Symbol("prey");
		ISymbol grabSymbol = new Symbol("grab");
		ISymbol predateSymbol = new Symbol("predate");
		subsetOfRules.add(new GrammaticalRule(preySymbol, grabSymbol));
		subsetOfRules.add(new GrammaticalRule(grabSymbol, predateSymbol));
		subGrammar = new Grammar(subsetOfRules);
		assertTrue(sphexGrammar.contains(subGrammar));
	}
	
	@Test
	public void whenTestedForEqualityWithSpecifiedGrammarThenReturnsExpectedValue() {
		assertTrue(sphexGrammar.equals(buildIdenticalGrammar()));
	}
	
	private IGrammar buildIdenticalGrammar() {
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

}
