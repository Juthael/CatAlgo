package propertyPoset.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import grammarModel.structure.ISyntaxGrove;
import grammars.seekWhence.utils.SwFileReader;
import propertyPoset.IProperty;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;
import propertyPoset.exceptions.PropertyPosetException;

public class PropertySetTest {
	
	private static ISyntaxGrove grove;
	private static IPropertySet set;
	

	@BeforeClass
	public static void setUpBeforeClass() {
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "BD1_1_12_123.txt");
		SwFileReader fileReader = new SwFileReader();
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
