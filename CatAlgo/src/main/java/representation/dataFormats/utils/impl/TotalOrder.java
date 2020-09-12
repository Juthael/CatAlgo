package representation.dataFormats.utils.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import representation.dataFormats.IPair;
import representation.dataFormats.impl.utils.utilsBR.Pair;
import representation.dataFormats.utils.ITotalOrder;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.impl.Symbol;
import representation.utils.HashCodeUtil;

public class TotalOrder implements ITotalOrder {

	Set<IPair> totalOrder;
	ISymbol minimum;
	List<ISymbol> property;
	
	public TotalOrder(Set<IPair> poset) throws RepresentationException {
		this.totalOrder = poset;
		findMinimum();
		setProperty();
	}
	
	private TotalOrder(Set<IPair> totalOrder, ISymbol minimum, List<ISymbol> property) {
		this.totalOrder = totalOrder;
		this.minimum = minimum;
		this.property = property;
	}
	
	//getters
	
	@Override
	public ITotalOrder clone() {
		ITotalOrder cloneOrder = new TotalOrder(new HashSet<IPair>(totalOrder), new Symbol(minimum.toString()), new ArrayList<ISymbol>(property));
		return cloneOrder;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean thisEqualsOther;
		if (o == this)
			thisEqualsOther = true;
		else if (!(o instanceof representation.dataFormats.utils.ITotalOrder))
			thisEqualsOther = false;
		else {
			ITotalOrder other = (ITotalOrder) o;
			thisEqualsOther = property.equals(other.getProperty());
		}
		return thisEqualsOther;
	}
	
	@Override
	public Set<IPair> getPairs(){
		return totalOrder;
	}
	
	@Override
	public List<ISymbol> getProperty(){
		return property;
	}
	
	@Override 
	public int hashCode() {
		int hashCode = 0;
		for (ISymbol symbol : property) {
			hashCode += (HashCodeUtil.SEED + symbol.hashCode());
		}
		return hashCode;
	}

	@Override
	public boolean isSubSetOf(ITotalOrder other) {
		return other.getPairs().containsAll(totalOrder);
	}	

	@Override
	public boolean isSuperSetOf(ITotalOrder other) {
		return totalOrder.containsAll(other.getPairs());
	}
	
	//setters

	@Override
	public void restrictTo(Set<IPair> pairs) throws RepresentationException {
		totalOrder.retainAll(pairs);
		if (totalOrder.isEmpty()) {
			throw new RepresentationException("TotalOrder.reduceTo(Set<IPair) : an empty successorRelation totalOrder is not allowed, "
					+ "even after reduction");
		}
		else {
			findMinimum();
			setProperty();
		}
	}
	
	//private
	
	private void consistencyCheck() throws RepresentationException {
		boolean propertyHasEverySymbol;
		Set<ISymbol> propertySymbols = new HashSet<ISymbol>(property);
		Set<ISymbol> allSymbols = new HashSet<ISymbol>();
		for (IPair pair : totalOrder) {
			allSymbols.add(pair.getAntecedent());
			allSymbols.add(pair.getConsequent());
		}
		propertyHasEverySymbol = (propertySymbols.equals(allSymbols));
		if (!propertyHasEverySymbol)
			throw new RepresentationException("TotalOrder.consistencyCheck() : property should contain every symbol in the set");
		else {
			boolean relationIsTransitive = true;
			int propIndex1 = 0;
			while (relationIsTransitive && propIndex1 < property.size()) {
				int propIndex2 = 0;
				while (relationIsTransitive && propIndex2 < propIndex1) {
					relationIsTransitive = totalOrder.contains(new Pair(property.get(propIndex1), property.get(propIndex2)));
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
		for (IPair pair : totalOrder) {
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
		for (IPair pair : totalOrder) {
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
	
	private void setProperty() throws RepresentationException {
		List<ISymbol> newProperty = new ArrayList<ISymbol>();
		List<IPair> successorSequence = getSuccessorRelation();
		newProperty.add(minimum);
		for (IPair pair : successorSequence)
			newProperty.add(pair.getConsequent());
		property = newProperty;		
		consistencyCheck();
	}	
	
	private void findMinimum() throws RepresentationException {
		Set<ISymbol> antecedents = new HashSet<ISymbol>();
		Set<ISymbol> consequents = new HashSet<ISymbol>();
		for (IPair pair : totalOrder) {
			antecedents.add(pair.getAntecedent());
			consequents.add(pair.getConsequent());
		}
		antecedents.removeAll(consequents);
		if (antecedents.size() != 1) {
			throw new RepresentationException("TotalOrder.findMinimum() : there should be one (and only one) antecedent "
					+ "that is the consequent of no other.");
		}
		else if (minimum != null) {
			ISymbol newMinimum = antecedents.iterator().next();
			if (!newMinimum.equals(minimum)) {
				throw new RepresentationException("TotalOrder.findMinimum() : the minimum should never change.");
			}
		}
		else minimum = antecedents.iterator().next();
	}

}
