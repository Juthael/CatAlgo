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
import grammars.copycatB.utils.CcFileReaderB;
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
	private static Path m1 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "M1_abc_ijk.txt");
	private static Path e2 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_ab-bc_ijk.txt");
	private static Path e2b = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");
	private static Path e2b_valAcc = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk_valuesAccessible.txt");
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
		propPosetBD1.reducePoset();
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
	public void thisTestCanBeUsedToAnalyzeSyntaxTreesStoredInATextFile() 
			throws PropertyPosetException, AlreadyExistsException, InvalidTypeException {
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
		testPoset.reducePoset();
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
			
		System.out.println("STOP");
	}	
	
	
	@Test
	public void whenPosetReductionCalledThenSupIrreducibleLeavesAreRemovedAndEncapsulated() throws Exception {
		boolean supIrreducibleLeavesAreRemoved;
		boolean removedPropAreEncapsulated = false;
		Set<String> leaves = propPosetBD1.getRelation().getPosetleaves();
		Set<String> supIrreducibleLeaves = new HashSet<String>();
		for (String leaf : leaves) {
			if (propPosetBD1.getRelation().getPredecessors(leaf).size() == 1)
				supIrreducibleLeaves.add(leaf);
		}
		propPosetBD1.reducePoset();
		Set<String> reducedPosetProperties = propPosetBD1.getProperties().getSetOfPropertyNames();
		supIrreducibleLeavesAreRemoved = !reducedPosetProperties.removeAll(supIrreducibleLeaves);
		if (supIrreducibleLeavesAreRemoved) {
			Set<IProperty> encapsulatedProperties = new HashSet<IProperty>();
			for (IProperty property : propPosetBD1.getProperties().getSetOfProperties()) {
				encapsulatedProperties.addAll(property.getEncapsulatedProperties());				
			}
			Set<IProperty> newEncaps = new HashSet<IProperty>(encapsulatedProperties);
			while (!newEncaps.isEmpty()) {
				Set<IProperty> subEncaps = new HashSet<IProperty>();
				for (IProperty encaps : newEncaps) {
					subEncaps.addAll(encaps.getEncapsulatedProperties());
				}
				newEncaps = subEncaps;
				encapsulatedProperties.addAll(subEncaps);
			}
			for (IProperty encapsProp : encapsulatedProperties) {
				supIrreducibleLeaves.remove(encapsProp.getPropertyName());
			}
			removedPropAreEncapsulated = supIrreducibleLeaves.isEmpty();
		}
		assertTrue(supIrreducibleLeavesAreRemoved && removedPropAreEncapsulated);
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
