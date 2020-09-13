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

public class TotalOrder implements ITotalOrder {

	private Set<IPair> orderedRelation;
	private ISymbol minimum;
	private List<ISymbol> property;
	
	public TotalOrder(Set<IPair> orderedRelation) throws RepresentationException {
		this.orderedRelation = orderedRelation;
		findMinimum();
		setProperty();
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

	@Override
	public boolean isSubSetOf(ITotalOrder other) {
		return other.getPairs().containsAll(orderedRelation);
	}	

	@Override
	public boolean isSuperSetOf(ITotalOrder other) {
		return orderedRelation.containsAll(other.getPairs());
	}
	
	//setters
	
	@Override
	public void extendWithMinimum(ISymbol newMinimum) {
		minimum = newMinimum;
		for (ISymbol symbol : property)
			orderedRelation.add(new Pair(newMinimum, symbol));
		property.add(0, newMinimum);
	}	

	@Override
	public void restrictTo(Set<IPair> pairs) throws RepresentationException {
		orderedRelation.retainAll(pairs);
		if (orderedRelation.isEmpty()) {
			throw new RepresentationException("TotalOrder.reduceTo(Set<IPair) : an empty successorRelation orderedRelation is not allowed, "
					+ "even after reduction");
		}
		else {
			setProperty();
		}
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
	}

}
