package representation.dataFormats.impl.utils.utilsBR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import representation.dataFormats.IPair;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

/**
 * A maximal transitive chain is the maximal chain of a transitive well-founded binary relation. Thus, this class associates 
 * a list of symbols (the chain) and its associated set of pairs (the relation). <br>
 * 
 * Unless it is a clone, a chain is meant to be constructed out of a root symbol. Then it can be gradually extended with the 
 * addition of new symbols (one at a time), together with the pairs to be added to the relation, so that it remains 
 * transitive.
 *  
 * @author Gael Tregouet
 *
 */
public class MaxTransitiveChain implements Cloneable {
	
	private List<ISymbol> maximalChain;
	private Set<IPair> relation;
	
	public MaxTransitiveChain(ISymbol firstElement) {
		maximalChain = new ArrayList<ISymbol>();
		maximalChain.add(firstElement);
		relation = new HashSet<IPair>();
	}
	
	public MaxTransitiveChain(List<ISymbol> maximalChain, Set<IPair> relation) {
		this.maximalChain = maximalChain;
		this.relation = relation;
	}
	
	// getters
	
	@Override
	public MaxTransitiveChain clone() {
		List<ISymbol> maxChainClone = new ArrayList<ISymbol>(maximalChain);
		Set<IPair> relationClone = new HashSet<IPair>(relation);
		return new MaxTransitiveChain(maxChainClone, relationClone);
	}
	
	public boolean contains(MaxTransitiveChain other) {
		boolean thisContainsOther = true;
		List<ISymbol> otherChain = other.getChain();
		int chainIndex = 0;
		while (thisContainsOther && chainIndex < otherChain.size()) {
			thisContainsOther = (this.get(chainIndex).equals(otherChain.get(chainIndex)));
			chainIndex++;
		}
		return thisContainsOther;
	}
	
	public ISymbol get(int index) {
		return maximalChain.get(index);
	}
	
	public List<ISymbol> getChain() {
		return maximalChain;
	}
	
	public ISymbol getLast() {
		return maximalChain.get(maximalChain.size() - 1);
	}
	
	public int size() {
		return maximalChain.size();
	}
	
	// setters
	
	public void extend (ISymbol newChainElem, Set<IPair> relation) throws RepresentationException  {
		if (relation.isEmpty() || this.relation.containsAll(relation)) {
			/*
			 * If an element can be appended to the chain although no new pair is added to the relation, 
			 * it either mean that :
			 * -the new chain is not the maximal transitive chain of this relation (as it should be)
			 * -or the relation isn't well founded (as it should be too)
			 */
			throw new RepresentationException("MaxTransitiveChain.extend(ISymbol, Set<Pair>) : the specified set of "
					+ "pairs should contain new elments.");
		}
		else {
			boolean newRelationIsTransitive = true;
			Set<IPair> newRelation = new HashSet<IPair>(this.relation);
			newRelation.addAll(relation);
			int chainIndex = 0;
			//This is to ensure that the new chain's relation will remain transitive
			while (newRelationIsTransitive && chainIndex < maximalChain.size()) {
				newRelationIsTransitive = newRelation.contains(new Pair(maximalChain.get(chainIndex), newChainElem));
				chainIndex++;
			}
			if (newRelationIsTransitive) {
				maximalChain.add(newChainElem);
				this.relation = newRelation;
			}
			else throw new RepresentationException("MaxTransitiveChain.extend(ISymbol, Set<Pair>) : the new relation is "
					+ "not transitive.");
		}
	}

}
