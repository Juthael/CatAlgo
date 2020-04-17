package propertyPoset.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;

import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammars.copycat2Strings.utils.CcFileReaderB;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;

public class PropertySetTest {
	
	private static ISyntaxGrove grove;
	private static IPropertySet set;
	

	@BeforeClass
	public static void setUpBeforeClass() {
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");		
		IGenericFileReader fileReader = new CcFileReaderB();
		try {
			grove = fileReader.getSyntacticGrove(backburnDozen1);
			grove.setPosetElementID();
		}
		catch (Exception e) {
			System.out.print("PropertySetTest : error during SyntacticGrove instantiation. " + System.lineSeparator() 
				+ e.getMessage());
		}
		// printChains(grove.getListOfSyntacticStringChains());
	}
	
	@Before
	public void setUp() {
		IPropertyPoset propPoset = null;
		try {
			//System.out.println(grove.getPosetMaxChains().getChainsInASingleString());
			propPoset = new PropertyPoset(grove.getPosetMaxChains());
		}
		catch (Exception e) {
			System.out.println("PropertySetTest : error during OriginalPropertyPoset instantiation." + System.lineSeparator() 
				+ e.getMessage());
			e.printStackTrace();
		}
		set = propPoset.getProperties();
	}

}
