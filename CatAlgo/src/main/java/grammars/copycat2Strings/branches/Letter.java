package grammars.copycat2Strings.branches;

import java.util.ArrayList;
import java.util.List;

import grammarModel.structure.ISyntacticStructure;
import grammarModel.structure.ISyntaxBranch;
import grammarModel.structure.ISyntaxLeaf;
import grammarModel.structure.impl.SyntaxBranch;
import grammars.copycat2Strings.leaves.LetteR;

public class Letter extends SyntaxBranch implements ISyntaxBranch {

	private static final String NAME = "Letter";
	private final LetteR letteR;
	private final CcString ccString;
	private final Direction direction;
	private final LetterValue letterValue;
	private final Position position;
	
	public Letter(LetteR letteR, CcString ccString, Direction direction, LetterValue letterValue, Position position) {
		this.letteR = letteR;
		this.ccString = ccString;
		this.direction = direction;
		this.letterValue = letterValue;
		this.position = position;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public ISyntaxLeaf getEponymLeaf() {
		return letteR;
	}	

	@Override
	public List<ISyntacticStructure> getListOfComponents() {
		List<ISyntacticStructure> components = new ArrayList<ISyntacticStructure>();
		components.add(letteR);
		components.add(ccString);
		components.add(direction);
		components.add(letterValue);
		components.add(position);
		return components;
	}

	@Override
	public ISyntacticStructure clone() {
		LetteR letteRClone = (LetteR) letteR.clone();
		CcString ccStringClone = (CcString) ccString.clone();
		Direction directionClone = (Direction) direction.clone();
		LetterValue letterValueClone = (LetterValue) letterValue.clone();
		Position positionClone = (Position) position.clone();
		return new Letter(letteRClone, ccStringClone, directionClone, letterValueClone, positionClone);
	}

}
