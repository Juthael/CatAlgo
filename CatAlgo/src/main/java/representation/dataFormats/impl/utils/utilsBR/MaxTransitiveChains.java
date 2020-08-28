package representation.dataFormats.impl.utils.utilsBR;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MaxTransitiveChains {

	private Set<MaxTransitiveChain> maxTransChains = new HashSet<MaxTransitiveChain>();
	
	public MaxTransitiveChains() {
	}
	
	//getters
	
	public Set<MaxTransitiveChain> getMaxTransChains() {
		return maxTransChains;
	}
	
	//setters
	
	public boolean addNewChain(MaxTransitiveChain specifiedChain) {
		boolean isMaxChain = true;
		Set<MaxTransitiveChain> nonMaxChains = new HashSet<MaxTransitiveChain>();
		Iterator<MaxTransitiveChain> chainIterator = maxTransChains.iterator();
		while (isMaxChain && chainIterator.hasNext()) {
			MaxTransitiveChain currentChain = chainIterator.next();
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
