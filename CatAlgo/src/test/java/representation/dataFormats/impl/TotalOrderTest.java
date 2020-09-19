package representation.dataFormats.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import representation.dataFormats.IPair;
import representation.dataFormats.ITotalOrder;
import representation.dataFormats.impl.Pair;
import representation.dataFormats.impl.TotalOrder;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;

public class TotalOrderTest {

	private static ITotalOrder totalOrder;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		ISymbol a = new Symbol("a");
		ISymbol b = new Symbol("b");
		ISymbol c = new Symbol("c");
		ISymbol d = new Symbol("d");
		IPair ab = new Pair(a, b);
		IPair ac = new Pair(a, c);
		IPair ad = new Pair(a, d);
		IPair bc = new Pair(b, c);
		IPair bd = new Pair(b, d);
		IPair cd = new Pair(c, d);
		Set<IPair> pairs = new HashSet<IPair>(Arrays.asList(ab, ac, ad, bc, bd, cd));
		try {
			totalOrder = new TotalOrder(pairs);
		} catch (RepresentationException e) {
			System.out.println("TotalOrderTest.setUpBeforeClass() : " + System.lineSeparator() + e.getMessage());
		}
	}
	
	@Test
	public void whenSetOfSubOrdersRequestedThenExpectedReturned() {
		Set<ITotalOrder> expectedSet = buildExpectedSet();
		//To see expected set : 
		/*
		System.out.println("EXPECTED : ");
		for (ITotalOrder order : expectedSet) {
			System.out.println(order.toString());
		}
		*/
		Set<ITotalOrder> returnedSet = totalOrder.getSetOfSubOrders();
		//To see returned set :
		/*
		System.out.println("RETURNED : ");
		for (ITotalOrder order : returnedSet) {
			System.out.println(order.toString());
		}
		*/
		assertTrue(expectedSet.equals(returnedSet));
	}
	
	private static Set<ITotalOrder> buildExpectedSet(){
		Set<ITotalOrder> expectedSet = new HashSet<ITotalOrder>();
		Set<List<ISymbol>> setsOfSubProp = new HashSet<List<ISymbol>>();
		ISymbol a = new Symbol("a");
		ISymbol b = new Symbol("b");
		ISymbol c = new Symbol("c");
		ISymbol d = new Symbol("d");
		//1
		setsOfSubProp.add(new ArrayList<ISymbol>(Arrays.asList(a)));
		//2
		setsOfSubProp.add(new ArrayList<ISymbol>(Arrays.asList(a, b)));
		setsOfSubProp.add(new ArrayList<ISymbol>(Arrays.asList(a, c)));
		setsOfSubProp.add(new ArrayList<ISymbol>(Arrays.asList(a, d)));
		//3
		setsOfSubProp.add(new ArrayList<ISymbol>(Arrays.asList(a, b, c)));
		setsOfSubProp.add(new ArrayList<ISymbol>(Arrays.asList(a, b, d)));
		setsOfSubProp.add(new ArrayList<ISymbol>(Arrays.asList(a, c, d)));
		//4
		setsOfSubProp.add(new ArrayList<ISymbol>(Arrays.asList(a, b, c, d)));
		for (List<ISymbol> subProp : setsOfSubProp) {
			expectedSet.add(new TotalOrder(subProp));
		}
		return expectedSet;
	}

}
