package grammars.seekWhence.utils.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

public class SwFileReaderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void whenAPathToACorrectTextFileIsGivenAsParameterThenASyntacticGroveIsReturned() {
		boolean aGroveHasBeenReturned = false;
		Path backburnDozen1 = Paths.get("D:","GoogleDrive","0_Doctorat","Modèle","grammars",
				"tests","seekWhence","BlackburnDozen","BlackburnDozen1","BD1_1_12_123.txt");
		SwFileReader fileReader = new SwFileReader();
		try {
			fileReader.getSyntacticGrove(backburnDozen1);
			aGroveHasBeenReturned = true;
		}
		catch (Exception e) {
			System.out.print(e.getMessage());
		}
		assertTrue(aGroveHasBeenReturned);
	}

}
