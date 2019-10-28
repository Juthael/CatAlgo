package propertyPoset;

public interface IImplication {
	
	String getAntecedent();
	
	String getConsequent();
	
	boolean equals(Object otherImpl);

}
