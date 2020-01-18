package grammarModel.genericTools.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.genericTools.IPosetMaxChains;
import grammarModel.structure.ISyntacticGrove;
import grammars.seekWhence.utils.impl.SwFileReader;
import propertyPoset.IImplication;

public class PosetMaxChainsTest {

	public static ISyntacticGrove grove;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Path backburnDozen1 = Paths.get("D:","GoogleDrive","0_Doctorat","Modèle","grammars",
				"tests","seekWhence","BlackburnDozen","BlackburnDozen1","BD1_1_12_123.txt");
		SwFileReader fileReader = new SwFileReader();
		try {
			grove = fileReader.getSyntacticGrove(backburnDozen1);
			// printChains(grove.getListOfSyntacticStringChains());
		}
		catch (Exception e) {
			System.out.println("PosetMaxChainsTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}

	@Test
	public void whenGetImplicationsCalledThenReturnsImplications() {
		boolean implicationsReturned = true;
		ISyntacticGrove groveClone = (ISyntacticGrove) grove.clone();
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
				//printImplications(implications);
			}
		}
		implicationsReturned = (implications != null && !implications.isEmpty());		
		assertTrue(implicationsReturned);
	}
	
	@Test
	public void eitherWayOfInstantiatingPosetMaxChainsGivesTheSameSetOfImplications() {
		boolean sameSetOfImplications = true;
		ISyntacticGrove groveClone = (ISyntacticGrove) grove.clone();
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
			implications2 = posetChains2.getImplications();
			if (implications1 == null || implications2 == null || implications1.isEmpty() || implications2.isEmpty())
				sameSetOfImplications = false;
			else {
				sameSetOfImplications = (implications1.equals(implications2));
			}
		}
		assertTrue(sameSetOfImplications);
	}
	
	@SuppressWarnings("unused")
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
