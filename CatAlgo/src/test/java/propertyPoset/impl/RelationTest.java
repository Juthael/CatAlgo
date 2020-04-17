package propertyPoset.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammars.copycat2Strings.utils.CcFileReaderB;
import propertyPoset.IPropertyPoset;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;
import propertyPoset.utils.IDimensionAnalysis;

public class RelationTest {
	
	private static ISyntaxGrove trueGrove;
	private IPropertyPoset truePropPoset;
	private static IRelation trueRelation;
	
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
	}

	@Test
	public void whenImplicationIsAddedThenConsequentCanBeRetreivedAsGreaterProperty() throws PropertyPosetException {
		/*
		Set<String> greaterBefore = mockRelation.getGreaterProperties("2");
		IImplication impl = new Implication("2", "3");
		mockRelation.addImplication(impl);;
		Set<String> greaterNow = mockRelation.getGreaterProperties("2");
		boolean threeWasntGreaterThen = !greaterBefore.contains("3");
		boolean threeIsGreaterNow = greaterNow.contains("3");
		assertTrue(threeWasntGreaterThen && threeIsGreaterNow);
		*/
		assertTrue(false);
	}
	
	@Test
	public void whenImplicationIsAddedWithTransitivityGuaranteeThenTransitivityVerified() throws PropertyPosetException {
		/*
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
		*/
		assertTrue(false);
	}
	
	@Test
	public void whenSuccessorRequestedThenReturnedGivenPropertyName() throws PropertyPosetException {
		/*
		Set<String> arithSeq1Succ = trueRelation.getSuccessors("ArithSeq1");
		boolean expectedSetSize = (arithSeq1Succ.size() == 3);
		boolean expectedSetElem1 = arithSeq1Succ.contains("ArithSeQ");
		boolean expectedSetElem2 = arithSeq1Succ.contains("FirstValue1");
		boolean expectedSetElem3 = arithSeq1Succ.contains("Increment2");
		assertTrue(expectedSetSize && expectedSetElem1 && expectedSetElem2 && expectedSetElem3);
		*/
		assertTrue(false);
	}
	
	@Test
	public void whenPredecessorRequestedThenReturnedGivenPropertyName() throws PropertyPosetException {
		/*
		Set<String> arithSeq1Prec = trueRelation.getPredecessors("ArithSeq1");
		boolean expectedSetSize = (arithSeq1Prec.size() == 1);
		boolean expectedSetElem = arithSeq1Prec.contains("Relation1");
		assertTrue(expectedSetSize && expectedSetElem);
		*/
		assertTrue(false);
	}
	
	@Test
	public void whenRankRequestedThenReturnedGivenPropertyName() throws PropertyPosetException {
		/*
		boolean incremenTRankIs8 = (trueRelation.getRank("IncremenT") == 8);
		boolean digitORankIs1 = (trueRelation.getRank("Digit0") == 1);
		boolean arithSeq2RankIs3 = (trueRelation.getRank("ArithSeq2") == 3);
		assertTrue(incremenTRankIs8 && digitORankIs1 && arithSeq2RankIs3);		
		*/
		assertTrue(false);
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
		/*
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
		*/
		assertTrue(false);
	}
	
	@Test
	public void whenPosetLeavesRequestedThenExpectedPropertyNamesReturned() throws PropertyPosetException {
		/*
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
		*/
		assertTrue(false);
	}
	
	@Test
	public void whenMaximalRankRequestedThenExpectedRankReturned() throws PropertyPosetException {
		assertTrue(false);
	}
	
	@Test
	public void whenDimensionAnalyzesRequiredThenConsistentDimensionAnalyzesReturned() throws PropertyPosetException{		
		/*
		Set<IDimensionAnalysis> analyzes = trueRelation.getDimensionAnalyzes();
		for(IDimensionAnalysis analysis : analyzes) {
			System.out.println("-------DIMENSION NAME : " + analysis.getDimensionName());
			for (String instance : analysis.getAllInstancesOfThisDimension()) {
				System.out.println("Dimension instance : " + instance);
				System.out.println(analysis.getValuesForThisDimensionInstance(instance));
			}
			System.lineSeparator();			
		}
		*/
		/*
		int nbOfInstancesForDimension1 = -1;
		for (IDimensionAnalysis analysis : analyzes) {
			if (analysis.getDimensionName().equals("1")) {
				nbOfInstancesForDimension1 = analysis.getAllInstancesOfThisDimension().size();
			}
		}
		assertTrue(nbOfInstancesForDimension1 == 2);
		*/
		assertTrue(false);
	}
	
	@Test
	public void whenRelationModifiedThenNewRelationIsConsistent() throws PropertyPosetException {
		/*
		boolean modificationIsConsistent = true;
		String targetProperty = "Relation0";
		Set<String> targetNewPrec = trueRelation.getPredecessors(targetProperty);
		String removedPrec = "FirstValue0";
		targetNewPrec.remove(removedPrec);
		trueRelation.modifyRelation(targetProperty, targetNewPrec);
		for (String property : trueRelation.getConsequents(trueRelation.getPosetRoot())) {
			if (!property.equals(targetProperty)) {
				if (trueRelation.getConsequents(property).contains(targetProperty)) {
					Set<String> propertyCsqts = trueRelation.getConsequents(property);
					propertyCsqts.retainAll(targetNewPrec);
					if (propertyCsqts.isEmpty()) {
						modificationIsConsistent = false;
					}
				}
				else {
					Set<String> propertyCsqts = trueRelation.getConsequents(property);
					propertyCsqts.retainAll(targetNewPrec);
					if (!propertyCsqts.isEmpty()) {
						modificationIsConsistent = false;
					}
				}
			}
		}
		assertTrue(modificationIsConsistent);
		*/
		assertTrue(false);
	}
	
	@Test
	public void whenNewPropertyIsAddedThenNewRelationIsConsistent() throws PropertyPosetException {
		/*
		boolean relationIsConsistent = true;
		String newProperty = "RelationO*";
		Set<String> newPropCsqts = trueRelation.getGreaterProperties("Relation0");
		newPropCsqts.add(newProperty);
		newPropCsqts.add("DigiT");
		Set<String> newPropPrec = trueRelation.getPredecessors("Relation0");
		newPropPrec.remove("FirstValue0");
		trueRelation.addNewProperty(newProperty, newPropPrec, newPropCsqts);
		for (String property : trueRelation.getConsequents(trueRelation.getPosetRoot())) {
			Set<String> propertyCsqts = trueRelation.getConsequents(property);
			if (propertyCsqts.contains(newProperty)) {
				if (!trueRelation.getConsequents(property).containsAll(newPropCsqts))
					relationIsConsistent = false;
			}
			else {
				propertyCsqts.retainAll(newPropPrec);
				if (!propertyCsqts.isEmpty())
					relationIsConsistent = false;
			}
		}
		assertTrue(relationIsConsistent);
		*/
		assertTrue(false);
	}
	
	private static void setTrueGrove() {
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
		IGenericFileReader fileReader = new CcFileReaderB();
		try {
			trueGrove = fileReader.getSyntacticGrove(backburnDozen1);
			trueGrove.markRecursion();
			trueGrove.setPosetElementID();
		}
		catch (Exception e) {
			System.out.print("PropertySetTest : error during SyntacticGrove instantiation. " + System.lineSeparator() 
				+ e.getMessage());
		}
		// printChains(trueGrove.getListOfSyntacticStringChains());	
	}
	
}
