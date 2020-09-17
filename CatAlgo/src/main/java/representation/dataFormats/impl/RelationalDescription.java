package representation.dataFormats.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IPair;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.impl.utils.utilsBR.Pair;
import representation.dataFormats.utils.ITotalOrder;
import representation.dataFormats.utils.impl.TotalOrder;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;
import representation.stateMachine.impl.Word;

public class RelationalDescription implements IRelationalDescription {

	private Set<ISymbol> setOfSymbols = new HashSet<ISymbol>();
	private Set<IPair> binaryRelation = new HashSet<IPair>();
	private Set<ITotalOrder> propertiesAsOrders;
	
	/*
	 * Caution : ITotalOrder members of this class are mutable and aggregated ; thus, only a set of cloned 
	 * ITotalOrder objects should be given as a parameter to this constructor.
	 */
	public RelationalDescription(Set<ITotalOrder> propertiesAsOrders) {
		for (ITotalOrder orderedRelation : propertiesAsOrders) {
			binaryRelation.addAll(orderedRelation.getPairs());
			setOfSymbols.addAll(orderedRelation.getProperty());
		}
		this.propertiesAsOrders = propertiesAsOrders;
	}
	
	public RelationalDescription(ISymbol leaf) {
		setOfSymbols.add(leaf);
		propertiesAsOrders = new HashSet<ITotalOrder>();
	}
	
	/*
	 * Caution : ITotalOrder members of this class are mutable and aggregated ; thus, only a set of cloned 
	 * ITotalOrder objects should be given as a parameter to this constructor.
	 */
	private RelationalDescription(Set<ISymbol> setOfSymbols, Set<IPair> binaryRelation, Set<ITotalOrder> propertiesAsOrders) {
		this.setOfSymbols = setOfSymbols;
		this.binaryRelation = binaryRelation;
		this.propertiesAsOrders = propertiesAsOrders;
	}
	
	//getters

	@Override
	public IRelationalDescription clone() {
		IRelationalDescription relationalDescriptionClone;
		Set<ITotalOrder> orderClones = new HashSet<ITotalOrder>();
		for (ITotalOrder order : propertiesAsOrders) {
			orderClones.add(order.clone());
		}
		relationalDescriptionClone = new RelationalDescription(setOfSymbols, binaryRelation, orderClones);
		return relationalDescriptionClone;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean thisEqualsOther;
		if (o == this)
			thisEqualsOther = true;
		else if (!(o instanceof representation.dataFormats.IRelationalDescription))
			thisEqualsOther = false;
		else {
			IRelationalDescription other = (IRelationalDescription) o;
			thisEqualsOther = (setOfSymbols.equals(other.getSetOfSymbols()) 
					&& propertiesAsOrders.equals(other.getPropertiesAsTotalOrders()));
		}
		return thisEqualsOther;
	}

	@Override
	public Set<IPair> getBinaryRelation() {
		return binaryRelation;
	}

	@Override
	public IFunctionalExpression getFunctionalExpression() throws RepresentationException {
		IFunctionalExpression functionalExpression;
		try {
			functionalExpression =  getLanguage().getFunctionalExpression();
		}
		catch (RepresentationException e) {
			throw new RepresentationException("RelationalDescription.getFunctionalExpression() : the language is needed as "
					+ "an intermediate format ; an error has occured while building it." + System.lineSeparator() 
					+ e.getMessage());
		}
		return functionalExpression;
	}

	@Override
	public IGrammar getGrammar() throws RepresentationException {
		IGrammar grammar;
		try {
			grammar =  getLanguage().getGrammar();
		}
		catch (RepresentationException e) {
			throw new RepresentationException("RelationalDescription.getGrammar() : the language is needed as "
					+ "an intermediate format ; an error has occured while building it." + System.lineSeparator() 
					+ e.getMessage());
		}
		return grammar;
	}

	@Override
	public ILanguage getLanguage() throws RepresentationException {
		ILanguage language;
		Set<IWord> words = new HashSet<IWord>();
		if (!propertiesAsOrders.isEmpty()) {
			for (ITotalOrder order : propertiesAsOrders) {
				words.add(new Word(order.getProperty()));
			}	
		}
		else {
			//then the set of symbols should contain only one element
			if (setOfSymbols.size() == 1) {
				List<ISymbol> listOfSymbols = new ArrayList<ISymbol>(setOfSymbols);
				words.add(new Word(listOfSymbols));
			}
			else throw new RepresentationException("RelationalDescription.getLanguage() : inconsistency. The set of "
					+ "orders is empty, so the set of symbols should contain only one element.");
		}
		language = new Language(words);
		return language;
	}

