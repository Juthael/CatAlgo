package propertyPoset.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fca.core.context.binary.BinaryContext;
import fca.exception.AlreadyExistsException;
import fca.exception.InvalidTypeException;
import grammarModel.structure.ISyntaxGrove;
import grammarModel.utils.IGenericFileReader;
import grammars.copycat2Strings.utils.CcFileReaderB;
import propertyPoset.IPropertyPoset;
import propertyPoset.IPropertySet;
import propertyPoset.IRelation;
import propertyPoset.exceptions.PropertyPosetException;

@SuppressWarnings("unused")
public class PropertyPosetTest {

	private static ISyntaxGrove grove;
	private static Path e2 = Paths.get(".", "src", "test", "java", "filesUsedForTests", "E2_a-bb-c_ijk.txt");		
	private IPropertyPoset propPosetBD1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		grove = setGrove(e2, new CcFileReaderB());
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
		/*
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
		*/
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
		/*assertTrue(valuesForDimension1BeforeAreAsExpected && valuesForDimension1AfterAreAsExpected 
				&& valuesForDimension1StarAfterAreAsExpected);
		*/
		assertTrue(false);
	}
	
	private static ISyntaxGrove setGrove(Path path, IGenericFileReader fileReader) {
		ISyntaxGrove grove = null;
		try {
			grove = fileReader.getSyntacticGrove(path);
			grove.markRecursion();
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