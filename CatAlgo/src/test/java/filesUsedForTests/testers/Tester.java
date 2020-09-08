package filesUsedForTests.testers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammars.sphex.utils.SphexFileReader;

public class Tester {

	private static Path e2 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
	private static Path sphex = Paths.get(".", "src", "test", "java", "filesUsedForTests", "usualSphex.txt");
	
	public Tester() {
	}

	public static void main(String[] args) throws Exception {
		ISyntaxGrove testGrove = setGrove(sphex, new SphexFileReader());
		//ISyntaxGrove testGrove = setGrove(e2, new CcFileReaderB());
			
		System.out.println(System.lineSeparator() + "Press any key");
		readString();
	}
	
	private static String readString() {
		String stringInput = null;
		try {
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader input = new BufferedReader(reader);
			stringInput = input.readLine();
		}
		catch (IOException err) {
			System.exit(-1);
		}
		return stringInput;
	}
	
	private static ISyntaxGrove setGrove(Path path, IGenericFileReader fileReader) {
		ISyntaxGrove grove = null;
		try {
			grove = fileReader.getSyntacticGrove(path);
			grove.markRecursion();
		}
		catch (Exception e) {
			System.out.print("PropertySetTest : error during SyntacticGrove instantiation. " + System.lineSeparator() 
				+ e.getMessage());
		}	
		return grove;
	}

}
