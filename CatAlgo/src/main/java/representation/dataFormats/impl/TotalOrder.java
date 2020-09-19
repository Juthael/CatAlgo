package representation.dataFormats.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import representation.dataFormats.IPair;
import representation.dataFormats.ITotalOrder;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;

public class TotalOrder implements ITotalOrder {

	private final Set<IPair> orderedRelation;
	private final ISymbol minimum;
	private final List<ISymbol> property;
	
	public TotalOrder(Set<IPair> orderedRelation) throws RepresentationException {
		this.orderedRelation = orderedRelation;
		minimum = findMinimum();
		property = setProperty();
		consistencyCheck();
	}
	
	//The list should never be empty. 
	public TotalOrder(List<ISymbol> property) {
		this.property = property;
		orderedRelation = new HashSet<IPair>();
		minimum = property.get(0);
		for (int i = 0 ; i < property.size() ; i++) {
			for (int j = i+1 ; j< property.size() ; j++) {
				orderedRelation.add(new Pair(property.get(i), property.get(j)));
			}
		}
	}	
	
	private TotalOrder(Set<IPair> orderedRelation, ISymbol minimum, List<ISymbol> property) {
		this.orderedRelation = orderedRelation;
		this.minimum = minimum;
		this.property = property;
	}
	
	//getters
	
	@Override
	public ITotalOrder clone() {
		ITotalOrder cloneOrder = new TotalOrder(new HashSet<IPair>(orderedRelation), new Symbol(minimum.toString()), 
				new ArrayList<ISymbol>(property));
		return cloneOrder;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean thisEqualsOther;
		if (o == this)
			thisEqualsOther = true;
		else if (!(o instanceof representation.dataFormats.ITotalOrder))
			thisEqualsOther = false;
		else {
			ITotalOrder other = (ITotalOrder) o;
			thisEqualsOther = property.equals(other.getProperty());
		}
		return thisEqualsOther;
	}	
	
	@Override
	public ITotalOrder extendWithMinimum(ISymbol newMinimum) {
		ITotalOrder extendedOrder;
		Set<IPair> orderedRel = new HashSet<IPair>(orderedRelation);
		List<ISymbol> prop = new ArrayList<ISymbol>(property);
		for (ISymbol symbol : property)
			orderedRel.add(new Pair(newMinimum, symbol));
		prop.add(0, newMinimum);
		extendedOrder = new TotalOrder(orderedRel, newMinimum, prop);
		return extendedOrder;
	}	
	
	@Override
	public Set<IPair> getPairs(){
		return orderedRelation;
	}
	
	@Override
	public List<ISymbol> getProperty(){
		return property;
	}
	
	@Override 
	public int hashCode() {
		int hashCode = 1;
		for (ISymbol symbol : property) {
			hashCode += hashCode * symbol.hashCode();
		}
		return hashCode;
	}

	//REWRITE	
	@Override
	public boolean isSubSetOf(ITotalOrder other) {
		boolean subsetOfOther = 
				(other.getProperty().containsAll(property)
						&& other.getPairs().containsAll(orderedRelation));
		return subsetOfOther;
	}	

	//REWRITE
	@Override
	public boolean isSuperSetOf(ITotalOrder other) {
		boolean supersetOfOther = 
				(property.containsAll(other.getProperty())
						&& orderedRelation.containsAll(other.getPairs()));
		return supersetOfOther;
	}
	
	@Override
	public Set<ITotalOrder> getSetOfSubOrders(){
		Set<ITotalOrder> setOfSubOrders = new HashSet<ITotalOrder>();
		if (property.size() > 1) {
			/*
			 * By convention, every property on every object or category has to start with the "root" attribute 
			 * associated with the microworld it belongs to ("letter" in Copycat, "integer" in SeekWhence, etc.)
			 */
			List<ISymbol> propMinusMinimum = property.subList(1, property.size());
			int propMinusMinSize = propMinusMinimum.size();
			List<ISymbol> subProperty;
			for (int i = 0 ; i < (1<<propMinusMinSize) ; i++) {
				subProperty = new ArrayList<ISymbol>();
				subProperty.add(minimum);
				for (int j = 0 ; j < propMinusMinSize ; j++) {
					if ((i & (1<<j)) > 0) {
						subProperty.add(propMinusMinimum.get(j));
					}
				}
				setOfSubOrders.add(new TotalOrder(subProperty));
			}
		}
		else setOfSubOrders.add(this);
		return setOfSubOrders;
	}
	
