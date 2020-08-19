package representation;

/**
 * <p>
 * A context descriptor describes a context of objects : its says what those objects are and, to do so, makes the best 
 * use of tailor-made categories discovered in the representation building process, and of the relationships that bind 
 * them. <br>
 * </p>
 * 
 * <p>
 * A representation is an information structure that determines which categories ( {@link ICategory} ) are being perceived 
 * in a given context. In order to build a representation ( {@link IRepresentation} ), a context descriptor needs a 
 * description of every object in the context, provided by a "context input" ( {@link IContextInput} ). 
 * Once the network of categories has been established, the paths that will be used within it to describe the context 
 * still need to be determined. There usually are many possibilities, not all of which are equally efficient. For a 
 * given context input, the context descriptor can return every possible description, or only the most efficient one 
 * on the basis of different criteria : it can minimize the amount information that is used by the description, or 
 * maximize the coding efficiency of its 'signified' (i.e., the most general category, which summarizes the whole context). <br> 
 * </p>
 * 
 * <p>
 * Most of the time, though, one can not be sure about which properties characterize objects in the context, because they 
 * have an inherent degree of ambiguity. For instance, in the context of the string <code> abbc </code>, is the 
 * <code> b </code> in second position the first letter of the "sameness" group <code> [bb] </code>, as in 
 * <code> [a][bb][c] </code> ? Or is it the second letter of the "increase by 1" group <code> [ab] </code>, as in 
 * <code> [ab][bc] </code> ? <br>
 * </p>
 * 
 * <p>
 * To solve this ambiguity, I make this strong assumption : whenever a context can be described in several conflicting 
 * ways, the description that a subject will perceive as being the <i> real </i> one will be the <i> most efficient </i> 
 * one. <br>
 * (It's hard to tell whether one is more "real" than the other in the previous example, by the way. Sometimes, no obvious 
 * solution emerges). <br>
 * </p>
 * 
 * <p>
 * Accordingly, here's how a context descriptor returns the "true" description of an ambiguous context (i.e., a context 
 * in which objects can be described in many conflicting ways) : <br> 
 * .for every possible set of object descriptions, a context input must be instantiated (if "every possible set" would 
 * mean a virtually infinite number of sets, "every reasonable set" will do). <br>
 * .this set of context inputs is given as a parameter to the {@link IContextDescriptor} constructor. With each context
 * input, the context descriptor builds a distinct representation. Each representation can then return the most efficient 
 * description of the context, given its own particular network of categories. <br>
 * .the context descriptor then ends up with a set of descriptions (one for every context input given to the constructor), 
 * whose efficiency can be compared. The "true" one, as stated above, is the most efficient. <br>
 * </p>
 * 
 * @author Gael Tregouet
 *
 */
public interface IContextDescriptor {
	
	

}
