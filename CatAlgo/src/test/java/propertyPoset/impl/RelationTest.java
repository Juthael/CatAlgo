package propertyPoset.impl;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import propertyPoset.IPropertyPoset;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IImplication;
import propertyPoset.utils.impl.Implication;

public class RelationTest {
	
	private static ISyntaxGrove trueGrove;
	private static ISyntaxGrove mockGrove;
	private IPropertyPoset truePropPoset;
	private IPropertyPoset mockPropPoset;
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
			//System.out.println(trueGrove.getPosetMaxChains().getChainsInASingleString());
			truePropPoset = new PropertyPoset(trueGrove.getPosetMaxChains());
		}
		catch (Exception e) {
			System.out.println("PropertySetTest : error during OriginalPropertyPoset instantiation, with param 'trueGrove' " 
					+ System.lineSeparator() + e.getMessage());
		}
		trueRelation = truePropPoset.getRelation();
		try {
			//System.out.println(grove.getPosetMaxChains().getChainsInASingleString());
			mockPropPoset = new PropertyPoset(mockGrove.getPosetMaxChains());
		}
		catch (Exception e) {
			System.out.println("PropertySetTest : error during OriginalPropertyPoset instantiation, with param 'mockGrove' " 
					+ System.lineSeparator() + e.getMessage());
		}
		mockRelation = mockPropPoset.getRelation();
	}

	@Test
	public void whenImplicationIsAddedThenConsequentCanBeRetreivedAsGreaterProperty() throws PropertyPosetException {
		Set<String> greaterBefore = mockRelation.getGreaterProperties("2");
		IImplication impl = new Implication("2", "3");
		mockRelation.addImplication(impl);;
		Set<String> greaterNow = mockRelation.getGreaterProperties("2");
		boolean threeWasntGreaterThen = !greaterBefore.contains("3");
		boolean threeIsGreaterNow = greaterNow.contains("3");
		assertTrue(threeWasntGreaterThen && threeIsGreaterNow);
	}
	
	@Test
	public void whenImplicationIsAddedWithTransitivityGuaranteeThenTransitivityVerified() throws PropertyPosetException {
		boolean ifSmallerThan8ThenDontKnowFor3Before = false;
		for (String property : mockPropPoset.getProperties().getSetOfPropertyNames()) {
			if (mockRelation.getGreaterProperties(property).contains("8") 
					&& !mockRelation.getGreaterProperties(property).contains("3"))
				ifSmallerThan8ThenDontKnowFor3Before = true;
		}
		
		IImplication impl = new Implication("8", "3");
		mockRelation.addImplicationEnsureTransitivity(impl);
		boolean ifSmallerThan8ThenSmallerThan3Now = true;
		for (String property : mockPropPoset.getProperties().getSetOfPropertyNames()) {
			if (mockRelation.getGreaterProperties(property).contains("8") 
					&& !mockRelation.getGreaterProperties(property).contains("3"))
				ifSmallerThan8ThenSmallerThan3Now = false;
		}
		assertTrue(ifSmallerThan8ThenDontKnowFor3Before && ifSmallerThan8ThenSmallerThan3Now);
	}
	
	@Test
	public void whenSuccessorRequestedThenReturnedGivenPropertyName() throws PropertyPosetException {
		Set<String> arithSeq1Succ = trueRelation.getSuccessors("ArithSeq1");
		boolean expectedSetSize = (arithSeq1Succ.size() == 3);
		boolean expectedSetElem1 = arithSeq1Succ.contains("ArithSeQ");
		boolean expectedSetElem2 = arithSeq1Succ.contains("FirstValue1");
		boolean expectedSetElem3 = arithSeq1Succ.contains("Increment2");
		assertTrue(expectedSetSize && expectedSetElem1 && expectedSetElem2 && expectedSetElem3);
	}
	
	@Test
	public void whenPredecessorRequestedThenReturnedGivenPropertyName() throws PropertyPosetException {
		Set<String> arithSeq1Prec = trueRelation.getPredecessors("ArithSeq1");
		boolean expectedSetSize = (arithSeq1Prec.size() == 1);
		boolean expectedSetElem = arithSeq1Prec.contains("Relation1");
		assertTrue(expectedSetSize && expectedSetElem);
	}
	
	@Test
	public void whenRankRequestedThenReturnedGivenPropertyName() throws PropertyPosetException {
		boolean incremenTRankIs8 = (trueRelation.getRank("IncremenT") == 8);
		boolean digitORankIs1 = (trueRelation.getRank("Digit0") == 1);
		boolean arithSeq2RankIs3 = (trueRelation.getRank("ArithSeq2") == 3);
		assertTrue(incremenTRankIs8 && digitORankIs1 && arithSeq2RankIs3);		
	}
	
	@Test
	public void whenDimensionStatusCheckedThenBooleanReturnedGivenPropertyName() 
			throws PropertyPosetException, GrammarModelException {
		/*
		for (String prop : trueGrove.getPosetMaxChains().getProperties()) {
			if (trueRelation.checkIfDimension(prop))
				System.out.println(prop);
		}
		 */		
		boolean propRelation2 = trueRelation.checkIfDimension("Relation2");
		boolean propRelation0 = trueRelation.checkIfDimension("Relation0");
		boolean propFirstValue1 = trueRelation.checkIfDimension("FirstValue1");
		boolean propSize1 = trueRelation.checkIfDimension("Size1");
		boolean propRelatioN = trueRelation.checkIfDimension("RelatioN");
		boolean propArithSeQ = trueRelation.checkIfDimension("ArithSeQ");
		boolean prop1 = trueRelation.checkIfDimension("1");
		boolean propIncremenT = trueRelation.checkIfDimension("IncremenT");
		boolean prop3 = trueRelation.checkIfDimension("3");
		boolean propPositioN = trueRelation.checkIfDimension("PositioN");
		boolean propNoAlterN = trueRelation.checkIfDimension("NoAlterN");
		boolean prop2 = trueRelation.checkIfDimension("2");
		boolean propDigiT = trueRelation.checkIfDimension("DigiT");
		boolean notPropSize0 = !trueRelation.checkIfDimension("Size0");
		assertTrue(propRelation2 && propRelation0 && propFirstValue1 && propSize1 && propRelatioN && propArithSeQ
				&& prop1 && propIncremenT && prop3 && propPositioN && propNoAlterN && prop2 && propDigiT && notPropSize0);
	}
	
	@Test
	public void whenDimensionRootStatusCheckedThenBooleanReturnedGivenPropertyName() throws GrammarModelException, PropertyPosetException {
		/*
		for (String prop : trueGrove.getPosetMaxChains().getProperties()) {
			if (trueRelation.checkIfDimensionRoot(prop))
				System.out.println(prop);
		}
		*/
		boolean propSeekWhenceCtxt0 = trueRelation.checkIfDimensionRoot("SeekWhenceCtxt0");
		boolean propRelation2 = trueRelation.checkIfDimensionRoot("Relation2");
		boolean propArithSeq2 = trueRelation.checkIfDimensionRoot("ArithSeq2");
		boolean notSize0 = !trueRelation.checkIfDimensionRoot("Size0");
		assertTrue(propSeekWhenceCtxt0 && propRelation2 && propArithSeq2 && notSize0);
	}
	
	@Test
	public void whenDimensionAtomStatusCheckedThenBooleanReturnedGivenPropertyName() throws GrammarModelException, PropertyPosetException {
		/*
		for (String prop : trueGrove.getPosetMaxChains().getProperties()) {
			if (trueRelation.checkIfDimensionAtom(prop))
				System.out.println(prop);
		}
		*/
		boolean propDigit4 = trueRelation.checkIfDimensionAtom("Digit4");
		boolean propSize0 = trueRelation.checkIfDimensionAtom("Size0");
		boolean notArithSeq1 = !trueRelation.checkIfDimensionAtom("ArithSeq1");
		assertTrue(propDigit4 && propSize0 && notArithSeq1);
	}
	
	@Test
	public void whenDimensionRootRequestedThenReturnedGivenDimensionName() throws PropertyPosetException {
		boolean arithSeqRootIsRelation2 = (trueRelation.getDimensionRoot("ArithSeQ").equals("Relation2"));
		assertTrue(arithSeqRootIsRelation2);
	}
	
	@Test
	public void whenDimensionRootRequestedThenExceptionThrownGivenNonDimensionPropertyName() {
		boolean exceptionIsThrown = false;
		try {
			trueRelation.getDimensionRoot("SizE");
		}
		catch (Exception e) {
			exceptionIsThrown = true;
		}
		assertTrue(exceptionIsThrown);
	}	
	
	@Test
	public void whenSubContextRootsRequestedThenExpectedPropReturned() throws PropertyPosetException {
		Set<String> expectedSubContextRoots = new HashSet<String>();
		expectedSubContextRoots.add("Relation2");
		Set<String> subContextRoots = trueRelation.getSubContextRoots();
		assertTrue(expectedSubContextRoots.equals(subContextRoots));
	}
	
	@Test
	public void whenPosetLeavesRequestedThenExpectedPropertyNamesReturned() throws PropertyPosetException {
		Set<String> expectedLeaves = new HashSet<String>();
		expectedLeaves.add("RelatioN");
		expectedLeaves.add("SizE");
		expectedLeaves.add("ArithSeQ");
		expectedLeaves.add("FirstValuE");
		expectedLeaves.add("1");
		expectedLeaves.add("IncremenT");
		expectedLeaves.add("0");
		expectedLeaves.add("3");
		expectedLeaves.add("PositioN");
		expectedLeaves.add("NoAlterN");
		expectedLeaves.add("2");
		expectedLeaves.add("DigiT");
		Set<String> returnedLeaves = trueRelation.getPosetleaves();
		assertTrue(expectedLeaves.equals(returnedLeaves));
	}
	
	@Test
	public void whenMaximalRankRequestedThenExpectedRankReturned() throws PropertyPosetException {
		assertTrue(mockRelation.getMaximalRank() == 3);
	}
	
	@Test
	public void whenPropertyRemovalRequestedThenPropertyRemovedGivenPropertyName() throws PropertyPosetException {
		String root = trueRelation.getPosetRoot();
		Set<String> predecessorBeforeRemoval = trueRelation.getPredecessors("Relation1");
		String propToRemove = predecessorBeforeRemoval.iterator().next();
		trueRelation.removeProperty(truePropPoset.getProperties().getProperty(propToRemove));
		Set<String> predecessorAfterRemoval = trueRelation.getPredecessors("Relation1");
		assertTrue(!predecessorAfterRemoval.equals(predecessorBeforeRemoval) 
				&& !trueRelation.getGreaterProperties(root).contains(propToRemove));
	}	
	
	@Test
	public void whenUpdateIsCalledThenUpdateOnRanksAndDimensionsAndRoots() throws PropertyPosetException {
		String dimension = "RelatioN";
		String propToRemove = "Relation2";
		int dimensionRankBeforeModif = trueRelation.getRank(dimension);
		String dimensionRootBeforeModif = trueRelation.getDimensionRoot(dimension);
		trueRelation.removeProperty(truePropPoset.getProperties().removeProperty(propToRemove));
		trueRelation.updateRelationData();
		int dimensionRankAfterModif = trueRelation.getRank(dimension);
		/*
		 * Note : the 'dimensionRootAfterModif' value is inconsistent in regards to the relation. 
		 * There shouldn't actually be any dimension root value since after the removal of 
		 * "Relation2", the set of predecessors of the dimension "RelatioN" is inf-irreducible. 
		 * This is because "Relation2" is a non-maximal element of the poset, so if it is removed 
		 * then every element of its upper set should be removed too. Here it is not, so 
		 * the poset isn't a lower semi-lattice anymore, hence this inconsistent behavior. 
		 */
		String dimensionRootAfterModif = trueRelation.getDimensionRoot(dimension);
		assertTrue(dimensionRankBeforeModif != dimensionRankAfterModif 
				&& !dimensionRootBeforeModif.equals(dimensionRootAfterModif));
	}
	
	private static void setTrueGrove() {
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "BD1_1_12_123.txt");
		SwFileReader fileReader = new SwFileReader();
		try {
			trueGrove = fileReader.getSyntacticGrove(backburnDozen1);
			trueGrove.markRedundancies();
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
	
	/* truePoset max chains : 
SeekWhenceCtxt0/Digit4/DigiT
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit4/Position4/PositioN
SeekWhenceCtxt0/Digit4/Position4/NoAlterN
SeekWhenceCtxt0/Digit4/Position4/1_1/1
SeekWhenceCtxt0/Digit1/DigiT
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit1/Position0/PositioN
SeekWhenceCtxt0/Digit1/Position0/NoAlterN
SeekWhenceCtxt0/Digit1/Position0/1_2/1
SeekWhenceCtxt0/Digit1/Position0/1_2/2
SeekWhenceCtxt0/Digit0/DigiT
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit0/Position3/PositioN
SeekWhenceCtxt0/Digit0/Position3/NoAlterN
SeekWhenceCtxt0/Digit0/Position3/2_2/2
SeekWhenceCtxt0/Digit3/DigiT
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit3/Position2/PositioN
SeekWhenceCtxt0/Digit3/Position2/NoAlterN
SeekWhenceCtxt0/Digit3/Position2/1_3/1
SeekWhenceCtxt0/Digit3/Position2/1_3/3
SeekWhenceCtxt0/Digit2/DigiT
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit2/Position1/PositioN
SeekWhenceCtxt0/Digit2/Position1/NoAlterN
SeekWhenceCtxt0/Digit2/Position1/2_3/2
SeekWhenceCtxt0/Digit2/Position1/2_3/3
SeekWhenceCtxt0/Digit5/DigiT
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit5/Position5/PositioN
SeekWhenceCtxt0/Digit5/Position5/NoAlterN
SeekWhenceCtxt0/Digit5/Position5/3_3/3
	 */

}