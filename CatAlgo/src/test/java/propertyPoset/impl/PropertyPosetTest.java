package propertyPoset.impl;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fca.core.context.binary.BinaryContext;
import grammarModel.structure.ISyntaxGrove;
import grammars.seekWhence.utils.impl.SwFileReader;
import propertyPoset.IProperty;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;

public class PropertyPosetTest {

	private static ISyntaxGrove grove;
	private IPropertyPoset propPoset;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setGrove();
	}

	@Before
	public void setUp() throws Exception {
		try {
			//System.out.println(trueGrove.getPosetMaxChains().getChainsInASingleString());
			propPoset = new PropertyPoset(grove.getPosetMaxChains());
		}
		catch (Exception e) {
			System.out.println("PropertyPoSetTest : error during OriginalPropertyPoset instantiation " 
					+ System.lineSeparator() + e.getMessage());
		}	
	}

	@Test
	public void whenPropertiesRequestedThenNonEmptyPropertySetReturned() {
		boolean nonEmptyPropSetReturned;
		IPropertySet propSet = propPoset.getProperties();
		nonEmptyPropSetReturned = !propSet.getSetOfProperties().isEmpty();
		assertTrue(nonEmptyPropSetReturned);
	}
	
	@Test
	public void whenRelationRequestedThenRelationReturned() {
		IRelation relation = propPoset.getRelation();
		assertTrue(relation != null);
	}
	
	@Test
	public void whenBinaryContextRequestedThenBinaryContextReturned() throws PropertyPosetException {
		BinaryContext context = propPoset.getBinaryContext();
		assertTrue(context != null);
	}
	
	@Test
	public void whenPosetReductionCalledThenNonInformativePropertiesRemovedAndEncapsulated() throws PropertyPosetException {
		boolean requestForSizEAfterReducThrowsException = false;
		boolean sizeCanBeRetreivedAsEncapsPropertyOfSize1 = false;
		@SuppressWarnings("unused")
		IProperty propSizE = propPoset.getProperties().getProperty("SizE");
		propPoset.reducePoset();
		try {
			@SuppressWarnings("unused")
			IProperty propSizENow = propPoset.getProperties().getProperty("SizE");	
		}
		catch (PropertyPosetException e) {
			requestForSizEAfterReducThrowsException = true;
		}
		IProperty propSize1 = propPoset.getProperties().getProperty("Size1");
		Set<IProperty> size1EncapsulatedProp = propSize1.getEncapsulatedProperties();
		for (IProperty encapsProp : size1EncapsulatedProp) {
			if (encapsProp.getPropertyName().equals("SizE"))
				sizeCanBeRetreivedAsEncapsPropertyOfSize1 = true;
		}
		assertTrue(requestForSizEAfterReducThrowsException && sizeCanBeRetreivedAsEncapsPropertyOfSize1);
	}
	
	@Test
	public void whenSubContextExtractionCalledThenExpectedSubContextsExtracted() throws PropertyPosetException {
		boolean expectedSubCon;
		boolean expectedSubSubCon;
		boolean noSubSubSubCon;
		propPoset.extractSubContexts();
		Set<IPropertyPoset> subContexts = propPoset.getSubContexts();
		if (subContexts.size() != 1) {
			expectedSubCon = false;
			expectedSubSubCon = false;
			noSubSubSubCon = false;
		}
		else {
			IPropertyPoset subContext = subContexts.iterator().next();
			if (!subContext.getRelation().getPosetRoot().equals("Relation2")) {
				expectedSubCon = false;
				expectedSubSubCon = false;
				noSubSubSubCon = false;
			}
			else {
				expectedSubCon = true;
				Set<IPropertyPoset> subSubContexts = subContext.getSubContexts();
				if (subContexts.size() != 1) {
					expectedSubSubCon = false;
					noSubSubSubCon = false;
				}
				else {
					IPropertyPoset subSubContext = subSubContexts.iterator().next();
					if (!subSubContext.getRelation().getPosetRoot().equals("ArithSeq2")) {
						expectedSubSubCon = false;
						noSubSubSubCon = false;
					}
					else {
						expectedSubSubCon = true;
						noSubSubSubCon = (subSubContext.getSubContexts().size() == 0);
					}
					
				}
			}
		}
		assertTrue(expectedSubCon && expectedSubSubCon && noSubSubSubCon);
	}
	
	private static void setGrove() {
		Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "BD1_1_12_123.txt");
		SwFileReader fileReader = new SwFileReader();
		try {
			grove = fileReader.getSyntacticGrove(backburnDozen1);
			grove.markRedundancies();
			grove.setPosetElementID();
		}
		catch (Exception e) {
			System.out.print("PropertySetTest : error during SyntacticGrove instantiation. " + System.lineSeparator() 
				+ e.getMessage());
		}
		// printChains(trueGrove.getListOfSyntacticStringChains());	
	}	

	/* poset max chains : 
SeekWhenceCtxt0/Digit4/DigiT
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit4/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit4/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit4/Position4/PositioN
SeekWhenceCtxt0/Digit4/Position4/NoAlterN
SeekWhenceCtxt0/Digit4/Position4/1_1/1
SeekWhenceCtxt0/Digit1/DigiT
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit1/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit1/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit1/Position0/PositioN
SeekWhenceCtxt0/Digit1/Position0/NoAlterN
SeekWhenceCtxt0/Digit1/Position0/1_2/1
SeekWhenceCtxt0/Digit1/Position0/1_2/2
SeekWhenceCtxt0/Digit0/DigiT
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit0/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit0/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit0/Position3/PositioN
SeekWhenceCtxt0/Digit0/Position3/NoAlterN
SeekWhenceCtxt0/Digit0/Position3/2_2/2
SeekWhenceCtxt0/Digit3/DigiT
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit3/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit3/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit3/Position2/PositioN
SeekWhenceCtxt0/Digit3/Position2/NoAlterN
SeekWhenceCtxt0/Digit3/Position2/1_3/1
SeekWhenceCtxt0/Digit3/Position2/1_3/3
SeekWhenceCtxt0/Digit2/DigiT
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit2/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit2/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit2/Position1/PositioN
SeekWhenceCtxt0/Digit2/Position1/NoAlterN
SeekWhenceCtxt0/Digit2/Position1/2_3/2
SeekWhenceCtxt0/Digit2/Position1/2_3/3
SeekWhenceCtxt0/Digit5/DigiT
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/RelatioN
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/Size1/SizE
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/Size1/3
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/ArithSeQ
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/FirstValue1/1
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/Increment2/IncremenT
SeekWhenceCtxt0/Digit5/Relation2/Size0/Relation1/ArithSeq1/Increment2/1
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/RelatioN
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/Size1/3
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/FirstValue0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/RelatioN
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/Size1/SizE
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/Size1/3
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/ArithSeQ
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/FirstValuE
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/FirstValue1/1
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/IncremenT
SeekWhenceCtxt0/Digit5/Relation2/ArithSeq2/Increment0/Relation0/ArithSeq0/Increment1/0
SeekWhenceCtxt0/Digit5/Position5/PositioN
SeekWhenceCtxt0/Digit5/Position5/NoAlterN
SeekWhenceCtxt0/Digit5/Position5/3_3/3
	 */	
	
}
