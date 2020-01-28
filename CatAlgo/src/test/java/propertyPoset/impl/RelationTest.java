package propertyPoset.impl;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.structure.impl.SyntaxGrove;
import grammars.seekWhence.branches.Value;
import grammars.seekWhence.leaves.ValuE;
import grammars.seekWhence.utils.impl.SwFileReader;
import propertyPoset.IOriginalPropertyPoset;
import propertyPoset.IRelation;

public class RelationTest {
	
	private static ISyntaxGrove trueGrove;
	private static ISyntaxGrove mockGrove;
	IOriginalPropertyPoset truePropPoset;
	IOriginalPropertyPoset mockPropPoset;
	private static IRelation trueRelation;
	private static IRelation mockRelation;
	
	/*
	 * mockGrove chains :
	 * grove/2/2
	 * grove/2/7/7
	 * grove/2/7/8
	 * grove/3/3
	 * grove/3/4/4
	 * grove/3/4/3
	 * grove/7/7
	 * grove/7/8
	 */
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setTrueGrove();
		setMockGrove();
	}

	@Before
	public void setUp() throws Exception {
		try {
			//System.out.println(grove.getPosetMaxChains().getChainsInASingleString());
			truePropPoset = new OriginalPropertyPoset(trueGrove.getPosetMaxChains());
		}
		catch (Exception e) {
			System.out.println("PropertySetTest : error during OriginalPropertyPoset instantiation, with param 'trueGrove' " 
					+ System.lineSeparator() + e.getMessage());
		}
		trueRelation = truePropPoset.getRelation();
		try {
			//System.out.println(grove.getPosetMaxChains().getChainsInASingleString());
			mockPropPoset = new OriginalPropertyPoset(mockGrove.getPosetMaxChains());
		}
		catch (Exception e) {
			System.out.println("PropertySetTest : error during OriginalPropertyPoset instantiation, with param 'mockGrove' " 
					+ System.lineSeparator() + e.getMessage());
		}
		mockRelation = mockPropPoset.getRelation();
	}

	@Test
	public void whenImplicationIsAddedThenConsequentCanBeRetreivedAsGreaterProperty() {
		fail("Not yet implemented");
	}
	
	@Test
	public void whenImplicationIsAddedWithTransitivityGuaranteeThenTransitivityVerified() {
		fail("Not yet implemented");
	}
	
	@Test
	public void whenSuccessorRequestedThenReturnedGivenPropertyName() {
		fail("Not yet implemented");
	}
	
	@Test
	public void whenPredecessorRequestedThenReturnedGivenPropertyName() {
		fail("Not yet implemented");
	}
	
	@Test
	public void whenRankRequestedThenReturnedGivenPropertyName() {
		fail("Not yet implemented");
	}
	 /*
	@Test
	public void when() {
		fail("Not yet implemented");
	}
	
	@Test
	public void when() {
		fail("Not yet implemented");
	}
	
	@Test
	public void when() {
		fail("Not yet implemented");
	}
	
	@Test
	public void when() {
		fail("Not yet implemented");
	}
	
	@Test
	public void when() {
		fail("Not yet implemented");
	}	
	*/
	
	private static void setTrueGrove() {
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "BD1_1_12_123.txt");
		SwFileReader fileReader = new SwFileReader();
		try {
			trueGrove = fileReader.getSyntacticGrove(backburnDozen1);
			trueGrove.setPosetElementID();
		}
		catch (Exception e) {
			System.out.print("PropertySetTest : error during SyntacticGrove instantiation. " + System.lineSeparator() 
				+ e.getMessage());
		}
		// printChains(trueGrove.getListOfSyntacticStringChains());	
	}
	
	private static void setMockGrove() throws GrammarModelException {
		/*
		 * mockGrove chains :
		 * grove/2/2
		 * grove/2/7/7
		 * grove/2/7/8
		 * grove/3/3
		 * grove/3/4/4
		 * grove/3/4/3
		 * grove/7/7
		 * grove/7/8
		 */
		ValuE valuE2 = new ValuE("2");
		ValuE valuE7 = new ValuE("7");
		ValuE valuE8 = new ValuE("8");
		ValuE valuE3 = new ValuE("3");
		ValuE valuE4 = new ValuE("4");
		Value value7i8 = new Value(valuE7, valuE8);
		Value value4i3 = new Value(valuE4, valuE3);
		Value value2ii7i8 = new Value(valuE2, value7i8);
		Value value3ii4i3 = new Value(valuE3, value4i3);
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(value2ii7i8);
		components.add(value3ii4i3);
		components.add(value7i8);
		mockGrove = new SyntaxGrove("grove", components);
		mockGrove.setPosetElementID();
	}

}
