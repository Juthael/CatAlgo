package representation;

import java.util.Set;

public interface IRepresentationBuilder {
	
	IRepresentation getOptimalRepresentation();
	
	Set<IRepresentation> buildAllPossibleRepresentations();
	
	IAlgorithmicDescription getTargetPairAlgorithmicDescriptionAccordingTo(IRepresentation representation);

}
