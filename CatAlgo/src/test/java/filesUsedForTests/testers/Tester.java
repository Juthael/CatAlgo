package filesUsedForTests.testers;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import fca.exception.LMLogger;
import fca.gui.lattice.LatticeViewer;
import fca.gui.lattice.element.GraphicalLattice;
import fca.gui.lattice.element.LatticeStructure;
import fca.gui.util.constant.LMIcons;
import fca.gui.util.constant.LMImages;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammars.copycatB.utils.CcFileReaderB;
import propertyPoset.IPropertyPoset;
import propertyPoset.impl.PropertyPoset;

public class Tester {

	//TestTest
	
	@SuppressWarnings("unused")
	private static Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "BD1_1_12_123.txt");
	@SuppressWarnings("unused")
	private static Path m1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "M1_abc_ijk.txt");
	@SuppressWarnings("unused")
	private static Path e2 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_ab-bc_ijk.txt");
	@SuppressWarnings("unused")
	private static Path e2b = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
	@SuppressWarnings("unused")
	private static Path e2b_valAcc = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk_valuesAccessible.txt");
	
	@SuppressWarnings("unused")
	private static Path e2_ijk = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_ijk.txt");
	
	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		ISyntaxGrove testGrove = setGrove(e2b_valAcc, new CcFileReaderB());
		IPropertyPoset testPoset = null;
		try {
			System.out.println(testGrove.getPosetMaxChains().getChainsInASingleString());
			testPoset = new PropertyPoset(testGrove.getPosetMaxChains());
		}
		catch (Exception e) {
			System.out.println("PropertyPosetTest : error during PropertyPoset instantiation " 
					+ System.lineSeparator() + e.getMessage());
		}
		//testPoset.reducePoset();
		BinaryContext context = testPoset.getBinaryContext();
	
		LMLogger.getLMLogger();
		LMImages.getLMImages();
		LMIcons.getLMIcons();

		ConceptLattice conLattice = new ConceptLattice(context);
		LatticeStructure lattStruc = new LatticeStructure(conLattice, context, LatticeStructure.BEST);
		GraphicalLattice graphLatt = new GraphicalLattice(conLattice, lattStruc);
		LatticeViewer lattViewer = new LatticeViewer(graphLatt);
		lattViewer.setExtendedState(Frame.MAXIMIZED_BOTH);
		lattViewer.setVisible(true); 
			
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
			grove.markRedundancies();
			grove.setPosetElementID();
		}
		catch (Exception e) {
			System.out.print("PropertySetTest : error during SyntacticGrove instantiation. " + System.lineSeparator() 
				+ e.getMessage());
		}
		// printChains(trueGrove.getListOfSyntacticStringChains());	
		return grove;
	}

}
