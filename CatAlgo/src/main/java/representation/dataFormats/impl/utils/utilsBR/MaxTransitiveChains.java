package representation.dataFormats.impl.utils.utilsBR;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import representation.dataFormats.IPair;

public class MaxTransitiveChains {

	private Set<TransitiveChain> maxTransChains = new HashSet<TransitiveChain>();
	
	public MaxTransitiveChains() {
	}
	
	//getters
	
	public Set<TransitiveChain> getMaxTransChains() {
		return maxTransChains;
	}
	
	//setters
	
	/*
	 * Ensures that (X, Y and Z being any substring) :
	 * -if 'abcX' has already been added and 'acY' is given as parameter, then no addition occurs.
	 * -if 'acX' has already been added and 'abcY' is given as parameter, then 'acX' is removed
	 * and the addition occurs. 
	 * This way, transitive chains are guaranteed to be maximal.
	 */
	public boolean add(TransitiveChain specifiedChain) {
		boolean isMaxChain = true;
		Set<TransitiveChain> nonMaxChains = new HashSet<TransitiveChain>();
		Iterator<TransitiveChain> chainIterator = maxTransChains.iterator();
		while (isMaxChain && chainIterator.hasNext()) {
			TransitiveChain currentChain = chainIterator.next();
			if (currentChain.contains(specifiedChain))
				isMaxChain = false;
			else if (specifiedChain.contains(currentChain))
				nonMaxChains.add(currentChain);
		}
		maxTransChains.removeAll(nonMaxChains);
		if (isMaxChain) {
			maxTransChains.add(specifiedChain);
		}
		return isMaxChain;
	}

}
