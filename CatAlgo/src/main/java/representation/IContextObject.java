package representation;

public interface IContextObject extends ICategory, IAcceptState {
	
	boolean conformsTo(IFunctionalExpression specifiedProperty);

}
