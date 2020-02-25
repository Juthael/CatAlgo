package grammarModel.structure.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.GrammarModelConstants;
import grammarModel.structure.ISyntaxGrove;
import grammars.seekWhence.utils.SwFileReader;

public class SyntaxBranchTest {

	public static ISyntaxGrove grove;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "BD1_1_12_123.txt");
		SwFileReader fileReader = new SwFileReader();
		try {
			grove = fileReader.getSyntacticGrove(backburnDozen1);
			// printChains(grove.getListOfSyntacticStringChains());
		}
		catch (Exception e) {
			System.out.println("SyntaxBranchTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}

	@Test
	public void whenGetListOfSyntacticStringChainsIsCalledThenANonEmptyListIsReturned() {
		boolean nonEmptyListReturned = true;
		List<List<String>> listOfSyntacticStringChains;
		try {
			listOfSyntacticStringChains = grove.getListOfTreeStringPaths();
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
		assertTrue(nonEmptyListReturned);
	}
	
	@Test
	public void whenGetListOfPosetMaxStringChainsIsCalledThenANonEmptyListIsReturned()  {
		ISyntaxGrove groveClone = (ISyntaxGrove) grove.clone();
		boolean nonEmptyListReturned = true;
		try {
			groveClone.setPosetElementID();
		}
		catch (Exception e){
			nonEmptyListReturned = false;
			System.out.println("SyntaxBranchTest.whenGetListOfPosetMaxStringChainsIsCalledThenANonEmptyListIsReturned() : "
					+ System.lineSeparator() + "error during poset ID setting." 
					+ System.lineSeparator() + e.getMessage());
		}
		List<List<String>> listOfPosetMaxStringChains;
		try {
			listOfPosetMaxStringChains = groveClone.getListOfPosetMaxStringChains();
			if (listOfPosetMaxStringChains == null || listOfPosetMaxStringChains.isEmpty())
				throw new Exception("'listOfPosetMaxStringChains' variable is either null or empty.");
		}
		catch (Exception e) {
			nonEmptyListReturned = false;
			System.out.println("SyntaxBranchTest."
					+ "whenGetListOfPosetMaxStringChainsIsCalledThenANonEmptyListIsReturned() : "
					+ System.lineSeparator() + "cannot retrieve poset string chains." 
					+ System.lineSeparator() + e.getMessage());
		}
		assertTrue(nonEmptyListReturned);
	}
	
	@Test
	public void whenGetListOfLeafIDsIsCalledThenANonEmptyListIsReturned() {
		boolean nonEmptyListReturned = true;
		try {
			List<Long> leafIDs = grove.getListOfLeafIDs();
			if (leafIDs == null || leafIDs.isEmpty())
				throw new Exception("'leafIDs' variable is either null or empty");
		}
		catch (Exception e) {
			nonEmptyListReturned = false;
			System.out.println("SyntaxBranchTest."
					+ "whenGetListOfLeafIDsIsCalledThenANonEmptyListIsReturned() : "
					+ System.lineSeparator() + "cannot retrieve poset IDs." 
					+ System.lineSeparator() + e.getMessage());
		}
		assertTrue(nonEmptyListReturned);
	}
	
	@Test
	public void whenGetStringOfTerminalsIsCalledThenANonEmptyStringIsReturned() {
		boolean nonEmptyStringReturned = true;
		try {
			String terminals = grove.getStringOfTerminals();
			if (terminals == null || terminals.isEmpty())
				throw new Exception("'terminals' String is either null or empty.");
		}
		catch (Exception e) {
			nonEmptyStringReturned = false;
			System.out.println("SyntaxBranchTest."
					+ "whenGetStringOfTerminalsIsCalledThenANonEmptyStringIsReturned() : "
					+ System.lineSeparator() + "cannot retrieve terminals String." 
					+ System.lineSeparator() + e.getMessage());
		}
		assertTrue(nonEmptyStringReturned);
	}
	
	@Test
	public void whenNumberOfLeafIDsAndTerminalComparedThenProveEquals() {
		boolean sameNbOfLeafIDsAndTerminals = true;
		String terminals;
		String[] arrayOfTerminals = null;
		List<Long> leafIDs = null;
		try {
			terminals = grove.getStringOfTerminals();
			if (terminals == null || terminals.isEmpty())
				throw new Exception("'terminals' String is either null or empty.");
			else {
				terminals.replaceFirst(GrammarModelConstants.SEPARATOR, "");
				arrayOfTerminals = terminals.split(GrammarModelConstants.SEPARATOR);
			}
		}
		catch (Exception e) {
			sameNbOfLeafIDsAndTerminals = false;
			System.out.println("SyntaxBranchTest."
					+ "whenNumberOfLeafIDsAndTerminalComparedThenProveEquals() : "
					+ System.lineSeparator() + "cannot retrieve terminals String." 
					+ System.lineSeparator() + e.getMessage());
		}
		if (sameNbOfLeafIDsAndTerminals) {
			try {
				leafIDs = grove.getListOfLeafIDs();
				if (leafIDs == null || leafIDs.isEmpty())
					throw new Exception("'leafIDs' variable is either null or empty");
			}
			catch (Exception e) {
				sameNbOfLeafIDsAndTerminals = false;
				System.out.println("SyntaxBranchTest."
						+ "whenNumberOfLeafIDsAndTerminalComparedThenProveEquals() : "
						+ System.lineSeparator() + "cannot retrieve leaf IDs." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		if (sameNbOfLeafIDsAndTerminals) {
			sameNbOfLeafIDsAndTerminals = (arrayOfTerminals.length == leafIDs.size());
		}
		assertTrue(sameNbOfLeafIDsAndTerminals);
	}	

}