	@Override
	public int getNbOfArgumentsFor(ISymbol function) throws RepresentationException {
		int nbOfArguments;
		if (!setOfSymbols.contains(function)) {
			throw new RepresentationException("RelationalDescription.getNbOfArguments(ISymbol) : the specified function "
					+ "cannot be found");
		}
		else {
			try {
				nbOfArguments =  getLanguage().getNbOfArgumentsFor(function);
			}
			catch (RepresentationException e) {
				throw new RepresentationException("RelationalDescription.getNbOfArgumentsFor(ISymbol) : the language is "
						+ "needed as an intermediate format ; an error has occured while building it." 
						+ System.lineSeparator() + e.getMessage());
			}
		}
		return nbOfArguments;
	}

	@Override
	public Set<ITotalOrder> getPropertiesAsTotalOrders() {
		return propertiesAsOrders;
	}
	
	@Override
	public IRelationalDescription getRelationalDescription() {
		return this;
	}	
	
	@Override
	public Set<ISymbol> getSetOfSymbols(){
		return setOfSymbols;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (ISymbol symbol : setOfSymbols)
			hashCode += symbol.hashCode();
		for (ITotalOrder order : propertiesAsOrders)
			hashCode += order.hashCode();
		return hashCode;
	}
	
	@Override
	public boolean meets(IDescription description) throws RepresentationException {
		IRelationalDescription other;
		try {
			other = description.getRelationalDescription();
		} catch (RepresentationException e) {
			throw new RepresentationException("RelationalDescription.meets(IDescription) : conversion from IDescription "
					+ "to IRelationalDescription has failed." + System.lineSeparator() + e.getMessage());
		}
		return meets(other);
	}	
	
	@Override
	public String toString() {
		try {
			return getLanguage().toString();
		} catch (RepresentationException e) {
			return "RelationalDescription.toString() : the language is "
					+ "needed as an intermediate format ; an error has occured while building it." 
					+ System.lineSeparator() + e.getMessage();
		}
	}
	
	//setters
	
	@Override
	public void applyFunction(ISymbol function) throws RepresentationException {
		if (propertiesAsOrders.isEmpty()) {
			//then the set of symbols should contain only one element. 
			if (setOfSymbols.size() == 1) {
				IPair newPair = new Pair(function, setOfSymbols.iterator().next());
				binaryRelation.add(newPair);
				ITotalOrder propertyAsOrder;
				try {
					propertyAsOrder = new TotalOrder(binaryRelation);
					propertiesAsOrders.add(propertyAsOrder);
				}
				catch (RepresentationException e) {
					throw new RepresentationException("RelationalDescription.applyFunction(ISymbol) : TotalOrder "
							+ "instantiation has failed." + System.lineSeparator() + e.getMessage());
				}	
			}
			else throw new RepresentationException("RelationalDescription.applyFunction(ISymbol) : inconsistency. "
					+ "The set of orders is empty, so the set of symbols should contain only one element.");
		}
		else {
			for (ITotalOrder order : propertiesAsOrders) {
				order.extendWithMinimum(function);
				binaryRelation.addAll(order.getPairs());
			}
		}
		setOfSymbols.add(function);
	}	

	@Override
	public void restrictTo(Set<IPair> pairs) throws RepresentationException {
		Set<ISymbol> symbolsToRetain = new HashSet<ISymbol>();
		for (IPair pair : pairs) {
			symbolsToRetain.add(pair.getAntecedent());
			symbolsToRetain.add(pair.getConsequent());
		}
		setOfSymbols.retainAll(symbolsToRetain);
		binaryRelation.retainAll(pairs);
		Set<ITotalOrder> newProp = new HashSet<ITotalOrder>();
		try {
			for (ITotalOrder order : propertiesAsOrders) {
				order.restrictTo(pairs);
				newProp.add(order);
			}
		}
		catch (RepresentationException e) {
			throw new RepresentationException("RelationalDescription.restrictTo(Set<IPair>) : restriction has "
					+ "failed." + System.lineSeparator() + e.getMessage());
		}
		propertiesAsOrders = newProp;		
	}
	
	//private
	
	private boolean meets(IRelationalDescription other) {
		boolean thisMeetsOther;
		if (!setOfSymbols.containsAll(other.getSetOfSymbols()) || 
				!binaryRelation.containsAll(other.getBinaryRelation())) {
			thisMeetsOther = false;
		}
		else {
			boolean eachPropertyIsMet = true;
			Iterator<ITotalOrder> otherPropIte = other.getPropertiesAsTotalOrders().iterator();
			while (eachPropertyIsMet && otherPropIte.hasNext()) {
				eachPropertyIsMet = meets(otherPropIte.next());
			}
			thisMeetsOther = eachPropertyIsMet;
		}
		return thisMeetsOther;
	}
	
	private boolean meets(ITotalOrder property) {
		boolean propertyIsMet = false;
		Iterator<ITotalOrder> propIte = propertiesAsOrders.iterator(); 
		while (!propertyIsMet && propIte.hasNext()) {
			propertyIsMet = propIte.next().isSuperSetOf(property);
		}
		return propertyIsMet;
	}
	
	

}
