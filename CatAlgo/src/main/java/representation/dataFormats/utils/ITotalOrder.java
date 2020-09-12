package representation.dataFormats.utils;

import java.util.List;
import java.util.Set;

import representation.dataFormats.IPair;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

public interface ITotalOrder extends Cloneable {

	//getters
	
	ITotalOrder clone();
	
	@Override
	boolean equals(Object o);
	
	Set<IPair> getPairs();
	
	List<ISymbol> getProperty();
	
	@Override
	int hashCode();
	
	boolean isSuperSetOf(ITotalOrder property);
	
	boolean isSubSetOf(ITotalOrder property);
	
	//setters
	
	void extendWithMinimum(ISymbol symbol);
	
	void restrictTo(Set<IPair> pairs) throws RepresentationException;
	
}
