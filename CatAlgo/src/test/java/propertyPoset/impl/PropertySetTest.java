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
import grammars.seekWhence.utils.impl.SwFileReader;
import propertyPoset.IOriginalPropertyPoset;
import propertyPoset.IProperty;
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
		IOriginalPropertyPoset propPoset = null;
		try {
			//System.out.println(grove.getPosetMaxChains().getChainsInASingleString());
			propPoset = new OriginalPropertyPoset(grove.getPosetMaxChains());
		}
		catch (Exception e) {
			System.out.println("PropertySetTest : error during OriginalPropertyPoset instantiation." + System.lineSeparator() 
				+ e.getMessage());
		}
		set = propPoset.getProperties();
	}

	@Test
	public void whenPropertyRemovedWith1ArgMethodThenCannotBeRetreived() throws PropertyPosetException {
		Set<String> propNames = set.getSetOfPropertyNames();
		String propToRemove = propNames.iterator().next();
		@SuppressWarnings("unused")
		IProperty propertyTR = set.getProperty(propToRemove);
		set.removeProperty(propToRemove);
		boolean exceptionThrownWhenRemovedPropRequested = false;
		try {
			set.getProperty(propToRemove);
		}
		catch(PropertyPosetException p) {
			exceptionThrownWhenRemovedPropRequested = true;
		}
		assertTrue(exceptionThrownWhenRemovedPropRequested);
	}
	
	@Test
	public void whenPropertyRemovedWith2ArgsMethodThenCanBeRetreivedAsEncapsulated() throws PropertyPosetException {
		Set<String> propNames = set.getSetOfPropertyNames();
		Iterator<String> propNamesIterator = propNames.iterator();
		String propToBeEncapsulatedName = propNamesIterator.next();
		String encapsulatingPropName = propNamesIterator.next();
		IProperty propToBeEncapsulated = set.getProperty(propToBeEncapsulatedName);
		set.removeProperty(propToBeEncapsulatedName, encapsulatingPropName);
		IProperty retreivedProperty = set.getProperty(encapsulatingPropName).getEncapsulatedProperties().iterator().next();
		assertTrue(propToBeEncapsulated == retreivedProperty);
	}

}
