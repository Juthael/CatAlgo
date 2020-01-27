package grammars.seekWhence.utils.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.structure.ISyntaxGrove;

@SuppressWarnings("unused")
public class SwFileReaderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void whenAPathToACorrectTextFileIsGivenAsParameterThenASyntacticGroveIsReturned() {
		boolean aGroveHasBeenReturned = false;
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "BD1_1_12_123.txt");
		SwFileReader fileReader = new SwFileReader();
		ISyntaxGrove grove;
		try {
			grove = fileReader.getSyntacticGrove(backburnDozen1);
			/*
			List<List<String>> paths = grove.getListOfSyntacticStringChains();
			StringBuilder sB = new StringBuilder();
			for (List<String> path : paths) {
				for (int i = 0 ; i < path.size() ; i++) {
					sB.append(path.get(i));
					if (i < path.size() - 1)
						sB.append("/");
				}
				sB.append(System.lineSeparator());
			}
			*/
			aGroveHasBeenReturned = true;
		}
		catch (Exception e) {
			System.out.print(e.getMessage());
		}
		assertTrue(aGroveHasBeenReturned);
	}	

}
