package representation.stateMachine.impl;

import java.util.Iterator;
import java.util.List;

import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;

public class Word implements IWord {

	List<ISymbol> listOfSymbols;
	
	public Word(List<ISymbol> listOfSymbols) {
		this.listOfSymbols = listOfSymbols;
	}
	
	@Override
	public int compareTo(IWord word) {
		int result = 0;
		Iterator<ISymbol> thisIterator = listOfSymbols.iterator();
		Iterator<ISymbol> otherIterator = word.getSymbolIterator();
		while (thisIterator.hasNext() && otherIterator.hasNext() && result == 0) {
			result = thisIterator.next().compareTo(otherIterator.next());
		}
		if (result == 0) {
			if (thisIterator.hasNext() && !otherIterator.hasNext())
				result = 1;
			else if (!thisIterator.hasNext() && otherIterator.hasNext())
				result = -1;
		}
		return result;
	}

	@Override
	public List<ISymbol> getListOfSymbols() {
		return listOfSymbols;
	}

	@Override
	public Iterator<ISymbol> getSymbolIterator() {
		return listOfSymbols.iterator();
	}

}
