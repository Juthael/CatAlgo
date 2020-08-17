package representation;

import java.util.Map;
import java.util.Set;

import representation.exceptions.RepresentationException;

/**
 * <p>
 * Given two categories from a representational machine ( {@link IRepresentation} ), a category transition builder is able 
 * to determine the rule that will allow a transition from the first to the the second category in the machine. A rule will 
 * actually be found (and a transition will exist) iff the first category is a super-category of the second. <br>
 * </p> 
 * 
 * <p>
 * As an element of a transition function, a rule ( {@link ITransition} ) associates an input state and a symbol to an output 
 * state. In this case, the input and output have to be given as arguments via the constructor or a setter method of the 
 * category transition builder. Thus, the only undetermined element of the rule is the symbol that will trigger the 
 * transition. <br>
 * </p>
 * 
 * <p>
 * Representational machines ( {@link IRepresentation} ) can be thought of as meta-machines since their states are 
 * categories, and therefore state machines themselves ( {@link ICategory} ). Accordingly, symbols are not arbitrary 
 * elements from a predetermined alphabet as it usually is the case in a regular state machine ; rather, a symbol will 
 * consist in a tailor-made operator ( {@link IOperator} ), inferred from the comparison of the input and output 
 * categories, and representing the procedure by which the input category's state machine can be expanded and 
 * transformed into the machine of its sub-category. <br>
 * This way, the transition function of a representational machine does not need to be provided to its constructor. Only 
 * the set of categories is necessary : from there, by systematically using the category transition builder on each 
 * possible pair of categories to check if a transition rule can be found, the transition function is gradually built. <br> 
 * </p>
 * 
 * <p>
 * The general procedure to infer the operator-symbol of a transition must include the following steps : <br>
 * 1-Verification that the binary relation describing the input category is actually a subset of the binary relation 
 * describing the output category. A transition must be returned if is the case, and none (<code> null </code>) 
 * otherwise. <br>  
 * 2-For every final (i.e., non-interface) state <i> A </i> in the input machine, a final state <i> A' </i> must be found 
 * in the output machine that meets <i> A </i>'s specifications ( {@link ISpecifications} ). Those two states are then 
 * designated as counterparts and closed for further association. A state that remains available for association is 
 * referred to as a "free state". <br>
 * 3-For every interface state <i> I </i> in the input machine, in an ascending order of rank (see the ranking function 
 * in {@link IState}), a counterpart is searched for in the output machine. This counterpart can be : <br>
 * .an interface <i> I' </i> that meets <i> I </i>'s specifications and whose local grammar is the same as 
 * <i> I </i>'s (a state's local grammar ( {@link IGrammar} ) maps every symbol that allows a transition to it 
 * with every symbol that allows a transition from it). In this case, the interface <i> I </i> remains unchanged
 * in the output machine, and the symbol-operator of the returned transition ( {@link IOperator} ) does not need to account 
 * for this mapping. <br>     
 * .a <i> value </i>, i.e. a connected set of states composed of a "root" state (the state in the set having the highest rank) 
 * and all free states that can be reached from this root. Since this description of a value is also the very description of 
 * a state machine, a value ( {@link IValue} ) is regarded as one ; it contains at least one state. The mapping of an output 
 * machine's value to an input machine's interface (allowed if the value meets the interface's specifications) triggers the 
 * registration of a value attribution ( {@link IValueAttribution} ) in the operator-symbol.  
 * </p>
 * 
 * @see representation.ICategory
 * @see representation.IRepresentation
 * @see representation.ITransitionFunction
 * @see representation.ITransition
 * @see representation.IOperator
 * @see representation.IBinaryRelation
 * @see representation.ISpecifications
 * @see representation.IInterfaceState
 * @see representation.IGrammar
 * @see representation.IValue
 * @see representation.IValueAttributionn
 * @author Gael Tregouet
 *
 */
public interface ICategoryTransitionBuilder {
	
	/**
	 * @see representation.ITransition
	 * @return true if the binary relation describing the input category is a subset of the binary relation describing 
	 * the output category ; false otherwise
	 */
	boolean transitionCanBeBuilt();
	
	/**
	 * Proceeds to an injective mapping of the input category's final (i.e., non-interface) states to their counterparts 
	 * in the output category's machine. <br>
	 * 
	 * It must be verified first that a transition can actually be built from the input to the output category. 
	 * 
	 * @see representation.ITransition
	 * @param inputCatFinalStates - the input category's final states
	 * @param outputCatFinalStates - the output category's final states 
	 * @return an injective mapping of the input category's final states to their counterparts in the output category's machine
	 * @throws RepresentationException if <code> transitionCanBeBuilt() </code> returns <code> false </code>
	 */
	Map<IState, IState> pairFinalStates(Set<IState> inputCatFinalStates, Set<IState> outputCatFinalStates);
	
	/**
	 * Whenever possible, maps input category's interfaces with counterpart interfaces from the output category. <br>
	 * 
	 * This mapping between two interfaces is allowed if the second interface meets the specifications of the first one, 
	 * and if they are associated with the same local grammar. <br>
	 * 
	 * It must be verified first that a transition can actually be built from the input to the output category. <br>
	 * 
	 * @see representation.IInterfaceState
	 * @see representation.ITransition
	 * @see representation.IGrammar
	 * @param inputCatInterfaces - the input category's interfaces
	 * @param outputCatInterfaces  - the input category's interfaces
	 * @return a mapping of the input category's interfaces with interfaces from the output category
	 * @throws RepresentationException if <code> transitionCanBeBuilt() </code> returns <code> false </code>  
	 */
	Map<IInterfaceState, IInterfaceState> pairUnimplementedInterfaces(
			Set<IInterfaceState> inputCatInterfaces, Set<IInterfaceState> outputCatInterfaces);
	
	/**
	 * 
	 * @see representation.IInterfaceState
	 * @return true if unimplemented interfaces pairs have been researched (see above) ; false otherwise
	 */
	boolean freeInterfacesHaveBeenPaired();	
	
	/**
	 * Implements every free interface with a value. <br>
	 * 
	 * A free interface is an input category's interface that hasn't found any counterpart amongst the output 
	 * category's interfaces. <br> 
	 * 
	 * A value is a connected set of states composed of a "root" state (the state in the set having the highest rank) 
	 * and all free states that can be reached from this root. <br>
	 * 
	 * It must be verified first that a transition can actually be built from the input to the output category, and 
	 * also that unimplemented interfaces pairs have been researched beforehand. <br>
	 * 
	 * Free interfaces must be implemented following an increasing order of rank. <br>
	 * 
	 * @see representation.IInterfaceState
	 * @see representation.IValue
	 * @see representation.ITransition
	 * @param inputCatFreeInterfaces free interfaces from the input category
	 * @param outputCatFreeStates free states from the output category 
	 * @return a mapping of interfaces with their implementing values
	 * @throws RepresentationException if <code> transitionCanBeBuilt() </code> returns <code> false </code>, or if 
	 * pairs of unimplemented interfaces haven't been researched beforehand. <br>
	 */
	Map<IInterfaceState, IValue> implementCat1FreeInterfaces(
			Set<IInterfaceState> inputCatFreeInterfaces, Set<IState> outputCatFreeStates);
	
	/**
	 * 
	 * @return the transition between the input and the output categories, if any has been found ; <code> null </code>
	 * otherwise
	 */
	ITransition getTransition();
	
	
	
	

}
