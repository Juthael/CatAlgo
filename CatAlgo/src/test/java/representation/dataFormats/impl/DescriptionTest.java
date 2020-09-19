package representation.dataFormats.impl;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.ITotalOrder;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;

public class DescriptionTest {

	private static IRelationalDescription abcd_abef_adg_RD;
	private static ILanguage abcd_abef_adg_L;
	private static IFunctionalExpression abcd_abef_adg_FE;
	
	@BeforeClass
	public static void setUpBeforClass() {
		//symbols at use
		ISymbol a = new Symbol("a");
		ISymbol b = new Symbol("b");
		ISymbol c = new Symbol("c");
		ISymbol d = new Symbol("d");
		ISymbol e = new Symbol("e");
		ISymbol f = new Symbol("f");
		ISymbol g = new Symbol("g");
		//chains at use
		ITotalOrder abcd = new TotalOrder(Arrays.asList(a, b, c, d));
		ITotalOrder abef = new TotalOrder(Arrays.asList(a, b, e, f));
		ITotalOrder adg = new TotalOrder(Arrays.asList(a, d, g));
		//relational description
		//abcd_aefg
		abcd_abef_adg_RD = new RelationalDescription(
				new HashSet<ITotalOrder>(Arrays.asList(abcd, abef, adg)), RelationalDescription.MAX_ORDERS);
		try {
			abcd_abef_adg_L = abcd_abef_adg_RD.getLanguage();
		} catch (RepresentationException e1) {
			System.out.println("DescriptionTest.setUpBeforClass() : error while converting a relational "
					+ "description into a language");
		}
		try {
			abcd_abef_adg_FE = abcd_abef_adg_RD.getFunctionalExpression();
		} catch (RepresentationException e1) {
			System.out.println("DescriptionTest.setUpBeforClass() : error while converting a relational "
					+ "description into a functional expression");
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void whenSameDescriptionsWithDifferentFormatsComparedThenEqual() {
		boolean descriptionsAreEqual = (abcd_abef_adg_RD.equals(abcd_abef_adg_L));
		if (descriptionsAreEqual) {
			descriptionsAreEqual = (abcd_abef_adg_RD.equals(abcd_abef_adg_FE));
			if (descriptionsAreEqual)
				descriptionsAreEqual = (abcd_abef_adg_L.equals(abcd_abef_adg_FE));
		}
		assertTrue(descriptionsAreEqual);
	}

}