	@Override
	public String toString() {
		String propertyString;
		StringBuilder sB = new StringBuilder();
		Iterator<ISymbol> propIterator = property.iterator();
		while (propIterator.hasNext()) {
			sB.append(propIterator.next().toString());
			if (propIterator.hasNext())
				sB.append(" ");
		}
		propertyString = sB.toString();
		return propertyString;
	}
	
	//private
	
	private void consistencyCheck() throws RepresentationException {
		boolean propertyHasEverySymbol;
		Set<ISymbol> propertySymbols = new HashSet<ISymbol>(property);
		Set<ISymbol> allSymbols = new HashSet<ISymbol>();
		for (IPair pair : orderedRelation) {
			allSymbols.add(pair.getAntecedent());
			allSymbols.add(pair.getConsequent());
		}
		propertyHasEverySymbol = (propertySymbols.equals(allSymbols));
		if (!propertyHasEverySymbol)
			throw new RepresentationException("TotalOrder.consistencyCheck() : property should contain every symbol in the set");
		else {
			boolean relationIsTransitive = true;
			int propIndex1 = 0;
			while (relationIsTransitive && propIndex1 < property.size() - 1) {
				int propIndex2 = propIndex1 + 1;
				while (relationIsTransitive && propIndex2 < property.size()) {
					relationIsTransitive = orderedRelation.contains(new Pair(property.get(propIndex1), property.get(propIndex2)));
					propIndex2++;
				}
				propIndex1++;
			}
			if (!relationIsTransitive)
				throw new RepresentationException("TotalOrder.consistencyCheck() : relation is not transitive");
		}
	}
	
	private List<List<IPair>> extendChain(List<IPair> chain) {
		List<List<IPair>> extendedChains = new ArrayList<List<IPair>>();
		List<IPair> extensions = new ArrayList<IPair>();
		ISymbol chainEnd = chain.get(chain.size() - 1).getConsequent();
		for (IPair pair : orderedRelation) {
			if (pair.getAntecedent().equals(chainEnd))
				extensions.add(pair);
		}
		if (!extensions.isEmpty()) {
			for (IPair extension : extensions) {
				List<IPair> extendedChain = new ArrayList<IPair>(chain);
				extendedChain.add(extension);
				//recursion
				extendedChains.addAll(extendChain(extendedChain));
			}
		}
		else extendedChains.add(chain);
		return extendedChains;
	}	
	
	private List<IPair> getSuccessorRelation() {
		List<IPair> successorRelation = new ArrayList<IPair>();
		List<IPair> rootPairs = new ArrayList<IPair>();
		for (IPair pair : orderedRelation) {
			if (pair.getAntecedent().equals(minimum))
				rootPairs.add(pair);
		}
		for (IPair rootPair : rootPairs) {
			List<IPair> chainStub = new ArrayList<IPair>();
			chainStub.add(rootPair);
			for (List<IPair> extendedChain : extendChain(chainStub)) {
				if (extendedChain.size() > successorRelation.size())
					successorRelation = extendedChain;
			}
		}
		return successorRelation;
	}
	
	private List<ISymbol> setProperty() throws RepresentationException {
		List<ISymbol> property = new ArrayList<ISymbol>();
		List<IPair> successorSequence = getSuccessorRelation();
		property.add(minimum);
		for (IPair pair : successorSequence)
			property.add(pair.getConsequent());
		return property;
	}	
	
	private ISymbol findMinimum() throws RepresentationException {
		ISymbol minimum;
		Set<ISymbol> antecedents = new HashSet<ISymbol>();
		Set<ISymbol> consequents = new HashSet<ISymbol>();
		for (IPair pair : orderedRelation) {
			antecedents.add(pair.getAntecedent());
			consequents.add(pair.getConsequent());
		}
		antecedents.removeAll(consequents);
		if (antecedents.size() != 1) {
			throw new RepresentationException("TotalOrder.findMinimum() : there should be one (and only one) antecedent "
					+ "that is the consequent of no other.");
		}
		else minimum = antecedents.iterator().next();
		return minimum;
	}

}
