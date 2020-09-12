package representation.dataFormats.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IPair;
import representation.dataFormats.IRelationalDescription;
import representation.dataFormats.utils.ITotalOrder;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;
import representation.stateMachine.impl.Word;
import representation.utils.HashCodeUtil;

public class RelationalDescription implements IRelationalDescription {

	private Set<ISymbol> setOfSymbols = new HashSet<ISymbol>();
	private Set<IPair> binaryRelation;
	private Set<ITotalOrder> orderedSubRelations;
	
	public RelationalDescription(Set<ITotalOrder> orderedRelations) {
		for (ITotalOrder orderedRelation : orderedRelations) {
			binaryRelation.addAll(orderedRelation.getPairs());
			setOfSymbols.addAll(orderedRelation.getProperty());
		}
		this.orderedSubRelations = orderedRelations;
	}

	//static
	public static IRelationalDescription applyFunctionToArguments(ISymbol function, Set<IRelationalDescription> arguments) {
		IRelationalDescription output;
		Set<ITotalOrder> orderedSubRelations = new HashSet<ITotalOrder>();
		for (IRelationalDescription argument : arguments) {
			IRelationalDescription newArgument = argument.clone();
			newArgument.applyFunction(function);
			orderedSubRelations.addAll(newArgument.getOrderedSubRelations());
		}
		output = new RelationalDescription(orderedSubRelations);
		return output;
	}
	
	//getters

	@Override
	public IRelationalDescription clone() {
		IRelationalDescription relationalDescriptionClone;
		Set<ITotalOrder> subRelationClones = new HashSet<ITotalOrder>();
		for (ITotalOrder subRelation : orderedSubRelations) {
			subRelationClones.add(subRelation.clone());
		}
		relationalDescriptionClone = new RelationalDescription(subRelationClones);
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
			thisEqualsOther = (orderedSubRelations.equals(other.getOrderedSubRelations()));
		}
		return thisEqualsOther;
	}
	
	@Override
	public IRelationalDescription getRelationalDescription() {
		return this;
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
		for (ITotalOrder orderedSubRelation : orderedSubRelations) {
			words.add(new Word(orderedSubRelation.getProperty()));
		}
		language = new Language(words);
		return language;
	}

	@Override
	public int getNbOfArgumentsFor(ISymbol symbol) throws RepresentationException {
		int nbOfArguments;
		if (!setOfSymbols.contains(symbol)) {
			throw new RepresentationException("RelationalDescription.getNbOfArguments(ISymbol) : the specified symbol "
					+ "cannot be found");
		}
		else {
			try {
				nbOfArguments =  getLanguage().getNbOfArgumentsFor(symbol);
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
	public Set<ITotalOrder> getOrderedSubRelations() {
		return orderedSubRelations;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (ITotalOrder subRelation : orderedSubRelations) {
			hashCode += (HashCodeUtil.SEED + subRelation.hashCode());
		}
		return hashCode;
	}
	
	@Override
	public String toString() {
		try {
			return getLanguage().toString();
		} catch (RepresentationException e) {
			return e.getMessage();
		}
	}
	
	//setters
	
	@Override
	public void applyFunction(ISymbol function) {
		for (ITotalOrder order : orderedSubRelations) {
			order.extendWithMinimum(function);
			binaryRelation.addAll(order.getPairs());
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
		Set<ITotalOrder> newOrderedSubRel = new HashSet<ITotalOrder>();
		try {
			for (ITotalOrder subRelation : orderedSubRelations) {
				subRelation.restrictTo(pairs);
				newOrderedSubRel.add(subRelation);
			}
		}
		catch (RepresentationException e) {
			throw new RepresentationException("RelationalDescription.restrictTo(Set<IPair>) : restriction has "
					+ "failed." + System.lineSeparator() + e.getMessage());
		}
		orderedSubRelations = newOrderedSubRel;		
	}
	
	//private
	
	private boolean meets(IRelationalDescription other) {
		boolean thisMeetsOther;
		if (!binaryRelation.containsAll(other.getBinaryRelation())) {
			thisMeetsOther = false;
		}
		else {
			boolean eachPropertyIsMet = true;
			Iterator<ITotalOrder> otherPropIte = other.getOrderedSubRelations().iterator();
			while (eachPropertyIsMet && otherPropIte.hasNext()) {
				eachPropertyIsMet = meets(otherPropIte.next());
			}
			thisMeetsOther = eachPropertyIsMet;
		}
		return thisMeetsOther;
	}
	
	private boolean meets(ITotalOrder property) {
		boolean propertyIsMet = false;
		Iterator<ITotalOrder> propIte = orderedSubRelations.iterator(); 
		while (!propertyIsMet && propIte.hasNext()) {
			propertyIsMet = propIte.next().isSuperSetOf(property);
		}
		return propertyIsMet;
	}

}
