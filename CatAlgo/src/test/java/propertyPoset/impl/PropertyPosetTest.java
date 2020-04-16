package propertyPoset.impl;

import static org.junit.Assert.assertTrue;

import java.awt.Frame;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import fca.exception.AlreadyExistsException;
import fca.exception.InvalidTypeException;
import fca.exception.LMLogger;
import fca.gui.lattice.LatticeViewer;
import fca.gui.lattice.element.GraphicalLattice;
import fca.gui.lattice.element.LatticeStructure;
import fca.gui.util.constant.LMIcons;
import fca.gui.util.constant.LMImages;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammars.copycat.utils.CcFileReader;
import grammars.copycat2Strings.utils.CcFileReaderB;
import grammars.seekWhence.utils.SwFileReader;
import propertyPoset.IProperty;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;

@SuppressWarnings("unused")
public class PropertyPosetTest {

	private static ISyntaxGrove grove;
	private static Path backburnDozen1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "BD1_1_12_123.txt");
	private IPropertyPoset propPosetBD1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		grove = setGrove(backburnDozen1, new SwFileReader());
	}

	@Before
	public void setUp() throws Exception {
		try {
			//System.out.println(trueGrove.getPosetMaxChains().getChainsInASingleString());
			propPosetBD1 = new PropertyPoset(grove.getPosetMaxChains());
		}
		catch (Exception e) {
			System.out.println("PropertyPoSetTest : error during PropertyPoset instantiation " 
					+ System.lineSeparator() + e.getMessage());
		}	
	}

	@Test
	public void whenPropertiesRequestedThenNonEmptyPropertySetReturned() {
		boolean nonEmptyPropSetReturned;
		IPropertySet propSet = propPosetBD1.getProperties();
		nonEmptyPropSetReturned = !propSet.getSetOfProperties().isEmpty();
		assertTrue(nonEmptyPropSetReturned);
	}
	
	@Test
	public void whenRelationRequestedThenRelationReturned() {
		IRelation relation = propPosetBD1.getRelation();
		assertTrue(relation != null);
	}
	
	@Test
	public void whenBinaryContextRequestedThenBinaryContextReturned() 
			throws PropertyPosetException, AlreadyExistsException,	InvalidTypeException {
		BinaryContext context = propPosetBD1.getBinaryContext();
		/*		
		LMLogger.getLMLogger();
		LMImages.getLMImages();
		LMIcons.getLMIcons();
		ConceptLattice conLattice = new ConceptLattice(context);
		LatticeStructure lattStruc = new LatticeStructure(conLattice, context, LatticeStructure.BEST);
		GraphicalLattice graphLatt = new GraphicalLattice(conLattice, lattStruc);
		LatticeViewer lattViewer = new LatticeViewer(graphLatt);
		lattViewer.setExtendedState(Frame.MAXIMIZED_BOTH);
		lattViewer.setVisible(true); 
		*/
		assertTrue(context != null);
	}
	
	@Test
	public void whenEnsureThatDimensionsHaveIndependentValuesIsCalledThenAsExpected() throws Exception {
		boolean valuesForDimension1BeforeAreAsExpected;
		boolean valuesForDimension1AfterAreAsExpected;
		boolean valuesForDimension1StarAfterAreAsExpected;
		Set<String> expectedPredecessorsOf1Before = new HashSet<String>();
		Set<String> expectedPredecessorsOf1After = new HashSet<String>();
		Set<String> expectedPredecessorsOf1StarAfter = new HashSet<String>();
		expectedPredecessorsOf1Before.add("1_1");
		expectedPredecessorsOf1Before.add("1_2");
		expectedPredecessorsOf1Before.add("1_3");
		expectedPredecessorsOf1After.addAll(expectedPredecessorsOf1Before);
		expectedPredecessorsOf1Before.add("FirstValue1");
		expectedPredecessorsOf1StarAfter.add("FirstValue1");
		expectedPredecessorsOf1Before.add("Increment2");
		expectedPredecessorsOf1StarAfter.add("Increment2");
		Set<String> predecessorsBefore = propPosetBD1.getRelation().getPredecessors("1");
		//BinaryContext contextBefore = propPosetBD1.getBinaryContext();
		valuesForDimension1BeforeAreAsExpected =  
				propPosetBD1.getRelation().getPredecessors("1").equals(expectedPredecessorsOf1Before);
		propPosetBD1.ensureDimensionsHaveIndependentValues();
		valuesForDimension1AfterAreAsExpected = 
				propPosetBD1.getRelation().getPredecessors("1").equals(expectedPredecessorsOf1After);
		valuesForDimension1StarAfterAreAsExpected = 
				propPosetBD1.getRelation().getPredecessors("1*").equals(expectedPredecessorsOf1StarAfter);
		//BinaryContext contextAfter = propPosetBD1.getBinaryContext();
		/*
		LMLogger.getLMLogger();
		LMImages.getLMImages();
		LMIcons.getLMIcons();
		ConceptLattice conLatticeBefore = new ConceptLattice(contextBefore);
		LatticeStructure lattStrucBefore = new LatticeStructure(conLatticeBefore, contextBefore, LatticeStructure.BEST);
		GraphicalLattice graphLattBefore = new GraphicalLattice(conLatticeBefore, lattStrucBefore);
		LatticeViewer lattViewerBefore = new LatticeViewer(graphLattBefore);
		lattViewerBefore.setExtendedState(Frame.MAXIMIZED_BOTH);
		lattViewerBefore.setVisible(true); 
		ConceptLattice conLatticeAfter = new ConceptLattice(contextAfter);
		LatticeStructure lattStrucAfter = new LatticeStructure(conLatticeAfter, contextAfter, LatticeStructure.BEST);
		GraphicalLattice graphLattAfter = new GraphicalLattice(conLatticeAfter, lattStrucAfter);
		LatticeViewer lattViewerAfter = new LatticeViewer(graphLattAfter);
		lattViewerAfter.setExtendedState(Frame.MAXIMIZED_BOTH);
		lattViewerAfter.setVisible(true); 
		System.out.println("HERE");
		*/
		assertTrue(valuesForDimension1BeforeAreAsExpected && valuesForDimension1AfterAreAsExpected 
				&& valuesForDimension1StarAfterAreAsExpected);
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

	/*
	
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
