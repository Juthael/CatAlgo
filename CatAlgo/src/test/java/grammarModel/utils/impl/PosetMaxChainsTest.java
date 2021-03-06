package grammarModel.utils.impl;

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
import propertyPoset.utils.IImplication;
import propertyPoset.utils.IPosetMaxChains;
import propertyPoset.utils.impl.PosetMaxChains;

@SuppressWarnings("unused")
public class PosetMaxChainsTest {

	public static ISyntaxGrove grove;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Path e2 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
		IGenericFileReader fileReader = new CcFileReaderB();
		try {
			grove = fileReader.getSyntacticGrove(e2);
			// printChains(grove.getListOfSyntacticStringChains());
		}
		catch (Exception e) {
			System.out.println("PosetMaxChainsTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}

	@Test
	public void whenGetImplicationsCalledThenReturnsImplications() {
		boolean implicationsReturned = true;
		ISyntaxGrove groveClone = (ISyntaxGrove) grove.clone();
		Set<IImplication> implications = null;
		try {
			groveClone.setPosetElementID();
		}
		catch (Exception e){
			implicationsReturned = false;
			System.out.println("PosetMaxChainsTest.whenGetImplicationsCalledThenReturnsImplications() : "
					+ System.lineSeparator() + "error during poset ID setting." 
					+ System.lineSeparator() + e.getMessage());
		}
		if (implicationsReturned) {
			IPosetMaxChains posetChains = null;
			try {
				posetChains = groveClone.getPosetMaxChains();
				if (posetChains == null || posetChains.getMaximalChains().isEmpty()) {
					throw new Exception("posetChains variable is null or empty.");
				}
			}
			catch (Exception e) {
				implicationsReturned = false;
				System.out.println("PosetMaxChainsTest.whenGetImplicationsCalledThenReturnsImplications() : "
						+ System.lineSeparator() + "IPosetMaxChains cannot be properly instantiated. " 
						+ System.lineSeparator() + e.getMessage());
			}
			if (implicationsReturned) {
				implications = posetChains.getImplications();
				// printImplications(implications);
			}
		}
		implicationsReturned = (implications != null && !implications.isEmpty());		
		assertTrue(implicationsReturned);
	}
	
	@Test
	public void eitherWayOfInstantiatingPosetMaxChainsGivesTheSameSetOfImplications() {
		boolean sameSetOfImplications = true;
		ISyntaxGrove groveClone = (ISyntaxGrove) grove.clone();
		IPosetMaxChains posetChains1 = null;
		IPosetMaxChains posetChains2 = null;
		Set<IImplication> implications1 = null;
		Set<IImplication> implications2 = null;
		try {
			groveClone.setPosetElementID();
		}
		catch (Exception e){
			sameSetOfImplications = false;
			System.out.println("PosetMaxChainsTest.eitherWayOfInstantiatingPosetMaxChainsGivesTheSameSetOfImplications() : "
					+ System.lineSeparator() + "error during poset ID setting." 
					+ System.lineSeparator() + e.getMessage());
		}
		if (sameSetOfImplications) {
			try {
				posetChains1 = groveClone.getPosetMaxChains();
				if (posetChains1 == null || posetChains1.getMaximalChains().isEmpty()) {
					throw new Exception("posetChains variable is null or empty.");
				}
			}
			catch (Exception e) {
				sameSetOfImplications = false;
				System.out.println("PosetMaxChainsTest.eitherWayOfInstantiatingPosetMaxChainsGivesTheSameSetOfImplications() : "
						+ System.lineSeparator() + "IPosetMaxChains cannot be properly instantiated. " 
						+ System.lineSeparator() + e.getMessage());
			}
			if (sameSetOfImplications) {
				try {
					posetChains2 = new PosetMaxChains(groveClone.getListOfPosetMaxStringChains());
				}
				catch (Exception e) {
					sameSetOfImplications = false;
					System.out.println("PosetMaxChainsTest"
							+ ".eitherWayOfInstantiatingPosetMaxChainsGivesTheSameSetOfImplications() : "
							+ System.lineSeparator() + "Error in the one-argument PosetMaxChains constructor. " 
							+ System.lineSeparator() + e.getMessage());
				}
			}
		}
		if (sameSetOfImplications) {
			implications1 = posetChains1.getImplications();
			// printImplications(implications1);
			implications2 = posetChains2.getImplications();
			// printImplications(implications2);
			if (implications1 == null || implications2 == null || implications1.isEmpty() || implications2.isEmpty())
				sameSetOfImplications = false;
			else {
				sameSetOfImplications = (implications1.equals(implications2));
				if (!sameSetOfImplications) {
					/*
					Set<IImplication> inSet1ButNotInSet2 = new HashSet<IImplication>();
					Set<IImplication> inSet2ButNotInSet1 = new HashSet<IImplication>();
					inSet1ButNotInSet2.addAll(implications1);
					inSet2ButNotInSet1.addAll(implications2);
					inSet1ButNotInSet2.removeAll(implications2);
					inSet2ButNotInSet1.removeAll(implications1);
					System.out.println("IN SET 1 BUT NOT IN SET 2 :");
					printImplications(inSet1ButNotInSet2);
					System.out.println("IN SET 2 BUT NOT IN SET 1 :");
					printImplications(inSet2ButNotInSet1);
					*/
				}
			}
		}
		assertTrue(sameSetOfImplications);
	}
	
	private static void printImplications(Set<IImplication> implications) {
		int i = 1;
		for (IImplication implication : implications) {
			System.out.println("Implication n. " + Integer.toString(i));
			System.out.println("antecedent : " + implication.getAntecedent());
			System.out.println("consequent : " + implication.getConsequent());
			System.out.println("");
			i++;
		}
	}


}
