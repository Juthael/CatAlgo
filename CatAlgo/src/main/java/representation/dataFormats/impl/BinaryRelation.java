package representation.dataFormats.impl;

import java.util.HashSet;
import java.util.Set;

import representation.dataFormats.IBinaryRelation;
import representation.dataFormats.IDescription;
import representation.dataFormats.IFunctionalExpression;
import representation.dataFormats.IGrammar;
import representation.dataFormats.ILanguage;
import representation.dataFormats.IPair;
import representation.dataFormats.impl.utils.utilsBR.MaxTransitiveChain;
import representation.dataFormats.impl.utils.utilsBR.MaxTransitiveChains;
import representation.dataFormats.impl.utils.utilsBR.Pair;
import representation.exceptions.RepresentationException;
import representation.stateMachine.ISymbol;

public class BinaryRelation implements IBinaryRelation {
	
	Set<Pair> pairs;

	public BinaryRelation(Set<Pair> pairs) {
		this.pairs = pairs;
	}

	@Override
	public IBinaryRelation getBinaryRelation() {
		return this;
	}

	@Override
	public boolean meets(IDescription description) {
		boolean otherDescMeetsThis;
		Set<IPair> otherSetOfPairs = description.getBinaryRelation().getPairs();
		otherDescMeetsThis = (otherSetOfPairs.containsAll(pairs));
		return otherDescMeetsThis;
	}

	@Override
	public ILanguage getLanguage() throws RepresentationException {
		ILanguage language;
		Set<MaxTransitiveChain> tempTransSubRel = new HashSet<MaxTransitiveChain>();
		MaxTransitiveChains finalTransSubRel = new MaxTransitiveChains();
		ISymbol root;
		try {
			root = getRoot();
		}
		catch (RepresentationException e) {
			throw new RepresentationException("BinaryRelation.getLanguage() : root search failed. " + System.lineSeparator() 
				+ e.getLocalizedMessage());
		}
		MaxTransitiveChain rootChain = new MaxTransitiveChain(root);
		tempTransSubRel.add(rootChain);
		while (!tempTransSubRel.isEmpty()) {
			//HERE
		}
		
	}

	@Override
	public IFunctionalExpression getFunctionalExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IPair> getPairs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGrammar getGrammar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbOfArgumentsFor(ISymbol symbol) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private ISymbol getRoot() throws RepresentationException {
		ISymbol root;
		Set<ISymbol> antecedents = new HashSet<ISymbol>();
		Set<ISymbol> consequents = new HashSet<ISymbol>();
		for (IPair pair : pairs) {
			antecedents.add(pair.getAntecedent());
			consequents.add(pair.getConsequent());
		}
		antecedents.removeAll(consequents);
		if (antecedents.size() != 1) {
			throw new RepresentationException("BinaryRelation.getRoot() : there should be one (and only one) antecedent "
					+ "that is the consequent of no other");
		}
		else root = antecedents.iterator().next();
		return root;
	}
	

}
