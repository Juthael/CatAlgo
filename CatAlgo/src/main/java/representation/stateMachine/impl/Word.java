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
	public boolean equals(Object o) {
		boolean thisEqualsOther;
		if (o == this)
			thisEqualsOther = true;
		else if (!(o instanceof representation.stateMachine.IWord))
			thisEqualsOther = false;
		else {
			IWord other = (IWord) o;
			thisEqualsOther = listOfSymbols.equals(other.getListOfSymbols());
		}
		return thisEqualsOther;
	}
	
	@Override
	public ISymbol get(int index) {
		ISymbol symbol = null;
		if (index < size())
			symbol = listOfSymbols.get(index);
		return symbol;
	}

	@Override
	public List<ISymbol> getListOfSymbols() {
		return listOfSymbols;
	}

	@Override
	public Iterator<ISymbol> getSymbolIterator() {
		return listOfSymbols.iterator();
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (ISymbol symbol : listOfSymbols)
			hashCode += symbol.hashCode();
		return hashCode;
	}
	
	@Override
	public int size() {
		return listOfSymbols.size();
	}

}
