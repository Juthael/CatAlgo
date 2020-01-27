package propertyPoset.utils;

public interface IImplication {
	
	String getAntecedent();
	
	String getConsequent();
	
	boolean equals(Object otherImpl);

}
