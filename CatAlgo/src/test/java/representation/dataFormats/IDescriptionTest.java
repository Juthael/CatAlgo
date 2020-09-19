package representation.dataFormats;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import representation.dataFormats.impl.RelationalDescription;
import representation.dataFormats.impl.TotalOrder;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;

public class IDescriptionTest {

	private static IDescription abcd_aefg;
	private static IDescription ahd_ahfi_aj;
	
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
		ISymbol h = new Symbol("h");
		ISymbol i = new Symbol("i");
		ISymbol j = new Symbol("j");
		//chains at use
		ITotalOrder abcd = new TotalOrder(Arrays.asList(a, b, c, d));
		ITotalOrder aefg = new TotalOrder(Arrays.asList(a, e, f, g));
		ITotalOrder ahd = new TotalOrder(Arrays.asList(a, h, d));
		ITotalOrder ahfi = new TotalOrder(Arrays.asList(a, h, f, i));
		ITotalOrder ajbc = new TotalOrder(Arrays.asList(a, j, b, c));
		//relational descriptions
		//abcd_aefg
		abcd_aefg = new RelationalDescription(
				new HashSet<ITotalOrder>(Arrays.asList(abcd, aefg)), RelationalDescription.MAX_ORDERS);
		ahd_ahfi_aj = new RelationalDescription(
				new HashSet<ITotalOrder>(Arrays.asList(ahd, ahfi, ajbc)), RelationalDescription.MAX_ORDERS);
	}
	
	@Test
	public void whenAbstractionRequestedThenExpectedDescriptionReturned() {
		boolean expectedDescriptionReturned = true;
		IDescription expectedDescription = buildExpectedDescription();
		//to see expected description :
		/*
		IRelationalDescription expectedRelationalDesc = (IRelationalDescription) expectedDescription;
		System.out.println("EXPECTED : ");
		System.out.println(expectedRelationalDesc.toString());
		*/
		IDescription abstractDescription = null;
		try {
			abstractDescription = 
					IDescription.doAbstract(new HashSet<IDescription>(Arrays.asList(abcd_aefg, ahd_ahfi_aj)));
		} catch (RepresentationException e) {
			System.out.println("IDescriptionTest.whenAbstractionRequestedThenExpectedDescriptionReturned() : " 
					+ System.lineSeparator() + e.getMessage());
			expectedDescriptionReturned = false;
		}
		//to see returned description :
		/*
		IRelationalDescription abstractRelationalDesc = (IRelationalDescription) abstractDescription;
		System.out.println("RETURNED : ");
		System.out.println(abstractRelationalDesc.toString());
		*/
		expectedDescriptionReturned = expectedDescription.equals(abstractDescription);
		assertTrue(expectedDescriptionReturned);
	}
	
	private IDescription buildExpectedDescription() {
		IDescription description;
		//symbols at use
		ISymbol a = new Symbol("a");
		ISymbol b = new Symbol("b");
		ISymbol c = new Symbol("c");
		ISymbol d = new Symbol("d");
		ISymbol f = new Symbol("f");
		//chains at use
		ITotalOrder abc = new TotalOrder(Arrays.asList(a, b, c));
		ITotalOrder ad = new TotalOrder(Arrays.asList(a, d));
		ITotalOrder af = new TotalOrder(Arrays.asList(a, f));
		//relational description
		description = new RelationalDescription(
				new HashSet<ITotalOrder>(Arrays.asList(abc, ad, af)), RelationalDescription.MAX_ORDERS);
		return description;
	}

}
