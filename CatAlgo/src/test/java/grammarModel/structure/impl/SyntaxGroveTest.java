package grammarModel.structure.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IChains;
import grammars.seekWhence.utils.SwFileReader;
import grammarModel.structure.ISyntacticStructure;

public class SyntaxGroveTest {

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
			System.out.println("SyntaxGroveTest.setUpBeforClass() : " + System.lineSeparator() + e.getMessage());
		}
	}
	
	@Test
	public void whenSetPosetElementIDIsCalledThenEveryStructureHasAPosetID() {
		boolean everyStructureHasAnID = true;
		ISyntaxGrove grove1 = (ISyntaxGrove) grove.clone();
		try {
			grove1.setPosetElementID();
		}
		catch (Exception e) {
			everyStructureHasAnID = false;
			System.out.println("SyntaxGroveTest.whenSetPosetElementIDIsCalledThenEveryStructureHasAPosetID() : "
					+ System.lineSeparator() +  "error during posetID setting." + System.lineSeparator()
					+ e.getMessage());
		}
		if (everyStructureHasAnID == true && grove1 != null) {
			List<ISyntacticStructure> allcomponents = getAllComponents(grove1);
			if (allcomponents.size() <= 1) {
				everyStructureHasAnID = false;
				System.out.println("SyntaxGroveTest.whenSetPosetElementIDIsCalledThenEveryStructureHasAPosetID() : "
						+ System.lineSeparator() +  "error during components retrieval." + System.lineSeparator());
			}
			else {
				int allComponentsIndex = 0;
				while (everyStructureHasAnID == true && allComponentsIndex < allcomponents.size()) {
					ISyntacticStructure currentComponent = allcomponents.get(allComponentsIndex);
					if (!currentComponent.getIDHasBeenSet()) {
						everyStructureHasAnID = false;
						System.out.println("SyntaxGroveTest"
								+ ".whenSetPosetElementIDIsCalledThenEveryStructureHasAPosetID() : "
								+ System.lineSeparator() +  "ID hasn't been set for at least one element." 
								+ System.lineSeparator());				
					}
					else {
						try {
							String currentPosetElementName = currentComponent.getPosetElementName();
							// System.out.println(currentPosetElementName);
							if (currentPosetElementName.isEmpty()) {
								everyStructureHasAnID = false;
								System.out.println("SyntaxGroveTest"
										+ ".whenSetPosetElementIDIsCalledThenEveryStructureHasAPosetID() : "
										+ System.lineSeparator() +  "one poset element name at least is empty." + 
										System.lineSeparator());
							}
						}
						catch (Exception e) {
							everyStructureHasAnID = false;
							System.out.println("SyntaxGroveTest"
									+ ".whenSetPosetElementIDIsCalledThenEveryStructureHasAPosetID() : "
									+ System.lineSeparator() +  "a poset element name couldn't be retrieved." + System.lineSeparator()
									+ e.getMessage());
						}
					}
					allComponentsIndex++;
				}	
			}
		}
		assertTrue(everyStructureHasAnID);
	}
	
	@SuppressWarnings("unused")
	private static void printChains(IChains chains) {
		printChains(chains.getChains());
	}
	
	private static void printChains(List<List<String>> paths) {
		StringBuilder sB = new StringBuilder();
		for (List<String> path : paths) {
			for (int i = 0 ; i < path.size() ; i++) {
				sB.append(path.get(i));
				if (i < path.size() - 1)
					sB.append("/");
			}
			sB.append(System.lineSeparator());
		}
		System.out.println(sB.toString());
	}	
	
	private static List<ISyntacticStructure> getAllComponents(ISyntacticStructure grove){
		List<ISyntacticStructure> allComponents = new ArrayList<ISyntacticStructure>();
		allComponents.add(grove);
		for (ISyntacticStructure component : grove.getListOfComponents()) {
			allComponents.addAll(getAllComponents(component));
		}
		return allComponents;
	}

}
