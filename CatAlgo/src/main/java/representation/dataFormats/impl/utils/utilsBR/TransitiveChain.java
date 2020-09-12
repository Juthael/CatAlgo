package representation.dataFormats.impl.utils.utilsBR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import representation.dataFormats.IPair;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

/**
 * A transitive chain of symbol is associated to the minimal binary relation R such as, if the symbol x is 
 * placed anywhere before the symbol y in the chain, then xRy. Thus, this class associates a list of symbols 
 * (the chain) and a set of pairs (the relation). <br>
 * 
 * Unless it is a clone, a chain is meant to be constructed out of a minimum symbol. Then it can be gradually extended with the 
 * addition of new symbols (one at a time), together with the pairs to be added to the relation, so that it remains 
 * transitive.
 *  
 * @author Gael Tregouet
 */
public class TransitiveChain implements Cloneable {
	
	private List<ISymbol> transitiveChain;
	private Set<IPair> relation;
	
	public TransitiveChain(ISymbol firstElement) {
		transitiveChain = new ArrayList<ISymbol>();
		transitiveChain.add(firstElement);
		relation = new HashSet<IPair>();
	}
	
	public TransitiveChain(List<ISymbol> maximalChain, Set<IPair> relation) {
		this.transitiveChain = maximalChain;
		this.relation = relation;
	}
	
	// getters
	
	@Override
	public TransitiveChain clone() {
		List<ISymbol> maxChainClone = new ArrayList<ISymbol>(transitiveChain);
		Set<IPair> relationClone = new HashSet<IPair>(relation);
		return new TransitiveChain(maxChainClone, relationClone);
	}
	
	public boolean contains(TransitiveChain other) {
		boolean argContained = relation.containsAll(other.getSetOfPairs());
		return argContained;
	}
	
	public ISymbol get(int index) {
		return transitiveChain.get(index);
	}
	
	public List<ISymbol> getChain() {
		return transitiveChain;
	}
	
	public ISymbol getLast() {
		return transitiveChain.get(transitiveChain.size() - 1);
	}
	
	public Set<IPair> getSetOfPairs(){
		return relation;
	}
	
	public boolean startsWith(TransitiveChain other) {
		boolean thisStartsWithOther = 
				(other.getChain().equals(this.transitiveChain.subList(0, other.size())));
		return thisStartsWithOther;
	}
	
	@Override
	public String toString() {
		String listOfSymbols;
		StringBuilder sB = new StringBuilder();
		Iterator<ISymbol> symbolIt = transitiveChain.iterator();
		while (symbolIt.hasNext()) {
			sB.append(symbolIt.next().toString());
			if (symbolIt.hasNext())
				sB.append("/");
		}
		listOfSymbols = sB.toString();
		return listOfSymbols;
	}
	
	public int size() {
		return transitiveChain.size();
	}
	
	// setters
	
	public void extend (ISymbol newChainElem, Set<IPair> relation) throws RepresentationException  {
		if (relation.isEmpty()) {
			throw new RepresentationException("TransitiveChain.extend(ISymbol, Set<Pair>) : the specified set of "
					+ "pairs is empty.");
		}
		else if (this.relation.containsAll(relation)) {
			/*
			 * If an element can be appended to the chain although no new pair is added to the relation, 
			 * it either mean that :
			 * -the new chain is not the maximal transitive chain of this relation (as it should be)
			 * -or the relation isn't well founded (as it should be too)
			 */
			StringBuilder sB = new StringBuilder();
			for (IPair pair : relation) {
				sB.append("(" + pair.getAntecedent().toString() + ", ");
				sB.append(pair.getConsequent().toString() + ")");
				sB.append(System.lineSeparator());
			}
			throw new RepresentationException("TransitiveChain.extend(ISymbol, Set<Pair>) : the specified set of "
					+ "pairs should contain new elements." + System.lineSeparator() + sB.toString());
		}
		else {
			boolean newRelationIsTransitive = true;
			Set<IPair> newRelation = new HashSet<IPair>(this.relation);
			newRelation.addAll(relation);
			int chainIndex = 0;
			//This is to ensure that the new chain's relation will remain transitive
			while (newRelationIsTransitive && chainIndex < transitiveChain.size()) {
				newRelationIsTransitive = newRelation.contains(new Pair(transitiveChain.get(chainIndex), newChainElem));
				chainIndex++;
			}
			if (newRelationIsTransitive) {
				transitiveChain.add(newChainElem);
				this.relation = newRelation;
			}
			else throw new RepresentationException("TransitiveChain.extend(ISymbol, Set<Pair>) : the new relation is "
					+ "not transitive.");
		}
	}

}
