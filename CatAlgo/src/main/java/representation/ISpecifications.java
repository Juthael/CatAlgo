package representation;

import java.util.Set;

public interface ISpecifications {
	
	Set<IConstraint> getAccessSpecifications();
	
	Set<IConstraint> getScopeSpecifications();
	
	void addConstraintOnPaths(IConstraint constraint);
	
	void addConstraintOnScope(IConstraint constraint);
	
	boolean aConstraintOnPathIsFullfilledBy(IWord path);
	
	boolean aConstraintOnScopeIsFullfilledBy(IWord path);
	
	boolean allConstraintsOnPathAreFullfilledBy(Set<IWord> paths);
	
	boolean allConstraintsOnScopeAreFullfilledBy(Set<IWord> paths);
	
	boolean theseSpecificationsAreSpecifiedBy(ISpecifications specifications);

}
