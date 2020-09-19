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
import representation.dataFormats.ITotalOrder;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;
import representation.stateMachine.IWord;
import representation.stateMachine.impl.Word;

public class RelationalDescription extends Description implements IRelationalDescription {

	public static final boolean MAX_ORDERS = true;
	public static final boolean ORDERS = false;
	
	private final Set<ISymbol> setOfSymbols;
	private final Set<IPair> binaryRelation;
	private final Set<ITotalOrder> maxOrders;
	//orders initialization may be costly, so only if requested
	private Set<ITotalOrder> orders = null;
	
	public RelationalDescription(Set<ITotalOrder> orders, boolean theseAreMaxOrders) {
		setOfSymbols = new HashSet<ISymbol>();
		binaryRelation = new HashSet<IPair>();
		if (theseAreMaxOrders)
			this.maxOrders = orders;
		else {
			this.orders = orders;
			maxOrders = setMaxOrdersFromOrders(orders);
		}
		for (ITotalOrder maxOrder : orders) {
			binaryRelation.addAll(maxOrder.getPairs());
			setOfSymbols.addAll(maxOrder.getProperty());
		}
		setHashCode();
	}
	
	public RelationalDescription(ISymbol leaf) {
		setOfSymbols = new HashSet<ISymbol>();
		setOfSymbols.add(leaf);
		binaryRelation = new HashSet<IPair>();
		maxOrders = new HashSet<ITotalOrder>();
		setHashCode();
	}
	
	private RelationalDescription(Set<ISymbol> setOfSymbols, Set<IPair> binaryRelation, Set<ITotalOrder> propertiesAsOrders) {
		this.setOfSymbols = setOfSymbols;
		this.binaryRelation = binaryRelation;
		this.maxOrders = propertiesAsOrders;
		setHashCode();
	}
	
	//getters
	
	@Override
	public IRelationalDescription applyFunction(ISymbol function) throws RepresentationException {
		IRelationalDescription result;
		Set<ITotalOrder> resultMaxOrders = new HashSet<ITotalOrder>();
		if (this.maxOrders.isEmpty()) {
			//then the set of symbols should contain only one element. 
			if (setOfSymbols.size() == 1) {
				Set<IPair> pairs = new HashSet<IPair>();
				pairs.add(new Pair(function, setOfSymbols.iterator().next()));
				ITotalOrder maxOrder;
				try {
					maxOrder = new TotalOrder(pairs);
				}
				catch (RepresentationException e) {
					throw new RepresentationException("RelationalDescription.applyFunction(ISymbol) : TotalOrder "
							+ "instantiation has failed." + System.lineSeparator() + e.getMessage());
				}
				resultMaxOrders.add(maxOrder);
			}
			else throw new RepresentationException("RelationalDescription.applyFunction(ISymbol) : inconsistency. "
					+ "The set of orders is empty, so the set of symbols should contain only one element.");
		}
		else {
			for (ITotalOrder order : this.maxOrders) {
				resultMaxOrders.add(order.extendWithMinimum(function));
			}
		}
		result = new RelationalDescription(resultMaxOrders, MAX_ORDERS);
		return result;
	}	

	@Override
	public IRelationalDescription clone() {
		IRelationalDescription relationalDescriptionClone;
		Set<ITotalOrder> orderClones = new HashSet<ITotalOrder>();
		for (ITotalOrder order : maxOrders) {
			orderClones.add(order.clone());
		}
		relationalDescriptionClone = new RelationalDescription(setOfSymbols, binaryRelation, orderClones);
		return relationalDescriptionClone;
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
		if (!maxOrders.isEmpty()) {
			for (ITotalOrder order : maxOrders) {
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
	public Set<ITotalOrder> getMaxTotalOrders() {
		return maxOrders;
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
	public IRelationalDescription getRelationalDescription() {
		return this;
	}	
	
	@Override
	public Set<ISymbol> getSymbols(){
		return setOfSymbols;
	}
	
	@Override
	public Set<ITotalOrder> getTotalOrders(){
		if (orders == null) {
			orders = setOrdersFromMaxOrders(maxOrders);
		}
		return orders;
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
	
	//private
	
	private boolean meets(IRelationalDescription other) {
		boolean thisMeetsOther;
		if (!setOfSymbols.containsAll(other.getSymbols()) || 
				!binaryRelation.containsAll(other.getBinaryRelation())) {
			thisMeetsOther = false;
		}
		else {
			boolean eachPropertyIsMet = true;
			Iterator<ITotalOrder> otherPropIte = other.getMaxTotalOrders().iterator();
			while (eachPropertyIsMet && otherPropIte.hasNext()) {
				eachPropertyIsMet = meets(otherPropIte.next());
			}
			thisMeetsOther = eachPropertyIsMet;
		}
		return thisMeetsOther;
	}
	
	private boolean meets(ITotalOrder property) {
		boolean propertyIsMet = false;
		Iterator<ITotalOrder> propIte = maxOrders.iterator(); 
		while (!propertyIsMet && propIte.hasNext()) {
			propertyIsMet = propIte.next().isSuperSetOf(property);
		}
		return propertyIsMet;
	}
	
	private Set<ITotalOrder> setOrdersFromMaxOrders(Set<ITotalOrder> maxOrders) {
		Set<ITotalOrder> orders = new HashSet<ITotalOrder>();
		for (ITotalOrder maxOrder : maxOrders) {
			orders.addAll(maxOrder.getSetOfSubOrders());
		}
		return orders;
	}
	
	private Set<ITotalOrder> setMaxOrdersFromOrders(Set<ITotalOrder> orders){
		Set<ITotalOrder> maxOrders = new HashSet<ITotalOrder>();
		for (ITotalOrder order : orders) {
			boolean orderIsMaxSoFar = true;
			Iterator<ITotalOrder> maxOrderIte = maxOrders.iterator();
			while (orderIsMaxSoFar && maxOrderIte.hasNext()) {
				orderIsMaxSoFar = !maxOrderIte.next().isSuperSetOf(order);
			}
			if (orderIsMaxSoFar) {
				Set<ITotalOrder> notMaxAfterAll = new HashSet<ITotalOrder>();
				for (ITotalOrder maxOrder : maxOrders) {
					if (order.isSuperSetOf(maxOrder))
						notMaxAfterAll.add(maxOrder);
				}
				maxOrders.removeAll(notMaxAfterAll);
				maxOrders.add(order);
			}
		}
		return maxOrders;
	}

}
