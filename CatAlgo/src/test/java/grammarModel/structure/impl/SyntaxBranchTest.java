package grammarModel.structure.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.relation.RelationException;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.exceptions.GrammarModelException;
import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammarModel.utils.ITreePaths;
import grammars.copycat2Strings.utils.CcFileReaderB;
import grammars.sphex.utils.SphexFileReader;
import representation.dataFormats.IPair;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.ITotalOrder;
import representation.dataFormats.impl.Pair;
import representation.dataFormats.impl.RelationalDescription;
import representation.dataFormats.impl.TotalOrder;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;

public class SyntaxBranchTest {

	public static ISyntaxGrove blackburnGrove;
	public static ISyntaxGrove sphexGrove;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
		Path usualSphex = Paths.get(".", "src", "test", "java", "filesUsedForTests", "usualSphex.txt");
		IGenericFileReader ccFileReader = new CcFileReaderB();
		IGenericFileReader sphexFileReader = new SphexFileReader();
		try {
			blackburnGrove = ccFileReader.getSyntacticGrove(backburnDozen1);
		}
		catch (Exception e) {
			System.out.println("SyntaxBranchTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
		try {
			sphexGrove = sphexFileReader.getSyntacticGrove(usualSphex);
		}
		catch (Exception e) {
			System.out.println("SyntaxBranchTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void whenRelationalDescRequestedThenExpectedRelationalDescReturned() {
		boolean expectedBRReturned;
		IRelationalDescription expectedRD = null;
		try {
			expectedRD = buildExpectedRelationalDesc();
		} catch (RelationException e1) {
			System.out.print("SyntaxBranchTest.whenRelationalDescRequestedThenExpectedRelationalDescReturned() : " 
					+ System.lineSeparator() + e1.getMessage());
			expectedBRReturned = false;
		} 
		ISyntaxBranch tree = sphexGrove.getListOfTrees().get(0);
		//to see the paths :
		/*
		try {
			ITreePaths paths = tree.getTreePaths(); 
			System.out.println(paths.toString());
		} catch (GrammarModelException e) {
			System.out.println("SyntaxBranchTest.whenBinaryRelationRequestedThenExpectedBRReturned() : " 
					+ System.lineSeparator() + e.getMessage());
			expectedBRReturned = false;
		}
		*/
		IRelationalDescription relationalDescription = null;
		try {
			relationalDescription = tree.getRelationalDescription();
		} catch (GrammarModelException e) {
			System.out.println("SyntaxBranchTest.whenBinaryRelationRequestedThenExpectedBRReturned() : " 
					+ System.lineSeparator() + e.getMessage());
		}
		//to see the relation : 
		//System.out.println(relationalDescription.toString());
		expectedBRReturned = relationalDescription.equals(expectedRD);
		assertTrue(expectedBRReturned);
	}
	
	@Test
	public void whenFunctionalExpressionRequestedThenExpectedFEReturned() {
		String expected = "prey ((grab predate provideFoodForTheGrubs) Λ (timePosition step1) Λ (position randomPlace))";
		String returned = sphexGrove.getListOfTrees().get(0).getFunctionalExpression().toString();
		//To see returned FE : 
		//System.out.println(returned);
		assertTrue(expected.equals(returned));
	}	
	
	@Test
	public void whenGetListOfLeafIDsIsCalledThenANonEmptyListIsReturned() {
		boolean nonEmptyListReturned = true;
		for (ISyntaxBranch branch : blackburnGrove.getListOfTrees()) {
			try {
				//method tested
				List<Long> leafIDs = branch.getListOfLeafIDs();
				if (leafIDs == null || leafIDs.isEmpty())
					throw new Exception("'leafIDs' variable is either null or empty");
			}
			catch (Exception e) {
				nonEmptyListReturned = false;
				System.out.println("SyntaxBranchTest."
						+ "whenGetListOfLeafIDsIsCalledThenANonEmptyListIsReturned() : "
						+ System.lineSeparator() + "cannot retrieve totalOrder IDs." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		assertTrue(nonEmptyListReturned);
	}	

	@Test
	public void whenPathsRequestedThenNonEmptyListsReturned() {
		boolean nonEmptyListReturned = true;
		for (ISyntaxBranch branch : blackburnGrove.getListOfTrees()) {
			List<List<String>> listOfSyntacticStringChains;
			try {
				//method tested
				listOfSyntacticStringChains = branch.getPathsAsListsOfStrings();
				if (listOfSyntacticStringChains == null || listOfSyntacticStringChains.isEmpty())
					throw new Exception("'listOfSyntacticStringChains' variable is either null or empty.");
			}
			catch (Exception e) {
				nonEmptyListReturned = false;
				System.out.println("SyntaxBranchTest."
						+ "whenGetListOfSyntacticStringChainsIsCalledThenANonEmptyListIsReturned() : "
						+ System.lineSeparator() + "cannot retrieve syntactic string chains." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		assertTrue(nonEmptyListReturned);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void whenRecursionIndexSettingRequestedThenPerformed() {
		boolean beforeSettingIndexesEqual0 = true;
		boolean afterSettingThenExpectedIndexes = true;
		Map<String, Set<Integer>> nameOntoIndexes = new HashMap<String, Set<Integer>>();
		SyntaxBranch branch = (SyntaxBranch) blackburnGrove.getListOfTrees().get(0).clone();
		try {
			ITreePaths paths = branch.getTreePaths();
			//to see paths :
			//System.out.println(paths.toString());
		} catch (GrammarModelException e) {
			System.out.println("SyntaxBranchTest.whenRecursionIndexSettingRequestedThenPerformed()"
					+ System.lineSeparator() + e.getMessage());
		}
		//before setting, all indexes equal 0
		Set<ISyntacticStructure> components = branch.getSetOfComponentsAndSubComponents();
		for (ISyntacticStructure component : components) {
			if (component.getRecursionIndex() != 0)
				beforeSettingIndexesEqual0 = false;
		}
		//method tested
		branch.setRecursionIndex();
		components = branch.getSetOfComponentsAndSubComponents();
		for (ISyntacticStructure component : components) {
			if (!nameOntoIndexes.containsKey(component.getName())) {
				Set<Integer> indexes = new HashSet<Integer>();
				indexes.add(component.getRecursionIndex());
				nameOntoIndexes.put(component.getName(), indexes);
			}
			else {
				nameOntoIndexes.get(component.getName()).add(component.getRecursionIndex());
			}
		}
		//to see indexes
		/*
		for (String name : nameOntoIndexes.keySet()) {
			System.out.println(name + ":");
			Set<Integer> indexes = nameOntoIndexes.get(name);
			for (Integer index : indexes)
				System.out.println(index + " ");
		}
		*/
		if (nameOntoIndexes.get("Letter").size() != 1)
			afterSettingThenExpectedIndexes = false;
		else if (nameOntoIndexes.get("Cluster").size() != 2 || nameOntoIndexes.get("Coordinate").size() != 2)
			afterSettingThenExpectedIndexes = false;
		assertTrue(beforeSettingIndexesEqual0 && afterSettingThenExpectedIndexes);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void whenRecursionMarkingRequestedThenPerformed() {
		boolean markingPerformed = true;
		Map<String, Set<Integer>> nameOntoIndexes = new HashMap<String, Set<Integer>>();
		SyntaxBranch branch = (SyntaxBranch) blackburnGrove.getListOfTrees().get(0).clone();
		branch.setRecursionIndex();
		try {
			ITreePaths paths = branch.getTreePaths();
			//to see paths BEFORE index marking :
			//System.out.println(paths.toString());
		} catch (GrammarModelException e) {
			System.out.println("SyntaxBranch.whenRecursionMarkingRequestedThenPerformed()" 
					+ System.lineSeparator() + e.getMessage());
		}
		try {
			//method tested
			branch.markRecursion();
		} catch (GrammarModelException e) {
			System.out.println("SyntaxBranch.whenRecursionMarkingRequestedThenPerformed()" 
					+ System.lineSeparator() + e.getMessage());
		}
		try {
			ITreePaths paths = branch.getTreePaths();
			//to see paths AFTER index marking :
			//System.out.println(paths.toString());
		} catch (GrammarModelException e) {
			System.out.println("SyntaxBranch.whenRecursionMarkingRequestedThenPerformed()" 
					+ System.lineSeparator() + e.getMessage());
		}
		for (ISyntacticStructure component : branch.getSetOfComponentsAndSubComponents()) {
			if (component.getRecursionIndex() > 0) {
				ISyntaxBranch branchComp = (ISyntaxBranch) component;
				if (!branchComp.getFunction().getName().contains(SyntaxLeaf.RECURSION_SYMBOL))
					markingPerformed = false;
			}
		}
		assertTrue(markingPerformed);
	}
	
	private IRelationalDescription buildExpectedRelationalDesc() throws RelationException {
		IRelationalDescription relationalDesc;
		ISymbol prey = new Symbol("prey");
		ISymbol position = new Symbol("position");
		ISymbol randomPlace = new Symbol("randomPlace");
		ISymbol grab = new Symbol("grab");
		ISymbol predate = new Symbol("predate");
		ISymbol provideFoodForTheGrubs = new Symbol("provideFoodForTheGrubs");
		ISymbol timePosition = new Symbol("timePosition");
		ISymbol step1 = new Symbol("step1");
		Set<ITotalOrder> propertiesAsOrders = new HashSet<ITotalOrder>();
		Set<IPair> randomPlaceSet = new HashSet<IPair>();
		Set<IPair> provideFoodSet = new HashSet<IPair>();
		Set<IPair> step1Set = new HashSet<IPair>();
		randomPlaceSet.add(new Pair(prey, position));
		randomPlaceSet.add(new Pair(position, randomPlace));
		randomPlaceSet.add(new Pair(prey, randomPlace));
		provideFoodSet.add(new Pair(prey, grab));
		provideFoodSet.add(new Pair(grab, predate));
		provideFoodSet.add(new Pair(prey, predate));
		provideFoodSet.add(new Pair(predate, provideFoodForTheGrubs));
		provideFoodSet.add(new Pair(prey, provideFoodForTheGrubs));
		provideFoodSet.add(new Pair(grab, provideFoodForTheGrubs));
		step1Set.add(new Pair(prey, timePosition));
		step1Set.add(new Pair(timePosition, step1));
		step1Set.add(new Pair(prey, step1));
		try {
			propertiesAsOrders.add(new TotalOrder(randomPlaceSet));
			propertiesAsOrders.add(new TotalOrder(provideFoodSet));
			propertiesAsOrders.add(new TotalOrder(step1Set));
		}
		catch (Exception e) {
			throw new RelationException("SyntaxBranchTest.buildExpectedRelationalDesc() : total orders instantiation "
					+ "has failed." + System.lineSeparator() + e.getMessage());
		}
		relationalDesc = new RelationalDescription(propertiesAsOrders, RelationalDescription.MAX_ORDERS);
		return relationalDesc;
	}

}
